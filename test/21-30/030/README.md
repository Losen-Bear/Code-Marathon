# 030 后缀表达式求值 — Java 题解

原题面：[本组题目](../test21-30.md)，查找题号 `030`。原记录保留不改。

## 解题思路

从左往右扫描后缀表达式。数字入栈，运算符取出右操作数和左操作数，计算后入栈。使用BigInteger避免题面没有给出的数值范围导致溢出。

## 正确性说明

后缀表达式中操作符出现在两个操作数表达式之后，栈顶两项就是最近完成的两个子表达式值。按正确左右顺序计算保持该不变式，最终唯一栈项为结果。

## 复杂度

栈操作 O(t)，空间 O(t)；大整数运算成本取决于操作数位数。

## 题面与格式说明

除法采用向0截断的整数除法，与Java/C++整型除法一致；数字可以为负数。

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
        String[] tokens = read().trim().split("\\s+"); ArrayDeque<BigInteger> stack = new ArrayDeque<>();
        for (String token : tokens) {
            if (!token.matches("[+*/-]")) { stack.push(new BigInteger(token)); continue; }
            BigInteger right = stack.pop(), left = stack.pop();
            switch (token) { case "+": stack.push(left.add(right)); break; case "-": stack.push(left.subtract(right)); break;
                case "*": stack.push(left.multiply(right)); break; default: stack.push(left.divide(right)); }
        }
        System.out.print(stack.pop());
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
