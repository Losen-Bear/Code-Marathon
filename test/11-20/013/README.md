# 013 这货不是括号匹配 — Java 题解

原题面：[本组题目](../test11-20.md)，查找题号 `013`。原记录保留不改。

## 解题思路

递归解析括号表达式。同一分支内的连接长度相加，遇到|取各分支最大值，遇到括号递归求内部最长长度。

## 正确性说明

串联可以分别选择各部分最长匹配，所以长度相加；或运算只能选一个分支，所以取最大。按照语法树递归计算得到根表达式的最长匹配长度。

## 复杂度

时间 O(n)，空间 O(n)。

## Java 实现

**提交方式：选择 Java，将下面代码块中的全部代码复制到提交框。**包含 `import`、`public class Main`、`main` 入口和全部辅助方法，无需拼接原题 Hint，也无需修改类名。

源文件：[Main.java](Main.java)。兼容 Java 8 及以上，无包名、无第三方依赖。

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static String expression; static int position;
    static int parse() {
        int sum = 0, best = 0;
        while (position < expression.length() && expression.charAt(position) != ')') {
            char c = expression.charAt(position++);
            if (c == 'a') sum++;
            else if (c == '(') { sum += parse(); position++; }
            else if (c == '|') { best = Math.max(best, sum); sum = 0; }
        }
        return Math.max(best, sum);
    }
    public static void main(String[] args) throws Exception { expression = read().trim(); System.out.print(parse()); }

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
