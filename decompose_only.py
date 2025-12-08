from openai import OpenAI
import json
import re
import subprocess
import os
import time, csv
import shutil
import psutil
import random

from A_program_decompose import split_java_class
from A_codemerge import merge

OPENAI_API_KEY = ""
API_Key=""
qwen_key=""


client_gpt = OpenAI(api_key=OPENAI_API_KEY,)
client_dsk = OpenAI(api_key=API_Key, base_url="https://api.deepseek.com")
client_qwen= OpenAI(api_key=qwen_key, base_url="https://dashscope.aliyuncs.com/compatible-mode/v1")

def path_extract(code, prompt,condition):
    
    gpt_prompt = f"""
            You are tasked with analyzing all execution paths of a given Java method using symbolic execution principles. The goal is to identify all possible paths through the code, track the state of variables at each step, and represent the final state using only the initial symbolic variables (e.g., x, y)
            """
    try:
        start_time = time.time()
        completion = client_gpt.chat.completions.create(
            #model="gpt-3.5-turbo",  
            #model="qwen-plus",
            #model='deepseek-reasoner',#DeepSeek-R1
            #model="gpt-4o",
            model="gpt-5",  
            messages=[
                {"role": "system", "content": gpt_prompt},
                {"role": "user", "content": f"{prompt}, and the java program is:{code},condition is: {condition}.If condition is 'none', this represents a complete standalone program, If condition has a specific value, this represents a subprogram/function snippet, and the condition describes the input requirements and execution constraints for this segment"}
            ],
            stream=False,
        )
        end_time = time.time()
        code_spec = completion.choices[0].message.content.strip()
        #code_spec= json.loads(gpt_output)
    except Exception as e:
        error_dict = {"UnknownError": str(e)}
    return code_spec

def whole_gen(code, prompt,examples):
    
    gpt_prompt = f"""
            You are a formal methods and JML expert. Generate JML specifications for the given Java program, the examples is {examples}.
            """
    try:
        start_time = time.time()
        completion = client_gpt.chat.completions.create(
            #model="gpt-3.5-turbo",  
            #model='deepseek-reasoner',#DeepSeek-R1
            #model="qwen-plus",
            #model="gpt-4o",
            model="gpt-5",  
            messages=[
                {"role": "system", "content": gpt_prompt},
                {"role": "user", "content": f"{prompt} the code is:{code} "}
            ],
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
def sub_gen(code, prompt,condition,examples):
    
    gpt_prompt = f"""
            You are a formal methods and JML expert. Generate JML specifications for the given Java program, using the provided path and condition information of the subprogram to guide specification writing for different subprogram cases. The correct examples is:{examples}
            """
    try:
        start_time = time.time()
        completion = client_gpt.chat.completions.create(
            #model="gpt-3.5-turbo",  
            #model='deepseek-reasoner',#DeepSeek-R1
            #model="qwen-plus",
            #model="gpt-4o",
            model="gpt-5",  
            messages=[
                {"role": "system", "content": gpt_prompt},
                {"role": "user", "content": f"{prompt} the code is:{code}, the condition of the subprogram is{condition}"}
            ],
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

def whole_modify(code, prompt,info,examples):
    
    gpt_prompt = f"""
            The following Java code is instrumented with JML specifications, verifier failed to verify the specifications given above, with error informatio, Please refine the specifications, so that it can pass the verification. The correct examples is {examples}"""
    try:
        start_time = time.time()
        completion = client_gpt.chat.completions.create(
            #model="gpt-3.5-turbo",  
            #model='deepseek-reasoner',#DeepSeek-R1
            #model="qwen-plus",
            #model="gpt-4o",
            model="gpt-5",  
            messages=[
                {"role": "system", "content": gpt_prompt},
                {"role": "user", "content": f" {prompt},The following Java code is instrumented with JML specifications:{code}, Verifier failed to verify the specifications given above, Please refine the specifications, so that it can pass the verification,You SHOULD NOT modify any content other than the specifications inserted into the code, You SHOULD output the code in its entirety, withou omitting any original content"}
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

def sub_modify(code, prompt,condition,info,examples):
    
    gpt_prompt = f"""
            The following Java code is instrumented with JML specifications for the path :
            Verifier failed to verify the specifications given above, with error informatio
            Please refine the specifications, so that it can pass the verification. The correct examples is:{examples}"""
    try:
        start_time = time.time()
        completion = client_gpt.chat.completions.create(
            #model="gpt-3.5-turbo",  
            #model='deepseek-reasoner',#DeepSeek-R1
            #model="qwen-plus",
            #model="gpt-4o",
            model="gpt-5",  
            messages=[
                {"role": "system", "content": gpt_prompt},
                {"role": "user", "content": f"{prompt},The following subprogram is instrumented with JML specifications:{code}, the condition for the subprogram is:{condition}. Verifier failed to verify the specifications given above and the feedback is {info}, Please refine the specifications, so that it can pass the verification, You SHOULD NOT modify any content other than the specifications inserted into the code, You SHOULD output the code in its entirety, withou omitting any original content"}
            ],
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
    
def static_verify(java_file_path, timeout_seconds=600):
    command = [
        "",
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
        '''print("Timeout expired, terminating process...")
        process.terminate()   
        try:
            stdout, stderr = process.communicate(timeout=10)  
        except subprocess.TimeoutExpired:
            print("Process did not terminate, killing it...")
            process.kill()
            stdout, stderr = process.communicate()'''
        return False, "Timeout expired, process killed."
def kill_process_by_name(names):
    for proc in psutil.process_iter(['pid', 'name']):
        if proc.info['name'] and any(name in proc.info['name'] for name in names):
            print(f"Killing process {proc.pid} ({proc.info['name']})")
            proc.terminate()
            try:
                proc.wait(timeout=3)
            except psutil.TimeoutExpired:
                proc.kill()
def extract_java_code(model_output):
    extracted_str_list = model_output.split("```")
    if len(extracted_str_list)>1:
        extracted_str = extracted_str_list[1]
    else:
        extracted_str = extracted_str_list[0]
    extracted_str = extracted_str if not extracted_str.startswith('java') else extracted_str[len('java'):]
    return extracted_str

def decompose(code,prompt_wg,prompt_m,java_file_path,pass_sub,fail_sub,log_path,examples,b=1):
    f_log = open(log_path, "w")
    results = split_java_class(code)
    whole_break_infor = {}
    duration_list=[]
    token_list=[]
    print(results)
    f_log.write(str(results)+"\n==============================\n")
    if len(results)<=1:
        return False,{}
    else:
        for i, r in enumerate(results):
            dec_json = {}
            pass_status=False
            
            fir_condition = r['condition']
            sub_code = r['code']
            print(f"this is {i} subprogram:{sub_code}")
            dec_json["code"] = sub_code
            dec_json["condition"] = fir_condition
            '''
            paths = path_extract(sub_code, prompt_ex,fir_condition)
            print(paths)
            dec_json["path"] = paths
            '''
            #f_log.write(str(fir_condition)+ "\n"+str(sub_code)+"\n"+str(paths)+"\n==============================\n")

            sub_spec, duration,tokens_used = sub_gen(sub_code, prompt_wg, fir_condition,examples)
            print(f"this is {i} subspec: {sub_spec}")
            
            #f_log.write(str(sub_spec)+ "\n"+str(duration)+"\n"+str(tokens_used)+"\n==============================\n")
            duration_list.append(duration)
            token_list.append(tokens_used)
            sub_spec= extract_java_code(sub_spec)
            sub_spec = fr"{sub_spec}"
            dec_json["spec"]=sub_spec

            tmp_file = open(java_file_path, 'w')
            tmp_file.write(sub_spec)
            tmp_file.close()
            sub_status,error_infor=static_verify(java_file_path, timeout_seconds=200)
            print(f"this is {i} verification status: {sub_status}")
            f_log.write(str(sub_status)+ "\n==============================\n")
            try:
                kill_process_by_name(['javac', 'cvc4'])
            except:
                pass
            if  sub_status:
                pass_status=True
                shutil.copy(java_file_path, os.path.join(pass_sub, f"{file_name}_{b}{i}.java"))
                dec_json["status"]=pass_status
                dec_json["duration"]=duration_list
                dec_json["token"]=token_list
            else:
                shutil.copy(java_file_path, os.path.join(fail_sub, f"{file_name}_{b}{i}.java"))
                dec_json["status"]=pass_status
                dec_json["duration"]=duration_list
                dec_json["token"]=token_list
                for m in range(5):
                    sub_spec, duration,tokens_used = sub_modify(sub_spec,prompt_m,fir_condition,error_infor,examples)
                    duration_list.append(duration)
                    token_list.append(tokens_used)

                    sub_spec = extract_java_code(sub_spec)
                    sub_spec = fr"{sub_spec}"
                    dec_json["spec"]=sub_spec

                    tmp_file = open(java_file_path, 'w')
                    tmp_file.write(sub_spec)
                    tmp_file.close()
                    sub_status,error_infor=static_verify(java_file_path, timeout_seconds=200)
                    f_log.write(str(sub_spec)+ "\n"+str(duration)+"\n"+str(tokens_used)+"\n"+str(sub_status)+"\n==============================\n")
                    try:
                        kill_process_by_name(['javac', 'cvc4'])
                    except:
                        pass
                    if sub_status:
                        pass_status=True
                        shutil.copy(java_file_path, os.path.join(pass_sub, f"{file_name}_{b}{i}.java"))
                        dec_json["status"]=pass_status
                        dec_json["duration"]=duration_list
                        dec_json["token"]=token_list
                        break
                    else:
                        shutil.copy(java_file_path, os.path.join(fail_sub, f"{file_name}_{b}{i}.java"))
                        dec_json["status"]=pass_status
                        dec_json["duration"]=duration_list
                        dec_json["token"]=token_list
            whole_break_infor[i]=dec_json
    print(whole_break_infor)             
    return True,whole_break_infor

def decompose_2(code,prompt_wg,prompt_m,java_file_path,pass_sub,fail_sub,log_path,examples,condition,b=3):
    f_log = open(log_path, "w")
    results = split_java_class(code)
    whole_break_infor = {}
    duration_list=[]
    token_list=[]
    print(results)
    f_log.write(str(results)+"\n==============================\n")
    if len(results)<=1:
        return False,{}
    else:
        for i, r in enumerate(results):
            dec_json = {}
            pass_status=False
            
            fir_condition = condition
            sub_code = r['code']
            dec_json["condition"] = fir_condition
            print(sub_code)
            print(f"this is {b}{i} subprogram:{sub_code}")
            dec_json["code"] = sub_code
            '''
            paths = path_extract(sub_code, prompt_ex,condition)
            print(paths)
            dec_json["path"] = paths
            f_log.write(str(fir_condition)+ "\n"+str(sub_code)+"\n"+str(paths)+"\n==============================\n")
            '''
            sub_spec, duration,tokens_used = sub_gen(sub_code, prompt_wg, condition,examples)
            print(f"this is {b}{i} spec:{sub_spec}")
            f_log.write(str(sub_spec)+ "\n"+str(duration)+"\n"+str(tokens_used)+"\n==============================\n")
            duration_list.append(duration)
            token_list.append(tokens_used)
            sub_spec= extract_java_code(sub_spec)
            sub_spec = fr"{sub_spec}"
            dec_json["spec"]=sub_spec

            tmp_file = open(java_file_path, 'w')
            tmp_file.write(sub_spec)
            tmp_file.close()
            sub_status,error_infor=static_verify(java_file_path, timeout_seconds=200)

            print(f"this is {b}{i} verifiy status:{sub_status}")
            f_log.write(str(sub_status)+ "\n==============================\n")
            try:
                kill_process_by_name(['javac', 'cvc4'])
            except:
                pass
            if  sub_status:
                pass_status=True
                shutil.copy(java_file_path, os.path.join(pass_sub, f"{file_name}_{b}{i}.java"))
                dec_json["status"]=pass_status
                dec_json["duration"]=duration_list
                dec_json["token"]=token_list
            else:
                shutil.copy(java_file_path, os.path.join(fail_sub, f"{file_name}_{b}{i}.java"))
                dec_json["status"]=pass_status
                dec_json["duration"]=duration_list
                dec_json["token"]=token_list
                for m in range(5):
                    sub_spec, duration,tokens_used = sub_modify(sub_spec,prompt_m,fir_condition,error_infor,examples)
                    duration_list.append(duration)
                    token_list.append(tokens_used)

                    sub_spec = extract_java_code(sub_spec)
                    sub_spec = fr"{sub_spec}"
                    dec_json["spec"]=sub_spec

                    tmp_file = open(java_file_path, 'w')
                    tmp_file.write(sub_spec)
                    tmp_file.close()
                    sub_status,error_infor=static_verify(java_file_path, timeout_seconds=200)
                    f_log.write(str(sub_spec)+ "\n"+str(duration)+"\n"+str(tokens_used)+"\n"+str(sub_status)+"\n==============================\n")
                    try:
                        kill_process_by_name(['javac', 'cvc4'])
                    except:
                        pass
                    if sub_status:
                        pass_status=True
                        shutil.copy(java_file_path, os.path.join(pass_sub, f"{file_name}_{b}{i}.java"))
                        dec_json["status"]=pass_status
                        dec_json["duration"]=duration_list
                        dec_json["token"]=token_list
                        break
                    else:
                        shutil.copy(java_file_path, os.path.join(fail_sub, f"{file_name}_{b}{i}.java"))
                        dec_json["status"]=pass_status
                        dec_json["duration"]=duration_list
                        dec_json["token"]=token_list
            whole_break_infor[i]=dec_json
    print(whole_break_infor)             
    return True,whole_break_infor

def merge_code(code, true_specs,java_file_path,pass_save_path,fail_save_path):
    merge_code=merge(code, true_specs)
    merge_code = fr"{merge_code}"

    tmp_file = open(java_file_path, 'w')
    tmp_file.write(merge_code)
    tmp_file.close()
    first_merge_status,error_infor=static_verify(java_file_path, timeout_seconds=200)
    try:
        kill_process_by_name(['javac', 'cvc4'])
    except:
        pass
    if  first_merge_status:
        shutil.copy(java_file_path, pass_save_path)
    else:
        shutil.copy(java_file_path, fail_save_path) 

    return first_merge_status,merge_code

    
source_dir=""
#source_dir=""


prmpt_pass=['LeapYear.java', 'FIND_FIRST_IN_SORTED.java', 'Perimeter.java', 'Absolute.java', 'BinarySearch.java', 'StrPalindrome.java', 'AddLoop.java', 'Inverse.java', 'CopyArray.java',  'Neg.java', 'Time.java', 'LinearSearch.java', 'FindFirstZero.java', 'Calculator.java', 'FIND_IN_SORTED.java', 'SetZero.java', 'BankAccount.java', 'FindInArray.java', 'StudentEnrollment.java', 'OddEven.java', 'Smallest.java']
for root, _, files in os.walk(source_dir):
    prompt_wg=read_prompt("")
    prompt_m=read_prompt("")
    oracle_clean = ""
    oracle = ""
    current_directory = os.getcwd()
    #current_directory=os.path.dirname(os.path.abspath(__file__))
    path_dir = os.path.join(current_directory, 'path')
    sub_dir = os.path.join(current_directory, 'path')
    pass_dir = os.path.join(current_directory, 'pass')
    fail_dir = os.path.join(current_directory, 'fail')
    pass_sub = os.path.join(current_directory, 'pass_sub')
    fail_sub = os.path.join(current_directory, 'fail_sub')
    tmp_dir = os.path.join(current_directory, 'tmp')
    log_dir = os.path.join(current_directory, 'log')
    
    
    for directory in [path_dir, fail_dir,tmp_dir,log_dir,sub_dir,pass_dir,pass_sub,fail_sub]:
        if not os.path.exists(directory):
            os.makedirs(directory)
    file_infor={}

    for file in files:
        code_infr={}
        path_pass_status = False
        if file.endswith('.java'):
            examples = []
            random_files = random.sample(prmpt_pass, 2)
            oracle_clean = ""
            oracle = ""
            for filename in random_files:
                file_name, file_extension = os.path.splitext(filename)
                oracle_clean_path = os.path.join(oracle_clean+"/"+file_name, filename)
                oracle_path = os.path.join(oracle+"/"+file_name, filename)  
                with open(oracle_clean_path, "r", encoding="utf-8") as f:
                    prm_code = f.read()
                with open(oracle_path, "r", encoding="utf-8") as f:
                    prm_spec = f.read()
                #example = prm_code + "\n" + prm_spec
                examples.append(prm_spec)
            java_file = os.path.join(root, file)
            print(java_file )
            base_name = os.path.basename(java_file)  
            file_name, file_extension = os.path.splitext(base_name)
            ##every file record path passed and failed
            '''
            file_pass_dir = os.path.join(pass_dir, file_name)
            file_fail_dir = os.path.join(fail_dir, file_name)
            for directory in [file_pass_dir, file_fail_dir]:
                if not os.path.exists(directory):
                    os.makedirs(directory)
            '''     
            with open(java_file, "r", encoding="utf-8") as f:
                code = f.read()
            ##path extraction
            code = fr"{code}"
            path_file=path_dir+ "/{filename}.txt".format(filename=file_name)
            sub_file=path_dir+ "/{filename}.json".format(filename=file_name)
            current_time_str = time.strftime("%Y_%m_%d_%H_%M_%S", time.localtime(time.time()))
            log_path = log_dir + "/log-{name}-{time_str}.txt".format(name=file_name, time_str=current_time_str)
            f_log = open(log_path, "w")
            condition=[]
            '''
            if not os.path.exists(path_file):       
                paths = path_extract(code, prompt_ex,condition)
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
            '''
            ## common gerate first
            code_infr["original"] = code
            full_pass_num = 0
            sub_list=[]
            ##first_gen
            path_pass_status=False
            java_file_path = tmp_dir+ "/{filename}.java".format(filename=file_name)
            
            #for index, path in enumerate(paths):
            full_spec, duration,tokens_used = whole_gen(code,prompt_wg,examples)
            full_spec= extract_java_code(full_spec)
            full_spec = fr"{full_spec}"
            f_log.write("this is full code spec: \n")
            f_log.write(full_spec+ "\n"+str(duration)+"\n"+str(tokens_used)+"\n==============================\n")
            java_file_path = tmp_dir+ "/{filename}.java".format(filename=file_name)
            tmp_file = open(java_file_path, 'w')
            tmp_file.write(full_spec)
            tmp_file.close()
            status_full,error_infor_full=static_verify(java_file_path, timeout_seconds=200)
            f_log.write(str(status_full)+"\n" + error_infor_full+ "\n==============================\n")
            try:
                kill_process_by_name(['javac', 'cvc4'])
            except:
                pass
            if  status_full:
                code_infr["original_spec"] = full_spec
                shutil.copy(java_file_path, os.path.join(pass_dir, f"{file_name}.java"))
                path_pass_status=True
                full_pass_num += 1
                break
            else:
                shutil.copy(java_file_path, os.path.join(fail_dir, f"{file_name}.java"))
      
            ##iterate refine
            for i in range (5):
                full_spec, duration,tokens_used = whole_modify(code,prompt_m,error_infor_full,examples)
                full_spec= extract_java_code(full_spec)
                full_spec = fr"{full_spec}"
                print(full_spec)
                f_log.write("this is full code spec: \n")
                f_log.write(str(i)+ "\n"+full_spec+ "\n"+str(duration)+"\n"+str(tokens_used)+"\n==============================\n")
                java_file_path = tmp_dir+ "/{filename}.java".format(filename=file_name)
                tmp_file = open(java_file_path, 'w')
                tmp_file.write(full_spec)
                tmp_file.close()
                status_full,error_infor_full=static_verify(java_file_path, timeout_seconds=200)
                f_log.write(str(status_full)+"\n" + error_infor_full+ "\n==============================\n")
                try:
                    kill_process_by_name(['javac', 'cvc4'])
                except:
                    pass
                if  status_full:
                    code_infr["original_spec"] = full_spec
                    shutil.copy(java_file_path, os.path.join(pass_dir, f"{file_name}.java"))
                    path_pass_status=True
                    full_pass_num += 1
                    break
                else:
                    shutil.copy(java_file_path, os.path.join(fail_dir, f"{file_name}.java"))
            
            if not path_pass_status: 
                branch_status, branch_infor = decompose(code,prompt_wg,prompt_m,java_file_path,pass_sub,fail_sub,log_path,examples,b=1)
                f_log.write("this is branch1: \n")
                f_log.write(str(branch_status)+"\n"+str(branch_infor)+"\n==============================\n")
                if branch_status:
                    true_specs = [v["spec"] for v in branch_infor.values() if v["status"]]## true specs
                    #false_dicts = {k: v for k, v in branch_infor.items() if v["status"] is False}
                    print(true_specs)
                    code_infr["first_branch"] = branch_infor
                    #f_log.write(str(branch_infor)+ "\n==============================\n") 
                    f_log.write(str(true_specs)+ "\n==============================\n") 
                    pass_save_path=os.path.join(pass_dir, f"{file_name}.java")
                    fail_save_path=os.path.join(fail_dir, f"{file_name}.java")
                    first_merge_status,mergedcode = merge_code(code, true_specs,java_file_path,pass_save_path,fail_save_path)
                    print(mergedcode)
                    code_infr["first_merge_status"] = first_merge_status
                    f_log.write(str(first_merge_status)+"\n"+mergedcode+  "\n==============================\n")
                    print(first_merge_status)
                    false_dicts = {k: v for k, v in branch_infor.items() if v["status"] is False} 
                    
                    merge_status,merged_code = merge_code(code, true_specs,java_file_path,pass_save_path,fail_save_path)
                    print(merged_code)
                    print(merge_status)
                    f_log.write("this is branch1: \n")
                    f_log.write(str(branch_status)+"\n"+str(branch_infor)+"\n==============================\n")

                    if false_dicts:
                        for v in false_dicts.values():
                            sub_code = v["code"]
                            fir_condition=v['condition']
                            condition.append(fir_condition)
                            branch_status, branch_infor = decompose_2(sub_code,prompt_wg,prompt_m,java_file_path,pass_sub,fail_sub,log_path,examples,fir_condition,b=2)
                            if branch_status:
                                #f_log.write(str(branch_infor)+ "\n==============================\n") 
                                v["second_branch"]=branch_infor
                                second_true_specs = [v["spec"] for v in branch_infor.values() if v["status"]]## true specs
                                print(second_true_specs)
                                f_log.write(str(second_true_specs)+ "\n==============================\n")
                                true_specs += second_true_specs
                                false_dicts = {k: v for k, v in branch_infor.items() if v["status"] is False} 
                                merge_status,merged_code = merge_code(code, true_specs,java_file_path,pass_save_path,fail_save_path)
                                print(merged_code)
                                print(merge_status)
                                    
                                if false_dicts:
                                    for v in false_dicts.values():
                                        sub_code = v["code"]
                                        sec_condition=v['condition']
                                        condition.append(sec_condition)
                                        branch_status, branch_infor = decompose_2(sub_code,prompt_wg,prompt_m,java_file_path,pass_sub,fail_sub,log_path,examples,condition,b=3)
                                        if branch_status:
                                            #f_log.write(str(branch_infor)+ "\n==============================\n") 
                                            v["Third"]=branch_infor
                                            third_true_specs = [v["spec"] for v in branch_infor.values() if v["status"]]## true specs
                                            print(third_true_specs)
                                            f_log.write(str(third_true_specs)+ "\n==============================\n")
                                            true_specs += third_true_specs
                                            false_dicts = {k: v for k, v in branch_infor.items() if v["status"] is False} 

                    last_merge_status,second_code = merge_code(code, true_specs,java_file_path,pass_save_path,fail_save_path)
                    print(second_code)
                    print(last_merge_status)
                    f_log.write(str(last_merge_status)+"\n"+second_code+  "\n==============================\n") 
                    code_infr["last_branch"]=false_dicts
                    code_infr["last_merge"]=last_merge_status
            sub_file=sub_dir+ "/{filename}.json".format(filename=file_name)       
            file_infor[file]=code_infr
            with open(sub_file, 'w', encoding='utf-8') as f:
                json.dump(file_infor, f, ensure_ascii=False, indent=4)
                                    
                                
               