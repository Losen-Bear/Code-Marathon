# 063 一碗水端平 — Java 题解

原题面：[题目记录](../test61-70.md)，原记录标题 `063 一碗水端平`。实际题号以本文件标题为准。

## 解题思路

篮子数量最多8，降序排列后回溯分配给k个孩子。维护每人总数与当前最好答案，超过最好值的分支剪掉；相同当前总数的孩子只尝试一次，消除对称分配。

## 正确性说明

每个篮子恰好分给一人，枚举覆盖所有分配。当前负担只能增大，所以不优于已有答案的分支可安全剪枝。当前负担相同的孩子可互换，跳过对称选择不影响最小最大负担。

## 复杂度

最坏时间O(k^n·k)，空间O(n+k)，n≤8。

## 题面与格式说明

按水果数量为非负整数处理；题面没有单独列出每篮数量上限，累加使用long。

## Java 实现

**提交方式：选择 Java，将下面代码块中的全部代码复制到提交框。**包含 `import`、`public class Main`、`main` 入口和全部辅助方法，无需拼接原题 Hint，也无需修改类名。

源文件：[Main.java](Main.java)。兼容 Java 8 及以上，无包名、无第三方依赖。

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static long[] fruits, loads; static long best;
    static void search(int index, long maximum) {
        if (maximum >= best) return;
        if (index < 0) { best = maximum; return; }
        HashSet<Long> tried = new HashSet<>();
        for (int child = 0; child < loads.length; child++) {
            if (!tried.add(loads[child])) continue;
            loads[child] += fruits[index]; search(index - 1, Math.max(maximum, loads[child])); loads[child] -= fruits[index];
        }
    }
    public static void main(String[] args) throws Exception {
        String[] lines = read().trim().split("\\R"); fruits = numbers(lines[0]); loads = new long[(int) numbers(lines[1])[0]];
        Arrays.sort(fruits); best = 0; for (long x : fruits) best += x;
        search(fruits.length - 1, 0); System.out.print(best);
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
