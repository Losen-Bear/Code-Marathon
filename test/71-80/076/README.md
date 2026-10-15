# 076 最长线段 — Java 题解

原题面：[题目记录](../test71-80.md)，原记录标题 `076 最长线段`。实际题号以本文件标题为准。

## 解题思路

四方向DP滚动数组。横向长度在一行内累加，纵向沿列累加，主对角线取上一行左列，反对角线取上一行右列。遇到0全部归零，记录最大长度。采用流式输入，不存整张矩阵。

## 正确性说明

每个方向上的以当前1结尾的连续线段，必由该方向前一格的连续线段延长1，或遇0重新开始。四个状态覆盖允许的全部方向，最大值就是最长1线段。

## 复杂度

时间O(mn)，空间O(n)。支持题面m,n≤10^4时避免O(mn)存储。

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
    static class FastInput {
        BufferedInputStream in = new BufferedInputStream(System.in,1<<16);
        int nextInt() throws IOException {
            int c; do { c = in.read(); } while (c <= 32 && c != -1);
            if (c == -1) throw new EOFException(); int value = 0;
            while (c > 32 && c != -1) { value = value * 10 + c - '0'; c = in.read(); } return value;
        }
    }
    public static void main(String[] args) throws Exception {
        FastInput input = new FastInput(); int m = input.nextInt(), n = input.nextInt(), best = 0;
        int[] vertical = new int[n], diagonal = new int[n], anti = new int[n+1];
        for (int r = 0; r < m; r++) {
            int horizontal = 0, previousDiagonal = 0;
            for (int c = 0; c < n; c++) {
                int oldDiagonal = diagonal[c], value = input.nextInt();
                if (value == 1) { horizontal++; vertical[c]++; diagonal[c] = previousDiagonal + 1; anti[c] = anti[c+1] + 1; }
                else { horizontal = 0; vertical[c] = 0; diagonal[c] = 0; anti[c] = 0; }
                previousDiagonal = oldDiagonal; best = Math.max(best, Math.max(horizontal,Math.max(vertical[c],Math.max(diagonal[c],anti[c]))));
            }
        }
        System.out.print(best);
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
