import re
import sys
import os
from pathlib import Path
from collections import defaultdict
import psutil
import subprocess

# ========== Configuration ==========


# Alternative usage: command line override
# python merge_jml_specs_v3_fixed.py /path/to/dir
if len(sys.argv) > 1:
    INPUT_DIR = Path(sys.argv[1]).expanduser().resolve()

# ===== Regular Expressions =====
CLASS_DECL_RE = re.compile(
    r'(?:(?:public|protected|private|abstract|final|static)\s+)*class\s+([A-Za-z_]\w*)\b'
)

# Method declaration: capture up to opening brace {, preserving indentation before method name
METHOD_RE = re.compile(
    r'(?P<indent>^[ \t]*)'
    r'(?:(?:public|protected|private|static|final|native|synchronized|abstract|strictfp)\s+)*'
    r'(?:[\w\[\]<>.?]+(?:\s*<[^>]+>)?\s+)+' 
    r'(?P<name>[A-Za-z_]\w*)'
    r'\s*\('
    r'(?P<params>[^)]*)'
    r'\)\s*'
    r'(?:throws\s+[^{]+)?\s*'
    r'\{',
    re.MULTILINE
)

# Single-line JML
REQUIRES_LINE_RE = re.compile(r'^\s*//@\s*requires\b.*$', re.MULTILINE)
ENSURES_LINE_RE  = re.compile(r'^\s*//@\s*ensures\b.*$',  re.MULTILINE)
ALSO_LINE_RE     = re.compile(r'^\s*//@\s*also\s*$',      re.MULTILINE)

# Block comments /*@ ... @*/
BLOCK_RE = re.compile(r'/\*@([\s\S]*?)@\*/', re.MULTILINE | re.DOTALL)

# Loop specifications (only normalize, don't migrate across methods)
LOOP_LINE_RE = re.compile(r'^\s*//@\s*(?:maintaining|loop_invariant|decreases)\b.*$', re.MULTILINE)

# ===== Utility Functions =====
def list_java_files(root: Path):
    return [p for p in root.rglob("*.java") if p.is_file()]

def ensure_line_starts_with_slash_at(line: str) -> str:
    s = line.strip()
    if not s:
        return s
    if s.startswith("//@"):
        return s
    if s.startswith("@"):
        s = s[1:].strip()
    return "//@ " + s

def ensure_semicolon(line: str) -> str:
    s = line.rstrip()
    if not s or s == "//@":
        return s
    return s if s.endswith(";") or s.strip() == "//@ also" else s + ";"

def normalize_block_to_line_comments(block_text: str) -> str:
    out = []
    for raw in block_text.splitlines():
        t = raw.strip()
        if not t:
            continue
        if t.startswith("@"):
            t = t[1:].strip()
        if not t:
            continue
        ln = ensure_line_starts_with_slash_at(t)
        ln = ensure_semicolon(ln)
        out.append(ln)
    return "\n".join(out)

def normalize_all_blocks(code: str) -> str:
    # Only normalize /*@ ... @*/ to //@ lines, don't modify other code
    return BLOCK_RE.sub(lambda m: normalize_block_to_line_comments(m.group(1)), code)

def extract_class_names(code: str):
    return set(m.group(1) for m in CLASS_DECL_RE.finditer(code))

def normalize_type(tok: str) -> str:
    t = tok.strip()
    t = re.sub(r'@\w+(?:\([^)]*\))?\s*', '', t)
    t = re.sub(r'\b(final|volatile|transient)\b\s*', '', t)
    t = re.sub(r'\s+', ' ', t).strip()
    parts = t.split()
    if not parts:
        return ""
    ty = parts[0]
    ty = re.sub(r'<.*?>', '', ty)
    return ty

def normalize_signature(name: str, params: str) -> str:
    if params.strip() == "":
        return f"{name}()"
    types = []
    for p in re.split(r',', params):
        if p.strip():
            types.append(normalize_type(p))
    return f"{name}({','.join(types)})"

def split_spec_cases(header_lines: list) -> list:
    """Split consecutive header comments into multiple spec-cases (separated by //@ also)"""
    cases = []
    cur_req, cur_ens = [], []
    for ln in header_lines:
        s = ln.strip()
        if s == "//@ also":
            if cur_req or cur_ens:
                cases.append((tuple(cur_req), tuple(cur_ens)))
            cur_req, cur_ens = [], []
            continue
        if s.startswith("//@ requires"):
            cur_req.append(ensure_semicolon(s))
        elif s.startswith("//@ ensures"):
            cur_ens.append(ensure_semicolon(s))
    if cur_req or cur_ens:
        cases.append((tuple(cur_req), tuple(cur_ens)))
    return cases

# ---------- Key: Line number based extraction for stable insertion points ----------
def _pos_to_line_idx_offsets(code: str):
    """Build helper table to convert character offsets to line numbers"""
    lines = code.splitlines(keepends=True)
    starts = []
    off = 0
    for ln in lines:
        starts.append(off)
        off += len(ln)
    return lines, starts  # lines include newlines; starts[i] is offset of line i

def _offset_to_line_idx(starts, pos: int) -> int:
    """Binary search to map character offset to line number"""
    lo, hi = 0, len(starts)
    while lo + 1 < hi:
        mid = (lo + hi) // 2
        if starts[mid] <= pos:
            lo = mid
        else:
            hi = mid
    return lo

def extract_method_headers_by_signature(code: str):
    """
    Extract line number ranges + specification cases for JML header comments
    located before method declaration lines.
    Only looks for //@ requires / //@ ensures / //@ also; other code unchanged.
    """
    result = {}
    lines, starts = _pos_to_line_idx_offsets(code)

    for m in METHOD_RE.finditer(code):
        name   = m.group("name")
        params = m.group("params") or ""
        indent = m.group("indent") or ""
        sig    = normalize_signature(name, params)

        # Line number of method declaration start (not '{')
        decl_char_pos = m.start()
        decl_line_idx = _offset_to_line_idx(starts, decl_char_pos)

        # Collect consecutive //@ lines and empty lines above
        cur = decl_line_idx - 1
        header_start_idx = decl_line_idx
        collected = []
        while cur >= 0:
            raw = lines[cur].rstrip("\n")
            st = raw.strip()
            if st == "" or st.startswith("//@"):
                collected.append(raw)
                header_start_idx = cur
                cur -= 1
                continue
            break
        collected.reverse()

        # Normalize to //@ lines
        header_lines = [
            ensure_semicolon(ensure_line_starts_with_slash_at(l))
            for l in collected
            if l.strip() == "//@ also" or l.strip().startswith("//@")
        ]
        cases = split_spec_cases(header_lines)

        result.setdefault(sig, {"ranges": [], "cases": []})
        # Record line number range: [header_start_idx, decl_line_idx)
        result[sig]["ranges"].append(
            (header_start_idx, decl_line_idx, indent)
        )
        if cases:
            result[sig]["cases"].extend(cases)

    return result

def merge_spec_cases(list_of_cases_lists):
    """Merge cases lists from multiple files (deduplicate, preserve order)"""
    merged = []
    seen = set()
    for cases in list_of_cases_lists:
        for (reqs, enss) in cases:
            key = (tuple(reqs), tuple(enss))
            if key not in seen and (reqs or enss):
                seen.add(key)
                merged.append((reqs, enss))
    return merged

def build_header_from_cases(cases):
    """Format output: separate branches with //@ also"""
    if not cases:
        return ""
    lines = []
    for i, (reqs, enss) in enumerate(cases):
        if i > 0:
            lines.append("//@ also")
        lines.extend(reqs)
        lines.extend(enss)
    return "\n".join(lines)

# ---------- Align all //@ lines to nearest Java code indentation ----------
def align_jml_to_code_indent(code: str) -> str:
    lines = code.splitlines()
    n = len(lines)
    i = 0
    while i < n:
        if not lines[i].lstrip().startswith("//@"):
            i += 1
            continue
        start = i
        i += 1
        while i < n and lines[i].lstrip().startswith("//@"):
            i += 1
        end = i
        target_indent = None
        j = end
        while j < n:
            if lines[j].strip() == "":
                j += 1
                continue
            if lines[j].lstrip().startswith("//@"):
                j += 1
                continue
            target_indent = re.match(r'^\s*', lines[j]).group(0)
            break
        if target_indent is not None:
            for k in range(start, end):
                lines[k] = target_indent + lines[k].lstrip()
    return "\n".join(lines)

# ---------- Key: Line number safe merge implementation ----------
def merge_one_class(files_for_class):
    """Merge multiple file versions for one class by method signature (only modify header comments, keep Java code intact)."""
    if not files_for_class:
        return None, None

    # Base code: keep its Java code completely unchanged
    base_path = files_for_class[0]
    base_code = normalize_all_blocks(base_path.read_text(encoding="utf-8"))

    # JML extraction from each version
    per_file_maps = []
    for p in files_for_class:
        txt = normalize_all_blocks(p.read_text(encoding="utf-8"))
        per_file_maps.append(extract_method_headers_by_signature(txt))

    # Methods in base (only replace headers for these)
    base_sig_map = extract_method_headers_by_signature(base_code)

    # Use "line number replacement" strategy to avoid character offset issues
    code_lines = base_code.splitlines()
    # Process from largest start line to smallest to avoid affecting subsequent line numbers
    replace_jobs = []

    for sig, info in base_sig_map.items():
        # Aggregate cases for this method from all versions, connect with //@ also
        cases_lists = []
        for m in per_file_maps:
            if sig in m:
                cases_lists.append(m[sig]["cases"])
        merged_cases = merge_spec_cases(cases_lists)
        header_block = build_header_from_cases(merged_cases)

        # This method's range in base (by line number)
        (header_start_line, decl_line, indent) = info["ranges"][0]

        # Prepare replacement content: only replace //@ and empty lines in [header_start_line, decl_line)
        header_lines_indented = []
        if header_block.strip():
            for ln in header_block.splitlines():
                header_lines_indented.append(indent + ln.strip())

        replace_jobs.append( (header_start_line, decl_line, header_lines_indented) )

    # Replace from largest start line to smallest for stable indices
    for start_line, decl_line, new_header_lines in sorted(replace_jobs, key=lambda x: -x[0]):
        # Clear original header comments (only //@ or empty lines), then insert new
        # Note: we don't cross decl_line, won't touch method body
        # For safety, only delete lines matching //@ or whitespace
        i = start_line
        while i < decl_line and i < len(code_lines):
            s = code_lines[i].strip()
            if s == "" or s.startswith("//@"):
                i += 1
                continue
            # If encountering non-comment, non-empty line (shouldn't happen), stop early
            break
        # Effective deletion range is [start_line, i)
        code_lines[start_line:i] = new_header_lines

    merged_code = "\n".join(code_lines)
    merged_code = align_jml_to_code_indent(merged_code)
    merged_code = re.sub(r'\n{3,}', '\n\n', merged_code).rstrip() + "\n"
    return merged_code, base_path

def static_verify(java_file_path, timeout_seconds=600):
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
            return True,str(result)
        else:
            print(f"{java_file_path}: Verification failed with errors.")
            print("Error details:", result.stderr)
            print("Error details:", result.stdout)
            #print(result)
            return False,str(result)
    except Exception as e:
        print(f"Failed to verify {java_file_path}: {e}")
        return False,str(e)

def kill_process_by_name(names):
    for proc in psutil.process_iter(['pid', 'name']):
        if proc.info['name'] and any(name in proc.info['name'] for name in names):
            print(f"Killing process {proc.pid} ({proc.info['name']})")
            proc.terminate()
            try:
                proc.wait(timeout=3)
            except psutil.TimeoutExpired:
                proc.kill()

def _list_program_dirs_2(root: Path):
    """
    Return all first-level subdirectories containing .java files, sorted by directory name.
    Example: /pass/WBS, /pass/ABS, ...
    """
    if not root.is_dir():
        return []
    dirs = []
    for p in sorted(root.iterdir()):
        if p.is_dir():
            has_java = any(f.suffix == ".java" for f in p.iterdir() if f.is_file())
            if has_java:
                dirs.append(p)
    return dirs
def _list_program_dirs(root: Path):
    """
    Return all directories containing .java files, including the root directory itself if it contains Java files.
    Otherwise, return first-level subdirectories containing Java files.
    """
    if not root.is_dir():
        return []
    
    # Check if root itself contains Java files
    root_has_java = any(f.suffix == ".java" for f in root.iterdir() if f.is_file())
    if root_has_java:
        return [root]
    
    # Otherwise, return subdirectories containing Java files
    dirs = []
    for p in sorted(root.iterdir()):
        if p.is_dir():
            has_java = any(f.suffix == ".java" for f in p.iterdir() if f.is_file())
            if has_java:
                dirs.append(p)
    return dirs

# ===== Main Program (Sequential merge: unchanged) =====
def merge(INPUT_DIR,OUTPUT_DIR,tmp):
    print(f"[INFO] Scanning by 'directory=program' mode: {INPUT_DIR}")

    program_dirs = _list_program_dirs(INPUT_DIR)
    if not program_dirs:
        print("[WARN] No program directories containing .java files found")
        return

    merged_cnt = 0
    done_list=[]

    for prog_dir in program_dirs:
        prog_name = prog_dir.name  # e.g., WBS
        if prog_name not in done_list:
            files = sorted([p for p in prog_dir.iterdir() if p.is_file() and p.suffix == ".java"])

            if not files:
                print(f"[WARN] Directory has no .java: {prog_dir}")
                continue

            print(f"\n[INFO] Processing program directory: {prog_name}  ({len(files)} versions)")

            base_idx = -1
            verified_code = None

            for i, p in enumerate(files):
                candidate = normalize_all_blocks(p.read_text(encoding="utf-8"))
                tmp.write_text(candidate, encoding="utf-8")
                print(f"[VERIFY] Trying base {i+1}/{len(files)}: {p.name}")
                status, result = static_verify(tmp, timeout_seconds=200)
                if status:
                    base_idx = i
                    verified_code = candidate
                    print(f"[PASS] Base determined as: {p.name}")
                    break
                else:
                    print(f"[FAIL] {p.name} verification failed, trying next version")

            if base_idx == -1:
                print(f"[WARN] No directly verifiable base in this directory, falling back to first version as base and attempting merge.")
                base_idx = 0
                verified_code = normalize_all_blocks(files[0].read_text(encoding="utf-8"))
                tmp.write_text(verified_code, encoding="utf-8")

            # Build merge order: base to end, then start to before base
            merge_order = list(range(base_idx + 1, len(files))) + list(range(0, base_idx))

            # Merge versions sequentially: each time based on "verified code + new file" → merge → verify → update if passed
            for j in merge_order:
                next_file = files[j]
                print(f"[STEP] Attempting merge: {next_file.name}")

                tmp.write_text(verified_code, encoding="utf-8")
                candidate_code, _ = merge_one_class([tmp, next_file])  # Only modify header comments, keep Java code
                tmp.write_text(candidate_code, encoding="utf-8")

                status, result = static_verify(tmp, timeout_seconds=200)
                if status:
                    verified_code = candidate_code
                    print(f"[PASS] Merge {next_file.name} verification passed ✅")
                else:
                    print(f"[SKIP] Merge {next_file.name} verification failed ❌, skipping this version")

            # Output final file: named by directory name
            out_path = f"{OUTPUT_DIR}/{prog_name}.java"
            out_path =Path(out_path)
            try:
                out_path.write_text(verified_code, encoding="utf-8")
                merged_cnt += 1
                print(f"[OK] Output final result: {out_path}")
            except Exception as e:
                print(f"[ERROR] Write failed: {out_path} -> {e}")

    if merged_cnt == 0:
        print("\n[WARN] No merge results output.")
    else:
        print(f"\n[DONE] Successfully output {merged_cnt} merged files.")
    return verified_code
#merge(INPUT_DIR,OUTPUT_DIR,tmp)