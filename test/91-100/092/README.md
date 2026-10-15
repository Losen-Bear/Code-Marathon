# 092 计算数组积分 — Java 题解

原题面：[题目记录](../test91-100.md)，原记录标题 `092 计算数组积分`。实际题号以本文件标题为准。

## 解题思路

预计算每对数的gcd，用mask记录已删元素。若已删2t个，下一对的乘数为t+1；枚举所有未用元素对，更新新mask的最高得分。

## 正确性说明

任意操作序列逐次选一对，对应一条状态链；乘数由已处理对数确定。每个状态保留最大得分，后续只依赖剩余元素和轮次，所以最优子结构成立。

## 复杂度

时间O(M²·2^M)，空间O(2^M+M²)，M=2n≤14。

## 题面与格式说明

不能强制每次配对包含最小未用下标：操作轮次有不同乘数，这种去重会漏掉最优顺序。

## Java 实现

**提交方式：选择 Java，将下面代码块中的全部代码复制到提交框。**包含 `import`、`public class Main`、`main` 入口和全部辅助方法，无需拼接原题 Hint，也无需修改类名。

源文件：[Main.java](Main.java)。兼容 Java 8 及以上，无包名、无第三方依赖。

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static long gcd(long a, long b) { while (b != 0) { long r = a%b; a = b; b = r; } return a; }
    public static void main(String[] args) throws Exception {
        long[] a = numbers(read()); int m = a.length; long[][] common = new long[m][m];
        for (int i = 0; i < m; i++) for (int j = i+1; j < m; j++) common[i][j] = gcd(a[i],a[j]);
        long[] dp = new long[1<<m]; Arrays.fill(dp,-1); dp[0] = 0;
        for (int mask = 0; mask < dp.length; mask++) if (dp[mask] >= 0 && (Integer.bitCount(mask)&1) == 0) {
            int operation = Integer.bitCount(mask)/2+1;
            for (int i = 0; i < m; i++) if ((mask & (1<<i)) == 0)
                for (int j = i+1; j < m; j++) if ((mask & (1<<j)) == 0) { int next = mask|(1<<i)|(1<<j); dp[next] = Math.max(dp[next],dp[mask]+operation*common[i][j]); }
        }
        System.out.print(dp[dp.length-1]);
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
