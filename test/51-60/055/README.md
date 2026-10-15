# 055 玩游戏的一天 — Java 题解

原题面：[本组题目](../test51-60.md)，查找题号 `055`。原记录保留不改。

## 解题思路

对每个候选单词做DFS，八方向移动，并用visited禁止重复格子。长度超过格子数直接排除；找到的单词放入TreeSet，按字典序输出。

## 正确性说明

按单词顺序匹配字符、八方向枚举所有邻格、visited限制一次使用，恰好与Boggle规则一致。逐单词判断后TreeSet只输出存在且不重复的单词。

## 复杂度

最坏时间 O(k·mn·7^L)，空间 O(mn+L+结果)，L≤mn≤16。

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
        visited[r][c] = true; boolean found = false;
        for (int dr = -1; dr <= 1 && !found; dr++) for (int dc = -1; dc <= 1 && !found; dc++)
            if (dr != 0 || dc != 0) found = dfs(r + dr, c + dc, index + 1);
        visited[r][c] = false; return found;
    }
    public static void main(String[] args) throws Exception {
        String[] t = read().trim().split("\\s+"); int m = Integer.parseInt(t[0]), n = Integer.parseInt(t[1]);
        board = new char[m][n]; visited = new boolean[m][n]; int position = 2;
        for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) board[i][j] = t[position++].charAt(0);
        int k = Integer.parseInt(t[position++]); TreeSet<String> result = new TreeSet<>();
        for (int index = 0; index < k; index++) {
            word = t[position++]; if (word.length() > m * n) continue; boolean found = false;
            for (int i = 0; i < m && !found; i++) for (int j = 0; j < n && !found; j++) found = dfs(i, j, 0);
            if (found) result.add(word);
        }
        System.out.print(String.join("\n", result));
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
