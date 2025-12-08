import os
import re
from typing import List, Tuple, Optional

def find_matching(code: str, start: int, open_ch: str, close_ch: str) -> int:
    if start >= len(code) or code[start] != open_ch:
        context = code[start:start+60].replace("\n", "\\n")
        raise ValueError(f"find_matching wrong: start={start}, context={context}")

    depth = 0
    i = start
    n = len(code)

    while i < n:
        c = code[i]

        if c == '"':
            i += 1
            while i < n:
                if code[i] == '\\':
                    i += 2
                    continue
                if code[i] == '"':
                    i += 1
                    break
                i += 1
            continue
        if c == "'":
            i += 1
            while i < n:
                if code[i] == '\\':
                    i += 2
                    continue
                if code[i] == "'":
                    i += 1
                    break
                i += 1
            continue

        if i + 1 < n and code[i] == '/' and code[i + 1] == '/':
            i += 2
            while i < n and code[i] != '\n':
                i += 1
            continue

        if i + 1 < n and code[i] == '/' and code[i + 1] == '*':
            i += 2
            while i + 1 < n and not (code[i] == '*' and code[i + 1] == '/'):
                i += 1
            i += 2
            continue
        if c == open_ch:
            depth += 1
        elif c == close_ch:
            depth -= 1
            if depth == 0:
                return i

        i += 1


    snippet = code[start-20:start+200].replace("\n", "\\n")
    print(f"[warning] find_matching: no match {close_ch}, start={start},  context={snippet[:300]}")

    return len(code) - 1

def skip_ws_comments(code: str, i: int) -> int:
    n = len(code)
    while i < n:
        if code[i].isspace():
            i += 1
            continue
        if i + 1 < n and code[i] == '/' and code[i+1] == '/':
            # line comment
            i += 2
            while i < n and code[i] != '\n':
                i += 1
            continue
        if i + 1 < n and code[i] == '/' and code[i+1] == '*':
            # block comment
            i += 2
            while i + 1 < n and not (code[i] == '*' and code[i+1] == '/'):
                i += 1
            i += 2 if i + 1 < n else 0
            continue
        break
    return i

def read_parenthesized(code: str, i: int) -> Tuple[str, int]:
    i = skip_ws_comments(code, i)
    if code[i] != '(':
        raise ValueError("Expected '('")
    j = find_matching(code, i, '(', ')')
    content = code[i+1:j]
    return content, j + 1

def read_stmt(code: str, i: int) -> Tuple[str, int]:
    i = skip_ws_comments(code, i)
    if code[i] == '{':
        j = find_matching(code, i, '{', '}')
        return code[i:j+1], j+1
    j = i
    n = len(code)
    while j < n and code[j] != ';':
        if code[j] in ('"', "'"):
            quote = code[j]
            j += 1
            while j < n:
                if code[j] == '\\':
                    j += 2
                    continue
                if code[j] == quote:
                    j += 1
                    break
                j += 1
            continue
        j += 1
    if j >= n or code[j] != ';':
        raise ValueError("Expected ';' to terminate statement")
    return code[i:j+1], j+1

def extract_class_header(java_code: str):
    java_code = java_code.replace('\r\n', '\n').lstrip('\ufeff').lstrip()
    pattern = re.compile(r'\b(?:public\s+|abstract\s+|final\s+)?class\s+[A-Za-z_]\w*', re.MULTILINE)
    m = pattern.search(java_code)
    if not m:
        raise ValueError("No class found")
    i = m.end()
    n = len(java_code)
    paren = brack = angle = 0
    while i < n:
        c = java_code[i]
        if c == '"': 
            i += 1
            while i < n:
                if java_code[i] == '\\':
                    i += 2
                    continue
                if java_code[i] == '"':
                    i += 1
                    break
                i += 1
            continue
        if c == "'":  
            i += 1
            while i < n:
                if java_code[i] == '\\':
                    i += 2
                    continue
                if java_code[i] == "'":
                    i += 1
                    break
                i += 1
            continue
        if i + 1 < n and java_code[i] == '/' and java_code[i + 1] == '/':  
            i += 2
            while i < n and java_code[i] != '\n':
                i += 1
            continue
        if i + 1 < n and java_code[i] == '/' and java_code[i + 1] == '*':  
            i += 2
            while i + 1 < n and not (java_code[i] == '*' and java_code[i + 1] == '/'):
                i += 1
            i += 2
            continue
        if c == '(':
            paren += 1
        elif c == ')':
            paren = max(0, paren - 1)
        elif c == '[':
            brack += 1
        elif c == ']':
            brack = max(0, brack - 1)
        elif c == '<':
            angle += 1
        elif c == '>':
            angle = max(0, angle - 1)
        elif c == '{' and paren == brack == angle == 0:
            class_header = java_code[m.start():i]
            return class_header.strip(), i
        i += 1
    raise ValueError("NO right blacket fount")

def extract_methods(java_code: str) -> List[Tuple[int, int, str, str]]:
    methods = []
    sig_pattern = re.compile(
        r'(?P<sig>\b(?:public|protected|private|static|final|synchronized|native|abstract|\s)+'
        r'[A-Za-z_][\w<>\[\]]*\s+[A-Za-z_]\w*\s*\([^)]*\))\s*\{'
    )
    for m in sig_pattern.finditer(java_code):
        sig_start = m.start('sig')
        brace_open = m.end()-1
        brace_close = find_matching(java_code, brace_open, '{', '}')
        full_text = java_code[sig_start:brace_close+1]
        sig = java_code[sig_start:brace_open].strip()
        body = java_code[brace_open+1:brace_close]
        methods.append((sig_start, brace_close+1, sig, body))
    return methods
def split_first_switch(method_body: str):
    code = method_body
    i = 0
    n = len(code)
    while i < n:
        i = skip_ws_comments(code, i)
        if i >= n:
            break
        if code.startswith('switch', i) and (i == 0 or not code[i-1].isalnum()) and (i+6 == n or not code[i+6].isalnum()):
            i_switch = i
            i += 6
            i = skip_ws_comments(code, i)
            cond, pos_after_cond = read_parenthesized(code, i)
            pos_after_cond = skip_ws_comments(code, pos_after_cond)
            if code[pos_after_cond] != '{':
                return None
            j = find_matching(code, pos_after_cond, '{', '}')
            body = code[pos_after_cond + 1:j]
            case_pattern = re.compile(r'(case\s+[^:]+:|default\s*:)', re.DOTALL)
            parts = list(case_pattern.finditer(body))
            if not parts:
                return None

            cases = []
            for idx, m in enumerate(parts):
                label = m.group(1).strip()
                start = m.end()
                end = parts[idx + 1].start() if idx + 1 < len(parts) else len(body)
                content = body[start:end].strip()
                content = re.sub(r'\bbreak\s*;\s*$', '', content).strip()
                content = unwrap_block(content)

                if label.startswith('case'):
                    val = label[len('case'):].strip(' :')
                    condition = f"{cond.strip()} == {val}"
                    is_default = False
                else:
                    condition = f"{cond.strip()} not in any case"
                    is_default = True

                cases.append((condition, content, i_switch, j + 1, is_default))
            return cases
        i += 1
    return None
def split_first_loop(method_body: str):
    code = method_body
    i = 0
    n = len(code)

    loop_pattern = re.compile(r'\b(for|while|do)\b')
    m = loop_pattern.search(code)
    if not m:
        return None

    kind = m.group(1)
    i_loop = m.start()

    # --- for / while ---
    if kind in ('for', 'while'):
        i = m.end()
        i = skip_ws_comments(code, i)
        cond, pos_after_cond = read_parenthesized(code, i)
        pos_after_cond = skip_ws_comments(code, pos_after_cond)
        body_stmt, pos_after_body = read_stmt(code, pos_after_cond)
        body_stmt_unwrapped = unwrap_block(body_stmt)
        has_return = bool(re.search(r'\breturn\b', body_stmt_unwrapped))
        return [(f"{kind} condition true ({cond})", body_stmt_unwrapped, i_loop, pos_after_body, has_return, False),
                (f"{kind} condition false ({cond})", "", i_loop, pos_after_body, has_return, True)]
    elif kind == 'do':
        i = m.end()
        pos_after_do = skip_ws_comments(code, i)
        body_stmt, pos_after_body = read_stmt(code, pos_after_do)
        pos_after_body = skip_ws_comments(code, pos_after_body)
        has_return = bool(re.search(r'\breturn\b', body_stmt))
        if code.startswith('while', pos_after_body):
            pos_cond_start = pos_after_body + 5
            cond, pos_after_cond = read_parenthesized(code, pos_cond_start)
            pos_after_cond = skip_ws_comments(code, pos_after_cond)
            if pos_after_cond < len(code) and code[pos_after_cond] == ';':
                pos_after_cond += 1
            body_stmt_unwrapped = unwrap_block(body_stmt)
            return [(f"do-while executes body once (initial run)", body_stmt_unwrapped, i_loop, pos_after_cond, has_return, False),
                    (f"do-while skipped (condition false after first run: {cond})", "", i_loop, pos_after_cond, has_return, True)]
    return None
def split_first_if(method_body: str):
    code = method_body
    i = 0
    n = len(code)
    while i < n:
        i = skip_ws_comments(code, i)
        if i >= n:
            break
        if code.startswith('if', i) and (i == 0 or not code[i-1].isalnum()) and (i+2 == n or not code[i+2].isalnum()):
            i_if = i
            i += 2
            i = skip_ws_comments(code, i)
            cond, pos_after_cond = read_parenthesized(code, i)
            pos_after_cond = skip_ws_comments(code, pos_after_cond)
            then_stmt, pos_after_then = read_stmt(code, pos_after_cond)
            pos_after_then = skip_ws_comments(code, pos_after_then)
            else_stmt = None
            if code.startswith('else', pos_after_then) and (pos_after_then+4 == n or not code[pos_after_then+4].isalnum()):
                pos_after_else = skip_ws_comments(code, pos_after_then + 4)
                else_stmt, pos_after_else_stmt = read_stmt(code, pos_after_else)
                return cond.strip(), then_stmt, else_stmt, i_if, pos_after_else_stmt

            remaining = code[pos_after_then:].strip()
            if remaining:
                return cond.strip(), then_stmt, remaining, i_if, n
            else:
                return cond.strip(), then_stmt, None, i_if, pos_after_then
        i += 1
    return None


def unwrap_block(stmt: str) -> str:
    s = stmt.strip()
    if not s:
        return s
    if s[0] == '{':
        j = find_matching(s, 0, '{', '}')
        if j == len(s) - 1:
            return s[1:-1].strip()
    return s


def rebuild_method(sig: str, body_before: str, injected: str, body_after: str, indent: str = "    ") -> str:
    injected = unwrap_block(injected)  
    combined = (body_before + "\n" + injected + "\n" + body_after).strip()

    lines = [f"{sig} {{"]

    if combined:
        for ln in combined.splitlines():
            if ln.strip():  
                lines.append(indent + ln.strip())

    lines.append("}")
    return "\n".join(lines)

def split_java_class(java_code: str, class_name: Optional[str] = None):
    java_code = java_code.replace('\r\n', '\n').lstrip('\ufeff').lstrip()
    class_header, class_lbrace = extract_class_header(java_code)
    if java_code[class_lbrace] != '{':
        next_brace = java_code.find('{', class_lbrace)
        if next_brace != -1:
            print(f"modify class_lbrace 从 {class_lbrace} → {next_brace}")
            class_lbrace = next_brace
        else:
            raise ValueError("Can not find left brace")

    #class_close = find_matching(java_code, class_lbrace, '{', '}')
    if java_code[class_lbrace] != '{':
        next_brace = java_code.find('{', class_lbrace)
        if next_brace == -1:
            raise ValueError("error")
        class_lbrace = next_brace

    try:
        class_close = find_matching(java_code, class_lbrace, '{', '}')
    except ValueError as e:
        for delta in range(1, 10):
            if class_lbrace + delta < len(java_code) and java_code[class_lbrace + delta] == '{':
                class_lbrace = class_lbrace + delta
                class_close = find_matching(java_code, class_lbrace, '{', '}')
                break
        else:
            snippet = java_code[class_lbrace-20:class_lbrace+60].replace('\n', '\\n')
            raise ValueError(f"start={class_lbrace}, context={snippet}") from e
    class_body = java_code[class_lbrace+1:class_close]

    methods = extract_methods(java_code)
    filtered = []
    for s, e, sig, body in methods:
        if class_lbrace < s < class_close:
            filtered.append((s, e, sig, body))

    outputs = []
    for idx, (s, e, sig, body) in enumerate(filtered, 1):
        found_if = split_first_if(body)
        found_switch = split_first_switch(body)
        found_loop=False
        found_loop = split_first_loop(body)
        if found_if:
            cond, then_src, else_src, if_start, if_end = found_if
            before = body[:if_start]
            after = body[if_end:]
            method_then = rebuild_method(sig, before, then_src, after)
            class_then = f"{class_header} {{\n    {method_then}\n}}"
            outputs.append({
                "method": sig,
                "condition": cond,
                "code": class_then
            })

            if else_src is not None:
                method_else = rebuild_method(sig, before, else_src, after)
                class_else = f"{class_header} {{\n    {method_else}\n}}"
                outputs.append({
                    "method": sig,
                    "condition": f"NOT ({cond})",
                    "code": class_else
                })
        elif found_switch:
            for cond, case_body, sw_start, sw_end, is_default in found_switch:
                before = body[:sw_start]
                after = body[sw_end:]
                if is_default and re.search(r'\breturn\b', case_body):
                    after = ""
                method_case = rebuild_method(sig, before, case_body, after)
                class_case = f"{class_header} {{\n    {method_case}\n}}"
                outputs.append({
                    "method": sig,
                    "condition": cond,
                    "code": class_case
                })
        elif found_loop:
            for cond, loop_body, loop_start, loop_end, has_return, skip_branch in found_loop:
                before = body[:loop_start]
                after = body[loop_end:]

                injected = loop_body if not skip_branch else ""
                method_loop = rebuild_method(sig, before, injected, after)
                class_loop = f"{class_header} {{\n    {method_loop}\n}}"
                outputs.append({
                    "method": sig,
                    "condition": cond,
                    "code": class_loop
                })
            continue
                    
        else:
            new_class = f"{class_header} {{\n    {sig} {{\n{body}\n    }}\n}}"
            outputs.append({
                "method": sig,
                "condition": "No control flow found",
                "code": new_class
            })
            continue

    return outputs
