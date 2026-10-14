# 040 走出网格 — Java 题解

原题面：[本组题目](../test31-40.md)，查找题号 `040`。原记录保留不改。

## 解题思路

按照样例顺序读取m,n,maxMove,startRow,startColumn。逐步DP记录尚在网格内的路径数，向四邻格转移；越界时加入答案，不再继续扩展。

## 正确性说明

dp在每一步恰好统计此前始终未越界的路径。所有下一步要么仍在网格内、要么首次越界，两类互斥且完整，因此累计越界数就是至多maxMove步离开的全部路径。

## 复杂度

时间 O(maxMove·mn)，空间 O(mn)。

## 题面与格式说明

描述和样例的参数顺序存在差异；Hint明确要求采用Sample Input顺序，所以第三项是maxMove。

## Java 实现

完整可提交代码见 [Main.java](Main.java)。每题独立编译，无包名，无第三方依赖，兼容 Java 8 及以上。

```sh
javac -encoding UTF-8 Main.java
java Main
```

程序读取标准输入、写入标准输出；题面中的“输入：”“输出：”和解释性注释不作为测试数据。

## 验证

本地样例和边界验证见 [cases.json](cases.json)；实际执行结果见仓库根目录 [VALIDATION.md](../../../VALIDATION.md)。未提交到 OJ，不标记为 AC。
