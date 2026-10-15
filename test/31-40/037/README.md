# 037 超大数运算 — Java 题解

原题面：[本组题目](../test31-40.md)，查找题号 `037`。原记录保留不改。

## 解题思路

指数按十进制逐位处理。若已处理前缀为e，读到数字d后新指数为10e+d，故 result=result^10·a^d mod1337。用快速幂计算两个小指数。

## 正确性说明

根据幂运算规则a^(10e+d)=(a^e)^10·a^d，取模保留等价余数。逐位归纳后结果对应完整大指数。无需把指数转换为整型，也不需要互质假设。

## 复杂度

时间 O(n)，空间 O(1)，输入数组空间 O(n)。

## Java 实现

**提交方式：选择 Java，将下面代码块中的全部代码复制到提交框。**包含 `import`、`public class Main`、`main` 入口和全部辅助方法，无需拼接原题 Hint，也无需修改类名。

源文件：[Main.java](Main.java)。兼容 Java 8 及以上，无包名、无第三方依赖。

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static int power(int a, int b) { int result = 1; while (b > 0) { if ((b & 1) != 0) result = result * a % 1337; a = a * a % 1337; b >>= 1; } return result; }
    public static void main(String[] args) throws Exception {
        int[] input = integers(read()); int a = input[0] % 1337, result = 1;
        for (int i = 0; i < input[1]; i++) result = power(result, 10) * power(a, input[i + 2]) % 1337;
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
