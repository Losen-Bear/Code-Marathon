"""Compile independent or grouped Java solutions and run their local cases."""
from pathlib import Path
from concurrent.futures import ThreadPoolExecutor
from contextlib import nullcontext
import argparse
import ast, json, math, re, subprocess, tempfile

ROOT = Path(__file__).resolve().parents[1]

def normalized(s):
    return s.replace('\r\n','\n').strip()

def validate(case, output):
    mode = case.get('validator')
    if mode == 'even-split':
        values = ast.literal_eval(output)
        n = int(case['input'])
        k = (math.isqrt(1 + 4 * n) - 1) // 2
        return (sum(values) == n and len(values) == k and len(set(values)) == k
                and all(x > 0 and x % 2 == 0 for x in values)
                and values[:-1] == list(range(2, 2*k, 2)))
    if mode == 'avoid-flood':
        rains = [int(x) for x in case['input'].split(',')]
        answer = ast.literal_eval(output)
        if len(answer) != len(rains): return False
        full = set()
        for rain, action in zip(rains,answer):
            if rain:
                if action != -1 or rain in full: return False
                full.add(rain)
            else:
                if action <= 0: return False
                full.discard(action)
        return True
    if case.get('strict'):
        return output == case['expected']
    return normalized(output) == normalized(case['expected'])

def run_one(folder, compiled=None, entry='Main', arguments=()):
    cases = json.loads((folder/'cases.json').read_text(encoding='utf-8'))
    failures = []
    context = nullcontext(compiled) if compiled is not None else tempfile.TemporaryDirectory(prefix='marathon-java-')
    with context as temp:
        if compiled is None:
            compilation = subprocess.run(['javac','-J-Duser.language=en','--release','8','-encoding','UTF-8','-d',temp,str(folder/'Main.java')],capture_output=True,text=True,encoding='utf-8',errors='replace',timeout=60)
            if compilation.returncode:
                return {'id':folder.name,'count':len(cases),'passed':0,'failures':[compilation.stderr]}
        for case in cases:
            try:
                result = subprocess.run(['java','-Duser.language=en','-Dfile.encoding=UTF-8','-Xmx256m','-cp',temp,entry,*arguments],input=case['input'],capture_output=True,text=True,encoding='utf-8',errors='replace',timeout=30)
                if result.returncode or not validate(case,result.stdout):
                    failures.append({'case':case['name'],'expected':case.get('expected'),'actual':result.stdout[:1000],'stderr':result.stderr[:1000]})
            except (subprocess.TimeoutExpired,ValueError,SyntaxError) as error:
                failures.append({'case':case['name'],'error':str(error)})
    return {'id':folder.name,'count':len(cases),'passed':len(cases)-len(failures),'failures':failures}

def run_bundle(source):
    folders = sorted(p.parent for p in source.parent.glob('*/Main.java'))
    assert len(folders) == 10, f'Expected ten solutions in {source.parent}'
    with tempfile.TemporaryDirectory(prefix='marathon-bundle-') as temp:
        compilation = subprocess.run(['javac','-J-Duser.language=en','--release','8','-encoding','UTF-8','-d',temp,str(source)],capture_output=True,text=True,encoding='utf-8',errors='replace',timeout=60)
        if compilation.returncode:
            return [{'id':folder.name,'count':len(json.loads((folder/'cases.json').read_text(encoding='utf-8'))),'passed':0,'failures':[compilation.stderr]} for folder in folders]
        return [run_one(folder,temp,source.stem,[folder.name]) for folder in folders]

def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--bundles', action='store_true', help='compile the six grouped Java files and test all problem selectors')
    options = parser.parse_args()
    folders = sorted(p.parent for p in ROOT.glob('test/*/*/Main.java'))
    assert len(folders) == 60, f'Expected 60 solutions, found {len(folders)}'
    with ThreadPoolExecutor(max_workers=4) as pool:
        if options.bundles:
            bundles = sorted(ROOT.glob('test/*/Solutions*.java'))
            assert len(bundles) == 6, f'Expected six bundles, found {len(bundles)}'
            results = [result for group in pool.map(run_bundle,bundles) for result in group]
        else:
            results = list(pool.map(run_one,folders))
    for result in results:
        print(f"{result['id']}: {result['passed']}/{result['count']}")
        for failure in result['failures']: print(json.dumps(failure,ensure_ascii=False))
    report = {'solutions':len(results),'cases':sum(r['count'] for r in results),'passed':sum(r['passed'] for r in results),'results':results}
    print(json.dumps({k:v for k,v in report.items() if k!='results'},ensure_ascii=False))
    # 只有汇总文本是交付物；编译文件和进程诊断不写入仓库。
    import os
    if os.environ.get('MARATHON_REPORT'):
        Path(os.environ['MARATHON_REPORT']).write_text(json.dumps(report,ensure_ascii=False,indent=2),encoding='utf-8')
    return 1 if any(r['failures'] for r in results) else 0

if __name__ == '__main__': raise SystemExit(main())
