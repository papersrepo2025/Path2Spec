from __future__ import annotations

import argparse
import json
import os
import pathlib
import random
import re
import shutil
import subprocess
import sys
import time
from pathlib import Path
from typing import Any, Dict, List, Optional, Sequence, Tuple

import psutil
from openai import OpenAI

from A_program_decompose import split_java_class
from Path_level_merge import merge
from layer_level_merge import layer_merge


# =========================
# Configuration
# =========================

OPENAI_API_KEY = ""
API_KEY = ""
QWEN_KEY = ""

OPENJML_BIN = "OJ/openjml"
OPENJML_TIMEOUT = 600
OPENJML_RETRY_TIMEOUT = 200

PROMPT_DIR = "/prompt"
PROMPTS_DIR = "/prompt"
ORACLE_CLEAN_DIR = "/prompt/oracle_clean"
ORACLE_DIR = "/prompt/oracle"

PROMPT_PASS_FILES = [
    "LeapYear.java",
    "FIND_FIRST_IN_SORTED.java",
    "Perimeter.java",
    "Absolute.java",
    "BinarySearch.java",
    "StrPalindrome.java",
    "AddLoop.java",
    "Inverse.java",
    "CopyArray.java",
    "Neg.java",
    "Time.java",
    "LinearSearch.java",
    "FindFirstZero.java",
    "Calculator.java",
    "FIND_IN_SORTED.java",
    "SetZero.java",
    "BankAccount.java",
    "FindInArray.java",
    "StudentEnrollment.java",
    "OddEven.java",
    "Smallest.java",
]

MODEL_QWEN = "qwen-plus"
MODEL_GPT = "gpt-5"
MODEL_DSK = "deepseek-reasoner"


client_gpt = OpenAI(api_key=OPENAI_API_KEY)
client_dsk = OpenAI(api_key=API_KEY, base_url="https://api.deepseek.com")
client_qwen = OpenAI(
    api_key=QWEN_KEY,
    base_url="https://dashscope.aliyuncs.com/compatible-mode/v1",
)


# =========================
# Utility Functions
# =========================

def read_prompt(file_path: str) -> str:
    with open(file_path, "r", encoding="utf-8") as file:
        return file.read().strip()


def ensure_dir(path: str | Path) -> None:
    Path(path).mkdir(parents=True, exist_ok=True)


def write_text(file_path: str | Path, content: str) -> None:
    Path(file_path).parent.mkdir(parents=True, exist_ok=True)
    with open(file_path, "w", encoding="utf-8") as f:
        f.write(content)


def read_text(file_path: str | Path) -> str:
    with open(file_path, "r", encoding="utf-8") as f:
        return f.read()


def extract_java_code(model_output: str) -> str:
    extracted_parts = model_output.split("```")
    extracted = extracted_parts[1] if len(extracted_parts) > 1 else extracted_parts[0]
    if extracted.startswith("java"):
        extracted = extracted[len("java"):]
    return extracted.strip()


def path_clear(input_string: str) -> List[str]:
    path_pattern = re.compile(r"Path \d+:.*?(?=\n\n|Path \d+:|$)", re.DOTALL)
    return path_pattern.findall(input_string)


def kill_process_by_name(names: Sequence[str]) -> None:
    for proc in psutil.process_iter(["pid", "name"]):
        proc_name = proc.info.get("name")
        if proc_name and any(name in proc_name for name in names):
            print(f"Killing process {proc.pid} ({proc_name})")
            proc.terminate()
            try:
                proc.wait(timeout=3)
            except psutil.TimeoutExpired:
                proc.kill()


def cleanup_verifier_processes() -> None:
    try:
        kill_process_by_name(["javac", "cvc4"])
    except Exception:
        pass


# =========================
# LLM Calls
# =========================

def _chat_completion(
    client: OpenAI,
    model: str,
    messages: List[Dict[str, str]],
    temperature: Optional[float] = None,
) -> Tuple[str, float, Dict[str, int]]:
    start_time = time.time()

    kwargs: Dict[str, Any] = {
        "model": model,
        "messages": messages,
        "stream": False,
    }
    if temperature is not None:
        kwargs["temperature"] = temperature

    completion = client.chat.completions.create(**kwargs)

    end_time = time.time()
    duration = end_time - start_time

    usage = getattr(completion, "usage", None)
    tokens_used = {
        "total": getattr(usage, "total_tokens", 0),
        "prompt": getattr(usage, "prompt_tokens", 0),
        "complete": getattr(usage, "completion_tokens", 0),
    }

    content = completion.choices[0].message.content.strip()
    return content, duration, tokens_used


def path_extract(code: str, prompt: str) -> str:
    messages = [
        {
            "role": "system",
            "content": (
                "You are tasked with analyzing all execution paths of a "
                "given Java method using symbolic execution principles."
            ),
        },
        {
            "role": "user",
            "content": f"{prompt} and the code is:{code}",
        },
    ]

    try:
        content, _, _ = _chat_completion(
            client=client_qwen,
            model=MODEL_QWEN,
            messages=messages,
            temperature=0.7,
        )
        return content
    except Exception as e:
        raise RuntimeError(f"path_extract failed: {e}") from e


def jml_gen(code: str, prompt: str, path: str, example: List[str]) -> Tuple[str, float, Dict[str, int]]:
    messages = [
        {
            "role": "system",
            "content": (
                "You are an expert in formal methods and Java Modeling Language (JML). "
                "You are tasked with generating a JML (Java Modeling Language) "
                f"specification for a specific execution path. Correct example is:{example}"
            ),
        },
        {
            "role": "user",
            "content": (
                f"{prompt}, and the code is:{code},"
                f"Please generate JML specification for the path and the component "
                f"compromise the path, Path is: {path}"
            ),
        },
    ]

    try:
        content, duration, tokens_used = _chat_completion(
            client=client_qwen,
            model=MODEL_QWEN,
            messages=messages,
            temperature=0.7,
        )
        print(content)
        return content, duration, tokens_used
    except Exception as e:
        raise RuntimeError(f"jml_gen failed: {e}") from e


def jml_modify(code: str, prompt: str, path: str, info: str) -> Tuple[str, float, Dict[str, int]]:
    messages = [
        {
            "role": "system",
            "content": "You are an expert in formal methods and Java Modeling Language (JML).",
        },
        {
            "role": "user",
            "content": (
                f"{prompt},The following Java code{code} is instrumented with "
                f"JML specifications for the path{path}: Verifier failed to verify "
                f"the specifications given above, with error information as {info}. "
                f"Please refine the specifications, so that it can pass the verification."
            ),
        },
    ]

    try:
        return _chat_completion(
            client=client_qwen,
            model=MODEL_QWEN,
            messages=messages,
            temperature=None,
        )
    except Exception as e:
        raise RuntimeError(f"jml_modify failed: {e}") from e


def sub_gen(code: str, prompt: str, condition: str, examples: List[str]) -> Tuple[str, float, Dict[str, int]]:
    system_prompt = (
        "You are a formal methods and JML expert. Generate JML specifications "
        "for the given Java program, using the provided path and condition "
        "information of the subprogram to guide specification writing for "
        f"different subprogram cases. The correct examples is:{examples}"
    )

    messages = [
        {"role": "system", "content": system_prompt},
        {
            "role": "user",
            "content": f"{prompt} the code is:{code}, the condition of the subprogram is{condition}",
        },
    ]

    try:
        content, duration, tokens_used = _chat_completion(
            client=client_gpt,
            model=MODEL_GPT,
            messages=messages,
            temperature=None,
        )
        print(content)
        return content, duration, tokens_used
    except Exception as e:
        raise RuntimeError(f"sub_gen failed: {e}") from e


def sub_modify(code: str, prompt: str, condition: str, info: str, examples: List[str]) -> Tuple[str, float, Dict[str, int]]:
    system_prompt = (
        "The following Java code is instrumented with JML specifications for the path : "
        "Verifier failed to verify the specifications given above, with error informatio "
        f"Please refine the specifications, so that it can pass the verification. "
        f"The correct examples is:{examples}"
    )

    messages = [
        {"role": "system", "content": system_prompt},
        {
            "role": "user",
            "content": (
                f"{prompt},The following subprogram is instrumented with JML specifications:{code}, "
                f"the condition for the subprogram is:{condition}. Verifier failed to verify "
                f"the specifications given above and the feedback is {info}, Please refine "
                f"the specifications, so that it can pass the verification, "
                f"You SHOULD NOT modify any content other than the specifications inserted into the code, "
                f"You SHOULD output the code in its entirety, withou omitting any original content"
            ),
        },
    ]

    try:
        return _chat_completion(
            client=client_gpt,
            model=MODEL_GPT,
            messages=messages,
            temperature=None,
        )
    except Exception as e:
        raise RuntimeError(f"sub_modify failed: {e}") from e


# =========================
# Verification
# =========================

def static_verify(java_file_path: str, timeout_seconds: int = OPENJML_TIMEOUT) -> Tuple[bool, str]:
    command = [
        OPENJML_BIN,
        "--esc",
        "--esc-max-warnings", "1",
        "--arithmetic-failure=quiet",
        "--nonnull-by-default",
        "--quiet",
        "-nowarn",
        "--prover=cvc4",
        java_file_path,
    ]

    process = subprocess.Popen(
        command,
        stdout=subprocess.PIPE,
        stderr=subprocess.PIPE,
        text=True,
    )

    try:
        stdout, stderr = process.communicate(timeout=timeout_seconds)
        return_code = process.returncode

        if return_code == 0:
            print(f"{java_file_path}: Verification passed.")
            return True, ""
        else:
            print(f"{java_file_path}: Verification failed.")
            print("Error details:", stdout)
            return False, stdout or stderr
    except subprocess.TimeoutExpired:
        print("Timeout expired, terminating process...")
        process.terminate()
        try:
            stdout, stderr = process.communicate(timeout=10)
        except subprocess.TimeoutExpired:
            print("Process did not terminate, killing it...")
            process.kill()
            stdout, stderr = process.communicate()
        return False, "Timeout expired, process killed."


# =========================
# Core Generation Pipeline
# =========================

def path_level_gen(
    code: str,
    examples: List[str],
    prompt_ex: str,
    prompt_gen: str,
    prompt_modi: str,
    java_file_path: str,
    path_pass_file_dir: str,
    path_fail_file_dir: str,
    path_file: str,
    pass_dir: str,
) -> Tuple[bool, Optional[str]]:
    pass_status = False
    spec = None

    if not os.path.exists(path_file):
        paths_raw = path_extract(code, prompt_ex)
        write_text(path_file, paths_raw)
    else:
        paths_raw = read_text(path_file)

    paths = path_clear(paths_raw)

    base_name = os.path.basename(java_file_path)
    file_name, _ = os.path.splitext(base_name)

    for index, path in enumerate(paths):
        code_spec, duration, tokens_used = jml_gen(code, prompt_gen, path, examples)
        code_spec = extract_java_code(code_spec)
        print(code_spec)

        write_text(java_file_path, code_spec)
        status, error_info = static_verify(java_file_path, timeout_seconds=OPENJML_TIMEOUT)
        cleanup_verifier_processes()

        if status:
            shutil.copy(java_file_path, os.path.join(path_pass_file_dir, f"{file_name}_{index}.java"))
        else:
            shutil.copy(java_file_path, os.path.join(path_fail_file_dir, f"{file_name}_{index}.java"))

            for retry_idx in range(5):
                code_spec, duration, tokens_used = jml_modify(code, prompt_modi, path, error_info)
                code_spec = extract_java_code(code_spec)

                write_text(java_file_path, code_spec)
                status, error_info = static_verify(java_file_path, timeout_seconds=OPENJML_TIMEOUT)
                cleanup_verifier_processes()

                if status:
                    shutil.copy(
                        java_file_path,
                        os.path.join(path_pass_file_dir, f"{file_name}_{index}_{retry_idx}.java"),
                    )
                    break
                else:
                    shutil.copy(
                        java_file_path,
                        os.path.join(path_fail_file_dir, f"{file_name}_{index}_{retry_idx}.java"),
                    )

    if os.listdir(path_pass_file_dir):
        input_dir = Path(path_pass_file_dir)
        tmp = Path(java_file_path)
        spec = merge(input_dir, pass_dir, tmp)
        pass_status = True

    return pass_status, spec


def decompose(
    code: str,
    prompt_ex: str,
    prompt_wg: str,
    prompt_m: str,
    java_file_path: str,
    path_pass_file_dir: str,
    path_fail_file_dir: str,
    sub_pass_dir: str,
    path_file: str,
    examples: List[str],
    b: int = 1,
) -> Tuple[bool, Dict[str, Dict[str, Any]]]:
    results = split_java_class(code)
    fail_sub_code: List[str] = []
    passed_spec: List[str] = []
    whole_break_info: Dict[str, Dict[str, Any]] = {}

    print(results)
    branch_status = False

    if len(results) <= 1:
        return False, {}

    branch_status = True

    for i, r in enumerate(results):
        dec_json: Dict[str, Any] = {}

        fir_condition = r["condition"]
        sub_code = r["code"]

        print(f"this is {i} subprogram:{sub_code}")

        dec_json["code"] = sub_code
        dec_json["condition"] = fir_condition


        path_pass_status, spec = path_level_gen(
            code=code,
            examples=examples,
            prompt_ex=prompt_ex,
            prompt_gen=prompt_wg,
            prompt_modi=prompt_m,
            java_file_path=java_file_path,
            path_pass_file_dir=path_pass_file_dir,
            path_fail_file_dir=path_fail_file_dir,
            path_file=path_file,
            pass_dir=sub_pass_dir,
        )

        if path_pass_status:
            passed_spec.append(spec)
            dec_json["status"] = path_pass_status
            dec_json["spec"] = spec
        else:
            fail_sub_code.append(sub_code)
            dec_json["status"] = path_pass_status

        whole_break_info[f"{b}_{i}"] = dec_json

    return branch_status, whole_break_info


def merge_code(
    code: str,
    true_specs: List[str],
    java_file_path: str,
    pass_save_path: str,
    fail_save_path: str,
) -> Tuple[bool, str]:
    merged_code = layer_merge(code, true_specs)
    merged_code = fr"{merged_code}"

    write_text(java_file_path, merged_code)

    first_merge_status, error_info = static_verify(
        java_file_path,
        timeout_seconds=OPENJML_RETRY_TIMEOUT,
    )
    cleanup_verifier_processes()

    if first_merge_status:
        shutil.copy(java_file_path, pass_save_path)
    else:
        shutil.copy(java_file_path, fail_save_path)

    return first_merge_status, merged_code


# =========================
# Dataset / Batch Pipeline
# =========================

def _load_random_examples() -> List[str]:
    examples: List[str] = []

    random_files = random.sample(PROMPT_PASS_FILES, 1)
    for filename in random_files:
        file_name, _ = os.path.splitext(filename)

        oracle_clean_path = os.path.join(ORACLE_CLEAN_DIR, file_name, filename)
        oracle_path = os.path.join(ORACLE_DIR, file_name, filename)


        with open(oracle_clean_path, "r", encoding="utf-8") as f:
            _ = f.read()

        with open(oracle_path, "r", encoding="utf-8") as f:
            prm_spec = f.read()

        examples.append(prm_spec)

    return examples


def _prepare_output_dirs(out_dir: Path) -> Dict[str, str]:
    current_directory = str(out_dir)

    dirs = {
        "path_dir": os.path.join(current_directory, "path"),
        "path_pass_dir": os.path.join(current_directory, "path_pass"),
        "path_fail_dir": os.path.join(current_directory, "path_fail"),
        "pass_dir": os.path.join(current_directory, "pass"),
        "fail_dir": os.path.join(current_directory, "fail"),
        "sub_pass_dir": os.path.join(current_directory, "sub_pass"),
        "tmp_dir": os.path.join(current_directory, "tmp"),
        "log_dir": os.path.join(current_directory, "log"),
    }

    for d in dirs.values():
        ensure_dir(d)

    return dirs


def path2spec(source_dir: str | Path, out_dir: str | Path) -> None:
    prompt_ex = read_prompt(os.path.join(PROMPT_DIR, "path_extract.txt"))
    prompt_gen = read_prompt(os.path.join(PROMPT_DIR, "specgen.txt"))
    prompt_modi = read_prompt(os.path.join(PROMPT_DIR, "specmodi.txt"))
    prompt_wg = read_prompt(os.path.join(PROMPT_DIR, "decompose_gen.txt"))
    prompt_m = read_prompt(os.path.join(PROMPT_DIR, "path_gen_modi.txt"))

    source_dir = str(source_dir)
    out_dir = Path(out_dir).resolve()

    dirs = _prepare_output_dirs(out_dir)

    file_info: Dict[str, Any] = {}

    for root, _, files in os.walk(source_dir):
        for file in files:
            if not file.endswith(".java"):
                continue

            code_info: Dict[str, Any] = {}
            examples = _load_random_examples()

            java_file = os.path.join(root, file)
            file_name, _ = os.path.splitext(os.path.basename(java_file))

            pass_file = os.path.join(dirs["pass_dir"], file)
            fail_file = os.path.join(dirs["fail_dir"], file)
            path_file = os.path.join(dirs["path_dir"], f"{file_name}.txt")
            sub_file = os.path.join(dirs["tmp_dir"], f"{file_name}.json")
            java_file_path = os.path.join(dirs["tmp_dir"], f"{file_name}.java")

            path_pass_file_dir = os.path.join(dirs["path_pass_dir"], file_name)
            path_fail_file_dir = os.path.join(dirs["path_fail_dir"], file_name)
            sub_path_file_dir = os.path.join(dirs["path_fail_dir"], file_name)

            for directory in [path_pass_file_dir, path_fail_file_dir, sub_path_file_dir]:
                ensure_dir(directory)

            with open(java_file, "r", encoding="utf-8") as f:
                code = f.read()

            code = fr"{code}"

            current_time_str = time.strftime("%Y_%m_%d_%H_%M_%S", time.localtime(time.time()))
            log_path = os.path.join(dirs["log_dir"], f"log-{file_name}-{current_time_str}.txt")

            print("==============================path-based gen==============================")

            code_info["original"] = code
            condition: List[str] = []

            with open(log_path, "w", encoding="utf-8") as f_log:
                # first_gen
                path_pass_status = False
                path_pass_status, spec = path_level_gen(
                    code=code,
                    examples=examples,
                    prompt_ex=prompt_ex,
                    prompt_gen=prompt_gen,
                    prompt_modi=prompt_modi,
                    java_file_path=java_file_path,
                    path_pass_file_dir=path_pass_file_dir,
                    path_fail_file_dir=path_fail_file_dir,
                    path_file=path_file,
                    pass_dir=dirs["pass_dir"],
                )

                java_file_path = os.path.join(dirs["tmp_dir"], f"{file_name}.java")
                print("==============================Decomposy-they-retry==============================")

                if not path_pass_status:
                    branch_status, branch_info = decompose(
                        code=code,
                        prompt_ex=prompt_ex,
                        prompt_wg=prompt_wg,
                        prompt_m=prompt_m,
                        java_file_path=java_file_path,
                        path_pass_file_dir=path_pass_file_dir,
                        path_fail_file_dir=path_fail_file_dir,
                        sub_pass_dir=dirs["sub_pass_dir"],
                        path_file=path_file,
                        examples=examples,
                        b=1,
                    )

                    f_log.write("this is branch1: \n")
                    f_log.write(str(branch_status) + "\n" + str(branch_info) + "\n==============================\n")

                    if branch_status:
                        true_specs = [v["spec"] for v in branch_info.values() if v["status"]]
                        print(true_specs)

                        code_info["first_branch"] = branch_info
                        f_log.write(str(true_specs) + "\n==============================\n")

                        pass_save_path = os.path.join(dirs["pass_dir"], f"{file_name}.java")
                        fail_save_path = os.path.join(dirs["fail_dir"], f"{file_name}.java")

                        first_merge_status, merged_code = merge_code(
                            code, true_specs, java_file_path, pass_save_path, fail_save_path
                        )
                        print(merged_code)

                        code_info["first_merge_status"] = first_merge_status
                        f_log.write(str(first_merge_status) + "\n" + merged_code + "\n==============================\n")
                        print(first_merge_status)

                        false_dicts = {k: v for k, v in branch_info.items() if v["status"] is False}

                        merge_status, merged_code = merge_code(
                            code, true_specs, java_file_path, pass_save_path, fail_save_path
                        )
                        print(merged_code)
                        print(merge_status)

                        f_log.write("this is branch1: \n")
                        f_log.write(str(branch_status) + "\n" + str(branch_info) + "\n==============================\n")

                        if false_dicts:
                            for v in false_dicts.values():
                                sub_code = v["code"]
                                fir_condition = v["condition"]
                                condition.append(fir_condition)

                                branch_status, branch_info = decompose(
                                    code=code,
                                    prompt_ex=prompt_ex,
                                    prompt_wg=prompt_wg,
                                    prompt_m=prompt_m,
                                    java_file_path=java_file_path,
                                    path_pass_file_dir=path_pass_file_dir,
                                    path_fail_file_dir=path_fail_file_dir,
                                    sub_pass_dir=dirs["sub_pass_dir"],
                                    path_file=path_file,
                                    examples=examples,
                                    b=2,
                                )

                                if branch_status:
                                    v["second_branch"] = branch_info

                                    second_true_specs = [
                                        item["spec"] for item in branch_info.values() if item["status"]
                                    ]
                                    print(second_true_specs)
                                    f_log.write(str(second_true_specs) + "\n==============================\n")

                                    true_specs += second_true_specs
                                    false_dicts = {
                                        k: item for k, item in branch_info.items() if item["status"] is False
                                    }

                                    merge_status, merged_code = merge_code(
                                        code, true_specs, java_file_path, pass_save_path, fail_save_path
                                    )
                                    print(merged_code)
                                    print(merge_status)

                                    if false_dicts:
                                        for v in false_dicts.values():
                                            sub_code = v["code"]
                                            sec_condition = v["condition"]
                                            condition.append(sec_condition)

                                            branch_status, branch_info = decompose(
                                                code=code,
                                                prompt_ex=prompt_ex,
                                                prompt_wg=prompt_wg,
                                                prompt_m=prompt_m,
                                                java_file_path=java_file_path,
                                                path_pass_file_dir=path_pass_file_dir,
                                                path_fail_file_dir=path_fail_file_dir,
                                                sub_pass_dir=dirs["sub_pass_dir"],
                                                path_file=path_file,
                                                examples=examples,
                                                b=3,
                                            )

                                            if branch_status:
                                                v["Third"] = branch_info

                                                third_true_specs = [
                                                    item["spec"] for item in branch_info.values() if item["status"]
                                                ]
                                                print(third_true_specs)
                                                f_log.write(str(third_true_specs) + "\n==============================\n")

                                                true_specs += third_true_specs
                                                false_dicts = {
                                                    k: item for k, item in branch_info.items()
                                                    if item["status"] is False
                                                }

                        last_merge_status, second_code = merge_code(
                            code, true_specs, java_file_path, pass_save_path, fail_save_path
                        )
                        print(second_code)
                        print(last_merge_status)

                        f_log.write(str(last_merge_status) + "\n" + second_code + "\n==============================\n")
                        code_info["last_branch"] = false_dicts
                        code_info["last_merge"] = last_merge_status

            file_info[file] = code_info

            with open(sub_file, "w", encoding="utf-8") as f:
                json.dump(file_info, f, ensure_ascii=False, indent=4)


# =========================
# CLI
# =========================

def main() -> None:
    parser = argparse.ArgumentParser(description="get source directory")
    parser.add_argument("src_dir", help="source directory")
    parser.add_argument(
        "-o",
        "--out",
        default="output",
        help="output folder (default: ./result)",
    )
    args = parser.parse_args()

    src_dir = pathlib.Path(args.src_dir).expanduser().resolve()
    if not src_dir.is_dir():
        sys.exit(f"No such directory: {src_dir}")

    out_dir = pathlib.Path(args.out).resolve()
    path2spec(src_dir, out_dir)


if __name__ == "__main__":
    main()
