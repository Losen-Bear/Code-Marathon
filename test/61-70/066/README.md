# 066 字符串变形 — Java 题解

原题面：[题目记录](../test61-70.md)，原记录标题 `066 字符串变形`。实际题号以本文件标题为准。

## 解题思路

排序字符后反复求下一个字典序排列：从右找第一个上升位置，交换右侧恰好更大的字符，再逆序其后缀。用BufferedWriter逐行输出。

## 正确性说明

选最右可增大的位置使前缀尽可能长不变，再选最小更大字符并将后缀升序，得到严格最小的后继排列。从最小排列开始遍历，覆盖全部不同排列且不重复。

## 复杂度

输出R个排列时，时间O(nR)，空间O(n)。

## 题面与格式说明

题面未给长度上限；排列输出本身可能阶乘级。字符顺序按Java UTF-16字典序，示例字母可直接适用。

## Java 实现

**提交方式：选择 Java，将下面代码块中的全部代码复制到提交框。**包含 `import`、`public class Main`、`main` 入口和全部辅助方法，无需拼接原题 Hint，也无需修改类名。

源文件：[Main.java](Main.java)。兼容 Java 8 及以上，无包名、无第三方依赖。

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static boolean next(char[] a) {
        int i = a.length - 2; while (i >= 0 && a[i] >= a[i + 1]) i--; if (i < 0) return false;
        int j = a.length - 1; while (a[j] <= a[i]) j--; char t = a[i]; a[i] = a[j]; a[j] = t;
        for (int l = i + 1, r = a.length - 1; l < r; l++, r--) { t = a[l]; a[l] = a[r]; a[r] = t; }
        return true;
    }
    public static void main(String[] args) throws Exception {
        char[] a = read().trim().toCharArray(); Arrays.sort(a); BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out, java.nio.charset.StandardCharsets.UTF_8));
        boolean first = true;
        do { if (!first) out.newLine(); first = false; out.write(a); } while (next(a)); out.flush();
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
