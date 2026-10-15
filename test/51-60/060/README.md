# 060 找数游戏 — Java 题解

原题面：[本组题目](../test51-60.md)，查找题号 `060`。原记录保留不改。

## 解题思路

按模3余数做DP。dp[r]为已处理元素中和余数为r的最大可达子集和，初始仅dp[0]=0。每个元素从旧数组转移，选择或跳过它。

## 正确性说明

每个子集要么不含当前元素，要么由旧子集加入当前元素形成，复制旧DP再转移正好覆盖两类且每个元素最多选一次。最终dp[0]就是最大可整除3的和。

## 复杂度

时间 O(n)，算法额外空间 O(1)。

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
        long impossible = Long.MIN_VALUE / 4; long[] dp = {0, impossible, impossible};
        for (long value : numbers(read())) {
            long[] next = dp.clone();
            for (int r = 0; r < 3; r++) if (dp[r] != impossible) { int rem = (int) ((r + value) % 3); next[rem] = Math.max(next[rem], dp[r] + value); }
            dp = next;
        }
        System.out.print(dp[0]);
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
