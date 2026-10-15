# 093 可怜的小白鼠 — Java 题解

原题面：[题目记录](../test91-100.md)，原记录标题 `093 可怜的小白鼠`。实际题号以本文件标题为准。

## 解题思路

可测试rounds=floor(minutesToTest/minutesToDie)轮。每只鼠有rounds+1种状态（在某轮死亡或始终存活）。逐次乘此状态数，直到可区分状态数≥buckets，次数就是所需最少鼠数。

## 正确性说明

p只鼠最多提供(rounds+1)^p种结果，因此必须达到桶数。给每桶分配一个不同的该进制编码，按各鼠编码位对应轮次喂养，可实现所有状态，从而达到下界。

## 复杂度

时间O(log buckets)，空间O(1)。

## 题面与格式说明

按原题输入输出格式实现。

## Java 实现

独立代码：[Main.java](Main.java)。兼容Java 8及以上，无包名、无第三方依赖。

```sh
javac -encoding UTF-8 Main.java
java Main
```

本地用例见[cases.json](cases.json)，执行结果与题面差异见[验证报告](../../../VALIDATION.md)。未向OJ提交，不声明全量评测AC。
