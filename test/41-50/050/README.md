# 050 1应俱全 — Java 题解

原题面：[本组题目](../test41-50.md)，查找题号 `050`。原记录保留不改。

## 解题思路

逐位统计十进制数位1。对位权factor，分出high、current、low；完整高位循环贡献high×factor，current=1再加low+1，current>1再加factor。

## 正确性说明

当前位从0到9周期循环，每个完整周期有factor个数在该位为1。残余周期根据当前位是否越过1分成三种情况，逐位累加既不遗漏也不重复任何1。

## 复杂度

时间 O(log n)，空间 O(1)。

## Java 实现

完整可提交代码见 [Main.java](Main.java)。每题独立编译，无包名，无第三方依赖，兼容 Java 8 及以上。

```sh
javac -encoding UTF-8 Main.java
java Main
```

程序读取标准输入、写入标准输出；题面中的“输入：”“输出：”和解释性注释不作为测试数据。

## 验证

本地样例和边界验证见 [cases.json](cases.json)；实际执行结果见仓库根目录 [VALIDATION.md](../../../VALIDATION.md)。未提交到 OJ，不标记为 AC。
