# 088 完美排列 — Java 题解

原题面：[题目记录](../test81-90.md)，原记录标题 `078 完美排列`。实际题号以本文件标题为准。

## 解题思路

状态压缩DP。mask记录已经放入排列的数字，下一位置i=bitCount(mask)+1。对每个未使用x，若i整除x或x整除i，就将当前计数加入新mask。

## 正确性说明

状态转移只添加满足当前位置规则且尚未使用的数字，所以每条状态链对应合法排列。任意合法排列按顺序添加都能形成一条唯一链，计数完整且不重复。

## 复杂度

时间O(N·2^N)，空间O(2^N)。

## 题面与格式说明

上传文件标为078，实际088。 原题记录中的标题编号为078，本题解按比赛网页编号088归档。

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
        int n = (int)numbers(read())[0]; long[] dp = new long[1<<n]; dp[0] = 1;
        for (int mask = 0; mask < dp.length; mask++) {
            int position = Integer.bitCount(mask)+1;
            for (int x = 1; x <= n; x++) if ((mask & (1<<(x-1))) == 0 && (position%x == 0 || x%position == 0)) dp[mask|(1<<(x-1))] += dp[mask];
        }
        System.out.print(dp[dp.length-1]);
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
