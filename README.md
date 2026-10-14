# Code-Marathon
西工大软件学院码拉松比赛

#西工大马拉松比赛题目记录&题解

## Java 题解

已整理本仓库当前上传的 **001—060，共60题**。每题包含解题思路、正确性说明、复杂度、独立 `Main.java` 和本地测试用例。原题面文件保留。61—70题的记录为空，尚未生成题解。

| 题号 | 目录 | 汇总代码 |
| --- | --- | --- |
| 001—010 | [题解索引](test/1-10/solutions.md) | [Solutions001_010.java](test/1-10/Solutions001_010.java) |
| 011—020 | [题解索引](test/11-20/solutions.md) | [Solutions011_020.java](test/11-20/Solutions011_020.java) |
| 021—030 | [题解索引](test/21-30/solutions.md) | [Solutions021_030.java](test/21-30/Solutions021_030.java) |
| 031—040 | [题解索引](test/31-40/solutions.md) | [Solutions031_040.java](test/31-40/Solutions031_040.java) |
| 041—050 | [题解索引](test/41-50/solutions.md) | [Solutions041_050.java](test/41-50/Solutions041_050.java) |
| 051—060 | [题解索引](test/51-60/solutions.md) | [Solutions051_060.java](test/51-60/Solutions051_060.java) |

每题在独立目录运行 `javac -encoding UTF-8 Main.java` 和 `java Main`。所有程序兼容 Java 8 及以上。

安装 Python 3 和 JDK 9 及以上版本后可在仓库根目录运行 `python tools/verify.py`，编译文件写入系统临时目录，不污染题解目录。验证详情及原题记录中的矛盾见 [VALIDATION.md](VALIDATION.md)。本地验证通过不等于 OJ 全量测试 AC。


## 每十题汇总代码

每个十题分组目录包含一个 `SolutionsXXX_YYY.java`，可直接编译，通过命令行题号选择运行某题，例如：

```sh
cd test/1-10
javac -encoding UTF-8 Solutions001_010.java
java Solutions001_010 005
```

在仓库根目录运行 `python tools/bundle_java.py` 可从独立 `Main.java` 重新生成六个汇总文件；运行 `python tools/verify.py --bundles` 可检查六个汇总文件的全部728个用例。每题的独立文件和详细题解仍可使用。
