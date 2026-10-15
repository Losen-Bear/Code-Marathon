# 100 最大的数字 — Java 题解

原题面：[题目记录](../test91-100.md)，原记录标题 `100 最大的数字`。实际题号以本文件标题为准。

## 解题思路

统计a的数位数量，将b左补0到相同长度。按从大到小尝试当前可用数字，保持tight状态表示前缀是否等于b；一旦选到更小的数字，剩余数位直接降序填满。

## 正确性说明

当前前缀相同且选更大数字必超过b；在合法候选中按降序尝试，第一个能完成的前缀最大。若已经更小，后缀降序就是最大合法后缀。回溯仅沿等于边界的分支深入，覆盖全部可能的最大方案。

## 复杂度

时间O(d²+10d)，空间O(d)，d≤19。

## 题面与格式说明

题面没有说明重排后能否前导0或无解如何输出。本实现允许数位重排后有前导0（输出为其数值），无合法重排时输出-1；对题目保证存在答案且无前导0争议的数据完全适用。

## Java 实现

**提交方式：选择 Java，将下面代码块中的全部代码复制到提交框。**包含 `import`、`public class Main`、`main` 入口和全部辅助方法，无需拼接原题 Hint，也无需修改类名。

源文件：[Main.java](Main.java)。兼容 Java 8 及以上，无包名、无第三方依赖。

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static int[] count = new int[10]; static char[] answer; static String limit;
    static boolean search(int position, boolean tight) {
        if (position == answer.length) return true;
        if (!tight) {
            int next = position; for (int d = 9; d >= 0; d--) for (int i = 0; i < count[d]; i++) answer[next++] = (char)('0'+d);
            return true;
        }
        int ceiling = limit.charAt(position)-'0';
        for (int d = ceiling; d >= 0; d--) if (count[d] > 0) {
            count[d]--; answer[position] = (char)('0'+d);
            if (search(position+1,d == ceiling)) return true; count[d]++;
        }
        return false;
    }
    public static void main(String[] args) throws Exception {
        String[] t = read().trim().split("\\s+"); String a = new BigInteger(t[0]).toString(), b = new BigInteger(t[1]).toString();
        for (char c : a.toCharArray()) count[c-'0']++; answer = new char[a.length()];
        if (b.length() > a.length()) { int p = 0; for (int d = 9; d >= 0; d--) for (int i = 0; i < count[d]; i++) answer[p++] = (char)('0'+d); }
        else { StringBuilder padded = new StringBuilder(); for (int i = b.length(); i < a.length(); i++) padded.append('0'); limit = padded.append(b).toString(); if (!search(0,true)) { System.out.print(-1); return; } }
        System.out.print(new BigInteger(new String(answer)));
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
