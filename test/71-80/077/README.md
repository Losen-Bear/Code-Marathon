# 077 还记得大明湖畔的排列组合吗？ — Java 题解

原题面：[题目记录](../test71-80.md)，原记录标题 `077 还记得大明湖畔的排列组合吗？`。实际题号以本文件标题为准。

## 解题思路

按阶乘数制选择。把k改成从0开始的序号；若还剩r个数字，每个首数字对应(r-1)!个排列。商决定选择第几个剩余数字，余数决定后缀序号。

## 正确性说明

字典序中相同首字符的排列连续且块大小相同，商余分块准确定位目标。逐位应用该定位方法最终得到唯一第k个排列。

## 复杂度

时间O(n²)，空间O(n)，n≤9。

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
        int[] input = integers(read()); int n = input[0], k = input[1] - 1; int[] factorial = new int[n+1]; factorial[0] = 1;
        ArrayList<Integer> digits = new ArrayList<>(); for (int i = 1; i <= n; i++) { factorial[i] = factorial[i-1]*i; digits.add(i); }
        StringBuilder out = new StringBuilder();
        for (int remaining = n; remaining > 0; remaining--) { int block = factorial[remaining-1]; out.append(digits.remove(k/block)); k %= block; }
        System.out.print(out);
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
