# 098 CPU管理 — Java 题解

原题面：[题目记录](../test91-100.md)，原记录标题 `098 CPU管理`。实际题号以本文件标题为准。

## 解题思路

统计任务最大频率f以及达到f的种类数c。由冷却规则形成f-1个至少n+1的框架，再放最后一组c项，下界为(f-1)(n+1)+c；任务本身数量也是下界，答案取两者最大。

## 正确性说明

最高频任务的相邻两次必须隔至少n个单位，框架给出必要时间。其他任务可填入框架空位，若多到填满则无需待命；若不够则用待命补足，两个下界的最大值均可达到。

## 复杂度

时间O(tasks.length)，空间O(不同任务种类数)。

## 题面与格式说明

实际输入按样例先给任务总数，再给任务列表和冷却时间。

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
        String[] tokens = read().trim().split("\\s+"); int count = Integer.parseInt(tokens[0]), maximum = 0, tied = 0; long cooldown = Long.parseLong(tokens[count+1]);
        HashMap<String,Integer> frequency = new HashMap<>(); for (int i = 1; i <= count; i++) frequency.put(tokens[i],frequency.getOrDefault(tokens[i],0)+1);
        for (int f : frequency.values()) { if (f > maximum) { maximum = f; tied = 1; } else if (f == maximum) tied++; }
        System.out.print(Math.max(count,(maximum-1L)*(cooldown+1)+tied));
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
