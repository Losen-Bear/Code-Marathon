# 099 贪吃鬼 — Java 题解

原题面：[题目记录](../test91-100.md)，原记录标题 `099 贪吃鬼`。实际题号以本文件标题为准。

## 解题思路

问题等价于在环上选恰好length/3个互不相邻的位置使权重和最大。环拆为排除首项或排除末项的两种线性区间，用dp[i][j]求前i项选恰好j个非相邻元素的最大和。

## 正确性说明

每轮会删除所选位置及两个邻居，最终自己选择的位置在原环上互不相邻。反过来，任意大小为n的非相邻集合可实现：2n个未选点分布在n个间隙，总有间隙至少含2点，取邻接该间隙的选中点后合并间隙仍至少留1点，递归完成。因此转成恰好n项的环独立集正确。

## 复杂度

时间O(L·(L/3))，空间O(L/3)，L为数组长度。

## 题面与格式说明

必须恰好选length/3堆，不能用允许任意选取数量的普通打家劫舍DP代替。

## Java 实现

**提交方式：选择 Java，将下面代码块中的全部代码复制到提交框。**包含 `import`、`public class Main`、`main` 入口和全部辅助方法，无需拼接原题 Hint，也无需修改类名。

源文件：[Main.java](Main.java)。兼容 Java 8 及以上，无包名、无第三方依赖。

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static long solve(long[] a, int left, int right, int count) {
        long impossible = Long.MIN_VALUE/4; long[] older = new long[count+1], previous = new long[count+1]; Arrays.fill(older,impossible); Arrays.fill(previous,impossible); older[0] = previous[0] = 0;
        for (int i = left; i <= right; i++) {
            long[] current = previous.clone();
            for (int j = 1; j <= count; j++) if (older[j-1] != impossible) current[j] = Math.max(current[j],older[j-1]+a[i]);
            older = previous; previous = current;
        }
        return previous[count];
    }
    public static void main(String[] args) throws Exception {
        long[] a = numbers(read()); int count = a.length/3; System.out.print(Math.max(solve(a,0,a.length-2,count),solve(a,1,a.length-1,count)));
    }

    static String read() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[8192];
        for (int n; (n = System.in.read(buffer)) != -1;) out.write(buffer, 0, n);
        return new String(out.toByteArray(), java.nio.charset.StandardCharsets.UTF_8);
    }
    static long[] numbers(String s) {
        Matcher matcher = Pattern.compile("-?\\d+").matcher(s);
        ArrayList<Long> values = new ArrayList<>();
        while (matcher.find()) values.add(Long.parseLong(matcher.group()));
        long[] result = new long[values.size()];
        for (int i = 0; i < result.length; i++) result[i] = values.get(i);
        return result;
    }
    static int[] integers(String s) {
        long[] values = numbers(s);
        int[] result = new int[values.length];
        for (int i = 0; i < result.length; i++) result[i] = Math.toIntExact(values[i]);
        return result;
    }
    static String join(int[] a) {
        StringBuilder out = new StringBuilder();
        for (int x : a) { if (out.length() > 0) out.append(' '); out.append(x); }
        return out.toString();
    }
}
```

本地运行：

```sh
javac -encoding UTF-8 Main.java
java Main
```

程序读取标准输入、写入标准输出；题面展示用的“输入：”“输出：”和解释文字无需输入。
