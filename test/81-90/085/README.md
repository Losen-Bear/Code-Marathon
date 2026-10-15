# 085 机器人迷宫探索 — Java 题解

原题面：[题目记录](../test81-90.md)，原记录标题 `075 机器人迷宫探索`。实际题号以本文件标题为准。

## 解题思路

按左、上、右、下的固定顺序DFS枚举所有简单路径。visited标记当前路径内格子，抵达终点立即输出当前坐标序列，回溯时清除标记。输出用BufferedWriter流式写出。

## 正确性说明

每次只访问可行且未使用的四邻格，因此生成路径合法。所有合法路径的每一步都出现在四方向枚举中，故没有遗漏；visited使路径不重复格子，不同分支坐标序列不同。

## 复杂度

输出敏感、最坏指数级；粗略时间O(V·3^V)，空间O(V)，V为可行格数。

## 题面与格式说明

上传文件标为075，实际085。坐标从1开始，按指定DFS顺序输出。上传样例1最后一行抄反了两个格子；网页实际为`1 1 1 0 1 1`，对应原有12条输出路径，详见[样例勘误](ERRATA.md)。 原题记录中的标题编号为075，本题解按比赛网页编号085归档。

## Java 实现

**提交方式：选择 Java，将下面代码块中的全部代码复制到提交框。**包含 `import`、`public class Main`、`main` 入口和全部辅助方法，无需拼接原题 Hint，也无需修改类名。

源文件：[Main.java](Main.java)。兼容 Java 8 及以上，无包名、无第三方依赖。

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static int[][] grid; static boolean[][] visited; static int endRow,endColumn;
    static int[] path; static BufferedWriter writer; static boolean found;
    static void search(int r, int c, int length) throws IOException {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0 || visited[r][c]) return;
        path[length++] = r*grid[0].length+c;
        if (r == endRow && c == endColumn) {
            if (found) writer.newLine(); found = true;
            for (int i = 0; i < length; i++) {
                if (i > 0) writer.write("->"); int p = path[i];
                writer.write("("+(p/grid[0].length+1)+","+(p%grid[0].length+1)+")");
            }
            return;
        }
        visited[r][c] = true;
        search(r,c-1,length); search(r-1,c,length); search(r,c+1,length); search(r+1,c,length);
        visited[r][c] = false;
    }
    public static void main(String[] args) throws Exception {
        int[] a = integers(read()); int m = a[0], n = a[1], position = 2; grid = new int[m][n]; visited = new boolean[m][n]; path = new int[m*n];
        for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) grid[i][j] = a[position++];
        int startRow = a[position++]-1, startColumn = a[position++]-1; endRow = a[position++]-1; endColumn = a[position]-1;
        writer = new BufferedWriter(new OutputStreamWriter(System.out)); search(startRow,startColumn,0);
        if (!found) writer.write("-1"); writer.flush();
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
