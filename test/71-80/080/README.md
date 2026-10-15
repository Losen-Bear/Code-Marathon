# 080 最大岛屿 — Java 题解

原题面：[题目记录](../test71-80.md)，原记录标题 `080 最大岛屿`。实际题号以本文件标题为准。

## 解题思路

最大正方形DP：当前位置为1时，以它为右下角的最大正方形边长等于上、左、左上三个边长的最小值加1；0位置为0。用一行滚动DP，最大边长平方即面积。

## 正确性说明

边长k的正方形要求三个相邻子正方形至少为k-1，形成必要上界；三个子正方形与当前1的并集又能组成该大小正方形，所以此上界可达到。

## 复杂度

时间O(mn)，DP空间O(n)，输入解析O(mn)。

## 题面与格式说明

“岛屿”特指全部为1的正方形，不是一般连通块。

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
        int[] input = integers(read()); int m = input[0], n = input[1], best = 0; int[] dp = new int[n+1];
        for (int r = 0; r < m; r++) { int diagonal = 0;
            for (int c = 1; c <= n; c++) { int old = dp[c]; dp[c] = input[2+r*n+c-1] == 0 ? 0 : Math.min(diagonal,Math.min(dp[c],dp[c-1]))+1; diagonal = old; best = Math.max(best,dp[c]); }
        }
        System.out.print(best*best);
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
