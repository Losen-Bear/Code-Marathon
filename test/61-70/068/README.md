# 068 找到高频数 — Java 题解

原题面：[题目记录](../test61-70.md)，原记录标题 `068 找到高频数`。实际题号以本文件标题为准。

## 解题思路

哈希计数后将不同数字按频率降序排序，取前k个，再将取出的数字按数值升序排序，以逗号连接。

## 正确性说明

频率降序的前k项恰好是出现次数最多的k种数字。最后的数值排序只改变展示顺序，不改变所选集合。

## 复杂度

时间O(n+u log u)，空间O(u)，u为不同数字个数。

## 题面与格式说明

输出无空格、无末尾逗号。题目保证选出的集合唯一；频率相同的内部顺序用数值排序确定。

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
        String[] lines = read().trim().split("\\R"); long[] a = numbers(lines[0]); int k = (int) numbers(lines[1])[0];
        HashMap<Long, Integer> frequency = new HashMap<>(); for (long x : a) frequency.put(x, frequency.getOrDefault(x, 0) + 1);
        ArrayList<Long> values = new ArrayList<>(frequency.keySet());
        values.sort((x,y) -> { int c = Integer.compare(frequency.get(y), frequency.get(x)); return c != 0 ? c : Long.compare(x,y); });
        ArrayList<Long> chosen = new ArrayList<>(values.subList(0,k)); Collections.sort(chosen); StringBuilder out = new StringBuilder();
        for (long x : chosen) { if (out.length() > 0) out.append(','); out.append(x); } System.out.print(out);
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
