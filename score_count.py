

import pandas as pd
import re
from collections import defaultdict

def parse_rank(s: str) -> dict:
    if pd.isna(s) or str(s).strip() == '':
        return {'A': 1, 'B': 1, 'C': 1, 'D': 1}

    s = re.sub(r'\s+', '', str(s))   
    groups = []                      
    i = 0
    n = len(s)
    while i < n:
        if s[i] == '(':
            j = s.find(')', i)
            if j == -1:
                j = i                
            groups.append(list(s[i+1:j]))
            i = j + 1
        else:
            groups.append([s[i]])
            i += 1
    seen = set()
    uniq_groups = []
    for g in groups:
        new_g = [ch for ch in g if ch in {'A','B','C','D'} and ch not in seen]
        if new_g:
            uniq_groups.append(new_g)
            seen.update(new_g)


    score_map = {'A': 1, 'B': 1, 'C': 1, 'D': 1}
    for score, grp in enumerate(uniq_groups, start=1):
        for ch in grp:
            score_map[ch] = 4 - (score - 1)   
    return score_map

def human_eval():
    df = pd.read_csv('/human_eval/SG_Bench_1106_label_huihui.csv',
                 usecols=[4], header=None, dtype=str)
    ranks = df.iloc[:, 0].tolist()          

    dicts = [parse_rank(r) for r in ranks]  
    print(dicts)
    print(len(dicts))
    n = len(dicts)
    avgA = sum(d['A'] for d in dicts) / n
    avgB = sum(d['B'] for d in dicts) / n
    avgC = sum(d['C'] for d in dicts) / n
    avgD = sum(d['D'] for d in dicts) / n

    print(f'Avg A = {avgA:.2f}')
    print(f'Avg B = {avgB:.2f}')
    print(f'Avg C = {avgC:.2f}')
    print(f'Avg D = {avgD:.2f}')
human_eval()
