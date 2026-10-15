# 011 拆分正偶数 — Java 题解

原题面：[本组题目](../test11-20.md)，查找题号 `011`。原记录保留不改。

## 解题思路

奇数无法拆分。偶数从2、4、6……依次贪心取，只要剩余值足够；最后将不足以成为新偶数的余量加到最后一个已取值上。使用long。

## 正确性说明

k个不同正偶数的最小和为k(k+1)，依次取最小偶数能达到最大k。将剩余量全部补到末项不会破坏严格递增，且其他项保持最小，从而得到字典序最小的最大数量拆分。

## 复杂度

时间和空间 O(√finalSum)，与输出数量一致。

## 题面与格式说明

Hint 使用int读取会在10^10时溢出，代码改用long；输出保留中括号与逗号后的空格。

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
        long left = numbers(read())[0]; ArrayList<Long> result = new ArrayList<>();
        if ((left & 1) == 0) {
            for (long x = 2; x <= left; x += 2) { result.add(x); left -= x; }
            if (left > 0) { int last = result.size() - 1; result.set(last, result.get(last) + left); }
        }
        System.out.print(result);
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

## 验证

本地样例和边界验证见 [cases.json](cases.json)；实际执行结果见仓库根目录 [VALIDATION.md](../../../VALIDATION.md)。未提交到 OJ，不标记为 AC。
