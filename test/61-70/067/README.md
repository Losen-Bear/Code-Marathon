# 067 抽奖 — Java 题解

原题面：[题目记录](../test61-70.md)，原记录标题 `067 抽奖`。实际题号以本文件标题为准。

## 解题思路

把所有号码放入HashSet，枚举两个不同下标i<j，如果它们之和也在集合中就增加答案。

## 正确性说明

号码互不相同且为正，和严格大于两个加数，因此满足条件的三号码集合有唯一最大的和数与唯一的无序加数对，枚举每对一次就准确计数每种中奖情况。

## 复杂度

期望时间O(n²)，空间O(n)。

## 题面与格式说明

按三号码组合计数。同一个和数可以与不同的加数对组成不同中奖情况，例如号码`1 2 3 4 5`有4种组合，输出4；若题意要求的是“可表示为另两数之和的号码个数”，则为3，属于另一种计数定义。

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
        long[] input = numbers(read()); int n = (int) input[0]; HashSet<Long> values = new HashSet<>();
        for (int i = 1; i <= n; i++) values.add(input[i]); long answer = 0;
        for (int i = 1; i <= n; i++) for (int j = i + 1; j <= n; j++) if (values.contains(input[i] + input[j])) answer++;
        System.out.print(answer);
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
