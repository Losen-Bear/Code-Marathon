# 061 填运算符 — Java 题解

原题面：[题目记录](../test61-70.md)，原记录标题 `061 填运算符`。实际题号以本文件标题为准。

## 解题思路

枚举四个运算符位置，每位按+、-、*、/顺序尝试，共4^4种表达式。用“加减分项、乘除在项内左结合”的规则计算。分数用BigInteger分子分母约分，避免整数除法截断和浮点比较误差。

## 正确性说明

所有合法表达式由四个运算符唯一确定，枚举完整且无重复。每次遇到加减才结束当前乘除项，正好符合运算优先级与左结合规则；精确有理数计算保证仅输出数学上等于目标值的表达式。

## 复杂度

固定256种组合，每种5个操作数；空间O(1)，大整数运算成本与数字位数相关。

## 题面与格式说明

题面未定义除法截断，代码采用数学上的精确除法；符号枚举顺序按Hint的+、-、*、/，与运算优先级不是同一概念。

## Java 实现

**提交方式：选择 Java，将下面代码块中的全部代码复制到提交框。**包含 `import`、`public class Main`、`main` 入口和全部辅助方法，无需拼接原题 Hint，也无需修改类名。

源文件：[Main.java](Main.java)。兼容 Java 8 及以上，无包名、无第三方依赖。

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static class Fraction {
        BigInteger numerator, denominator;
        Fraction(BigInteger n, BigInteger d) {
            if (d.signum() < 0) { n = n.negate(); d = d.negate(); }
            BigInteger gcd = n.gcd(d); numerator = n.divide(gcd); denominator = d.divide(gcd);
        }
        Fraction add(Fraction b) { return new Fraction(numerator.multiply(b.denominator).add(b.numerator.multiply(denominator)), denominator.multiply(b.denominator)); }
        Fraction multiply(Fraction b) { return new Fraction(numerator.multiply(b.numerator), denominator.multiply(b.denominator)); }
        Fraction divide(Fraction b) { return new Fraction(numerator.multiply(b.denominator), denominator.multiply(b.numerator)); }
    }
    static String[] values; static BigInteger target; static char[] operators = new char[4];
    static ArrayList<String> result = new ArrayList<>();
    static void enumerate(int position) {
        if (position < 4) { for (char c : new char[]{'+','-','*','/'}) { operators[position] = c; enumerate(position + 1); } return; }
        Fraction sum = new Fraction(BigInteger.ZERO, BigInteger.ONE);
        Fraction term = new Fraction(new BigInteger(values[0]), BigInteger.ONE);
        for (int i = 0; i < 4; i++) {
            Fraction next = new Fraction(new BigInteger(values[i + 1]), BigInteger.ONE);
            switch (operators[i]) {
                case '+': sum = sum.add(term); term = next; break;
                case '-': sum = sum.add(term); term = new Fraction(next.numerator.negate(), next.denominator); break;
                case '*': term = term.multiply(next); break;
                default: if (next.numerator.signum() == 0) return; term = term.divide(next);
            }
        }
        sum = sum.add(term);
        if (sum.numerator.equals(target.multiply(sum.denominator))) {
            StringBuilder out = new StringBuilder(values[0]);
            for (int i = 0; i < 4; i++) out.append(operators[i]).append(values[i + 1]);
            result.add(out.append('=').append(target).toString());
        }
    }
    public static void main(String[] args) throws Exception {
        values = read().trim().split("\\s+"); target = new BigInteger(values[5]); enumerate(0);
        System.out.print(String.join("\n", result));
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
