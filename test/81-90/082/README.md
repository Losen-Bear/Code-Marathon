# 082 翻越障碍物 — Java 题解

原题面：[题目记录](../test81-90.md)，原记录标题 `072 翻越障碍物`。实际题号以本文件标题为准。

## 解题思路

正向扫描所有上坡高度差，用最小堆暂时给最大的ladders次上坡保留梯子。堆超过梯子数时，弹出最小坡度并扣砖块；砖块不足的当前边无法通过。

## 正确性说明

梯子不受高度差限制，优先用于最大坡度能最省砖。堆动态维护该最优分配，因此某前缀需砖数最小；第一次连最优分配都不足时，当前下标就是最远可达点。

## 复杂度

时间O(n log(ladders+2))，堆空间O(ladders+1)，输入空间O(n)。

## 题面与格式说明

上传文件标为072，实际082。标准输入按样例先读数组长度，再读高度、砖数、梯数。 原题记录中的标题编号为072，本题解按比赛网页编号082归档。

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
        long[] input = numbers(read()); int n = (int) input[0], ladders = (int) input[n+2]; long bricks = input[n+1];
        PriorityQueue<Long> heap = new PriorityQueue<>();
        for (int i = 0; i < n-1; i++) {
            long rise = input[i+2]-input[i+1]; if (rise > 0) heap.add(rise);
            if (heap.size() > ladders) bricks -= heap.remove();
            if (bricks < 0) { System.out.print(i); return; }
        }
        System.out.print(n-1);
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
