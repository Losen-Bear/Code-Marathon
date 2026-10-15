# 007 能量核心 — Java 题解

原题面：[本组题目](../test1_10.md)，查找题号 `007`。原记录保留不改。

## 解题思路

逐格扫描，每遇到未访问的非0格子就增加簇数，并用队列 BFS 标记它所在的上下左右连通块。

## 正确性说明

一个能量簇就是一个非0格子的四连通分量。BFS 恰好遍历当前分量，之后不会再次计数；不同分量互不可达，都会各被计数一次。

## 复杂度

时间 O(nm)，空间 O(nm)。

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
        String[] t = read().trim().split("\\s+"); int n = Integer.parseInt(t[0]), m = Integer.parseInt(t[1]);
        char[][] a = new char[n][]; for (int i = 0; i < n; i++) a[i] = t[i + 2].toCharArray();
        int answer = 0; int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) if (a[i][j] != '0') {
            answer++; a[i][j] = '0'; queue.add(i * m + j);
            while (!queue.isEmpty()) {
                int p = queue.remove(), r = p / m, c = p % m;
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d], nc = c + dc[d];
                    if (nr >= 0 && nr < n && nc >= 0 && nc < m && a[nr][nc] != '0') {
                        a[nr][nc] = '0'; queue.add(nr * m + nc);
                    }
                }
            }
        }
        System.out.print(answer);
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
