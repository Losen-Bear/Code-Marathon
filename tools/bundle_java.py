"""Build one compilable Java source per group of ten independent solutions."""
from pathlib import Path
import re, textwrap

ROOT = Path(__file__).resolve().parents[1]

def main():
    for start in range(1, 101, 10):
        end = start + 9
        group = ROOT / 'test' / f'{start}-{end}'
        classname = f'Solutions{start:03d}_{end:03d}'
        imports, problems = [], []
        for n in range(start, end + 1):
            source = group / f'{n:03d}' / 'Main.java'
            code = source.read_text(encoding='utf-8')
            for declaration in re.findall(r'^import .+;$', code, re.M):
                if declaration not in imports: imports.append(declaration)
            body = re.sub(r'^import .+;\n', '', code, flags=re.M).strip()
            assert body.count('public class Main {') == 1, source
            body = body.replace('public class Main {', f'static class Problem{n:03d} {{', 1)
            title = (source.parent / 'README.md').read_text(encoding='utf-8').splitlines()[0]
            title = title.removeprefix('# ').removesuffix(' — Java 题解').replace('*/', '* /')
            problems.append(textwrap.indent(f'/** {title}；原独立文件：{n:03d}/Main.java */\n{body}', '    '))
        header = f'''/**
 * {start:03d}—{end:03d}题 Java 代码汇总；每题代码位于对应的静态内部类。
 * 编译：javac -encoding UTF-8 {classname}.java
 * 运行示例：java {classname} {start:03d}
 * 命令行参数选择题号，题目数据仍从标准输入读取。
 * 向OJ提交单题时，使用题号目录中的Main.java。
 * 由tools/bundle_java.py生成，算法逻辑与独立文件一致。
 */
public class {classname} {{
    public static void main(String[] args) throws Exception {{
        if (args.length != 1) {{
            System.err.println("用法：java {classname} <题号{start:03d}—{end:03d}>");
            return;
        }}
        switch (Integer.parseInt(args[0])) {{
'''
        for n in range(start, end + 1):
            header += f'            case {n}: Problem{n:03d}.main(new String[0]); break;\n'
        header += f'''            default: throw new IllegalArgumentException("本文件支持的题号为{start:03d}—{end:03d}");
        }}
    }}

'''
        output = '\n'.join(imports) + '\n\n' + header + '\n\n'.join(problems) + '\n}\n'
        destination = group / f'{classname}.java'
        destination.write_text(output, encoding='utf-8')
        print(destination.relative_to(ROOT))

if __name__ == '__main__': main()
