# 002 极限削木游戏 — Java 题解

原题面：[本组题目](../test1_10.md)，查找题号 `002`。原记录保留不改。

## 解题思路

一次切割后，中间段最长可以保留 floor((n-1)/2)：最短段至少为1，最长段至少与中间段一样长。令 f(n)≤x 的最大值为 M(x)，则 M(0)=2，M(x)=2M(x-1)+2，得到 2^(x+2)-2。用 BigInteger 输出。

## 正确性说明

若要保留长度 t，至少需要 1+t+t=2t+1，且切成1、t、t可以达到。最大可保留长度决定最大操作次数；floor((n-1)/2)≤M(x-1) 等价于 n≤2M(x-1)+2，递推边界给出答案。

## 复杂度

答案有 O(x) 个二进制位；时间、空间与大整数输出长度相关。

## 题面与格式说明

题面没有给出 x 的上限；代码用任意精度整数避免 long 溢出，但极大的 x 会受到实际输出长度和内存限制。

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
        int x = Integer.parseInt(read().trim());
        System.out.print(BigInteger.ONE.shiftLeft(Math.addExact(x, 2)).subtract(BigInteger.valueOf(2)));
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
