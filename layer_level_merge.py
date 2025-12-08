import re
from difflib import SequenceMatcher
from pathlib import Path


METHOD_SIG = re.compile(
    r'public\s+(?:static\s+)?[^\s]+\s+([A-Za-z_]\w*)\s*\((.*?)\)',
    re.MULTILINE | re.DOTALL
)
"""
METHOD_SIG = re.compile(
    r'public\s+(?:static\s+)?[^\s]+\s+([A-Za-z_]\w*)\s*\(\s*(.*?)\s*\)',
    re.MULTILINE | re.DOTALL
)
"""
METHOD_SIG = re.compile(
    r'(?:public|private|protected)?\s*(?:static\s+)?[^\s]+\s+([A-Za-z_]\w*)\s*\(\s*(.*?)\s*\)',
    re.MULTILINE | re.DOTALL
)

def normalize_signature(name, params):
    params = re.sub(r'\s+', ' ', params.strip())
    if not params:
        return f"{name}()"

    types = []
    for p in params.split(','):
        p = p.strip()
        if not p:
            continue

        t = re.sub(r'^(?:final|var|@[\w.]+)\s+', '', p)
        t = t.split()[0]
        types.append(t)
    return f"{name}({','.join(types)})"

def _jml_block_to_lines(block_text: str):

    inner = block_text.strip()
    stmts = []
    buf = ""
    depth = 0


    for ch in inner:
        if ch == '(':
            depth += 1
        elif ch == ')':
            depth = max(0, depth - 1)
        elif ch == ';' and depth == 0:
            if buf.strip():
                stmts.append(buf.strip())
            buf = ""
            continue
        buf += ch
    if buf.strip():
        stmts.append(buf.strip())

    lines = []
    for s in stmts:

        s = re.sub(r'^\s*@\s*', '', s)

        if not s.endswith(';'):
            s += ';'


        s = re.sub(r'(\n\s*)@', r'\1//@', s)

        s = re.sub(r'(\n\s*)(?=[!&|\(\\])', r'\1//@ ', s)

        lines.append(f"//@ {s}")


    formatted = []
    for ln in lines:
        if "\n" in ln:
            parts = ln.split("\n")
            first = parts[0]
            rest = [
                ((" " * 3) + p if not p.strip().startswith("//@") else p)
                for p in parts[1:]
            ]
            formatted.append("\n".join([first] + rest))
        else:
            formatted.append(ln)

    return formatted

def preprocess_java_code(java_code: str) -> str:

    java_code = java_code.replace('}for', '}\nfor')
    java_code = java_code.replace('} while', '}\nwhile')
    java_code = java_code.replace(';for', ';\nfor')
    java_code = java_code.replace(';while', ';\nwhile')
    java_code = re.sub(r'\n{2,}', '\n', java_code)
    return java_code

def extract_specs(java_code: str):

    specs = {}
    java_code = preprocess_java_code(java_code)
    class_blocks = re.split(r'(?=public\s+class\s+|class\s+)', java_code)

    blk_pat = re.compile(r'/\*@([\s\S]*?)(?:@\*/|\*/)', re.DOTALL)  # 兼容 @*/ 与 */
    for cls_code in class_blocks:
        for m in METHOD_SIG.finditer(cls_code):
            method, params = m.group(1), m.group(2)
            sig = normalize_signature(method, params)

            prefix = cls_code[:m.start()]
            post_body = cls_code[m.end():]

            head_specs = []

            for blk in blk_pat.finditer(prefix):
                head_specs.extend(_jml_block_to_lines(blk.group(1)))

            comment_block = []
            for line in prefix.splitlines()[::-1]:
                if line.strip().startswith("//@"):
                    comment_block.insert(0, line.rstrip())
                elif comment_block:
                    break
            if comment_block:
                head_specs.extend(comment_block)

            # ---------- 方法体 ----------
            brace, body_part = 0, []
            for i, ch in enumerate(post_body):
                body_part.append(ch)
                if ch == '{':
                    brace += 1
                elif ch == '}':
                    brace -= 1
                    if brace == 0 and i > 0:
                        break
            body_str = preprocess_java_code(''.join(body_part))
            body_lines = body_str.splitlines()


            loop_specs = []
            for idx, line in enumerate(body_lines):
                if re.match(r'\s*(for|while)\s*\(', line):
                    cond_m = re.search(r'\(([^)]*)\)', line)
                    cond_text = cond_m.group(1).strip() if cond_m else ""


                    block = []
                    j = idx - 1
                    while j >= 0:
                        s = body_lines[j].strip()
                        if s.startswith("//@"):
                            block.insert(0, body_lines[j])
                            j -= 1
                        elif "*/" in s:  
                            blk_lines = []
                            while j >= 0 and "/*@" not in body_lines[j]:
                                blk_lines.insert(0, body_lines[j])
                                j -= 1
                            if j >= 0 and "/*@" in body_lines[j]:
                                blk_lines.insert(0, body_lines[j])
                                j -= 1
                            block = blk_lines + block
                        else:
                            break


                    expanded = []
                    raw = "\n".join(block)
                    for b in blk_pat.finditer(raw):
                        expanded.extend(_jml_block_to_lines(b.group(1)))
                    expanded.extend([l for l in block if l.strip().startswith("//@")])
                    expanded = list(dict.fromkeys(expanded))


                    assume_lines = []
                    k = idx + 1
                    while k < len(body_lines) and body_lines[k].strip().startswith("//@"):
                        if "assume" in body_lines[k]:
                            assume_lines.append(body_lines[k].strip())
                        else:
                            break
                        k += 1
                    assume_lines = list(dict.fromkeys(assume_lines))

                    loop_specs.append((cond_text, "\n".join(expanded), "\n".join(assume_lines)))


            inline_specs = []  
            seen_inline = set() 
            def next_code_after(start_i: int) -> str:
                k = start_i + 1
                while k < len(body_lines):
                    t = body_lines[k].strip()
                    if not t or t.startswith("//") or "/*@" in t or "@*/" in t or t.endswith("*/"):
                        k += 1
                        continue
                    return t
                return ""


            for idx, line in enumerate(body_lines):
                s = line.strip()
                if s.startswith("//@") and re.search(r'\b(assume|assert)\b', s):
                    if s in seen_inline:
                        continue
                    seen_inline.add(s)
                    anchor = next_code_after(idx)
                    inline_specs.append((anchor, s))


            for idx, line in enumerate(body_lines):
                for mm in blk_pat.finditer(line):
                    conv = _jml_block_to_lines(mm.group(1))
                    conv = [ln for ln in conv if re.search(r'\b(assume|assert)\b', ln)]
                    for ln in conv:
                        if ln in seen_inline:
                            continue
                        seen_inline.add(ln)
                        anchor = next_code_after(idx)
                        inline_specs.append((anchor, ln))

            specs[sig] = [{
                "head": head_specs,
                "loops": loop_specs,
                "inline": inline_specs
            }]

    return specs



def get_indent2(line): 
    return re.match(r'\s*', line).group(0)

def indent_block2(block: str, base_indent: str, extra_indent: int = 0):
    indent = base_indent + " " * extra_indent
    return "\n".join(
        indent + ln.strip() for ln in block.splitlines() if ln.strip()
    )

def best_match_loop(loop_cond, main_loops):
    best, score = None, 0.0
    for cond in main_loops:
        s = SequenceMatcher(None, loop_cond, cond).ratio()
        if s > score:
            score, best = s, cond
    return best if score > 0.4 else None  

def get_indent(line): 
    return re.match(r'\s*', line).group(0)

def indent_block(block: str, base_indent: str, extra_indent: int = 0):
    indent = base_indent + " " * extra_indent
    return "\n".join(indent + ln.strip() for ln in block.splitlines() if ln.strip())

def _split_loop_key(key_text):

    cond, pre, post = key_text, "", ""
    if "#PRE:" in key_text:
        cond, rest = key_text.split("#PRE:", 1)
        pre = rest
        if "#POST:" in rest:
            pre, post = rest.split("#POST:", 1)
    return cond.strip(), pre.strip(), post.strip()

def layer_merge(main_code: str, subs_list):

    parsed_subs = [
        extract_specs(subs) if isinstance(subs, str) else subs for subs in subs_list
    ]
    lines = main_code.splitlines()
    out, i = [], 0

    while i < len(lines):
        line = lines[i]
        m = METHOD_SIG.search(line)
        if not m:
            out.append(line)
            i += 1
            continue

        method, params = m.group(1), m.group(2)
        sig = normalize_signature(method, params)


        all_variants = []
        for subs in parsed_subs:
            if sig in subs:
                all_variants.extend(subs[sig])


        if all_variants:
            base_indent = get_indent(line)
            formatted_heads = []
            for variant in all_variants:
                block = "\n".join(h for h in variant["head"] if h.strip())
                if not block.strip():
                    continue
                if formatted_heads:
                    formatted_heads.append(f"{base_indent}    //@ also")
                formatted_heads.append(indent_block(block, base_indent, 4))
            if formatted_heads:
                out.extend("\n".join(formatted_heads).splitlines())


        method_lines, brace, start_i = [], 0, i
        while i < len(lines):
            method_lines.append(lines[i])
            brace += lines[i].count("{") - lines[i].count("}")
            if brace == 0 and i != start_i:
                break
            i += 1
        body_lines = method_lines[:]  # 可变


        merged_inlines = []
        seen_blocks = set()
        for v in all_variants:
            for anchor, block in v["inline"]:
                key = block.strip() 
                if key not in seen_blocks:
                    merged_inlines.append((anchor, block))
                    seen_blocks.add(key)


        for variant in all_variants:
            loops = variant["loops"]


            def _collect_loops(lines_):
                pos = []
                for idx_, l_ in enumerate(lines_):
                    if re.match(r'\s*(for|while)\s*\(', l_):
                        cm = re.search(r'\(([^)]*)\)', l_)
                        pos.append((idx_, cm.group(1).strip() if cm else ""))  # (行号, 条件)
                return pos

            for cond, pre_spec, assume in loops:
                loop_pos = _collect_loops(body_lines)
                if not loop_pos:
                    continue


                best_cond, best_score = None, 0.0
                for _, cond2 in loop_pos:
                    s = SequenceMatcher(None, cond, cond2).ratio()
                    if s > best_score:
                        best_score, best_cond = s, cond2
                if best_cond is None:
                    continue

                tgt_idx = next((idx_ for idx_, c_ in loop_pos if c_ == best_cond), None)
                if tgt_idx is None:
                    continue

                base_indent = get_indent(body_lines[tgt_idx])


                insert_block = indent_block(pre_spec, base_indent, 4)
                if insert_block.strip():
                    body_lines[tgt_idx:tgt_idx] = insert_block.splitlines()
                    tgt_idx += len(insert_block.splitlines())


                if assume.strip():
                    brace_line_idx = tgt_idx
                    if '{' not in body_lines[brace_line_idx]:
                        j2 = brace_line_idx + 1
                        while j2 < len(body_lines) and '{' not in body_lines[j2]:
                            j2 += 1
                        brace_line_idx = j2 if j2 < len(body_lines) else brace_line_idx
                    assume_block = indent_block(assume, base_indent, 4)
                    insert_at = brace_line_idx + 1
                    body_lines[insert_at:insert_at] = assume_block.splitlines()

 
        def _best_anchor_line(anchor_text: str, lines_, threshold=0.70):
            if not anchor_text or not anchor_text.strip():
                return None
            best_i, best_s = None, 0.0
            tgt = anchor_text.strip()
            for idx_, ln_ in enumerate(lines_):
                s = SequenceMatcher(None, tgt, ln_.strip()).ratio()
                if s > best_s:
                    best_s, best_i = s, idx_
            return best_i if best_s >= threshold else None

        def _fallback_for_return(lines_):

            last = None
            for idx_, ln_ in enumerate(lines_):
                if re.search(r'\breturn\b', ln_):
                    last = idx_
            return last

        def _fallback_for_first_loop(lines_):
            for idx_, ln_ in enumerate(lines_):
                if re.match(r'\s*(for|while)\s*\(', ln_):
                    return idx_
            return None

        def _fallback_first_code(lines_):

            for idx_, ln_ in enumerate(lines_):
                t = ln_.strip()
                if idx_ == 0:
                    continue
                if t and not t.startswith("//"):
                    return idx_
            return None


        for anchor_text, jml_block in merged_inlines:
            pos = _best_anchor_line(anchor_text, body_lines, threshold=0.70)


            if pos is None:
                at = (anchor_text or "").strip()
                if re.search(r'\breturn\b', at):
                    pos = _fallback_for_return(body_lines)
                if pos is None and re.search(r'\b(for|while)\b', at):
                    pos = _fallback_for_first_loop(body_lines)
                if pos is None:
                    pos = _fallback_first_code(body_lines)

            if pos is not None:
                base_indent = get_indent(body_lines[pos])
                insert_block = indent_block(jml_block, base_indent, 4)

                block_lines = insert_block.splitlines()
                window = "\n".join(body_lines[max(0, pos-5):pos+5])
                if all(bl.strip() and bl.strip() not in window for bl in block_lines):
                    body_lines[pos:pos] = block_lines


        out.extend(body_lines)
        i += 1

    return "\n".join(out)
