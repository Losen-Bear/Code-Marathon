# 001 光轨城市：信号优化计划 — Java 题解

原题面：[本组题目](../test1_10.md)，查找题号 `001`。原记录保留不改。

## 解题思路

二分答案 k。k=1 时只能得到两种交替串，分别统计需要翻转的位数；k≥2 时，每个长度 L 的连续段需要 L/(k+1) 次翻转。累加后与 numOps 比较。

## 正确性说明

每 k+1 个原来相同的连续字符至少要翻转一个，否则仍存在超长段。k≥2 时可以把翻转点放在段内，满足下界并避免与邻段合并；因此累加值就是所需最少次数。可行性随 k 增大单调，二分得到最小可行值。

## 复杂度

时间 O(n log n)，额外空间 O(1)。

## Java 实现

**提交方式：选择 Java，将下面代码块中的全部代码复制到提交框。**包含 `import`、`public class Main`、`main` 入口和全部辅助方法，无需拼接原题 Hint，也无需修改类名。

源文件：[Main.java](Main.java)。兼容 Java 8 及以上，无包名、无第三方依赖。

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static boolean possible(String s, int limit, long operations) {
        long needed = 0;
        if (limit == 1) {
            int different = 0;
            for (int i = 0; i < s.length(); i++)
                if (s.charAt(i) - '0' != (i & 1)) different++;
            return Math.min(different, s.length() - different) <= operations;
        }
        for (int i = 0; i < s.length();) {
            int j = i + 1;
            while (j < s.length() && s.charAt(j) == s.charAt(i)) j++;
            needed += (j - i) / (limit + 1);
            if (needed > operations) return false;
            i = j;
        }
        return true;
    }
    public static void main(String[] args) throws Exception {
        String[] input = read().trim().split("\\s+");
        String s = input[0]; long operations = Long.parseLong(input[1]);
        int lo = 1, hi = s.length();
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (possible(s, mid, operations)) hi = mid; else lo = mid + 1;
        }
        System.out.print(lo);
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
