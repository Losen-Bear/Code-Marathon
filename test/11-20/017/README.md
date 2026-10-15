# 017 反转链表 — Java 题解

原题面：[本组题目](../test11-20.md)，查找题号 `017`。原记录保留不改。

## 解题思路

将输入数字构成链表，用prev、current、next三个指针逐节点反转，再顺序输出反转后的链表。

## 正确性说明

循环开始时prev是已处理前缀的反转链表，current是未处理后缀。保存next后改变current.next并推进，循环不变式维持，结束时prev就是完整反转结果。

## 复杂度

时间 O(n)，反转操作额外空间 O(1)；输入链表与输出缓冲 O(n)。

## 题面与格式说明

真实输入为[3 6 4 1]，空链表为[]；真实输出用空格分隔，样例的箭头和展示说明不属于标准输入输出。

## Java 实现

**提交方式：选择 Java，将下面代码块中的全部代码复制到提交框。**包含 `import`、`public class Main`、`main` 入口和全部辅助方法，无需拼接原题 Hint，也无需修改类名。

源文件：[Main.java](Main.java)。兼容 Java 8 及以上，无包名、无第三方依赖。

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static class Node { int value; Node next; Node(int value) { this.value = value; } }
    public static void main(String[] args) throws Exception {
        int[] values = integers(read()); Node head = null, tail = null;
        for (int value : values) { Node node = new Node(value); if (head == null) head = node; else tail.next = node; tail = node; }
        Node prev = null;
        while (head != null) { Node next = head.next; head.next = prev; prev = head; head = next; }
        StringBuilder out = new StringBuilder();
        for (Node p = prev; p != null; p = p.next) { if (out.length() > 0) out.append(' '); out.append(p.value); }
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
