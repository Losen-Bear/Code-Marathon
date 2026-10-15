# 040 走出网格 — Java 题解

原题面：[本组题目](../test31-40.md)，查找题号 `040`。原记录保留不改。

## 解题思路

按照样例顺序读取m,n,maxMove,startRow,startColumn。逐步DP记录尚在网格内的路径数，向四邻格转移；越界时加入答案，不再继续扩展。

## 正确性说明

dp在每一步恰好统计此前始终未越界的路径。所有下一步要么仍在网格内、要么首次越界，两类互斥且完整，因此累计越界数就是至多maxMove步离开的全部路径。

## 复杂度

时间 O(maxMove·mn)，空间 O(mn)。

## 题面与格式说明

描述和样例的参数顺序存在差异；Hint明确要求采用Sample Input顺序，所以第三项是maxMove。

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
        int[] a = integers(read()); int m = a[0], n = a[1], moves = a[2], row = a[3], column = a[4];
        long mod = 1000000007L, answer = 0; long[][] dp = new long[m][n]; dp[row][column] = 1;
        int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
        for (int step = 0; step < moves; step++) {
            long[][] next = new long[m][n];
            for (int r = 0; r < m; r++) for (int c = 0; c < n; c++) for (int d = 0; d < 4; d++) {
                int nr = r + dr[d], nc = c + dc[d];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) answer = (answer + dp[r][c]) % mod;
                else next[nr][nc] = (next[nr][nc] + dp[r][c]) % mod;
            }
            dp = next;
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
