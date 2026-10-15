# 091 智慧码头 — Java 题解

原题面：[题目记录](../test91-100.md)，原记录标题 `091 智慧码头`。实际题号以本文件标题为准。

## 解题思路

将返程B反向看作另一辆同向车，二者同时从左上角到右下角。第t步每辆车列号由t-行号确定，状态dp[r1][r2]记录两车所在行的最大累计货物。枚举二者上一步来自上方或左方的4种组合。

## 正确性说明

反向路径保留经过格子和总货物。单调路径经过某格必在固定步数r+c，因此两车在同一格提货时可在同层统一计数。不同格每辆最多取1，同格按库存最多取2。四种转移枚举全部合法路径对，取最大值正确。

## 复杂度

时间O(N³)，空间O(N²)。

## 题面与格式说明

按“每辆车每格最多取一件”理解：两车同格且库存为2时可共取2件，库存为1时只能共取1件。0格允许通行，只是没有货物。

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
        int[] input = integers(read()); int n = input[0]; int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) grid[i][j] = input[1+i*n+j];
        long impossible = Long.MIN_VALUE/4; long[][] dp = new long[n][n]; for (long[] row : dp) Arrays.fill(row,impossible); dp[0][0] = Math.min(2,grid[0][0]);
        for (int step = 1; step <= 2*(n-1); step++) {
            long[][] next = new long[n][n]; for (long[] row : next) Arrays.fill(row,impossible);
            for (int r1 = Math.max(0,step-n+1); r1 <= Math.min(n-1,step); r1++)
                for (int r2 = Math.max(0,step-n+1); r2 <= Math.min(n-1,step); r2++) {
                    long best = impossible;
                    for (int a = 0; a <= 1; a++) for (int b = 0; b <= 1; b++) if (r1-a >= 0 && r2-b >= 0) best = Math.max(best,dp[r1-a][r2-b]);
                    if (best == impossible) continue;
                    int gain = r1 == r2 ? Math.min(2,grid[r1][step-r1]) : Math.min(1,grid[r1][step-r1])+Math.min(1,grid[r2][step-r2]);
                    next[r1][r2] = best+gain;
                }
            dp = next;
        }
        System.out.print(dp[n-1][n-1]);
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
