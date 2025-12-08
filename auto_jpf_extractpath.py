#!/usr/bin/env python3


import os
import glob
import subprocess
import sys
from pathlib import Path

class JPFAutomator:
    def __init__(self, java_dir, jpf_jar_path):
        self.java_dir = os.path.abspath(java_dir)
        self.jpf_jar_path = os.path.abspath(jpf_jar_path)
        self.classes_dir = ""
        
    def create_jpf_config(self, java_file):

        file_name = os.path.basename(java_file)
        class_name = file_name.replace('.java', '')
        jpf_file = os.path.join("", f"{class_name}.jpf")
        print(jpf_file)
        
        config_content = f"""# Auto-generated JPF configuration for {class_name}
target={class_name}
symbolic.debug=true
symbolic.method = {class_name}.{class_name.lower()}(sym#sym)
symbolic.minint=-10
symbolic.maxint=10
symbolic.dp=choco
listener=gov.nasa.jpf.symbc.SymbolicListener
symbolic.true=true
symbolic.output=true
symbolic.output.path=/tmp/symbolic_output

"""
        
        with open(jpf_file, 'w') as f:
            f.write(config_content)
        
        print(f"Created JPF config: {jpf_file}")
        return jpf_file
    
    def compile_java_files(self):


        if not os.path.exists(self.classes_dir):
            os.makedirs(self.classes_dir)
            print(f"Created classes directory: {self.classes_dir}")
        
  
        java_files = glob.glob(os.path.join(self.java_dir, "*.java"))
        
        if not java_files:
            print("No Java files found!")
            return False
        

        compile_cmd = ["javac", "-d", ""] + java_files
        print(compile_cmd)
        
       
        
        try:
            print("Compiling Java files...")
            result = subprocess.run(compile_cmd, capture_output=True, text=True, cwd=self.java_dir)
            
            if result.returncode == 0:
                print("✓ Compilation successful")
                return True
            else:
                print("✗ Compilation failed:")
                print(result.stderr)
                return False
                
        except Exception as e:
            print(f"Compilation error: {e}")
            return False
    
    def run_jpf_analysis(self, jpf_file):
        jpf_core_dir = ""
        #file_path = "src/examples/Abs.jpf" 
        if not os.path.exists(jpf_file):
            print(f"JPF file not found: {jpf_file}")
            return False
        file_name = os.path.basename(jpf_file)
        file_path="src/examples/" + file_name
        print(file_path)
        jpf_cmd = ["java", "-jar", "build/RunJPF.jar", file_path]

        print(jpf_cmd)
        
        try:
            print(f"Running JPF analysis for {os.path.basename(jpf_file)}...")
            print("=" * 60)
            
            #result = subprocess.run(jpf_cmd, capture_output=True, text=True, cwd=os.path.dirname(self.jpf_jar_path))
            result = subprocess.run(
            jpf_cmd,
            cwd=jpf_core_dir, 
            capture_output=True,
            text=True
            )
            
            print(result.stdout)
            result_path=""
            class_name = file_name.replace('.jpf', '')
            result_file_path=result_path+class_name+".txt"
            with open(result_file_path, "w", encoding="utf-8") as file:
                file.write(str(result.stdout))
            if result.stderr:
                print("Errors:")
                print(result.stderr)
            
            print("=" * 60)
            return result.returncode == 0
            
        except Exception as e:
            print(f"JPF execution error: {e}")
            return False
    
    def process_all_java_files(self):
        #if not self.compile_java_files():
            #return False
        

        java_files = glob.glob(os.path.join(self.java_dir, "*.java"))
        print(java_files)
        results = []
        
        for java_file in java_files:
            class_name = os.path.basename(java_file).replace('.java', '')
            print(f"\nProcessing {class_name}...")
            

            jpf_file = self.create_jpf_config(java_file)
            
            if not self.compile_java_files():
                pass
            success = self.run_jpf_analysis(jpf_file)
            results.append((class_name, success))

        print("\n" + "=" * 60)
        print("SUMMARY:")
        print("=" * 60)
        
        for class_name, success in results:
            status = "✓ SUCCESS" if success else "✗ FAILED"
            print(f"{class_name}: {status}")
        
        return all(success for _, success in results)

def main():

    if len(sys.argv) != 3:
        print("Usage: python jpf_automator.py <java_directory> <jpf_jar_path>")
        print("Example: python jpf_automator.py /path/to/java/files /path/to/jpf-core/build/RunJPF.jar")
        sys.exit(1)
    
    java_dir = sys.argv[1]
    jpf_jar_path = sys.argv[2]
    

    if not os.path.isdir(java_dir):
        print(f"Error: Java directory not found: {java_dir}")
        sys.exit(1)
    
    if not os.path.isfile(jpf_jar_path):
        print(f"Error: JPF jar file not found: {jpf_jar_path}")
        sys.exit(1)
    

    automator = JPFAutomator(java_dir, jpf_jar_path)
    success = automator.process_all_java_files()
    
    if success:
        print("\n✓ All analyses completed successfully!")
        sys.exit(0)
    else:
        print("\n✗ Some analyses failed!")
        sys.exit(1)

if __name__ == "__main__":
    main()