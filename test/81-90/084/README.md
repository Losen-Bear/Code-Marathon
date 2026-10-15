# 084 拟合 — Java 题解

原题面：[题目记录](../test81-90.md)，原记录标题 `074 拟合`。实际题号以本文件标题为准。

## 解题思路

加权编辑距离。dp[i][j]为前i、j个数列前缀变相同的最小代价；删除A末项花|a|，删除B末项花|b|，将两末项配成共同数花|a-b|。滚动两行DP。

## 正确性说明

相同结果序列中保留下来的元素按原顺序配对。最后一步只有删除某侧末项或配对两末项三种情况；由三角不等式，修改两值到共同值的最小总成本是|a-b|，递推准确。

## 复杂度

时间O(nm)，空间O(m)。

## 题面与格式说明

上传文件标为074，实际084。允许删除全部元素，两列同时变为空也合法。 原题记录中的标题编号为074，本题解按比赛网页编号084归档。

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
        long[] input = numbers(read()); int n = (int) input[0], m = (int) input[1]; long[] dp = new long[m+1];
        for (int j = 1; j <= m; j++) dp[j] = dp[j-1]+Math.abs(input[1+n+j]);
        for (int i = 1; i <= n; i++) {
            long a = input[1+i]; long[] next = new long[m+1]; next[0] = dp[0]+Math.abs(a);
            for (int j = 1; j <= m; j++) {
                long b = input[1+n+j]; next[j] = Math.min(dp[j]+Math.abs(a),Math.min(next[j-1]+Math.abs(b),dp[j-1]+Math.abs(a-b)));
            }
            dp = next;
        }
        System.out.print(dp[m]);
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
