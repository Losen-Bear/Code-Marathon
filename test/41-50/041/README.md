# 041 最好逛吃路线 — Java 题解

原题面：[本组题目](../test41-50.md)，查找题号 `041`。原记录保留不改。

## 解题思路

公式改写为(values[i]+i)+(values[j]-j)。扫描j时维护此前values[i]+i的最大值，计算这一对的得分，再把j加入候选。

## 正确性说明

固定j后右项不变，最大得分只取决于此前左项最大值。先求答案后更新保证i<j，扫描覆盖所有右端点。

## 复杂度

时间 O(n)，算法额外空间 O(1)。

## Java 实现

完整可提交代码见 [Main.java](Main.java)。每题独立编译，无包名，无第三方依赖，兼容 Java 8 及以上。

```sh
javac -encoding UTF-8 Main.java
java Main
```

程序读取标准输入、写入标准输出；题面中的“输入：”“输出：”和解释性注释不作为测试数据。

## 验证

本地样例和边界验证见 [cases.json](cases.json)；实际执行结果见仓库根目录 [VALIDATION.md](../../../VALIDATION.md)。未提交到 OJ，不标记为 AC。
