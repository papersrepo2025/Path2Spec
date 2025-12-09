from openai import OpenAI
import re
import subprocess
import os
import time
import shutil
import psutil
import os
import re
from pathlib import Path
import random
import json

from Path_level_merge import merge
#from code_merge import merge
from layer_level_merge import layer_merge
from A_program_decompose import split_java_class

OPENAI_API_KEY = ""
API_Key=""
qwen_key=""

client_gpt = OpenAI(api_key=OPENAI_API_KEY,)
client_dsk = OpenAI(api_key=API_Key, base_url="https://api.deepseek.com")
client_qwen= OpenAI(api_key=qwen_key, base_url="https://dashscope.aliyuncs.com/compatible-mode/v1")

def read_prompt(file_path):
    with open(file_path, 'r') as file:
        content = file.read().strip()
    return content

def path_extract(code, prompt):
    try:
        start_time = time.time()
        completion = client_qwen.chat.completions.create(
            #model="gpt-3.5-turbo",  
            model="qwen-plus",
            #model='deepseek-reasoner',#DeepSeek-R1
            #model='qwen-plus',#qwen
            #model="gpt-4o",
            #model="gpt-5",  
            messages=[
                {"role": "system", "content": "You are tasked with analyzing all execution paths of a given Java method using symbolic execution principles."},
                {"role": "user", "content": f"{prompt} and the code is:{code}"}
            ],
            stream=False,
            temperature=0.7,
        )
        end_time = time.time()
        code_spec = completion.choices[0].message.content.strip()
        #code_spec= json.loads(gpt_output)
    except Exception as e:
        error_dict = {"UnknownError": str(e)}
    return code_spec
def jml_gen(code, prompt,path,example):
    try:
        start_time = time.time()
        completion = client_qwen.chat.completions.create(
            #model="gpt-3.5-turbo",  
            #model='deepseek-reasoner',#DeepSeek-R1
            model='qwen-plus',#qwen
            #model="gpt-4o",
            #model="gpt-5",  
            messages=[
                {"role": "system", "content": f"You are an expert in formal methods and Java Modeling Language (JML). You are tasked with generating a JML (Java Modeling Language) specification for a specific execution path. Correct example is:{example}"},
                {"role": "user", "content": f"{prompt}, and the code is:{code},Please generate JML specification for the path and the component compromise the path, Path is: {path}"}
            ],
            temperature=0.7,
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
            The following Java code is instrumented with JML specifications for the path:
            Verifier failed to verify the specifications given above, with error information.
            Please refine the specifications, so that it can pass the verification."""
    try:
        start_time = time.time()
        completion = client_qwen.chat.completions.create(
            #model="gpt-3.5-turbo",  
            #model='deepseek-reasoner',#DeepSeek-R1
            model='qwen-plus',#qwen
            #model="gpt-4o",
            #model="gpt-5",  
            messages=[
                {"role": "system", "content": "You are an expert in formal methods and Java Modeling Language (JML)."},
                {"role": "user", "content": f"""{prompt},The following Java code{code} is instrumented with JML specifications for the path{path}: Verifier failed to verify the specifications given above, with error information as {info}.
            Please refine the specifications, so that it can pass the verification."""}
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

def static_verify(java_file_path, timeout_seconds=600):
    command = [
        "/home/danhuang/AIAgent_Spec/AI4spec/OJ/openjml",
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
    
def path_level_gen(code,examples,prompt_ex,prompt_gen,prompt_modi,java_file_path,path_pass_file_dir,path_fail_file_dir,path_file,pass_dir):
    pass_status = False
    base_name = os.path.basename(java_file_path)  
    file_name, file_extension = os.path.splitext(base_name)
    if not os.path.exists(path_file):       
        paths = path_extract(code, prompt_ex)
        print(paths)
        #path_filename = path_dir+ "/{filename}.txt".format(filename=file_name)
        path_file = open(path_file, 'w')
        path_file.write(paths)
        path_file.close()
    else:
        with open(path_file, 'r') as f:
            paths=f.read()

    paths=path_clear(paths)
    
    for index, path in enumerate(paths):
        code_spec, duration,tokens_used = jml_gen(code, prompt_gen,path, examples)
        #path_token[index]={0:{"duration":duration,"tokens_used":tokens_used}}
        code_spec= extract_java_code(code_spec)
        print(code_spec)

        tmp_file = open(java_file_path, 'w')
        tmp_file.write(code_spec)
        tmp_file.close()

        status,error_infor=static_verify(java_file_path, timeout_seconds=600)
        try:
            kill_process_by_name(['javac', 'cvc4'])
        except:
            pass
            
        if status:
            shutil.copy(java_file_path, os.path.join(path_pass_file_dir, f"{file_name}_{index}.java"))
        else:
            shutil.copy(java_file_path, os.path.join(path_fail_file_dir, f"{file_name}_{index}.java"))
            for i in range(5):
                code_spec, duration,tokens_used = jml_modify(code, prompt_modi, path, error_infor)
                code_spec= extract_java_code(code_spec)
                tmp_file = open(java_file_path, 'w')
                tmp_file.write(code_spec)
                tmp_file.close()
                status,error_infor=static_verify(java_file_path, timeout_seconds=600)
                try:
                    kill_process_by_name(['javac', 'cvc4'])
                except:
                    pass
                if status:
                    shutil.copy(java_file_path, os.path.join(path_pass_file_dir, f"{file_name}_{index}_{i}.java"))

                    break
                else:
                    shutil.copy(java_file_path, os.path.join(path_fail_file_dir, f"{file_name}_{index}_{i}.java"))
    if os.listdir(path_pass_file_dir):             
        INPUT_DIR = Path(path_pass_file_dir)
        tmp = Path(java_file_path)
        spec=merge(INPUT_DIR,pass_dir,tmp)
        pass_status=True
    return pass_status,spec

def decompose(code,prompt_ex,prompt_wg,prompt_m,java_file_path,path_pass_file_dir,path_fail_file_dir,sub_pass_dir,path_file,examples,b=1):
    #f_log = open(log_path, "w")
    results = split_java_class(code)
    fail_sub_code = []
    passed_spec=[]
    whole_break_infor = {}
    print(results)
    branch_status = False
    #f_log.write(str(results)+"\n==============================\n")
    if len(results)<=1:
        return False,{}
    else:
        branch_status = True
        for i, r in enumerate(results):
            dec_json = {}
            pass_status=False
            
            fir_condition = r['condition']
            sub_code = r['code']
            print(f"this is {i} subprogram:{sub_code}")
            dec_json["code"] = sub_code
            dec_json["condition"] = fir_condition
  
            #f_log.write(str(fir_condition)+ "\n"+str(sub_code)+"\n"+str(paths)+"\n==============================\n")

            #sub_spec, duration,tokens_used = sub_gen(sub_code, prompt_wg, fir_condition,examples)
            path_pass_status,spec = path_level_gen(code,examples,prompt_ex,prompt_wg,prompt_m,java_file_path,path_pass_file_dir,path_fail_file_dir,path_file,sub_pass_dir)
            
            if  path_pass_status:
                passed_spec.append(spec) 
                dec_json["status"]=path_pass_status  
                dec_json["spec"]=spec
            else:
                fail_sub_code.append(sub_code)
                dec_json["status"]=path_pass_status
        whole_break_infor[f"{b}_{i}"]=dec_json

            
    return branch_status, whole_break_infor

def merge_code(code, true_specs,java_file_path,pass_save_path,fail_save_path):
    merge_code=layer_merge(code, true_specs)
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

def mail(source_dir):
    prmpt_pass=['LeapYear.java', 'FIND_FIRST_IN_SORTED.java', 'Perimeter.java', 'Absolute.java', 'BinarySearch.java', 'StrPalindrome.java', 'AddLoop.java', 'Inverse.java', 'CopyArray.java',  'Neg.java', 'Time.java', 'LinearSearch.java', 'FindFirstZero.java', 'Calculator.java', 'FIND_IN_SORTED.java', 'SetZero.java', 'BankAccount.java', 'FindInArray.java', 'StudentEnrollment.java', 'OddEven.java', 'Smallest.java']

    for root, _, files in os.walk(source_dir):
        prompt_ex=read_prompt("")
        prompt_gen=read_prompt("")
        prompt_modi=read_prompt("")
        
        
        #path_dir = os.path.join("/home/danhuang/AIAgent_Spec/HQSPEC/test/test", 'path')
        ##
        current_directory = os.getcwd()
        path_dir = os.path.join(current_directory, 'path')# path information
        
        path_pass_dir = os.path.join(current_directory, 'path_pass') #path_level_spec
        path_fail_dir = os.path.join(current_directory, 'path_fail')#path_level_spec
        
        pass_dir= os.path.join(current_directory, 'pass')# passed spec
        fail_dir = os.path.join(current_directory, 'fail')# failed spec
        
        sub_pass_dir = os.path.join(current_directory, 'sub_pass')# passed sub spec
        #sub_fail_dir = os.path.join(current_directory, 'sub_fail')# passed sub spec
        
        tmp_dir = os.path.join(current_directory, 'tmp')
        log_dir = os.path.join(current_directory, 'log')
        
        
        for directory in [path_dir,path_pass_dir, path_fail_dir,pass_dir,fail_dir,tmp_dir,log_dir,sub_pass_dir]:
            if not os.path.exists(directory):
                os.makedirs(directory)
                
        file_infor={}
        pass_num = 0
        path_token={}

        for file in files:
            code_infr={}
            path_pass_status = False
            if file.endswith('.java'):
                examples = []
                random_files = random.sample(prmpt_pass, 1)
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
                    examples.append(prm_spec)
                ##create some related files
                java_file = os.path.join(root, file)
                base_name = os.path.basename(java_file)  
                file_name, file_extension = os.path.splitext(base_name)
                
                pass_file = os.path.join(pass_dir, file)
                fail_file = os.path.join(fail_dir, file)
                path_file = path_dir+ "/{filename}.txt".format(filename=file_name)
                java_file_path = tmp_dir+ "/{filename}.java".format(filename=file_name)

                path_pass_file_dir = os.path.join(path_pass_dir, file_name)
                path_fail_file_dir = os.path.join(path_fail_dir, file_name)
                sub_path_file_dir = os.path.join(path_fail_dir, file_name)
                
                for directory in [path_pass_file_dir, path_fail_file_dir]:
                    if not os.path.exists(directory):
                        os.makedirs(directory)
        
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
                ## common gerate first
                code_infr["original"] = code
                full_pass_num = 0
                sub_list=[]
                ##first_gen
                path_pass_status=False
                java_file_path = tmp_dir+ "/{filename}.java".format(filename=file_name)
            
                # path-based gen
                path_pass_status,spec = path_level_gen(code,prompt_ex,prompt_gen,prompt_modi,java_file_path,path_pass_file_dir,path_fail_file_dir,path_file,pass_dir)

                
                if not path_pass_status: 
                    branch_status, branch_infor = decompose(code,prompt_ex,prompt_gen,prompt_modi,java_file_path,path_pass_file_dir,path_fail_file_dir,sub_pass_dir,path_file,examples,b=1)
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
                                branch_status, branch_infor = decompose(code,prompt_ex,prompt_gen,prompt_modi,java_file_path,path_pass_file_dir,path_fail_file_dir,sub_pass_dir,path_file,examples,b=2)
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
                                            branch_status, branch_infor = decompose(code,prompt_ex,prompt_gen,prompt_modi,java_file_path,path_pass_file_dir,path_fail_file_dir,sub_pass_dir,path_file,examples,b=3)
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
                sub_file=tmp_dir + "/{filename}.json".format(filename=file_name)       
                file_infor[file]=code_infr
                with open(sub_file, 'w', encoding='utf-8') as f:
                    json.dump(file_infor, f, ensure_ascii=False, indent=4)

if __name__ == '__main__':
    source_dir = ""
    mail(source_dir)
                                
               