## Summary

- 题解页面直接展示全部100道题的完整Java程序，复制后即可作为Main.java编译，避免仅提交方法而出现`class, interface, or enum expected`。

## Changes

- 100个单题README和10个分组solutions.md展示共200个完整Java代码块，均包含import、public class Main、main入口、输入输出及全部辅助方法。
- 首页增加直接复制提交说明，10份原题记录增加完整程序入口，区分原题Hint模板与可提交程序。
- 新增tools/sync_java_docs.py，从独立Main.java同步代码块；验证器先检查页面与源码是否一致。保留既有解题思路、算法源码、用例和题意差异说明。

## Testing

- `python tools/sync_java_docs.py --check` — 100份独立程序结构检查通过，120个生成页面与源码一致，覆盖200个完整Java代码块。
- `python tools/verify.py` — 100份独立代码以Java 8目标编译，1203/1203个用例通过。
- `python tools/verify.py --bundles` — 10份汇总代码以Java 8目标编译，1203/1203个用例通过。

## Risks / Notes

- 未向OJ提交，不声明全量评测AC；既有题面歧义及性能边界仍见VALIDATION.md。
- 修改Main.java后需要运行`python tools/sync_java_docs.py`更新题解页面。
