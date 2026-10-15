# 039 特殊的回文 — Java 题解

原题面：[本组题目](../test31-40.md)，查找题号 `039`。原记录保留不改。

## 解题思路

从外向内贪心寻找最短的相同非空前缀、后缀，找到后贡献2并递归处理剩余中间部分；找不到时剩余整体贡献1。直接比较字符，避免哈希碰撞。

## 正确性说明

最优分块的首尾块必相同。选择更短的匹配前后缀能够把其他匹配方案进一步细分，不减少中间可形成的块数，因此优先取最短匹配不会降低答案。剩余无匹配时只可作为一个中心块。

## 复杂度

时间 O(n²)，算法额外空间 O(1)。

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
        String s = read().trim(); int left = 0, right = s.length(), answer = 0;
        while (left < right) {
            boolean matched = false;
            for (int len = 1; len * 2 <= right - left; len++)
                if (s.regionMatches(left, s, right - len, len)) { answer += 2; left += len; right -= len; matched = true; break; }
            if (!matched) { answer++; break; }
        }
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
