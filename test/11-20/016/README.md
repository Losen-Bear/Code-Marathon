# 016 科学分汤 — Java 题解

原题面：[本组题目](../test11-20.md)，查找题号 `016`。原记录保留不改。

## 解题思路

以25毫升为单位向上取整，设dp[a][b]为剩余两种汤时的目标概率，四种操作等概率转移。a=b=0返回0.5，只有a≤0返回1，只有b≤0返回0。用二维迭代避免递归栈溢出，n≥25000时输出1.00000。

## 正确性说明

25毫升缩放不改变哪一回合首次耗尽，递推由全概率公式得到。对大n，设单位数u，每轮鸡汤消耗在1至4均匀取值，排骨汤消耗为4减去鸡汤消耗。排骨汤先耗尽或同时耗尽只可能在t≤u时出现：t≤u/2时要求排骨汤累计消耗≥u；t≥u/2时，鸡汤上一轮未耗尽，所以其本轮累计消耗≤u+3（最后一轮最多加4）。用独立有界变量的Hoeffding尾界及至多u轮的并集上界，得到1-dp[u][u]≤u·exp(-4(u/4-3)²/(9u))。u≥1000时此界小于10^-8，足够保证保留5位小数为1.00000。

## 复杂度

小n时间、空间 O((ceil(n/25))²)；n≥25000时 O(1)。

## 题面与格式说明

大n采用数值近似，并非声称概率恰好为1；输出固定5位小数。

## Java 实现

**提交方式：选择 Java，将下面代码块中的全部代码复制到提交框。**包含 `import`、`public class Main`、`main` 入口和全部辅助方法，无需拼接原题 Hint，也无需修改类名。

源文件：[Main.java](Main.java)。兼容 Java 8 及以上，无包名、无第三方依赖。

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static double value(double[][] dp, int a, int b) {
        if (a <= 0 && b <= 0) return 0.5;
        if (a <= 0) return 1;
        if (b <= 0) return 0;
        return dp[a][b];
    }
    public static void main(String[] args) throws Exception {
        long n = numbers(read())[0];
        if (n >= 25000) { System.out.print("1.00000"); return; }
        int units = (int) ((n + 24) / 25); double[][] dp = new double[units + 1][units + 1];
        for (int a = 1; a <= units; a++) for (int b = 1; b <= units; b++)
            dp[a][b] = (value(dp, a - 4, b) + value(dp, a - 3, b - 1)
                + value(dp, a - 2, b - 2) + value(dp, a - 1, b - 3)) / 4;
        System.out.printf(Locale.ROOT, "%.5f", value(dp, units, units));
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
