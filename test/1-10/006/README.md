# 006 猫和鱼 — Java 题解

原题面：[本组题目](../test1_10.md)，查找题号 `006`。原记录保留不改。

## 解题思路

先把所有鱼交给第二只猫，累加 reward2。把鱼 i 改交给第一只猫可增加 reward1[i]-reward2[i]。排序这些差值，取最大的k项。

## 正确性说明

任意合法方案恰好挑选k项差值；若它漏选更大的差值，交换后收益不减，因此最大的k项最优，即使差值为负也必须满足恰好k条。

## 复杂度

时间 O(n log n)，空间 O(n)。

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
        String[] lines = read().trim().split("\\R");
        long[] first = numbers(lines[0]), second = numbers(lines[1]); int k = (int) numbers(lines[2])[0];
        long answer = 0; long[] delta = new long[first.length];
        for (int i = 0; i < first.length; i++) { answer += second[i]; delta[i] = first[i] - second[i]; }
        Arrays.sort(delta);
        for (int i = delta.length - k; i < delta.length; i++) answer += delta[i];
        System.out.print(answer);
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
