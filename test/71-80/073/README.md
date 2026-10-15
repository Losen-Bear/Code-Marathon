# 073 忘掉原神 — Java 题解

原题面：[题目记录](../test71-80.md)，原记录标题 `074 忘掉原神`。实际题号以本文件标题为准。

## 解题思路

维护三个状态：尚未清零区间的连续和before、当前处于清零区间的和zero、清零结束后继续选人的和after。当前值可以正常加入，也可以在清零阶段贡献0；每一步取三种状态最大值。

## 正确性说明

选中的连续段与清零段的交集仍是一个连续段，所以合法方案必能分成清零前、清零中、清零后三个阶段。三状态转移枚举各阶段继续或切换，覆盖所有方案；清零整段允许全负数组答案0。

## 复杂度

时间O(n)，算法空间O(1)，输入解析空间O(n)。

## 题面与格式说明

对应上传文件误标074的题目；可以清零连续多个负值，不是只能删除一个元素。 原题记录中的标题编号为074，本题解按比赛网页编号073归档。

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
        long[] input = numbers(read()); int n = (int) input[0]; long before = Long.MIN_VALUE/4, zero = 0, after = Long.MIN_VALUE/4, best = 0;
        for (int i = 1; i <= n; i++) {
            long x = input[i], oldBefore = before, oldZero = zero, oldAfter = after;
            before = Math.max(x, oldBefore + x); zero = Math.max(0, Math.max(oldBefore, oldZero));
            after = Math.max(oldZero + x, oldAfter + x); best = Math.max(best, Math.max(before, Math.max(zero, after)));
        }
        System.out.print(best);
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
