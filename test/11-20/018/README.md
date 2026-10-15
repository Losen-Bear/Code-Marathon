# 018 关于多项式 — Java 题解

原题面：[本组题目](../test11-20.md)，查找题号 `018`。原记录保留不改。

## 解题思路

用导数3px²+2qx+r的两个零点将[-100,100]分成三个单调区间。在每个区间用二分查找一个实根，按区间顺序输出。

## 正确性说明

三个不同实根保证导数有两个临界点，根分别位于三个单调区间。单调区间内至多一个根，二分根据函数符号保留含根区间，固定迭代次数得到足够的两位小数精度。

## 复杂度

时间 O(迭代次数)，空间 O(1)。

## 题面与格式说明

不依赖“相邻根距离至少1”的额外假设；支持间距小于1的三根。

## Java 实现

**提交方式：选择 Java，将下面代码块中的全部代码复制到提交框。**包含 `import`、`public class Main`、`main` 入口和全部辅助方法，无需拼接原题 Hint，也无需修改类名。

源文件：[Main.java](Main.java)。兼容 Java 8 及以上，无包名、无第三方依赖。

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static double p, q, r, s;
    static double f(double x) { return ((p * x + q) * x + r) * x + s; }
    static double root(double left, double right) {
        if (f(left) == 0) return left;
        if (f(right) == 0) return right;
        for (int i = 0; i < 100; i++) {
            double mid = (left + right) / 2;
            if ((f(left) < 0) == (f(mid) < 0)) left = mid; else right = mid;
        }
        double result = (left + right) / 2;
        return Math.abs(result) < 0.0000001 ? 0 : result;
    }
    public static void main(String[] args) throws Exception {
        String[] input = read().trim().split("\\s+");
        p = Double.parseDouble(input[0]); q = Double.parseDouble(input[1]); r = Double.parseDouble(input[2]); s = Double.parseDouble(input[3]);
        double d = Math.sqrt(q * q - 3 * p * r);
        double a = (-q - d) / (3 * p), b = (-q + d) / (3 * p);
        double first = Math.min(a, b), second = Math.max(a, b);
        System.out.printf(Locale.ROOT, "%.2f %.2f %.2f", root(-100, first), root(first, second), root(second, 100));
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
