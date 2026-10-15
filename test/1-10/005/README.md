# 005 泡泡堂 — Java 题解

原题面：[本组题目](../test1_10.md)，查找题号 `005`。原记录保留不改。

## 解题思路

按障碍划分行段与列段。扫描某行段的起点时统计整段敌人数；列段同理缓存到 col[j]。每个 S 格子的得分就是行段敌人数加列段敌人数。

## 正确性说明

障碍外的敌人不可达，段内敌人全部可达。放置格子本身为空，行列敌人集合没有交集，因此相加既不遗漏也不重复。

## 复杂度

时间 O(mn)，额外空间 O(n)，输入矩阵空间 O(mn)。

## 题面与格式说明

输入沿用 Hint 的 {{'E',...},{...}} 格式，支持换行和空格；题面“二维整数数组”应为字符数组。样例中的“输入：”“输出：”是展示前缀。

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
        String raw = read(); ArrayList<char[]> rows = new ArrayList<>();
        Matcher matcher = Pattern.compile("[\\{\\[]([^{}\\[\\]]*)[\\}\\]]").matcher(raw);
        while (matcher.find()) {
            String row = matcher.group(1).replaceAll("[^EBS]", "");
            if (!row.isEmpty()) rows.add(row.toCharArray());
        }
        if (rows.isEmpty()) throw new IllegalArgumentException("输入应为题面给出的二维字符数组");
        char[][] grid = rows.toArray(new char[0][]);
        int m = grid.length, n = grid[0].length, best = 0, rowCount = 0;
        int[] columnCount = new int[n];
        for (int i = 0; i < m; i++) {
            if (grid[i].length != n) throw new IllegalArgumentException("矩阵行长度不一致");
            for (int j = 0; j < n; j++) {
                if (j == 0 || grid[i][j - 1] == 'B') {
                    rowCount = 0;
                    for (int k = j; k < n && grid[i][k] != 'B'; k++) if (grid[i][k] == 'E') rowCount++;
                }
                if (i == 0 || grid[i - 1][j] == 'B') {
                    columnCount[j] = 0;
                    for (int k = i; k < m && grid[k][j] != 'B'; k++) if (grid[k][j] == 'E') columnCount[j]++;
                }
                if (grid[i][j] == 'S') best = Math.max(best, rowCount + columnCount[j]);
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

## 验证

本地样例和边界验证见 [cases.json](cases.json)；实际执行结果见仓库根目录 [VALIDATION.md](../../../VALIDATION.md)。未提交到 OJ，不标记为 AC。
