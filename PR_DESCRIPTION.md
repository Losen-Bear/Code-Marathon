## Summary

- 打开任一十题分组目录，即可在目录首页看到十道题的完整Java程序，按题号跳转并复制提交，无需逐题打开。

## Changes

- 新增10个分组README.md，每页展开10份独立Main程序；既有solutions.md保留相同完整程序。
- 首页主要入口改为“十题完整程序”，将命令行运行的汇总.java文件标为本地批量运行文件。
- 同步工具同时生成目录首页，保持全部300个代码块与100份Main.java一致。

## Testing

- `python tools/sync_java_docs.py --check` — 130个生成页面与源码一致，覆盖300个完整Java代码块。
- 逐项核对全部300个代码块与源码一致，10个目录首页各含10份完整程序，链接和题号锚点有效。
- `git diff --check` — 通过。

## Risks / Notes

- 本次未改Java源码，未重复执行2026-10-16已通过的1203例算法评测；详情见VALIDATION.md。
- 修改Main.java后需要运行`python tools/sync_java_docs.py`更新题解页面。
