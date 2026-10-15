# 062 寻找反向对 — Java 题解

原题面：[题目记录](../test61-70.md)，原记录标题 `062 寻找反向对`。实际题号以本文件标题为准。

## 解题思路

把最大数i插入一个i-1元素的排列，可新增0到i-1个逆序对。dp[i][j]=Σdp[i-1][j-t]，用滑动窗口求和，将每层从O(k·i)降为O(k)。

## 正确性说明

插入位置与新增逆序对数一一对应，且删除最大数能唯一还原原排列，所以递推无遗漏和重复。滚动窗口恰好维护规定范围内的和。

## 复杂度

时间O(nk)，空间O(k)。

## 题面与格式说明

按原题输入输出格式实现。

## Java 实现

**提交方式：选择 Java，将下面代码块中的全部代码复制到提交框。**包含 `import`、`public class Main`、`main` 入口和全部辅助方法，无需拼接原题 Hint，也无需修改类名。

源文件：[Main.java](Main.java)。兼容 Java 8 及以上，无包名、无第三方依赖。

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        int[] input = integers(read()); int n = input[0], k = input[1]; long mod = 1000000007L;
        if (k > (long) n * (n - 1) / 2) { System.out.print(0); return; }
        long[] dp = new long[k + 1]; dp[0] = 1;
        for (int size = 1; size <= n; size++) {
            long[] next = new long[k + 1]; long window = 0;
            for (int j = 0; j <= k; j++) {
                window = (window + dp[j]) % mod;
                if (j >= size) window = (window - dp[j - size] + mod) % mod;
                next[j] = window;
            }
            dp = next;
        }
        System.out.print(dp[k]);
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
