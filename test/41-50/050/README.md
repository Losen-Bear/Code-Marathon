# 050 1应俱全 — Java 题解

原题面：[本组题目](../test41-50.md)，查找题号 `050`。原记录保留不改。

## 解题思路

逐位统计十进制数位1。对位权factor，分出high、current、low；完整高位循环贡献high×factor，current=1再加low+1，current>1再加factor。

## 正确性说明

当前位从0到9周期循环，每个完整周期有factor个数在该位为1。残余周期根据当前位是否越过1分成三种情况，逐位累加既不遗漏也不重复任何1。

## 复杂度

时间 O(log n)，空间 O(1)。

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
        long n = numbers(read())[0], answer = 0;
        for (long factor = 1; factor <= n; factor *= 10) {
            long high = n / factor / 10, current = n / factor % 10, low = n % factor;
            answer += high * factor;
            if (current == 1) answer += low + 1; else if (current > 1) answer += factor;
        }
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

## 验证

本地样例和边界验证见 [cases.json](cases.json)；实际执行结果见仓库根目录 [VALIDATION.md](../../../VALIDATION.md)。未提交到 OJ，不标记为 AC。
