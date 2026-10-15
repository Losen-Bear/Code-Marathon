# 086 回到原点 — Java 题解

原题面：[题目记录](../test81-90.md)，原记录标题 `076 回到原点`。实际题号以本文件标题为准。

## 解题思路

dp[i]表示当前步指针位于i的方案数。每步从左、原地、右三个来源转移，越界来源不计。最终取dp[0]。只保留min(n,steps/2+1)个位置。

## 正确性说明

每条路径最后一步必为三种动作之一，来源互斥，所以求和。恰好steps步返回0的路径走到的最远下标不能超过steps/2，截去更远位置不会改变答案。

## 复杂度

时间O(steps·min(n,steps/2+1))，空间O(min(n,steps/2+1))。

## 题面与格式说明

上传文件标为076，实际086；题面未给steps上限，采用有限步DP。steps=0时空操作序列贡献1。 原题记录中的标题编号为076，本题解按比赛网页编号086归档。

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
        long[] a = numbers(read()); int steps = Math.toIntExact(a[0]), width = (int)Math.min(a[1],steps/2L+1); long mod = 1000000007L;
        long[] dp = new long[width]; dp[0] = 1;
        for (int step = 0; step < steps; step++) {
            long[] next = new long[width];
            for (int i = 0; i < width; i++) { next[i] = dp[i]; if (i > 0) next[i] += dp[i-1]; if (i+1 < width) next[i] += dp[i+1]; next[i] %= mod; }
            dp = next;
        }
        System.out.print(dp[0]);
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
