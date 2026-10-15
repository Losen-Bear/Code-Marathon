# 031 吃苹果 — Java 题解

原题面：[本组题目](../test31-40.md)，查找题号 `031`。原记录保留不改。

## 解题思路

记忆化递归。要通过除2或除3减少剩余数量，先分别吃掉n%2或n%3个苹果，再用一天执行该整除操作。f(n)=1+min(n%2+f(n/2), n%3+f(n/3))，f(0)=0、f(1)=1。

## 正确性说明

考虑最优方案第一次批量操作，之前只能逐个吃。若为了相同除数多吃一个完整倍数，可先批量操作再少吃若干个，不会更差，所以仅需枚举最近的可整除数。两种首个批量选择覆盖最优方案。

## 复杂度

记忆化状态数 O(log² n)，时间、空间 O(log² n)。

## Java 实现

**提交方式：选择 Java，将下面代码块中的全部代码复制到提交框。**包含 `import`、`public class Main`、`main` 入口和全部辅助方法，无需拼接原题 Hint，也无需修改类名。

源文件：[Main.java](Main.java)。兼容 Java 8 及以上，无包名、无第三方依赖。

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static HashMap<Long, Integer> memo = new HashMap<>();
    static int days(long n) {
        if (n <= 1) return (int) n;
        if (memo.containsKey(n)) return memo.get(n);
        int result = 1 + Math.min((int) (n % 2) + days(n / 2), (int) (n % 3) + days(n / 3));
        memo.put(n, result); return result;
    }
    public static void main(String[] args) throws Exception { System.out.print(days(numbers(read())[0])); }

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
