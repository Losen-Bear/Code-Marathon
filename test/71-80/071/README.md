# 071 云餐一楼 — Java 题解

原题面：[题目记录](../test71-80.md)，原记录标题 `072 云餐一楼`。实际题号以本文件标题为准。

## 解题思路

求严格递增长度。tails[len]保存长度len+1的递增子序列最小末尾；每个价格二分找第一个≥它的位置并替换，没有该位置就扩展长度。

## 正确性说明

更小的末尾为后续扩展提供不更差的机会，因此只保留最小末尾即可。二分位置之前的末尾都严格小于当前值，可以扩展为新长度；最终tails长度等于最长严格递增子序列长度。

## 复杂度

时间O(n log n)，空间O(n)。

## 题面与格式说明

对应上传文件中误标为072的“云餐一楼”，比赛网页编号为071。相同价格不能连续选择。 原题记录中的标题编号为072，本题解按比赛网页编号071归档。

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
        long[] input = numbers(read()); int n = (int) input[0], length = 0; long[] tails = new long[n];
        for (int i = 1; i <= n; i++) {
            int left = 0, right = length;
            while (left < right) { int mid = (left + right) / 2; if (tails[mid] >= input[i]) right = mid; else left = mid + 1; }
            tails[left] = input[i]; if (left == length) length++;
        }
        System.out.print(length);
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
