# Code-Marathon

西工大软件学院码拉松比赛题目记录与Java题解。

## 直接复制提交

选择下面对应分组的**题解索引**，找到题号，复制该题 `java` 代码块的全部内容，粘贴到 OJ 并选择 Java 提交。100 道题的分组页面和单题题解均已直接展示完整程序。

每个代码块都包含 `import`、`public class Main`、`public static void main(String[] args)`、输入输出及全部辅助方法。每次只复制一道题的一个完整代码块；无需拼接 Hint，无需修改类名。原题记录中的 Hint 保留作题面参考，完整提交代码以题解页面或该题 `Main.java` 为准。

例如：[005 泡泡堂完整程序](test/1-10/005/README.md#java-实现)。

## Java 题解

已整理 **001—100，共100题**。每题包含解题思路、正确性说明、复杂度、独立`Main.java`及本地测试用例；每十题另有一个可编译的汇总`.java`文件。原上传题面保留。

| 题号 | 目录 | 汇总代码 |
| --- | --- | --- |
| 001—010 | [题解索引](test/1-10/solutions.md) | [Solutions001_010.java](test/1-10/Solutions001_010.java) |
| 011—020 | [题解索引](test/11-20/solutions.md) | [Solutions011_020.java](test/11-20/Solutions011_020.java) |
| 021—030 | [题解索引](test/21-30/solutions.md) | [Solutions021_030.java](test/21-30/Solutions021_030.java) |
| 031—040 | [题解索引](test/31-40/solutions.md) | [Solutions031_040.java](test/31-40/Solutions031_040.java) |
| 041—050 | [题解索引](test/41-50/solutions.md) | [Solutions041_050.java](test/41-50/Solutions041_050.java) |
| 051—060 | [题解索引](test/51-60/solutions.md) | [Solutions051_060.java](test/51-60/Solutions051_060.java) |
| 061—070 | [题解索引](test/61-70/solutions.md) | [Solutions061_070.java](test/61-70/Solutions061_070.java) |
| 071—080 | [题解索引](test/71-80/solutions.md) | [Solutions071_080.java](test/71-80/Solutions071_080.java) |
| 081—090 | [题解索引](test/81-90/solutions.md) | [Solutions081_090.java](test/81-90/Solutions081_090.java) |
| 091—100 | [题解索引](test/91-100/solutions.md) | [Solutions091_100.java](test/91-100/Solutions091_100.java) |

每题在独立目录运行`javac -encoding UTF-8 Main.java`和`java Main`。所有题解兼容Java 8及以上，无第三方依赖。

上传记录中的题号偏移见[071—080校正](test/71-80/NUMBERING.md)及[081—090校正](test/81-90/NUMBERING.md)。缺失的075题已从比赛网页[补取](test/71-80/075/statement.md)；085题的矩阵抄录差异见[样例勘误](test/81-90/085/ERRATA.md)。

## 每十题汇总代码

每个十题分组目录包含一个`SolutionsXXX_YYY.java`，通过命令行题号选择运行某题，题目数据仍从标准输入读取。例如：

```sh
cd test/61-70
javac -encoding UTF-8 Solutions061_070.java
java Solutions061_070 065
```

向OJ提交单题时使用该题目录中的`Main.java`。在仓库根目录运行`python tools/bundle_java.py`可从独立文件重新生成十个汇总文件。

## 本地验证

安装Python 3.9及以上、JDK 9及以上后，在仓库根目录运行：

```sh
python tools/sync_java_docs.py --check
python tools/verify.py
python tools/verify.py --bundles
```

题解中的 200 个 Java 代码块（100 个单题页面、10 个分组页面中的 100 题）均从独立 `Main.java` 同步。修改源码后运行 `python tools/sync_java_docs.py` 更新页面；验证器会先检查页面与源码是否一致，避免复制到旧版本或不完整片段。

两种形式均已通过全部 **1203** 个本地用例。可加`--range 61-100`仅检查后40题。编译使用`--release 8`，产物写入系统临时目录，不污染题解目录。

题面解释、规模限制及逐题结果见[VALIDATION.md](VALIDATION.md)。本地验证通过不等于OJ全量评测AC。
