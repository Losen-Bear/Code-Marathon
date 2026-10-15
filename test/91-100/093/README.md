# 093 可怜的小白鼠 — Java 题解

原题面：[题目记录](../test91-100.md)，原记录标题 `093 可怜的小白鼠`。实际题号以本文件标题为准。

## 解题思路

可测试rounds=floor(minutesToTest/minutesToDie)轮。每只鼠有rounds+1种状态（在某轮死亡或始终存活）。逐次乘此状态数，直到可区分状态数≥buckets，次数就是所需最少鼠数。

## 正确性说明

p只鼠最多提供(rounds+1)^p种结果，因此必须达到桶数。给每桶分配一个不同的该进制编码，按各鼠编码位对应轮次喂养，可实现所有状态，从而达到下界。

## 复杂度

时间O(log buckets)，空间O(1)。

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
    public static void main(String[] args) throws Exception {
        int[] a = integers(read()); int states = a[2]/a[1]+1, answer = 0; long capacity = 1;
        while (capacity < a[0]) { capacity *= states; answer++; } System.out.print(answer);
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
