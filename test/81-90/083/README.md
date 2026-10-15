# 083 货物运输 — Java 题解

原题面：[题目记录](../test81-90.md)，原记录标题 `073 货物运输`。实际题号以本文件标题为准。

## 解题思路

将货物按目的仓库排序。dp[x]是到达仓库x时最大利润，可从dp[x-1]空车前进，或接受任一在x结束的货物，利润为dp[start]+x-start+bonus。

## 正确性说明

最优方案在x的最后动作要么是空车前进，要么是完成一批在x结束的货物。该货物之前必须在起点已经空闲，故由dp[start]衔接。两类完整且不重叠运输。

## 复杂度

时间O(n+m log m)，空间O(n+m)，利润用long。

## 题面与格式说明

上传文件标为073，实际083。上一批目的地等于下一批起点时允许衔接。 原题记录中的标题编号为073，本题解按比赛网页编号083归档。

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
        long[] input = numbers(read()); int n = (int) input[0], m = (int) input[1]; long[][] goods = new long[m][3];
        for (int i = 0; i < m; i++) for (int j = 0; j < 3; j++) goods[i][j] = input[2+i*3+j];
        Arrays.sort(goods,Comparator.comparingLong(x -> x[1])); long[] dp = new long[n+1]; int next = 0;
        for (int x = 1; x <= n; x++) {
            dp[x] = dp[x-1];
            while (next < m && goods[next][1] == x) {
                long[] g = goods[next++]; dp[x] = Math.max(dp[x],dp[(int)g[0]]+g[1]-g[0]+g[2]);
            }
        }
        System.out.print(dp[n]);
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
