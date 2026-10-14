# 006 猫和鱼 — Java 题解

原题面：[本组题目](../test1_10.md)，查找题号 `006`。原记录保留不改。

## 解题思路

先把所有鱼交给第二只猫，累加 reward2。把鱼 i 改交给第一只猫可增加 reward1[i]-reward2[i]。排序这些差值，取最大的k项。

## 正确性说明

任意合法方案恰好挑选k项差值；若它漏选更大的差值，交换后收益不减，因此最大的k项最优，即使差值为负也必须满足恰好k条。

## 复杂度

时间 O(n log n)，空间 O(n)。

## Java 实现

完整可提交代码见 [Main.java](Main.java)。每题独立编译，无包名，无第三方依赖，兼容 Java 8 及以上。

```sh
javac -encoding UTF-8 Main.java
java Main
```

程序读取标准输入、写入标准输出；题面中的“输入：”“输出：”和解释性注释不作为测试数据。

## 验证

本地样例和边界验证见 [cases.json](cases.json)；实际执行结果见仓库根目录 [VALIDATION.md](../../../VALIDATION.md)。未提交到 OJ，不标记为 AC。
