# 090 采矿 — Java 题解

原题面：[题目记录](../test81-90.md)，原记录标题 `090 采矿`。实际题号以本文件标题为准。

## 解题思路

按样例所支持的0/1背包：每种输入矿物作为一件可选项目，倒序遍历时间，决定取或不取，输出最大价值。

## 正确性说明

倒序确保加入当前矿物的来源来自上一层，故每种最多取一次。取与不取涵盖所有合法方案，DP给出规定时间内最大价值。

## 复杂度

时间O(nm)，空间O(n)。

## 题面与格式说明

正文未明确每种矿物数量；样例70分钟的1分钟、价值2项目若可无限重复会得到140，而样例为3，所以采用与样例一致的每种一次，并明确保留此歧义。

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
