# 072 失落遗迹的宝藏 — Java 题解

原题面：[题目记录](../test71-80.md)，原记录标题 `073 失落遗迹的宝藏`。实际题号以本文件标题为准。

## 解题思路

0/1背包：dp[t]表示在不超过t时间内能获得的最大价值。每件宝物从总时间倒序更新dp[t]=max(dp[t],dp[t-cost]+value)。

## 正确性说明

倒序更新确保dp[t-cost]来自尚未加入当前宝物的上一层，所以同一件只会选一次。选或不选当前宝物覆盖全部方案，最终容量位置即最优。

## 复杂度

时间O(NT)，空间O(T)。

## 题面与格式说明

对应上传文件误标073的题目。样例70分钟选69分钟与1分钟两件，总价值3，符合每件只取一次。 原题记录中的标题编号为073，本题解按比赛网页编号072归档。

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
        long[] input = numbers(read()); int capacity = (int) input[0], n = (int) input[1]; long[] dp = new long[capacity + 1];
        for (int i = 0; i < n; i++) {
            int time = (int) input[2 + i*2]; long value = input[3 + i*2];
            for (int t = capacity; t >= time; t--) dp[t] = Math.max(dp[t], dp[t - time] + value);
        }
        System.out.print(dp[capacity]);
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
