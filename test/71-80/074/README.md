# 074 大数学家 — Java 题解

原题面：[题目记录](../test71-80.md)，原记录标题 `075 大数学家`。实际题号以本文件标题为准。

## 解题思路

n为2或3时必须拆成两项，答案n-1。其余情况下尽量拆出3，余数1时改用2+2，余数2时使用2，乘积对10^9+7取模。

## 正确性说明

任何≥5的部分拆成3与剩余数能增大乘积，1应合并到其他部分，4可替换为2+2。因此最优仅由2和3组成；三个2不如两个3，故尽量多用3，避免余数1。

## 复杂度

时间O(n)，空间O(1)。

## 题面与格式说明

对应上传文件误标075的“大数学家”。“输出过大”未定义阈值，代码统一对10^9+7取模。题面范围2≤n≤57内，只有n=57受此约定影响：精确最大乘积为1162261467，代码输出162261460。优化目标仍是取模前的乘积。原题记录中的标题编号为075，本题解按比赛网页编号074归档。

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
        int n = (int) numbers(read())[0]; if (n <= 3) { System.out.print(n - 1); return; }
        long result = 1, mod = 1000000007L;
        while (n > 4) { result = result * 3 % mod; n -= 3; }
        System.out.print(result * n % mod);
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
