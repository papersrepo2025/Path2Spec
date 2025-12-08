import os
import re
import csv
from pathlib import Path

def read_java_files(folder_path, allowed_files):
    java_files = {}
    if os.path.exists(folder_path):
        for file in allowed_files:
            file_path = os.path.join(folder_path, file)
            if os.path.exists(file_path):
                try:
                    with open(file_path, 'r', encoding='utf-8') as f:
                        content = f.read()
                    java_files[file] = content
                except Exception as e:
                    print(f"read file {file_path}: {e}")
                    java_files[file] = "verify_fail"
            else:
                java_files[file] = "verify_fail"
    else:
        for file in allowed_files:
            java_files[file] = "verify_fail"
    return java_files

def remove_comments(java_code):
    if java_code == "verify_fail":
        return java_code
    
    code = re.sub(r'/\*.*?\*/', '', java_code, flags=re.DOTALL)
    code = re.sub(r'//.*$', '', code, flags=re.MULTILINE)
    code = '\n'.join([line.strip() for line in code.split('\n') if line.strip()])
    return code

def extract_jml_spec(java_code):

    if java_code == "verify_fail":
        return java_code
    jml_pattern = r'(@\w+.*?)(?=\n\s*@|\n\s*public|\n\s*private|\n\s*protected|$)'
    jml_matches = re.findall(jml_pattern, java_code, re.DOTALL | re.MULTILINE)
    
    cleaned_jml = []
    for match in jml_matches:
        cleaned = re.sub(r'//.*$', '', match, flags=re.MULTILINE)  
        cleaned = re.sub(r'/\*.*?\*/', '', cleaned, flags=re.DOTALL)  
        cleaned = cleaned.strip()
        if cleaned:
            cleaned_jml.append(cleaned)
    
    return '\n'.join(cleaned_jml)

def analyze_files(files_data):
    results = {}
    
    all_files = set()
    for folder_data in files_data.values():
        all_files.update(folder_data.keys())
    
    for filename in sorted(all_files):
        file_contents = {}
        for folder_name, folder_data in files_data.items():
            file_contents[folder_name] = folder_data.get(filename, "verify_fail")
        
        analysis_result = []
        
        content_groups = {}
        for folder_name, content in file_contents.items():
            if content != "verify_fail":
                if content not in content_groups:
                    content_groups[content] = []
                content_groups[content].append(folder_name)
        
        duplicate_folders = []
        for content, folders in content_groups.items():
            if len(folders) > 1:
                duplicate_folders.append(f"Total same content: {', '.join(folders)}")
        
        if duplicate_folders:
            analysis_result.extend(duplicate_folders)
        else:
            analysis_result.append("No totaly same file")
        
        code_contents = {}
        for folder_name, content in file_contents.items():
            if content != "verify_fail":
                code_no_comments = remove_comments(content)
                if code_no_comments not in code_contents:
                    code_contents[code_no_comments] = []
                code_contents[code_no_comments].append(folder_name)
        
        code_duplicates = []
        for code, folders in code_contents.items():
            if len(folders) > 1:
                code_duplicates.append(f"Originral code is same: {', '.join(folders)}")
        
        if code_duplicates:
            analysis_result.extend(code_duplicates)
        else:
            analysis_result.append("")
        
        jml_contents = {}
        for folder_name, content in file_contents.items():
            if content != "verify_fail":
                jml_spec = extract_jml_spec(content)
                if jml_spec and jml_spec != "verify_fail":
                    if jml_spec not in jml_contents:
                        jml_contents[jml_spec] = []
                    jml_contents[jml_spec].append(folder_name)
        
        jml_duplicates = []
        for jml, folders in jml_contents.items():
            if len(folders) > 1:
                jml_duplicates.append(f"With same JML content: {', '.join(folders)}")
        
        if jml_duplicates:
            analysis_result.extend(jml_duplicates)
        else:
            analysis_result.append("No same JML contents in other different files")
        
        results[filename] = '; '.join(analysis_result)
    
    return results

def main():

    folders = {
        'Path2spec': '',
        'specgen': '', 
        'PathOnly': '',
        'Decom': ''
    }
    
    base_file_list= ['BubbleSort.java', 'SmallestEvenMul.java', 'SelectionSort.java', 'PowerOfTwoLoop.java', 'GCD.java', 'MatrixMul.java', 'NumberOfCuts.java', 'DominantIndex.java', 'BubbleSortDesc.java', 'Conjunction.java', 'Return100.java', 'AddLoopFor.java', 'NotCommonFactor.java', 'PowerOfThree.java', 'ContainsDuplicate.java', 'ConvertTemperature.java', 'DivisorGame.java', 'FindFirstZero.java', 'CalculatorShuffled.java', 'IsPalindrome.java', 'EchoIntLoop.java', 'Inverse.java', 'AddLoop.java', 'SetZero.java', 'OddEven.java', 'PrimeCheck.java', 'MinInArray.java', 'LeapYearSeq.java', 'IsCommonMultipleBranch.java', 'RotateArray.java', 'Smallest.java', 'MyPower.java', 'UniqueNumNested.java', 'XOR.java', 'CanWinNim.java', 'StrPalindrome.java', 'MySqrt.java', 'PassPillow.java', 'Perimeter.java', 'IsDescending.java', 'LinearSearch.java', 'JewelsInStones.java', 'IntSquare.java', 'MulNested.java', 'PassPillowBranch.java', 'ReverseString.java', 'PowerOfTwoBranch.java', 'MulLoop.java', 'NotCommonFactorBranch.java', 'IsAllUnique.java', 'ReLUSeq.java', 'AddTwoLoop.java', 'RepeatedNumNested.java', 'PowerOfTwo.java', 'SolveQuadraticEquation.java', 'UniqueCharNested.java', 'ComputeAreaBranch.java', 'CompareArray.java', 'CopyArray.java', 'SortLibrary.java', 'ThreeConsecutiveOdds.java', 'AbsSeq.java', 'IsPrefix.java', 'Absolute.java', 'ReLU.java', 'IsSuffix.java', 'ReverseArray.java', 'Biggest.java', 'ComputeArea.java', 'CheckABeforeB.java', 'ConvertToKelvin.java', 'Abs.java', 'Swap.java', 'IsAscending.java', 'Fibonacci.java', 'MatrixAdd.java', 'SubLoop.java', 'SmallestEvenMulBranch.java', 'MaxInArray.java', 'MoveZeroes.java', 'SelectionSortDesc.java', 'NegAbs.java', 'BinarySearch.java', 'Temperatures.java', 'IsSubsequence.java', 'IsBoomerang.java', 'IsCommonFactorBranch.java', 'PowerOfFour.java', 'LCM.java', 'Return100Nested.java', 'NotCommonMultiple.java', 'Disjunction.java']
    
    specgen_file_list=['RepeatedNumNested.java', 'AbsSeq.java', 'IntSquare.java', 'DivisorGame.java', 'IsCommonMultipleBranch.java', 'Abs.java', 'CompareArray.java', 'ReverseArray.java', 'Inverse.java', 'NumberOfCuts.java', 'NegAbs.java', 'SubLoop.java', 'IsPrefix.java', 'CanWinNim.java', 'AddTwoLoop.java', 'CopyArray.java', 'Biggest.java', 'NotCommonFactorBranch.java', 'MulNested.java', 'ConvertToKelvin.java', 'ReverseString.java', 'SetZero.java', 'ConvertTemperature.java', 'LinearSearch.java', 'PowerOfTwoBranch.java', 'Perimeter.java', 'AddLoopFor.java', 'OddEven.java', 'MulLoop.java', 'Disjunction.java', 'ReLU.java', 'CalculatorShuffled.java', 'SmallestEvenMul.java', 'IsPalindrome.java', 'DominantIndex.java', 'PowerOfFour.java', 'MatrixAdd.java', 'AddLoop.java', 'PowerOfTwo.java', 'MinInArray.java', 'CheckABeforeB.java', 'LeapYearSeq.java', 'Temperatures.java', 'Conjunction.java', 'Swap.java', 'FindFirstZero.java', 'IsAllUnique.java', 'IsCommonFactorBranch.java', 'XOR.java', 'Return100.java', 'EchoIntLoop.java', 'Absolute.java', 'PowerOfTwoLoop.java', 'Smallest.java', 'NotCommonFactor.java', 'IsSuffix.java', 'NotCommonMultiple.java', 'Return100Nested.java', 'ReLUSeq.java', 'PassPillowBranch.java', 'MyPower.java']

    allowed_files = {
        'Path2spec': base_file_list,
        'specgen': specgen_file_list,  
        'PathOnly': base_file_list,
        'Decom': base_file_list
    }
    
    all_files_data = {}
    for folder_name, folder_path in folders.items():
        print(f"reading files of: {folder_name}")
        all_files_data[folder_name] = read_java_files(folder_path, allowed_files[folder_name])
        print(f"  find{len([f for f in all_files_data[folder_name].values() if f != 'verify_fail'])} java files")
    
    all_filenames = set()
    for folder_name, folder_data in all_files_data.items():
        existing_files = [f for f, content in folder_data.items() if content != "verify_fail"]
        all_filenames.update(existing_files)
    
    all_filenames = sorted(all_filenames)
    print(f"\nTotal compare {len(all_filenames)} files")

    analysis_results = analyze_files(all_files_data)
    

    output_file = '/1_compare_spec_new/spec_compare.csv'
    with open(output_file, 'w', newline='', encoding='utf-8') as csvfile:
        writer = csv.writer(csvfile)
        
        writer.writerow(['Filename', 'Path2spec', 'specgen', 'PathOnly', 'Decom', 'Analysis_Result'])
        
        for filename in all_filenames:
            row = [filename]
            for folder_name in folders.keys():
                content = all_files_data[folder_name].get(filename, "verify_fail")
                row.append(content)
            row.append(analysis_results.get(filename, "get file failed"))
            writer.writerow(row)
    
    print(f"Saved to: {output_file}")
    
    print("\ninformations:")
    for folder_name in folders.keys():
        file_count = len([f for f in all_files_data[folder_name].values() if f != "verify_fail"])
        total_allowed = len(allowed_files[folder_name])
        print(f"  {folder_name}: {file_count}/{total_allowed} files")

if __name__ == "__main__":
    main()