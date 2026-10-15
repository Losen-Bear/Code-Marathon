# 036 寻找字符串 — Java 题解

原题面：[本组题目](../test31-40.md)，查找题号 `036`。原记录保留不改。

## 解题思路

每个与首字符相同的格子作为起点，DFS沿上下左右搜索下一字符，visited禁止同一路径重复使用格子。回溯时恢复标记，找到完整单词就返回true。

## 正确性说明

DFS状态逐位保证字符和位置顺序正确，visited保证每格最多使用一次；枚举所有起点及合法邻格覆盖所有可能路径，因此返回true当且仅当单词存在。

## 复杂度

最坏时间 O(mn·3^L)，空间 O(mn+L)，L为单词长度。

## 题面与格式说明

原文未明确邻接方向及能否重复；按经典单词搜索与样例解释为上下左右相邻、单个格子不可重复。若OJ另有规则，需要据此调整。

## Java 实现

**提交方式：选择 Java，将下面代码块中的全部代码复制到提交框。**包含 `import`、`public class Main`、`main` 入口和全部辅助方法，无需拼接原题 Hint，也无需修改类名。

源文件：[Main.java](Main.java)。兼容 Java 8 及以上，无包名、无第三方依赖。

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static char[][] board; static boolean[][] visited; static String word;
    static boolean dfs(int r, int c, int index) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || visited[r][c] || board[r][c] != word.charAt(index)) return false;
        if (index + 1 == word.length()) return true;
        visited[r][c] = true;
        boolean found = dfs(r - 1, c, index + 1) || dfs(r + 1, c, index + 1) || dfs(r, c - 1, index + 1) || dfs(r, c + 1, index + 1);
        visited[r][c] = false; return found;
    }
    public static void main(String[] args) throws Exception {
        String[] t = read().trim().split("\\s+"); word = t[0]; int m = Integer.parseInt(t[1]), n = Integer.parseInt(t[2]);
        board = new char[m][n]; visited = new boolean[m][n];
        for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) board[i][j] = t[3 + i * n + j].charAt(0);
        if (word.length() > m * n) { System.out.print("false"); return; }
        for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) if (dfs(i, j, 0)) { System.out.print("true"); return; }
        System.out.print("false");
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

## 验证

本地样例和边界验证见 [cases.json](cases.json)；实际执行结果见仓库根目录 [VALIDATION.md](../../../VALIDATION.md)。未提交到 OJ，不标记为 AC。
