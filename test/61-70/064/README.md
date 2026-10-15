# 064 智能翻译 — Java 题解

原题面：[题目记录](../test61-70.md)，原记录标题 `064 智能翻译`。实际题号以本文件标题为准。

## 解题思路

把整数按三位一组分为Billion、Million、Thousand及个位组。每组按百位、十位、个位转换，忽略值为0的中间组，整体0输出Zero。

## 正确性说明

三位分组是十进制位权的唯一分解，组内百位和不足百部分的词法映射唯一，按高位到低位拼接即所需英文形式。

## 复杂度

本题数值范围内时间、空间O(1)。

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
    static final String[] SMALL = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    static final String[] TENS = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    static String part(int n) {
        ArrayList<String> words = new ArrayList<>();
        if (n >= 100) { words.add(SMALL[n / 100]); words.add("Hundred"); n %= 100; }
        if (n >= 20) { words.add(TENS[n / 10]); n %= 10; }
        if (n > 0) words.add(SMALL[n]); return String.join(" ", words);
    }
    public static void main(String[] args) throws Exception {
        long n = numbers(read())[0]; if (n == 0) { System.out.print("Zero"); return; }
        int[] units = {1000000000, 1000000, 1000, 1}; String[] names = {"Billion", "Million", "Thousand", ""};
        ArrayList<String> words = new ArrayList<>();
        for (int i = 0; i < units.length; i++) if (n >= units[i]) {
            words.add(part((int) (n / units[i]))); if (!names[i].isEmpty()) words.add(names[i]); n %= units[i];
        }
        System.out.print(String.join(" ", words));
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
