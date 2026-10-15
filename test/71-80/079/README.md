# 079 找到中位数 — Java 题解

原题面：[题目记录](../test71-80.md)，原记录标题 `079 找到中位数`。实际题号以本文件标题为准。

## 解题思路

两个指针归并升序数组，只遍历到合并后中间位置，保存当前值和前一个值。奇数长度直接输出中间值，偶数长度取二者平均。用BigDecimal输出整数或.5，避免相加溢出。

## 正确性说明

归并每次取两个未处理最小值，所以输出顺序等于整体排序。中位数只取中间一个或两个位置，停在这些位置即可得到定义要求的值。

## 复杂度

时间O(m+n)，算法空间O(1)，输入解析O(m+n)。

## 题面与格式说明

空数组用空行表示。题目保证至少一个元素；整数中位数不输出.0。

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
        BufferedReader reader = new BufferedReader(new StringReader(read())); String line = reader.readLine(); long[] a = numbers(line == null ? "" : line);
        line = reader.readLine(); long[] b = numbers(line == null ? "" : line); int size = a.length+b.length, i = 0, j = 0; long previous = 0, current = 0;
        for (int k = 0; k <= size/2; k++) { previous = current; current = j == b.length || (i < a.length && a[i] <= b[j]) ? a[i++] : b[j++]; }
        if ((size & 1) == 1) System.out.print(current);
        else System.out.print(java.math.BigDecimal.valueOf(previous).add(java.math.BigDecimal.valueOf(current)).divide(java.math.BigDecimal.valueOf(2)).stripTrailingZeros().toPlainString());
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
