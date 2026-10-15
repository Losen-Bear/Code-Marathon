# 025 固定范围内最大值 — Java 题解

原题面：[本组题目](../test21-30.md)，查找题号 `025`。原记录保留不改。

## 解题思路

单调队列存储下标。先删除已经离开窗口的队首，再删除值不大于当前值的队尾，加入当前下标。窗口形成后队首就是最大值。

## 正确性说明

被队尾删除的旧元素比新元素小或相等，而且更早失效，所以再也不可能成为窗口最大值。队列值严格递减且只保留窗口内候选，队首自然是最大值。

## 复杂度

时间 O(n)，队列空间 O(k)，输入和输出缓冲 O(n)。

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
        int[] input = integers(read()); int n = input[0], k = input[n + 1];
        if (k <= 0 || k > n) return;
        ArrayDeque<Integer> queue = new ArrayDeque<>(); StringBuilder out = new StringBuilder();
        for (int i = 0; i < n; i++) {
            while (!queue.isEmpty() && queue.peekFirst() <= i - k) queue.removeFirst();
            while (!queue.isEmpty() && input[queue.peekLast() + 1] <= input[i + 1]) queue.removeLast();
            queue.addLast(i);
            if (i + 1 >= k) { if (out.length() > 0) out.append(' '); out.append(input[queue.peekFirst() + 1]); }
        }
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
