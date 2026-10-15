# 019 最大利益日 — Java 题解

原题面：[本组题目](../test11-20.md)，查找题号 `019`。原记录保留不改。

## 解题思路

Kadane算法。ending表示必须以当前元素结尾的最大子数组和，转移为max(x,ending+x)，同时维护全局最大值。

## 正确性说明

以当前点结尾的非空连续子数组，要么只含当前元素，要么由前一位置的最优结尾子数组扩展。两种情况覆盖所有候选，全局最大值覆盖所有结尾位置。

## 复杂度

时间 O(n)，核心DP空间 O(1)，输入解析空间 O(n)。

## 题面与格式说明

全负数组也必须选非空子数组，因此不能把答案初始化为0。

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
        long[] input = numbers(read()); int n = (int) input[0];
        long ending = input[1], best = ending;
        for (int i = 2; i <= n; i++) { ending = Math.max(input[i], ending + input[i]); best = Math.max(best, ending); }
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
