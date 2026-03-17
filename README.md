# Overview

Formal specifications are critical for program verification, comprehension, and maintenance. However, manually writing them is costly and difficult to scale. Recent studies have shown that Large Language Models (LLMs) are promising for automated specification generation, but existing methods suffer from quality issues.

We analyze a state-of-the-art approach and find that at least **34.6%** of successfully verified specifications actually fail to meaningfully capture the program's distinct behavior, which is a quality issue not captured by metrics that only measure verification success. We further found that a major factor contributing to such hidden quality issues stems from the design of existing methods: these methods treat a program as a single unit, resulting in overly general, coarse-grained constraints.

To this end, we introduce **Path2Spec**, a divide-and-conquer framework that addresses these limitations through systematic path-based reasoning. Path2Spec leverages LLMs to extract all execution paths from an input program, generates path-specific specifications for each, and merges them into a comprehensive overall specification. For complex programs where path-based generation struggles, Path2Spec employs a decompose-then-retry strategy that recursively breaks a program into smaller subprograms based on logical branches, generates specifications for each, and merges them back.

This repository contains several experimental pipelines for automatically generating **JML (Java Modeling Language)** specifications for Java programs with the help of LLMs, and then validating them with **OpenJML**.

The codebase currently includes three main styles of workflow:

1. **Whole-program generation**
   - Generate JML for the entire Java program directly.
   - If verification fails, ask the model to refine the specification.
   - If whole-program generation still fails, fall back to decomposition.

2. **Path-only generation**
   - Extract execution paths from a Java method.
   - Generate JML for each path independently.
   - Verify each path-level candidate separately.
   - Merge passed path-level specifications later.

3. **Decomposition-only generation**
   - Split a Java class into smaller subprograms or branch-specific variants.
   - Generate JML for each subprogram.
   - Verify each subprogram independently.
   - Merge verified specifications back into a full program.

---

## 1. Requirements

### Python
Recommended: **Python 3.10+**

Install required packages:

```bash
pip install openai psutil
```

If your scripts also depend on local helper modules, make sure these files are available in the same project:

- `A_program_decompose.py`
- `A_codemerge.py`
- `Path_level_merge.py`
- `layer_level_merge.py`

### OpenJML
You need a working **OpenJML** installation.

Your scripts usually call something like:

```bash
/AI4spec/OJ/openjml
```

or

```bash
/openjml
```

Make sure the path in the script matches the actual location on your machine.

You also need a working SMT solver such as **cvc4**, since the scripts call:

```bash
--prover=cvc4
```

---

## 2. API Keys

Several scripts use OpenAI-compatible clients. Before running, fill in:

```python
OPENAI_API_KEY = ""
API_KEY = ""
QWEN_KEY = ""
```

Typical meanings:

- `OPENAI_API_KEY`: OpenAI / GPT model access
- `API_KEY`: DeepSeek API key
- `QWEN_KEY`: Qwen API key

If a script only uses one client in practice, you only need to configure the one it actually calls.

---

## 3. Prompt Files

Several pipelines depend on external prompt templates, for example:

- `/prompt/path_extract.txt`
- `/prompt/specgen.txt`
- `/prompt/specmodi.txt`
- `/prompt/decompose_gen.txt`
- `/prompt/path_gen_modi.txt`
- `/prompt/merge_code_ex.txt`

Make sure these prompt files exist and the paths are correct.

---

## 4. Oracle / Example Files

Some pipelines sample verified examples from oracle folders, for example:

- `/prompts/oracle_clean/...`
- `/prompts/oracle/...`

These are used as demonstrations for the LLM when generating JML.

If you use those pipelines, make sure the example directories are populated correctly.

---

## 5. Typical Project Layout

A practical layout looks like this:

```text
code/
├── path2spec.py
│   path_only.py
│   decompose_only.py
│   Path_level_merge.py
│   layer_level_merge.py
│   A_program_decompose.py
│   A_JML_Veri.py
│   logic_compare.py
│   score_count.py
│   make_compare_file.py
├── prompt/
│   ├── path_extract.txt
│   ├── specgen.txt
│   ├── specmodi.txt
│   ├── decompose_gen.txt
│   ├── path_gen_modi.txt
│   └── merge_code_ex.txt
├── prompts/
│   ├── oracle_clean/
│   └── oracle/
├── spec_eval_data/
│   └── *.java
└── output/
```

---

## 6. Main Pipelines

### A. Whole-program generation pipeline

This style of script does the following:

1. Read one Java file.
2. Ask the model to generate a full JML-instrumented program.
3. Run OpenJML verification.
4. If verification fails, ask the model to refine the specification several times.
5. If the whole-program attempt still fails, split the program into subprograms.
6. Generate and verify subprogram specifications.
7. Merge verified subprogram specs back into a full program.

### Input
- A directory containing `.java` files
- Prompt templates
- Optional oracle examples

### Output
Usually produces folders like:

- `pass/` — verified full-program outputs
- `fail/` — failed full-program outputs
- `pass_sub/` — verified subprogram outputs
- `fail_sub/` — failed subprogram outputs
- `tmp/` — temporary working files
- `log/` — logs for generation and verification
- `path/` or `sub/` — JSON/path intermediate results

### How to run
If the script has a `main()` with `SOURCE_DIR` or `source_dir` inside, update that variable and run:

```bash
python path2spec.py
```

If it uses CLI arguments instead, run for example:

```bash
python path2spec.py /path/to/java_inputs -o /path/to/output
```

---

### B. Path-only generation pipeline

This pipeline is the most path-oriented baseline.

It does the following:

1. Extract execution paths from a Java method.
2. Split the extracted text into `Path 1`, `Path 2`, etc.
3. For each path:
   - generate a JML version for that path,
   - verify it with OpenJML,
   - if it fails, refine it multiple times.
4. Save every passed and failed path-level candidate.

### Input
- A directory of `.java` programs, such as:

```python
SOURCE_DIR = "/spec_eval_data"
```

- Prompt files:
  - `path_extract.txt`
  - `specgen.txt`
  - `specmodi.txt`

### Output
Usually creates:

- `path/` — saved path extraction text
- `pass/<program_name>/` — verified path-level Java files
- `fail/<program_name>/` — failed path-level Java files
- `tmp/` — temporary verification file
- `log/` — detailed per-file generation logs
- `log/path_generation_summary.json` — structured run summary

### How to run

```bash
python path_only.py
```

Before running, make sure you have updated:

- `SOURCE_DIR`
- `OPENJML_BIN`
- API keys
- prompt file paths

### B. Decompose-only generation pipeline

This pipeline is the most decomposition-oriented baseline.

It does the following:

1. Decompose program into subprogram from first logical branches.
2. Split the extracted program into `subprogram 1`, `subprogram 2`, etc.
3. For subprogram:
   - generate a JML version for that subprogram,
   - verify it with OpenJML,
   - if it fails, refine it multiple times.
4. Save every passed and failed subprogram candidate.

### Input
- A directory of `.java` programs, such as:

```python
SOURCE_DIR = "/spec_eval_data"
```

- Prompt files:


### Output
Usually creates:
- `fail`
- `fail_sub`
- `log`
- `pass`
- `pass_sub`
- `path`
- `tmp`


### How to run

```bash
python decompose_only.py
```

Before running, make sure you have updated:

- `SOURCE_DIR`
- `OPENJML_BIN`
- API keys
- prompt file paths

---



## 11. Common Problems

### OpenJML path not found
Check:

```python
OPENJML_BIN = "/AI4spec/OJ/openjml"
```

and make sure the file exists.

### `cvc4` not found
Install CVC4 or change the prover setting if needed.

### API request fails
Check:
- API key
- model name
- network access
- base URL

### Prompt files not found
Make sure the `/prompt/...` and `/prompts/...` directories exist and are correctly referenced.


---

## 12. Notes on the Current Codebase

This repository is currently closer to a **research prototype** than a production package.

That means:

- some scripts are baselines for comparison,
- some scripts stop at candidate generation,
- some scripts handle merging only,
- directory paths may need to be adjusted manually,
- some modules depend on local helper files.

A good next step is to standardize these scripts into a single package structure such as:

- `path2spec.py`
- `path_only.py`
- `decompose_only.py`
- `layer_level_merge.py`
- `Path_level_merge.py`
- `A_JML_Veri.py`
- `A_program_decompose.py`
- `score_count.py`
- `logic_compare.py`
- `make_compare_file.py`

---

## 13. Summary

In short:

- **path2spec.py**: tries to generate one full verified specification directly.
- **path_only.py**: generates and verifies one specification candidate per execution path.
- **decompose_only.py**: splits code into smaller subprograms and verifies them independently.
- **layer_level_merge**: combines verified candidates into a final result from layer level.
- **Path_level_merge**: combines verified candidates into a final result from path level.
- **A_JML_Veri**: use openjml to static method to verify the spec whether allign with the code.
- **A_program_decompose.py**: decompose the program into subprograms base on the first logical branches
- **logic_compare.py**: logic compare the preconditions-postcondition pair from path2spec and specs generated by specgen.
- **make_compare_file.py**: randemly make compare file for human anotation.

The main result of running these scripts is a collection of:
- verified Java+JML candidates,
- failed candidates for analysis,
- logs,
- and possibly final merged Java files.

