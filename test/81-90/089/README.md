# 089 字母映射 — Java 题解

原题面：[题目记录](../test81-90.md)，原记录标题 `079 字母映射`。实际题号以本文件标题为准。

## 解题思路

前缀DP。末尾一位在1—9时贡献前一前缀方案数；末尾两位在10—26时贡献前两位之前的方案数。0不能独立解码。

## 正确性说明

最后一个字母的编码只能长1或2位，两类按合法数值区间过滤，互斥且覆盖所有解码方式，递推求得总数。

## 复杂度

时间O(|s|)，空间O(1)。

## 题面与格式说明

上传文件标为079，实际089。空输入按无可解码字母串返回0；题目保证最终结果为32位整数，滚动值用long。 原题记录中的标题编号为079，本题解按比赛网页编号089归档。

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
        String s = read().trim(); if (s.isEmpty()) { System.out.print(0); return; }
        long older = 1, previous = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 1; i < s.length(); i++) {
            long current = s.charAt(i) != '0' ? previous : 0; int pair = (s.charAt(i-1)-'0')*10+s.charAt(i)-'0';
            if (pair >= 10 && pair <= 26) current += older; older = previous; previous = current;
        }
        System.out.print(previous);
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
