# 042 中缀表达式转后缀表达式 — Java 题解

原题面：[本组题目](../test41-50.md)，查找题号 `042`。原记录保留不改。

## 解题思路

调度场算法：数字直接输出；左括号入栈；运算符入栈前弹出优先级大于等于它的操作符；右括号弹出至左括号。最后清空栈。

## 正确性说明

栈只保存尚未确定右操作数边界的操作符。高优先级和同优先级左结合操作必须先完成，括号限制弹出边界，因此输出顺序正好是子表达式先于其运算符的后缀顺序。

## 复杂度

时间 O(t)，空间 O(t)。

## 题面与格式说明

按题面要求保留最后一个空格。输入的符号与数字按空格分隔；支持作为完整数字token的负整数，不另处理括号前一元负号。

## Java 实现

**提交方式：选择 Java，将下面代码块中的全部代码复制到提交框。**包含 `import`、`public class Main`、`main` 入口和全部辅助方法，无需拼接原题 Hint，也无需修改类名。

源文件：[Main.java](Main.java)。兼容 Java 8 及以上，无包名、无第三方依赖。

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static int priority(String s) { return s.equals("+") || s.equals("-") ? 1 : 2; }
    public static void main(String[] args) throws Exception {
        String[] tokens = read().trim().split("\\s+"); ArrayDeque<String> operators = new ArrayDeque<>(); StringBuilder out = new StringBuilder();
        for (String token : tokens) {
            if (token.matches("[+-]?\\d+")) out.append(token).append(' ');
            else if (token.equals("(")) operators.push(token);
            else if (token.equals(")")) { while (!operators.peek().equals("(")) out.append(operators.pop()).append(' '); operators.pop(); }
            else {
                while (!operators.isEmpty() && !operators.peek().equals("(") && priority(operators.peek()) >= priority(token)) out.append(operators.pop()).append(' ');
                operators.push(token);
            }
        }
        while (!operators.isEmpty()) out.append(operators.pop()).append(' ');
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

## 验证

本地样例和边界验证见 [cases.json](cases.json)；实际执行结果见仓库根目录 [VALIDATION.md](../../../VALIDATION.md)。未提交到 OJ，不标记为 AC。
