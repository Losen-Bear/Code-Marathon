# 070 多多参加采访 — Java 题解

原题面：[题目记录](../test61-70.md)，原记录标题 `070 多多参加采访`。实际题号以本文件标题为准。

## 解题思路

按结束时间升序选择采访。当前采访的开始时间严格大于上一场结束时间时接受，否则拒绝。答案为总场次减接受数量。

## 正确性说明

最早结束的采访占用后续时间最少；把任意最优方案的首场换成它不会减少可选择的后续采访，递归使用相同交换论证得到最多接受场次。

## 复杂度

时间O(n log n)，空间O(n)。

## 题面与格式说明

时间区间是闭区间，因此首尾相接仍冲突，判断必须用>，不能用>=。输入换行或空格均可。

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
        long[] input = numbers(read()); int n = (int) input[0]; long[][] a = new long[n][2];
        for (int i = 0; i < n; i++) { a[i][0] = input[2*i+1]; a[i][1] = input[2*i+2]; }
        Arrays.sort(a, Comparator.comparingLong(x -> x[1])); long end = Long.MIN_VALUE; int accepted = 0;
        for (long[] interval : a) if (interval[0] > end) { accepted++; end = interval[1]; }
        System.out.print(n - accepted);
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
