# 065 逆序对的总数 — Java 题解

原题面：[题目记录](../test61-70.md)，原记录标题 `065 逆序对的总数`。实际题号以本文件标题为准。

## 解题思路

归并排序计数。合并两段时，若右段当前值小于左段当前值，它与左段尚未合并的所有元素构成逆序对，增加mid-i。

## 正确性说明

递归统计各半段内部，合并统计跨半段。左半段有序，右值小于当前左值时剩余左值都更大，精确增加这些数对。三类互斥覆盖全部逆序对。

## 复杂度

时间O(n log n)，空间O(n)，计数使用long。

## 题面与格式说明

按原题输入输出格式实现。

## Java 实现

**提交方式：选择 Java，将下面代码块中的全部代码复制到提交框。**包含 `import`、`public class Main`、`main` 入口和全部辅助方法，无需拼接原题 Hint，也无需修改类名。

源文件：[Main.java](Main.java)。兼容 Java 8 及以上，无包名、无第三方依赖。

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static long[] a, temp;
    static long count(int left, int right) {
        if (right - left <= 1) return 0; int mid = (left + right) / 2;
        long answer = count(left, mid) + count(mid, right); int i = left, j = mid, k = left;
        while (i < mid && j < right) {
            if (a[i] <= a[j]) temp[k++] = a[i++];
            else { answer += mid - i; temp[k++] = a[j++]; }
        }
        while (i < mid) temp[k++] = a[i++]; while (j < right) temp[k++] = a[j++];
        System.arraycopy(temp, left, a, left, right - left); return answer;
    }
    public static void main(String[] args) throws Exception { a = numbers(read()); temp = new long[a.length]; System.out.print(count(0, a.length)); }

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
