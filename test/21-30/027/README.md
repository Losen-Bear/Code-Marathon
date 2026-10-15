# 027 节点加和 — Java 题解

原题面：[本组题目](../test21-30.md)，查找题号 `027`。原记录保留不改。

## 解题思路

由低位到高位模拟加法：按下标同时访问两个输入链表的数字，计算sum和进位，向结果链表尾部追加sum%10。最后仍有进位就追加。

## 正确性说明

处理第i位时carry恰好是上一位的十进制进位；当前两位与carry之和决定该位和下一进位。归纳可知所得逆序数字正好表示两数之和。

## 复杂度

时间 O(max(m,n))，结果空间 O(max(m,n))。

## 题面与格式说明

输入是两个以空格分隔数字的行，输出同样用空格分隔；箭头只用于题面展示。

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
        String[] lines = read().trim().split("\\R"); int[] a = integers(lines[0]), b = integers(lines[1]);
        Node dummy = new Node(0), tail = dummy; int carry = 0;
        for (int i = 0; i < Math.max(a.length, b.length) || carry != 0; i++) {
            int sum = carry + (i < a.length ? a[i] : 0) + (i < b.length ? b[i] : 0);
            tail.next = new Node(sum % 10); tail = tail.next; carry = sum / 10;
        }
        StringBuilder out = new StringBuilder();
        for (Node p = dummy.next; p != null; p = p.next) { if (out.length() > 0) out.append(' '); out.append(p.value); }
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
