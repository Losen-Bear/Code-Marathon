## Summary

- 将001—060题的Java代码按每十题汇总为一个可编译的源文件，方便在对应分组目录中阅读和运行。

## Changes

- 增加六个SolutionsXXX_YYY.java，以静态内部类隔离题目代码，用命令行题号选择运行。
- 增加tools/bundle_java.py，可从独立Main.java重新生成汇总文件。
- 更新分组索引、README导航及验证脚本的--bundles选项。
- 原题面、独立Main.java和本地用例保持可用。

## Testing

- `python tools/bundle_java.py` — 生成六个汇总文件。
- `python tools/verify.py --bundles` — 六文件Java 8兼容编译、60题的728个已有用例全部通过。

## Risks / Notes

- 向OJ提交单题时使用题号目录中的Main.java；汇总文件用于本地选择题号运行。
- 题面差异及算法验证限制仍见VALIDATION.md。
