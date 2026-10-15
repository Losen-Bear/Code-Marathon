# 078 走地板 — Java 题解

原题面：[题目记录](../test71-80.md)，原记录标题 `078 走地板`。实际题号以本文件标题为准。

## 解题思路

从每个非0格子出发DFS。访问时暂时把该格设为0，递归四邻格，结束后恢复原值；维护所有简单路径的最大数字和。若找到全部正值之和，可以直接结束搜索。

## 正确性说明

临时置0使同一路径不能重复格子，四邻枚举覆盖所有合法下一步。枚举所有起点及所有可走分支覆盖全部合法路径，累加值取最大即答案。正值总和是任意路径的上界，达到后可安全结束。

## 复杂度

最坏时间指数级，粗略O(V·3^V)，空间O(V)，V为可走格数。

## 题面与格式说明

题面没有给出网格/非零格子上限。精确求最长简单路径可能指数级，最大规模性能未经过OJ验证。

## Java 实现

**提交方式：选择 Java，将下面代码块中的全部代码复制到提交框。**包含 `import`、`public class Main`、`main` 入口和全部辅助方法，无需拼接原题 Hint，也无需修改类名。

源文件：[Main.java](Main.java)。兼容 Java 8 及以上，无包名、无第三方依赖。

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static long[][] grid; static long best = Long.MIN_VALUE, upper;
    static void search(int r, int c, long total) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0 || best == upper) return;
        long value = grid[r][c]; total += value; best = Math.max(best,total); grid[r][c] = 0;
        search(r-1,c,total); search(r+1,c,total); search(r,c-1,total); search(r,c+1,total); grid[r][c] = value;
    }
    public static void main(String[] args) throws Exception {
        long[] input = numbers(read()); int m = (int) input[0], n = (int) input[1]; grid = new long[m][n];
        for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) { grid[i][j] = input[2+i*n+j]; upper += Math.max(0,grid[i][j]); }
        for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) search(i,j,0);
        System.out.print(best == Long.MIN_VALUE ? 0 : best);
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
