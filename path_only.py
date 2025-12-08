from openai import OpenAI
import re
import subprocess
import os
import time,
import shutil
import psutil
import os
import re


OPENAI_API_KEY = ""
API_Key=""
qwen_key=""


client_gpt = OpenAI(api_key=OPENAI_API_KEY,)
client_dsk = OpenAI(api_key=API_Key, base_url="https://api.deepseek.com")
client_qwen= OpenAI(api_key=qwen_key, base_url="https://dashscope.aliyuncs.com/compatible-mode/v1")

def path_extract(code, prompt):
    
    gpt_prompt = f"""
            You are tasked with analyzing all execution paths of a given Java method using symbolic execution principles. 
            ```
            {code}
            ```
            """
    try:
        start_time = time.time()
        completion = client_gpt.chat.completions.create(
            #model="gpt-3.5-turbo",  
            #model="qwen-plus",
            #model='deepseek-reasoner',#DeepSeek-R1
            #model='qwen-plus',#qwen
            #model="gpt-4o",
            model="gpt-5",  
            messages=[
                {"role": "system", "content": gpt_prompt},
                {"role": "user", "content": prompt}
            ],
            stream=False,
            #temperature=0.7,
        )
        end_time = time.time()
        code_spec = completion.choices[0].message.content.strip()
        #code_spec= json.loads(gpt_output)
    except Exception as e:
        error_dict = {"UnknownError": str(e)}
    return code_spec
def read_prompt(file_path):
    with open(file_path, 'r') as file:
        content = file.read().strip()
    return content
def jml_gen(code, prompt,path):
    
    gpt_prompt = f"""
            You are an expert in formal methods and Java Modeling Language (JML). You are tasked with generating a JML (Java Modeling Language) specification for a specific execution path{path} in a given Java method{code}
            """
    try:
        start_time = time.time()
        completion = client_gpt.chat.completions.create(
            #model="gpt-3.5-turbo",  
            #model='deepseek-reasoner',#DeepSeek-R1
            #model='qwen-plus',#qwen
            #model="gpt-4o",
            model="gpt-5",  
            messages=[
                {"role": "system", "content": gpt_prompt},
                {"role": "user", "content": prompt}
            ],
            #temperature=0.7,
            stream=False,
        )
        end_time = time.time()
        duration = end_time - start_time
        tokens_total = completion.usage.total_tokens
        prompt_tokens = completion.usage.prompt_tokens
        completion_tokens = completion.usage.completion_tokens
        tokens_used={"total":tokens_total,"prompt":prompt_tokens,"complete":completion_tokens}
        code_spec = completion.choices[0].message.content.strip()
        #code_spec= json.loads(gpt_output)
        print(code_spec)
    except Exception as e:
        error_dict = {"UnknownError": str(e)}
    return code_spec, duration,tokens_used
def jml_modify(code, prompt,path,info):
    
    gpt_prompt = f"""
            The following Java code{code} is instrumented with JML specifications for the path{path}:
            Verifier failed to verify the specifications given above, with error information as {info}.
            Please refine the specifications, so that it can pass the verification."""
    try:
        start_time = time.time()
        completion = client_gpt.chat.completions.create(
            #model="gpt-3.5-turbo",  
            #model='deepseek-reasoner',#DeepSeek-R1
            #model='qwen-plus',#qwen
            #model="gpt-4o",
            model="gpt-5",  
            messages=[
                {"role": "system", "content": gpt_prompt},
                {"role": "user", "content": prompt}
            ],
            #temperature=0.7,
            stream=False,
        )
        end_time = time.time()
        duration = end_time - start_time
        tokens_total = completion.usage.total_tokens
        prompt_tokens = completion.usage.prompt_tokens
        completion_tokens = completion.usage.completion_tokens
        tokens_used={"total":tokens_total,"prompt":prompt_tokens,"complete":completion_tokens}
        code_spec = completion.choices[0].message.content.strip()
        #code_spec= json.loads(gpt_output)
    except Exception as e:
        error_dict = {"UnknownError": str(e)}
    return code_spec, duration,tokens_used
def read_prompt(file_path):
    with open(file_path, 'r') as file:
        content = file.read().strip()
    return content

def path_clear(input_string):
    path_pattern = re.compile(r'Path \d+:.*?(?=\n\n|Path \d+:|$)', re.DOTALL)
    paths = path_pattern.findall(input_string)

    return paths
def static_verify_2(java_file_path, timeout_seconds=200):
    command = [
        "AI4spec/OJ/openjml",
        "--esc",
        "--esc-max-warnings", "1",
        "--arithmetic-failure=quiet",
        "--nonnull-by-default",
        "--quiet",
        "-nowarn",
        "--prover=cvc4",
        java_file_path
    ]

    try:
        result = subprocess.run(command, stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True, timeout=timeout_seconds)

        if result.returncode == 0:
            print(f"{java_file_path}: Verification passed.")
            return True,""
        else:
            print(f"{java_file_path}: Verification failed with errors.")
            print("Error details:", result.stderr)
            print("Error details:", result.stdout)
            #print(result)
            return False,result.stdout
    except Exception as e:
        print(f"Failed to verify {java_file_path}: {e}")
        return False,str(e)
def static_verify(java_file_path, timeout_seconds=600):
    command = [
        "/AI4spec/OJ/openjml",
        "--esc",
        "--esc-max-warnings", "1",
        "--arithmetic-failure=quiet",
        "--nonnull-by-default",
        "--quiet",
        "-nowarn",
        "--prover=cvc4",
        java_file_path
    ]

    process = subprocess.Popen(command, stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)

    try:
        stdout, stderr = process.communicate(timeout=timeout_seconds)
        return_code = process.returncode
        
        if return_code == 0:
            print(f"{java_file_path}: Verification passed.")
            return True, ""
        else:
            print(f"{java_file_path}: Verification failed.")
            print("Error details:", stdout)
            return False, stdout
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
def extract_java_code(model_output):
    extracted_str_list = model_output.split("```")
    if len(extracted_str_list)>1:
        extracted_str = extracted_str_list[1]
    else:
        extracted_str = extracted_str_list[0]
    extracted_str = extracted_str if not extracted_str.startswith('java') else extracted_str[len('java'):]
    return extracted_str
def kill_process_by_name(names):
    for proc in psutil.process_iter(['pid', 'name']):
        if proc.info['name'] and any(name in proc.info['name'] for name in names):
            print(f"Killing process {proc.pid} ({proc.info['name']})")
            proc.terminate()
            try:
                proc.wait(timeout=3)
            except psutil.TimeoutExpired:
                proc.kill()
    
source_dir="/spec_eval_data"
done_list=['ExSymExeSwitch.java', 'IsAscending.java', 'absolute_value.java', 'loops_2.java', 'ArraysAndLoops3.java', 'add_pointers.java', 'LargestPerimeter.java', 'search.java', 'array_find.java', 'ExSymExe9.java', 'array_max_advanced.java', 'ReverseString.java', 'ExSymExe7.java', 'DigitRoot.java', 'ExSymExe20.java', 'PrimeNumbers.java', 'loops_1.java', 'add.java', 'ani.java', 'ExSymExe19.java', 'ExGenSymExe.java', 'ExDarko.java', 'wp1.java', 'PowerOfThree.java', 'ExSymExeI2F.java', 'mult.java', 'DominantIndex.java', 'ExSymExe27.java', 'incr_a_by_b.java', 'ExSymExe29.java', 'IsAllUnique.java', 'IsSubsequence.java', 'array_swap.java', 'diff.java', 'ComputeArea.java', 'search_2.java', 'ExSymExe2.java', 'triangle_sides.java', 'ArraysAndLoops5.java', 'ArraysAndLoops4.java', 'CheckABeforeB.java', 'ExSymExe16.java', 'ConvertToTitle.java', 'MyPower.java', 'ExSymExe14.java', 'PrimeCheck.java', 'ExSymExe10.java', 'triangle_angles.java', 'IsOneBitCharacter.java', 'ExSymExeSimple.java', 'IsSuffix.java', 'simple_interest.java', 'loops_3.java', 'ExSymExeGetStatic.java', 'ExSymExeArrays.java', 'max_of_2.java', 'increment_arr.java', 'ArraysAndLoops2.java', 'ContainsDuplicate.java', 'TransposeMatrix.java', 'div_rem.java', 'NextGreaterElem.java', 'reset_1st.java', 'MySqrt.java', 'reverse_array.java', 'array_double.java', 'order_3.java', 'RotateArray.java', 'ExSymExeTestAssignments.java', 'ExSymExeD2I.java', 'ExSymExeF2I.java', 'check_evens_in_array.java', 'ExSymExeBool.java', 'ExSymExeSuzette.java', 'max.java', 'ThreeConsecutiveOdds.java', 'ExSymExe17.java', 'SelectionSort.java', 'SelectionSortDesc.java', 'replace_evens.java', 'sample.java', 'add_pointers_3_vars.java', 'equal_arrays.java', 'ExSymExeLCMP.java', 'bubble_sort.java', 'ExSymExeResearch.java', 'StrPalindrome.java', 'max_pointers.java', 'FindClosestNum.java', 'ArraysAndLoops1.java']

do_l=['absolute_value.java', 'loops_2.java', 'ArraysAndLoops3.java', 'add_pointers.java', 'search.java', 'array_find.java', 'array_max_advanced.java', 'loops_1.java', 'add.java', 'ani.java', 'wp1.java', 'mult.java', 'array_swap.java', 'diff.java', 'search_2.java', 'triangle_sides.java', 'ArraysAndLoops5.java', 'ArraysAndLoops4.java', 'simple_interest.java', 'loops_3.java', 'max_of_2.java', 'increment_arr.java', 'ArraysAndLoops2.java', 'div_rem.java', 'reset_1st.java', 'reverse_array.java', 'array_double.java', 'order_3.java', 'check_evens_in_array.java', 'max.java', 'replace_evens.java', 'sample.java', 'add_pointers_3_vars.java', 'equal_arrays.java', 'bubble_sort.java']
for root, _, files in os.walk(source_dir):
    prompt_ex=read_prompt("/prompt/path_extract.txt")
    prompt_gen=read_prompt("/prompt/specgen.txt")
    prompt_modi=read_prompt("/prompt/specmodi.txt")
    prompt_m=read_prompt("/prompt/merge_code_ex.txt")
    ##
    current_directory = os.getcwd()
    #current_directory=os.path.dirname(os.path.abspath(__file__))
    path_dir = os.path.join(current_directory, 'path')
    pass_dir = os.path.join(current_directory, 'pass')
    fail_dir = os.path.join(current_directory, 'fail')
    tmp_dir = os.path.join(current_directory, 'tmp')
    log_dir = os.path.join(current_directory, 'log')
    merge_dir = os.path.join(current_directory, 'merge')
    
    
    for directory in [path_dir, fail_dir,tmp_dir,log_dir]:
        if not os.path.exists(directory):
            os.makedirs(directory)
    file_infor={}
    pass_num = 0
    path_token={}
    for file in files:
        if file.endswith('.java') and file not in do_l:
            java_file = os.path.join(root, file)
            print(java_file )
            base_name = os.path.basename(java_file)  
            file_name, file_extension = os.path.splitext(base_name)
            ##every file record path passed and failed
            file_pass_dir = os.path.join(pass_dir, file_name)
            file_fail_dir = os.path.join(fail_dir, file_name)
            for directory in [file_pass_dir, file_fail_dir]:
                if not os.path.exists(directory):
                    os.makedirs(directory)
                    
            with open(java_file, "r", encoding="utf-8") as f:
                code = f.read()
            ##path extraction
            path_file=path_dir+ "/{filename}.txt".format(filename=file_name)
            print(path_file)
            current_time_str = time.strftime("%Y_%m_%d_%H_%M_%S", time.localtime(time.time()))
            f_log = open(log_dir + "/log-{name}-{time_str}.txt".format(name=file_name, time_str=current_time_str), "w")
            if not os.path.exists(path_file):       
                paths = path_extract(code, prompt_ex)
                f_log.write(paths+"\n==============================\n")
                print(paths)
                tmp_filename = path_dir+ "/{filename}.txt".format(filename=file_name)
                tmp_file = open(tmp_filename, 'w')
                tmp_file.write(paths)
                tmp_file.close()
            else:
                with open(path_file, 'r') as f:
                    paths=f.read()
            #paths iteration
            paths=path_clear(paths)
            print(paths)
                    
            #JML extraction
            path_token={}
            path_pass_status=False
            for index, path in enumerate(paths):
                code_spec, duration,tokens_used = jml_gen(code, prompt_gen,path)
                f_log.write(str(index)+ "\n"+code_spec+ "\n"+str(duration)+"\n"+str(tokens_used)+"\n==============================\n")
                path_token[index]={0:{"duration":duration,"tokens_used":tokens_used}}
                code_spec= extract_java_code(code_spec)
                print(code_spec)
                java_file_path = tmp_dir+ "/{filename}.java".format(filename=file_name)
                tmp_file = open(java_file_path, 'w')
                tmp_file.write(code_spec)
                tmp_file.close()
                #openjml verify
                status,error_infor=static_verify(java_file_path, timeout_seconds=200)
                f_log.write(str(status)+ str(error_infor)+"\n==============================\n")
                try:
                    kill_process_by_name(['javac', 'cvc4'])
                except:
                    pass
                 
                if status:
                    path_pass_status=True
                    shutil.copy(java_file_path, os.path.join(file_pass_dir, f"{file_name}_{index}.java"))
                else:
                    shutil.copy(java_file_path, os.path.join(file_fail_dir, f"{file_name}_{index}.java"))
                    for i in range(5):
                        code_spec, duration,tokens_used = jml_modify(code, prompt_modi,path,error_infor)
                        f_log.write(str(index)+ "\n"+code_spec+ "\n"+str(duration)+"\n"+str(tokens_used)+"\n==============================\n")
                        code_spec= extract_java_code(code_spec)
                        tmp_file = open(java_file_path, 'w')
                        tmp_file.write(code_spec)
                        tmp_file.close()
                        status,error_infor=static_verify(java_file_path, timeout_seconds=200)
                        f_log.write(str(status)+ str(error_infor)+"\n==============================\n")
                        try:
                            kill_process_by_name(['javac', 'cvc4'])
                        except:
                            pass
                        path_token[index]={i+1:{"duration":duration,"tokens_used":tokens_used,}}
                        if status:
                            shutil.copy(java_file_path, os.path.join(file_pass_dir, f"{file_name}_{index}_{i}.java"))
                            path_pass_status=True
                            break
                        else:
                            shutil.copy(java_file_path, os.path.join(file_fail_dir, f"{file_name}_{index}_{i}.java"))


                        