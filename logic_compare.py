import re
from pathlib import Path
from z3 import *
from difflib import SequenceMatcher
import json
import os

JOINER = ";"  


def preprocess_code(code: str):
    code = re.sub(r'/\*(?!@)[\s\S]*?\*/', '', code)
    code = re.sub(r'//(?!@).*', '', code)
    return code

def extract_methods_with_jml(java_code: str, debug=False):
    import re
    lines = java_code.splitlines()
    block_map = {}
    in_block = False
    buf = []
    for i, line in enumerate(lines):
        if not in_block and re.search(r'/\*@', line):
            in_block = True
            buf = [line]
            continue
        if in_block:
            buf.append(line)
            if "@*/" in line:
                block_text = "\n".join(buf)
                block_text = block_text.replace('@{|', '').replace('@|}', '')
                out = []
                for raw in block_text.splitlines():
                    s = raw.lstrip().lstrip('*').strip()
                    if s.startswith('@'):
                        s = s[1:].lstrip()
                    if any(s.startswith(k) for k in ('requires', 'ensures', 'also', 'old ', 'behavior', 'normal_behavior', 'pure')):
                        if s.startswith(('requires', 'ensures', 'also', 'old ')):
                            out.append(f"//@ {s}")
                if out:
                    block_map[i] = out
                in_block = False
                buf = []
                continue

    def find_nearest_block(end_line_idx: int):
        prev_keys = [k for k in block_map.keys() if k < end_line_idx]
        if not prev_keys:
            return None
        return block_map[max(prev_keys)]


    results = {}
    jml_buffer = []

    for i, line in enumerate(lines):
        stripped = line.strip()
        if stripped.startswith("//@"):
            if debug:
                print(f"[DEBUG] get line of JML {i+1}: {stripped}")
            jml_buffer.append(stripped)
            continue
        if re.search(r'\b(public|private|protected)?\s*(static\s+)?[A-Za-z_\[\]<>]+\s+[A-Za-z_]\w*\s*\(.*\)', stripped):
            m = re.search(r'([A-Za-z_]\w*)\s*\((.*?)\)', stripped)
            if not m:
                continue
            sig = f"{m.group(1)}({m.group(2).strip()})"
            if debug:
                print(f"[DEBUG] pairs {i+1}: {sig}")

            block_lines = find_nearest_block(i)
            merged_block = []
            if block_lines:
                merged_block.extend(block_lines)

            tail_seq = []
            for l in reversed(jml_buffer):
                if l.strip().startswith("//@"):
                    tail_seq.insert(0, l)
                else:
                    break
                
            
            merged_block.extend(tail_seq)

            if merged_block:
                if debug:
                    print(f"[DEBUG] get {len(merged_block)} JML lines before method {sig}")
                cond_map = parse_jml_block(merged_block, debug)
                results[sig] = cond_map
                if debug:
                    print(f"[DEBUG] get {len(cond_map)} Conditions {sig}")
            else:
                if debug:
                    print(f"[DEBUG] Method{sig} No JML")

            jml_buffer = []  
    return results

def parse_jml_block(jml_lines, debug=False):
    cleaned = []
    for line in jml_lines:
        line = re.sub(r'^\s*(//@|/\*@|@|\*+/?)+\s*', '', line)
        line = line.replace('*/', '').strip()
        if line:
            cleaned.append(line)

    if debug:
        print(f"[DEBUG] parse_jml_block original: {jml_lines}")
        print(f"[DEBUG] parse_jml_block cleaned: {cleaned}")

    condition_result_map = {}
    current_requires = []  

    def add_case(require_key, result_value):
        if require_key not in condition_result_map:
            condition_result_map[require_key] = []
        condition_result_map[require_key].append(result_value)

    for line in cleaned:
        # -------- handle also --------
        if line.startswith("also"):
            current_requires = []
            continue

        # -------- handle requires --------
        if line.startswith("requires"):
            expr = line[len("requires"):].strip(" ;")
            if expr == "":
                expr = "true"
            current_requires = [expr]
            continue

        # -------- handle ensures --------
        if line.startswith("ensures"):
            expr = line[len("ensures"):].strip(" ;")

            if "<==>" in expr:
                left, right = expr.split("<==>", 1)
            elif "==>" in expr:
                left, right = expr.split("==>", 1)
            else:
                left, right = expr, None

            left = left.strip()
            right = right.strip() if right else None

            if left.startswith("(") and left.endswith(")"):
                left = left[1:-1].strip()
            if right and right.startswith("(") and right.endswith(")"):
                right = right[1:-1].strip()

            if right:
                if "\\result" in right:
                    precond = left
                    postcond = right
                elif "\\result" in left:
                    precond = right
                    postcond = left
                else:
                    precond = left
                    postcond = right
            else:
                precond = "true"
                postcond = left

            if current_requires:
                for r in current_requires:
                    add_case(r, postcond)
            else:
                add_case(precond, postcond)

    if not condition_result_map:
        condition_result_map["true"] = ["true"]

    if debug:
        print(f"[DEBUG] Final condition_result_map: {json.dumps(condition_result_map, indent=2)}")

    return condition_result_map


def parse_numeric_expr(expr):
    pattern = r'(\\result)\s*(==|!=|>=|<=|>|<)\s*([\-]?\d+(\.\d+)?|\w+)'

    def parse_single(e):
        if not isinstance(e, str):
            return None
        e = e.strip()
        m = re.match(pattern, e)
        if m:
            var, op, val = m.group(1), m.group(2), m.group(3)
            try:
                val = float(val)
            except ValueError:
                pass  
            return var, op, val
        return None

    if isinstance(expr, list):
        results = [parse_single(e) for e in expr]
        return [r for r in results if r is not None] or None

    return parse_single(expr)

def normalize_expr(expr):
    if isinstance(expr, list):

        return [normalize_expr(e) for e in expr]

    if not isinstance(expr, str):
        return str(expr)

    expr = expr.strip()
    expr = expr.replace("(", "").replace(")", "")
    expr = re.sub(r'\\+', r'\\', expr) 
    expr = re.sub(r'\s+', ' ', expr)   
    expr = expr.replace(';', '').strip()
    return expr
def compare_numeric_logic(v1, v2):
    if not isinstance(v1, list):
        v1 = [v1]
    if not isinstance(v2, list):
        v2 = [v2]

    p1_list = [p for p in (parse_numeric_expr(x) for x in v1) if p]
    p2_list = [p for p in (parse_numeric_expr(x) for x in v2) if p]

    if not p1_list or not p2_list:
        return None

    var1 = p1_list[0][0]
    var2 = p2_list[0][0]
    if var1 != var2:
        return None

    def normalize(op, val):
        if op == ">":  return (">=", val + 1e-9) if isinstance(val, (int, float)) else (">=", val)
        if op == "<":  return ("<=", val - 1e-9) if isinstance(val, (int, float)) else ("<=", val)
        return (op, val)

    v1_min, v1_max = -float('inf'), float('inf')
    v2_min, v2_max = -float('inf'), float('inf')

    def update_bounds(op, val, cur_min, cur_max):
        if not isinstance(val, (int, float)):
            return cur_min, cur_max  
        if op in (">=", ">"):
            cur_min = max(cur_min, val)
        elif op in ("<=", "<"):
            cur_max = min(cur_max, val)
        elif op == "==":
            cur_min = cur_max = val
        return cur_min, cur_max

    for _, op, val in p1_list:
        op, val = normalize(op, val)
        v1_min, v1_max = update_bounds(op, val, v1_min, v1_max)

    for _, op, val in p2_list:
        op, val = normalize(op, val)
        v2_min, v2_max = update_bounds(op, val, v2_min, v2_max)

    if v1_min == -float('inf') and v1_max == float('inf') and v2_min == -float('inf') and v2_max == float('inf'):
        return None
    if v1_min >= v2_min and v1_max <= v2_max:
        if v1_min == v2_min and v1_max == v2_max:
            return "same"
        else:
            return "v1_better"

    if v2_min >= v1_min and v2_max <= v1_max:
        if v1_min == v2_min and v1_max == v2_max:
            return "same"
        else:
            return "v2_better"

    return "unknown"
def compare_numeric_logic_pre(v1, v2):
    if not isinstance(v1, list):
        v1 = [v1]
    if not isinstance(v2, list):
        v2 = [v2]

    p1_list = [p for p in (parse_numeric_expr(x) for x in v1) if p]
    p2_list = [p for p in (parse_numeric_expr(x) for x in v2) if p]

    if not p1_list or not p2_list:
        return None

    var1 = p1_list[0][0]
    var2 = p2_list[0][0]
    if var1 != var2:
        return None

    def normalize(op, val):
        if op == ">":  return (">=", val + 1e-9) if isinstance(val, (int, float)) else (">=", val)
        if op == "<":  return ("<=", val - 1e-9) if isinstance(val, (int, float)) else ("<=", val)
        return (op, val)

    v1_min, v1_max = -float('inf'), float('inf')
    v2_min, v2_max = -float('inf'), float('inf')

    def update_bounds(op, val, cur_min, cur_max):
        if not isinstance(val, (int, float)):
            return cur_min, cur_max  
        if op in (">=", ">"):
            cur_min = max(cur_min, val)
        elif op in ("<=", "<"):
            cur_max = min(cur_max, val)
        elif op == "==":
            cur_min = cur_max = val
        return cur_min, cur_max

    for _, op, val in p1_list:
        op, val = normalize(op, val)
        v1_min, v1_max = update_bounds(op, val, v1_min, v1_max)

    for _, op, val in p2_list:
        op, val = normalize(op, val)
        v2_min, v2_max = update_bounds(op, val, v2_min, v2_max)

    if v1_min == -float('inf') and v1_max == float('inf') and v2_min == -float('inf') and v2_max == float('inf'):
        return None

    if v1_min >= v2_min and v1_max <= v2_max:
        if v1_min == v2_min and v1_max == v2_max:
            return "same"
        else:
            return "v2_better"

    if v2_min >= v1_min and v2_max <= v1_max:
        if v1_min == v2_min and v1_max == v2_max:
            return "same"
        else:
            return "v1_better"

    return "unknown"

def canonicalize_commutative_expr(expr: str) -> str:
    if not isinstance(expr, str):
        return expr
    e = expr
    e = e.replace(" ", "")
    pattern = r'([A-Za-z_]\w*)\s*([+*])\s*([A-Za-z_]\w*)'
    def repl(m):
        a, op, b = m.group(1), m.group(2), m.group(3)
        ordered = sorted([a, b])
        return f"{ordered[0]}{op}{ordered[1]}"

    old = None
    while old != e:
        old = e
        e = re.sub(pattern, repl, e)

    return e

def z3_compare(expr1, expr2):
    expr1 = canonicalize_commutative_expr(normalize_expr(expr1))
    expr2 = canonicalize_commutative_expr(normalize_expr(expr2))

    result = Real('result')
    num = Real('num')

    def parse(e):
        e = e.replace("\\result", "result")
        return eval(e, {"result": result, "num": num, "And": And, "Or": Or, "Not": Not})

    try:
        a = parse(expr1)
        b = parse(expr2)
    except Exception:
        return "unknown"

    s = Solver()


    s.push()
    s.add(And(a, Not(b)))  
    if s.check() == unsat:
        s.pop()
        s.add(And(b, Not(a))) 
        if s.check() == unsat:
            return "same"
        else:
            return "v1_better"
    else:
        s.pop()
        s.add(And(b, Not(a)))
        if s.check() == unsat:
            return "v2_better"

    return "unknown"

def z3_compare_pre(expr1, expr2):
    expr1 = canonicalize_commutative_expr(normalize_expr(expr1))
    expr2 = canonicalize_commutative_expr(normalize_expr(expr2))

    result = Real('result')
    num = Real('num')

    def parse(e):
        e = e.replace("\\result", "result")
        return eval(e, {"result": result, "num": num, "And": And, "Or": Or, "Not": Not})

    try:
        a = parse(expr1)
        b = parse(expr2)
    except Exception:
        return "unknown"

    s = Solver()


    s.push()
    s.add(And(a, Not(b)))  
    if s.check() == unsat:
        s.pop()
        s.add(And(b, Not(a))) 
        if s.check() == unsat:
            return "same"
        else:
            return "v2_better"
    else:
        s.pop()
        s.add(And(b, Not(a)))
        if s.check() == unsat:
            return "v1_better"

    return "unknown"
COMM_PATTERNS = [
    (re.compile(r'([A-Za-z_]\w*)\s*\+\s*([A-Za-z_]\w*)'), '+'),
    (re.compile(r'([A-Za-z_]\w*)\s*\*\s*([A-Za-z_]\w*)'), '*'),
]

def normalize_commutative(atom: str) -> str:
    if not isinstance(atom, str):
        return atom
    s = atom.replace(' ', '')
    changed = True
    while changed:
        changed = False
        for pat, op in COMM_PATTERNS:
            def repl(m):
                a, b = m.group(1), m.group(2)
                lo, hi = sorted([a, b])
                return f"{lo}{op}{hi}"
            new_s = pat.sub(repl, s)
            if new_s != s:
                s = new_s
                changed = True
    return s

def split_conj(s: str) -> list[str]:
    if not isinstance(s, str):
        return []
    s = normalize_expr(s)
    parts = [p.strip() for p in s.split('&&')]
    parts = [p for p in parts if p]
    parts = [normalize_commutative(p) for p in parts]
    return parts

def to_atom_multiset(expr) -> list[str]:
    atoms = []
    if isinstance(expr, list):
        for e in expr:
            atoms.extend(split_conj(e))
    else:
        atoms.extend(split_conj(expr))
    return atoms

def parse_to_z3_bool(e: str):
    result = Real('result')
    num = Real('num')
    x = Real('x')
    y = Real('y')
    env = {
        "result": result, "num": num, "x": x, "y": y,
        "And": And, "Or": Or, "Not": Not, "BoolVal": BoolVal
    }

    if not isinstance(e, str):
        return BoolVal(True)

    e = e.strip()
    if not e:
        return BoolVal(True)
    e = e.replace("\\result", "result")
    e = e.replace("Integer.MIN_VALUE", "-2147483648").replace("Integer.MAX_VALUE", "2147483647")
    e = e.replace("&&", " and ").replace("||", " or ")

    try:
        val = eval(e, env)
        from z3 import ArithRef, BoolRef
        if isinstance(val, ArithRef):
            return val != 0
        if isinstance(val, BoolRef):
            return val
        return BoolVal(True)
    except Exception:
        return BoolVal(True)

def list_and_to_z3(expr):
    atoms = to_atom_multiset(expr)
    z3_atoms = []
    for a in atoms:
        z = parse_to_z3_bool(a)
        if z is None:
            return None
        z3_atoms.append(z)
    if not z3_atoms:
        return BoolVal(True)
    return And(*z3_atoms)


def z3_entail(e1, e2) -> str:
    z1 = list_and_to_z3(e1)
    z2 = list_and_to_z3(e2)
    if z1 is None or z2 is None:
        return "unknown"
    s = Solver()
    s.add(And(z1, Not(z2)))
    if s.check() == unsat:
        return "entails"
    else:
        return "not_entails"
def canonicalize_commutative_simple(expr: str) -> str:
    if not isinstance(expr, str):
        return expr
    e = expr
    e = re.sub(r'\s+', '', e)
    pat = re.compile(r'([A-Za-z_]\w*|\d+)\s*([+*])\s*([A-Za-z_]\w*|\d+)')
    def repl(m):
        a, op, b = m.group(1), m.group(2), m.group(3)
        if op in ['+', '*']:
            if a > b:
                a, b = b, a
        return f'{a}{op}{b}'
    old = None
    while old != e:
        old = e
        e = pat.sub(repl, e)

    return e

def strip_parens(s: str) -> str:
    s = s.strip()
    while s.startswith('(') and s.endswith(')'):
        depth = 0
        ok = True
        for i, ch in enumerate(s):
            if ch == '(':
                depth += 1
            elif ch == ')':
                depth -= 1
                if depth < 0:
                    ok = False
                    break
            if i < len(s)-1 and depth == 0:
                ok = False
                break
        if ok and depth == 0:
            s = s[1:-1].strip()
        else:
            break
    return s

def split_conjuncts(expr: str):
    if expr is None:
        return []
    if not isinstance(expr, str):
        return [str(expr)]
    e = expr.replace('AND', 'and').replace('And', 'and')
    e = e.replace('；', '&&')
    e = re.sub(r'\band\b', '&&', e)
    parts = [p.strip() for p in e.split('&&') if p.strip()]
    return parts

def normalize_atom(atom: str) -> str:
    a = strip_parens(atom)
    a = canonicalize_commutative_simple(a)
    a = re.sub(r'\s+', ' ', a).strip()


    m = re.match(r'^(.*?)(==|!=|<=|>=|<|>)(.*)$', a)
    if not m:

        t = a.strip().lower()
        if t == 'true':
            return 'true'  
        if t == 'false':
            return 'false'
        return canonicalize_commutative_simple(a)

    left, op, right = m.group(1).strip(), m.group(2), m.group(3).strip()
    left = strip_parens(left)
    right = strip_parens(right)
    left = canonicalize_commutative_simple(left)
    right = canonicalize_commutative_simple(right)

    def is_const(s):
        return re.fullmatch(r'-?\d+(\.\d+)?', s) is not None or s.lower() in {'true','false','integer.min_value','integer.max_value'}

    if op in ['==', '!=']:
        lr = sorted([left, right])
        return f'{lr[0]} {op} {lr[1]}'

    swap_map = {'<': '>', '>': '<', '<=': '>=', '>=': '<='}
    should_swap = False
    if is_const(left) and not is_const(right):
        should_swap = True
    elif not is_const(left) and not is_const(right):
        if left > right:
            should_swap = True
    elif is_const(left) and is_const(right):
        if left > right:
            should_swap = True

    if should_swap:
        op = swap_map.get(op, op)
        left, right = right, left

    return f'{left} {op} {right}'

def to_atom_multiset(expr):
    atoms = []
    if expr is None:
        return atoms

    if isinstance(expr, (list, tuple)):
        for e in expr:
            atoms.extend(to_atom_multiset(e))
        return atoms

    for a in split_conjuncts(expr):
        na = normalize_atom(a)
        if na.lower() == 'true' or na == '':
            continue
        atoms.append(na)
    return atoms
def z3_entail_conj(diffA_atoms, diffB_atoms):
    def vars_in_list(atoms):
        s = set()
        for a in atoms:
            s |= set(re.findall(r'\b[a-zA-Z_]\w*\b', a))
        return s - {"Integer", "MIN_VALUE", "MAX_VALUE", "result", "true", "false"}

    vars_all = vars_in_list(diffA_atoms) | vars_in_list(diffB_atoms)
    env = {v: Real(v) for v in vars_all}

    def eval_atom_list(atoms):
        zlist = []
        for a in atoms:
            s = a.strip()
            try:
                z = eval(s, {}, env)  
            except Exception:
                return None
            zlist.append(z)
        if not zlist:
            return None
        return And(*zlist)

    A = eval_atom_list(diffA_atoms)
    B = eval_atom_list(diffB_atoms)
    if A is None or B is None:
        return "unknown"

    s = Solver()
    s.push()
    s.add(And(A, Not(B)))
    if s.check() == unsat:
        s.pop()
        s.add(And(B, Not(A)))
        if s.check() == unsat:
            return "same"
        else:
            return "A_implies_B"  
    else:
        s.pop()
        s.add(And(B, Not(A)))
        if s.check() == unsat:
            return "B_implies_A"  
    return "unknown"
def logic_compare(expr1, expr2, mode: str) -> str:

    A = sorted(to_atom_multiset(expr1))
    B = sorted(to_atom_multiset(expr2))

    if sorted(A) == sorted(B):
        return "same"

    setA, setB = set(A), set(B)
    common = setA & setB
    diffA = list(setA - common)
    diffB = list(setB - common)

  
    if not diffA and not diffB:
        return "same"


    def vars_in_list(atoms):
        s = set()
        for a in atoms:
            s |= extract_vars(a)
        return s

    varsA = vars_in_list(diffA)
    varsB = vars_in_list(diffB)

    print(f"differrnt part A: is {diffA}")
    print(f"differrnt part B: is {diffB}")
    #if varsA != varsB:
        #return "unknown"

    if len(diffA) != len(diffB):

        if mode == "post":
            if len(diffA) > len(diffB):
                return "v1_better"
            else:
                return "v2_better"
        else: 
            if len(diffA) > len(diffB):
                return "v2_better"  
            else:
                return "v1_better"  

    entail = z3_entail_conj(diffA, diffB)
    if entail == "same":
        return "same"
    if entail == "A_implies_B":  
        if mode == "post":
            return "v1_better"
        else:  
            return "v2_better"
    if entail == "B_implies_A":  
        if mode == "post":
            return "v2_better"
        else:
            return "v1_better"
    if entail == "unknown":  
        if mode == "post":
            return "same"
        else:
            return "same"

    return "unknown"


def compare_values(v1, v2):
    return logic_compare(v1, v2, mode="post")
def compare_pre(v1, v2):
    return logic_compare(v1, v2, mode="pre")

def split_condition(cond):
    if cond.strip() == "true":
        return []
    cond = cond.replace("&&", "；") 
    parts = [p.strip() for p in cond.split("；") if p.strip()]
    #print(parts)
    return parts

def extract_vars(expr):
    if not isinstance(expr, str):
        return set()
    tokens = re.findall(r'[A-Za-z_]\w*', expr)
    exclude = {"Integer", "MIN_VALUE", "MAX_VALUE", "true", "false"}
    return {t for t in tokens if t not in exclude}

def vars_overlap(a, b):
    va, vb = extract_vars(a), extract_vars(b)
    return bool(va & vb)  

def condition_includes(a, b):


    def has_and(expr):
        return "&&" in expr

    def has_or(expr):
        return "||" in expr

    if has_and(a) or has_and(b):
        A_atoms = set(split_conjuncts(a))
        B_atoms = set(split_conjuncts(b))
        return A_atoms == B_atoms

    A_parts = set(split_condition(a))
    B_parts = set(split_condition(b))

    if not B_parts:
        return True
    if not A_parts:
        return True

    if B_parts.issubset(A_parts):
        return True

    for sub_a in A_parts:
        for sub_b in B_parts:
            if vars_overlap(sub_a, sub_b):
                return True

    try:
        solver = Solver()
        x, y = Reals('x y')
        expr_a = a.replace("&&", " and ").replace("||", " or ")
        expr_b = b.replace("&&", " and ").replace("||", " or ")
        expr_a = expr_a.replace("Integer.MIN_VALUE", "-2147483648").replace("Integer.MAX_VALUE", "2147483647")
        expr_b = expr_b.replace("Integer.MIN_VALUE", "-2147483648").replace("Integer.MAX_VALUE", "2147483647")

        env = {"x": x, "y": y, "And": And, "Or": Or, "Not": Not}
        fa = eval(expr_a, env)
        fb = eval(expr_b, env)

        solver.add(And(fa, Not(fb)))
        if solver.check() == unsat:
            return True
    except Exception:
        pass

    return False
def normalize_method_name(name: str) -> str:
    name = name.strip()
    name = re.sub(r'\s+', '', name)
    name = name.replace('；', ';')

    name = re.sub(r'\b(int|long|double|boolean|char|float|String)\b', '', name, flags=re.IGNORECASE)
    name = name.replace(',,', ',')  
    name = re.sub(r'\(,', '(', name)
    name = re.sub(r',\)', ')', name)
    name = name.lower()
    return name

def find_similar_method(method, spec2, threshold=1):
    norm_m = normalize_method_name(method)
    best_match = None
    best_score = 0
    for m2 in spec2.keys():
        norm_m2 = normalize_method_name(m2)
        score = SequenceMatcher(None, norm_m, norm_m2).ratio()
        if score > best_score:
            best_score = score
            best_match = m2
    return best_match if best_score >= threshold else None

def compare_methods(spec1, spec2):
    results = {}
    results2 = {}
    checked_pairs_con = set()
    checked_pairs_me = set()
    used_conds_2 = set() 
    con_pairs=0
    for method, cond_res1 in spec1.items():
        match_method = find_similar_method(method, spec2)
        matched = False
        for cond1, val1 in cond_res1.items():
            post_condition_comparisons = []
            if not match_method:
                post_condition_comparisons.append({"condition": f'{cond1} ⇔ spec2_empty',"file1_result": val1,'post_condition_comparison':'v1_better','pre_condition_comparison':'v1_better'})
                con_pairs +=1
                results[f"{method};{cond1}"] = post_condition_comparisons
                print(f"this is the spec1:{spec1}")
                print(f"this is the spec2:{spec2}")
            else:
                matched = True
                cond_res2 = spec2[match_method]
                if len(cond_res1) <= 1:
                    var2_total=[]
                    #con_total=[]
                    con_total=''
                    for cond2, val2 in cond_res2.items():
                        #con_total += " || " + cond2
                        #con_total += " && " + cond2
                        #con_total.append(cond2)
                        var2_total = var2_total + val2
                    con_total=cond1
                    comp1 = compare_values(val1, var2_total)
                    comp2 = compare_pre(cond1, con_total)
                    post_condition_comparisons.append({
                                    "condition": f"{cond1} ⇔ {con_total}",
                                    "file1_result": val1,
                                    "file2_result": var2_total,
                                    "post_condition_comparison": comp1,
                                    "pre_condition_comparison": comp2
                                })
                    con_pairs +=1
                    results[f"{method};{cond1}"] = post_condition_comparisons
                else:
                    var2_total=[]
                    con_total=''
                    #con_total=[]
                    for cond2, val2 in cond_res2.items():                 
                        if (condition_includes(cond1, cond2) or condition_includes(cond2, cond1)) and f"{match_method};{cond2}" not in used_conds_2:
                            print(f"this is the original cond1:{cond1}")
                            print(f"this is the original cond2:{cond2}")
                            var2_total = var2_total + val2
                            used_conds_2.add(f"{method};{cond2}") 
                            checked_pairs_con.add(f"{method};{cond2}")
                            con_total += " || " + cond2
                            #con_total.append(cond2)
                    print(f"this is the final cond2:{con_total}")
                    #con_total = cond1
                    comp1 = compare_values(val1, var2_total)
                    comp2 = compare_pre([cond1], con_total)
                    post_condition_comparisons.append({
                        "condition": f"{cond1} ⇔ {con_total}",
                        "file1_result": val1,
                        "file2_result": var2_total,
                        "post_condition_comparison": comp1,
                        "pre_condition_comparison": comp2
                            })
                    con_pairs +=1
                    results[f"{method};{cond1}"] = post_condition_comparisons
    
    for method2, cond_res2 in spec2.items():
        for cond2, val2 in cond_res2.items():
            spec_condition_comparisons=[]
            if f"{method2};{cond2}" not in checked_pairs_con:
                spec_condition_comparisons.append({
                    "condition": cond2,
                    "file1_result": "empty",
                    "file2_result": val2,
                    "post_condition_comparison": "v2_better",
                    "pre_condition_comparison": "v2_better",
                })
                results2[f"{method2};{cond2}"]=spec_condition_comparisons
    return results,results2,con_pairs


    

if __name__ == "__main__":
    wrong_list=['BubbleSort.java', 'IsAscending.java', 'ExSymExe12.java', 'BinarySearch.java', 'loops_2.java', 'ExSymExeI2D.java', 'LargestPerimeter.java', 'search.java', 'array_find.java', 'ExSymExe9.java', 'array_max_advanced.java', 'ReverseString.java', 'DivisorGame.java', 'ExSymExe7.java', 'ExSymExe20.java', 'ExSymExeLongBytecodes.java', 'PrimeNumbers.java', 'ExSymExe6.java', 'loops_1.java', 'ExSymExe19.java', 'ExGenSymExe.java', 'LinearSearch.java', 'ExSymExeI2F.java', 'mult.java', 'ExSymExe15.java', 'RepeatedChar.java', 'DominantIndex.java', 'ExSymExe27.java', 'incr_a_by_b.java', 'Calculator.java', 'IsAllUnique.java', 'array_swap.java', 'BubbleSortDesc.java', 'search_2.java', 'ExSymExe2.java', 'ArraysAndLoops5.java', 'ExSymExe5.java', 'ArraysAndLoops4.java', 'ExSymExe13.java', 'ConvertToTitle.java', 'MyPower.java', 'ExSymExe14.java', 'PrimeCheck.java', 'ExSymExe4.java', 'ExSymExe10.java', 'IsOneBitCharacter.java', 'ExSymExeSimple.java', 'IsSuffix.java', 'loops_3.java', 'ExSymExeGetStatic.java', 'ExSymExeArrays.java', 'CopyArray.java', 'SetZero.java', 'increment_arr.java', 'IsMonotonic.java', 'ArraysAndLoops2.java', 'TransposeMatrix.java', 'ExSymExe18.java', 'ExSymExe8.java', 'IntCube.java', 'NextGreaterElem.java', 'reverse_array.java', 'ExSymExeF2L.java', 'ExSymExe1.java', 'ExSymExe11.java', 'OddEven.java', 'array_double.java', 'RotateArray.java', 'CanWinNim.java', 'check_evens_in_array.java', 'Fibonacci.java', 'ExSymExeBool.java', 'Perimeter.java', 'ExSymExe3.java', 'MaxInArray.java', 'CompareArray.java', 'UglyNum.java', 'ExSymExe17.java', 'SelectionSort.java', 'UniqueNumNested.java', 'SelectionSortDesc.java', 'replace_evens.java', 'LCM.java', 'sample.java', 'ExSymExe21.java', 'ExSymExe26.java', 'equal_arrays.java', 'bubble_sort.java', 'ExSymExeResearch.java', 'RepeatedNumNested.java', 'StrPalindrome.java', 'FindClosestNum.java']
    wrong_list_g5=['BubbleSort.java', 'IsAscending.java', 'ExSymExeD2L.java', 'absolute_value.java', 'BinarySearch.java', 'loops_2.java', 'IsDescending.java', 'ComputeOverlapBranch.java', 'SmallestEvenMulBranch.java', 'PrimeNumbers.java', 'ComputeAreaBranch.java', 'UniqueCharNested.java', 'ExGenSymExe.java', 'PowerOfThree.java', 'ExSymExe15.java', 'RepeatedChar.java', 'IsBoomerang.java', 'ExSymExeComplexMath.java', 'IsSubsequence.java', 'ComputeArea.java', 'BubbleSortDesc.java', 'ExSymExe2.java', 'JewelsInStones.java', 'ExSymExe5.java', 'ConvertToTitle.java', 'PrimeCheck.java', 'GCD.java', 'UglyNumLoop.java', 'IsOneBitCharacter.java', 'MoveZeroes.java', 'ExSymExeGetStatic.java', 'PowerOfTwo.java', 'Conjunction.java', 'ExSymExeArrays.java', 'IsMonotonic.java', 'ChangeCase.java', 'ContainsDuplicate.java', 'TransposeMatrix.java', 'ExSymExe18.java', 'div_rem.java', 'NextGreaterElem.java', 'PassPillow.java', 'reset_1st.java', 'MySqrt.java', 'reverse_array.java', 'RotateArray.java', 'MaxInArray.java', 'ThreeConsecutiveOdds.java', 'SelectionSort.java', 'UniqueNumNested.java', 'SelectionSortDesc.java', 'replace_evens.java', 'LCM.java', 'ExSymExe21.java', 'ExSymExe26.java', 'bubble_sort.java', 'StrPalindrome.java']
    groundtruth_wrong=['add_pointers.java', 'ExSymExe9.java', 'ExSymExe7.java', 'DigitRoot.java', 'PrimeNumbers.java', 'ExMIT.java', 'add.java', 'ComputeAreaBranch.java', 'UniqueCharNested.java', 'PowerOfThree.java', 'LinearSearch.java', 'ExSymExeI2F.java', 'ConvertTemperature.java', 'mult.java', 'IsCommonFactor.java', 'ExSymExe15.java', 'RepeatedChar.java', 'IsBoomerang.java', 'ExSymExeComplexMath.java', 'incr_a_by_b.java', 'ExSymExe29.java', 'IsSubsequence.java', 'Absolute.java', 'ExSymExe2.java', 'ReverseArray.java', 'triangle_sides.java', 'ArraysAndLoops5.java', 'ExSymExe5.java', 'ArraysAndLoops4.java', 'MulNested.java', 'ExSymExe13.java', 'ExSymExe16.java', 'MyPower.java', 'ExSymExe14.java', 'PrimeCheck.java', 'ExSymExe10.java', 'GCD.java', 'PowerOfFourLoop.java', 'NumberOfCuts.java', 'ComputeOverlap.java', 'ExSymExeGetStatic.java', 'PowerOfTwo.java', 'Conjunction.java', 'IsCommonMultiple.java', 'ExSymExeArrays.java', 'CopyArray.java', 'max_of_2.java', 'increment_arr.java', 'IsMonotonic.java', 'ReLU.java', 'ContainsDuplicate.java', 'TransposeMatrix.java', 'ExSymExe18.java', 'ExSymExe8.java', 'div_rem.java', 'reset_1st.java', 'MySqrt.java', 'reverse_array.java', 'ExSymExe1.java', 'array_double.java', 'Abs.java', 'order_3.java', 'RotateArray.java', 'ExSymExeF2I.java', 'check_evens_in_array.java', 'Fibonacci.java', 'Perimeter.java', 'MaxInArray.java', 'ReLUSeq.java', 'FindFirstZero.java', 'ExSymExeSuzette.java', 'ThreeConsecutiveOdds.java', 'ExSymExe17.java', 'UniqueNumNested.java', 'replace_evens.java', 'LCM.java', 'bubble_sort.java', 'ExSymExeResearch.java']
    oracle = " " #groundtruth folder
    source_dir = " " #LLM generated spec folder

    compare_result = {}
    empty_file = []
    failed_file = []
    empty = 0


    total_pairs1 = 0  # pairs in groundtruth
    total_pairs2 = 0  # Total pairs in llm generated specification
    total_pairs3 = 0  # 
    actual_count_pair = 0


    v1_better_or_same_num = 0
    v2_better_or_same_num = 0
    mixed_pair_num = 0

    program_v1_better_or_same_num = 0
    program_v2_better_or_same_num = 0
    program_mixed_pair_num = 0
    for root, _, files in os.walk(oracle):
        for file in files:
            if file.endswith('.java') and file not in groundtruth_wrong:
                print("This is file name", file)  
                ##count ground truth pairs
                spec1_java_file = os.path.join(root, file)
                code1 = preprocess_code(Path(spec1_java_file).read_text(encoding="utf-8", errors="ignore"))
                count_pair1 = 0
                spec1 = extract_methods_with_jml(code1, debug=True)
                print("Spec in file1", spec1)   
                for key, value in spec1.items():
                    for key, val in value.items():
                        count_pair1 += 1
                        print(count_pair1)
                total_pairs1+=count_pair1
               
                if file not in wrong_list:
                #if file not in wrong_list_g5:
                #if anything:
                    spec2_java_file = os.path.join(source_dir, file)
                    if os.path.isfile(spec2_java_file):
                        code2 = preprocess_code(Path(spec2_java_file).read_text(encoding="utf-8", errors="ignore"))
                        count_pair2 = 0
                        spec2 = extract_methods_with_jml(code2, debug=True)  
                        for key, value in spec1.items():
                            for key, val in value.items():
                                count_pair2 += 1
                                #print(count_pair2)
                        total_pairs2+=count_pair2
                        count_pair3 =0 
                        for key, value in spec2.items():
                            for key, val in value.items():
                                count_pair3 += 1
                                #print(count_pair2)
                        total_pairs3+=count_pair3
                            
                        result,results2,con_pairs= compare_methods(spec1, spec2)  #two spec compare  
                        actual_count_pair  +=con_pairs
                        if result == {} or result == []:
                            empty_file.append(file)
                            continue
                        program_post_condition_comparisons = set()
                        program_pre_condition_comparisons = set()
                        
                        for con,case_result in result.items():
                            empty +=1
                            for rsult in case_result:
                                post_condition_comparisons = set()
                                pre_condition_comparisons = set()
                                pre_condition_comparisons.add(rsult['pre_condition_comparison'])
                                post_condition_comparisons.add(rsult['post_condition_comparison'])
                                program_pre_condition_comparisons.add(rsult['pre_condition_comparison'])
                                program_post_condition_comparisons.add(rsult['post_condition_comparison'])
                                #print(pre_condition_comparisons)
                                #print(post_condition_comparisons)
                                if post_condition_comparisons.issubset({'same'}) and pre_condition_comparisons.issubset({'same'}):
                                    v2_better_or_same_num+=1
                                elif post_condition_comparisons.issubset({'v1_better', 'same'}) and pre_condition_comparisons.issubset({'v1_better', 'same'}):
                                    v1_better_or_same_num+=1
                                    #print(v1_better_or_same_num)
                                    #print(f"better result is:{rsult}")
                                elif post_condition_comparisons.issubset({'v2_better', 'same'}) and pre_condition_comparisons.issubset({'v2_better', 'same'}):
                                    v2_better_or_same_num+=1
                                    #print(v2_better_or_same_num)
                                    #print(f"v2 better result is:{rsult}")
                                else:
                                    mixed_pair_num+=1
                                    #print(mixed_pair_num)
                                    #print(f"mix result:{rsult}")
                        compare_result[file] = result
                        if program_post_condition_comparisons.issubset({'same'}) and program_pre_condition_comparisons.issubset({'same'}):
                            program_v2_better_or_same_num+=1
                            print(file)
                        elif program_post_condition_comparisons.issubset({'v1_better', 'same'}) and program_pre_condition_comparisons.issubset({'v1_better', 'same'}):
                            program_v1_better_or_same_num+=1
                            failed_file.append(file)
                            print(f"better result is:{rsult}")
                        elif program_post_condition_comparisons.issubset({'v2_better', 'same'}) and program_pre_condition_comparisons.issubset({'v2_better', 'same'}):
                            program_v2_better_or_same_num+=1
                            print(f"v2 better result is:{rsult}")
                        else:
                            program_mixed_pair_num+=1
                            print(mixed_pair_num)
                            print(f"mix result:{rsult}")
                        #v2_better_or_same_num=v2_better_or_same_num+len(result_2)
    #print(compare_result)
    print("Total pair1", total_pairs1)  
    print("Total pair2", total_pairs2) 
    print("Total pair3", total_pairs3) 
    print(f"empty_files: {len(empty_file)}")
    print(f"actual count: {actual_count_pair}")
    print(empty)
    #print(f"empty_files: {empty_files}")
    print(f"v1_better_or_same: {v1_better_or_same_num}")
    print(f"v2_better_or_same: {v2_better_or_same_num}")
    print(f"mixed_files: {mixed_pair_num}")
    print(f"program_v1_better_or_same: {program_v1_better_or_same_num}")
    print(failed_file)
    print(f"program_v2_better_or_same: {program_v2_better_or_same_num}")
    print(f"mixed_files: {program_mixed_pair_num}")
    