"""Publish complete, independently submittable Java programs in solution pages."""
from pathlib import Path
import argparse
import re

ROOT = Path(__file__).resolve().parents[1]
NOTICE_START = '<!-- submission-notice:start -->'
NOTICE_END = '<!-- submission-notice:end -->'


def code_block(source):
    return '```java\n' + source.rstrip() + '\n```\n'


def generated_documents():
    sources = sorted(ROOT.glob('test/*/*/Main.java'))
    if len(sources) != 100:
        raise ValueError('Expected exactly 100 independent Main.java files')
    groups = {}
    documents = {}
    for source in sources:
        code = source.read_text(encoding='utf-8')
        if (len(re.findall(r'^public class Main\s*\{', code, re.M)) != 1
                or not re.search(r'public static void main\(String\[\] args\)', code)
                or re.search(r'^\s*package\s', code, re.M)):
            raise ValueError(f'{source}: expected an independent Main program')
        readme = source.parent / 'README.md'
        original = readme.read_text(encoding='utf-8')
        title = original.splitlines()[0].removeprefix('# ').removesuffix(' — Java 题解')
        section = (
            '## Java 实现\n\n'
            '**提交方式：选择 Java，将下面代码块中的全部代码复制到提交框。**'
            '包含 `import`、`public class Main`、`main` 入口和全部辅助方法，'
            '无需拼接原题 Hint，也无需修改类名。\n\n'
            '源文件：[Main.java](Main.java)。兼容 Java 8 及以上，无包名、无第三方依赖。\n\n'
            + code_block(code)
            + '\n本地运行：\n\n```sh\njavac -encoding UTF-8 Main.java\njava Main\n```\n\n'
            '程序读取标准输入、写入标准输出；题面展示用的“输入：”“输出：”和解释文字无需输入。\n\n'
        )
        updated, count = re.subn(
            r'^## Java 实现\n.*?(?=^## |\Z)', lambda _: section,
            original, count=1, flags=re.M | re.S,
        )
        if count != 1:
            raise ValueError(f'{readme}: missing Java implementation section')
        documents[readme] = updated.rstrip() + '\n'
        groups.setdefault(source.parent.parent, []).append((source.parent.name, title, code))

    for group, problems in groups.items():
        if len(problems) != 10:
            raise ValueError(f'{group}: expected ten programs')
        numbers = sorted(int(number) for number, _, _ in problems)
        first, last = numbers[0], numbers[-1]
        if numbers != list(range(first, first + 10)):
            raise ValueError(f'{group}: expected consecutive problem numbers')
        bundle = f'Solutions{first:03d}_{last:03d}.java'
        intro = (
            f'# {first}-{last} Java 完整程序与题解索引\n\n'
            '**提交某一题时，复制下面该题 Java 代码块的全部内容。**'
            '每段都是独立的 `public class Main` 程序，已包含输入、输出及所有辅助方法。'
            '每次只提交一道题的一个代码块。\n\n'
        )
        if first in (71, 81):
            intro += '题号采用比赛网页实际编号，上传记录的偏移见[题号校正](NUMBERING.md)。\n\n'
        intro += '| 题号 | 题目 | 解题说明 | 完整源文件 |\n| --- | --- | --- | --- |\n'
        for number, title, _ in problems:
            name = title.removeprefix(number + ' ')
            intro += f'| [{number}](#submit-{number}) | {name} | [题解]({number}/README.md) | [Main.java]({number}/Main.java) |\n'
        intro += (
            f'\n本地批量运行可用 [{bundle}]({bundle})；它需要命令行题号参数，'
            '向 OJ 提交时请使用下面的单题完整程序。\n\n'
        )
        for number, title, code in problems:
            intro += (
                f'<a id="submit-{number}"></a>\n\n## {title}\n\n'
                f'[解题思路与格式说明]({number}/README.md) · [独立源文件]({number}/Main.java)\n\n'
                + code_block(code) + '\n'
            )
        documents[group / 'solutions.md'] = intro.rstrip() + '\n'

        statements = list(group.glob('test*.md'))
        if len(statements) != 1:
            raise ValueError(f'{group}: expected one uploaded statement file')
        statement = statements[0]
        original = statement.read_text(encoding='utf-8')
        notice = (
            NOTICE_START + '\n'
            '> **完整 Java 提交程序：** [打开本组全部十题的完整代码](solutions.md)。'
            '本文的 Hint 是原题提供的模板，可能含 `TODO` 或仅有方法；'
            '请从题解页面复制对应题的完整 `public class Main` 程序提交。\n'
            + NOTICE_END
        )
        if NOTICE_START in original:
            updated = re.sub(
                re.escape(NOTICE_START) + r'.*?' + re.escape(NOTICE_END),
                lambda _: notice, original, count=1, flags=re.S,
            )
        else:
            heading, rest = original.split('\n', 1)
            updated = heading + '\n\n' + notice + '\n' + rest
        documents[statement] = updated
    return documents


def check_documents():
    documents = generated_documents()
    return [path for path, expected in documents.items()
            if not path.exists() or path.read_text(encoding='utf-8') != expected]


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--check', action='store_true', help='fail if documentation is out of sync')
    options = parser.parse_args()
    documents = generated_documents()
    stale = []
    for path, expected in documents.items():
        if path.read_text(encoding='utf-8') != expected:
            stale.append(path)
            if not options.check:
                path.write_text(expected, encoding='utf-8')
    if options.check and stale:
        for path in stale:
            print('Out of sync:', path.relative_to(ROOT))
        print('Run python tools/sync_java_docs.py')
        return 1
    print(f'100 independent Main programs; 200 complete Java code blocks; '
          f'{len(documents)} documentation files checked; {len(stale)} updated.')
    return 0


if __name__ == '__main__':
    raise SystemExit(main())
