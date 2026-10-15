# 053 下一个漂亮数 — Java 题解

原题面：[本组题目](../test51-60.md)，查找题号 `053`。原记录保留不改。

## 解题思路

漂亮数的某个数字d一旦出现，必须恰好出现d次，且0不能出现。枚举1到9哪些数字出现，按它们规定的频率生成所有不超过9位的排列，取严格大于n的最小值。

## 正确性说明

任何漂亮数对应唯一数字子集与该频率集合的一个排列，枚举覆盖全部候选。n≤10^8，而999999999是9位漂亮数，所以只枚举至多9位就一定能找到下一项。取最小的大于n值满足“下一个”。

## 复杂度

时间 O(Σ L! / ∏d! · L)，和遍历的合法数字多重集排列相关，L≤9；递归空间 O(9)。

## Java 实现

**提交方式：选择 Java，将下面代码块中的全部代码复制到提交框。**包含 `import`、`public class Main`、`main` 入口和全部辅助方法，无需拼接原题 Hint，也无需修改类名。

源文件：[Main.java](Main.java)。兼容 Java 8 及以上，无包名、无第三方依赖。

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static long n, best = Long.MAX_VALUE; static int[] counts = new int[10];
    static void generate(long prefix, int remaining) {
        if (remaining == 0) { if (prefix > n) best = Math.min(best, prefix); return; }
        for (int d = 1; d <= 9; d++) if (counts[d] > 0) { counts[d]--; generate(prefix * 10 + d, remaining - 1); counts[d]++; }
    }
    public static void main(String[] args) throws Exception {
        n = numbers(read())[0]; int minLength = Long.toString(n).length();
        for (int mask = 1; mask < (1 << 9); mask++) {
            Arrays.fill(counts, 0); int length = 0;
            for (int d = 1; d <= 9; d++) if ((mask & (1 << (d - 1))) != 0) { counts[d] = d; length += d; }
            if (length >= minLength && length <= 9) generate(0, length);
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
