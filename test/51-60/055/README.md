# 055 玩游戏的一天 — Java 题解

原题面：[本组题目](../test51-60.md)，查找题号 `055`。原记录保留不改。

## 解题思路

对每个候选单词做DFS，八方向移动，并用visited禁止重复格子。长度超过格子数直接排除；找到的单词放入TreeSet，按字典序输出。

## 正确性说明

按单词顺序匹配字符、八方向枚举所有邻格、visited限制一次使用，恰好与Boggle规则一致。逐单词判断后TreeSet只输出存在且不重复的单词。

## 复杂度

最坏时间 O(k·mn·7^L)，空间 O(mn+L+结果)，L≤mn≤16。

## Java 实现

完整可提交代码见 [Main.java](Main.java)。每题独立编译，无包名，无第三方依赖，兼容 Java 8 及以上。

```sh
javac -encoding UTF-8 Main.java
java Main
```

程序读取标准输入、写入标准输出；题面中的“输入：”“输出：”和解释性注释不作为测试数据。

## 验证

本地样例和边界验证见 [cases.json](cases.json)；实际执行结果见仓库根目录 [VALIDATION.md](../../../VALIDATION.md)。未提交到 OJ，不标记为 AC。
