# 012 最小全覆盖子串 — Java 题解

原题面：[本组题目](../test11-20.md)，查找题号 `012`。原记录保留不改。

## 解题思路

滑动窗口。need[c]记录还欠多少字符，missing记录还欠的总数。右指针加入字符，当missing为0时不断收缩左边并更新最短答案。

## 正确性说明

missing=0当且仅当窗口覆盖B的全部频率要求。对每个右端点，持续收缩找到其最短合法窗口；遍历所有右端点即可找到全局最短子串。

## 复杂度

时间 O(|A|+|B|)，空间 O(字符集大小)。

## 题面与格式说明

无解时输出空字符串。字符串按Java UTF-16字符处理；题目普通字符输入可直接适用。

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
        BufferedReader reader = new BufferedReader(new StringReader(read()));
        String a = reader.readLine(), b = reader.readLine(); if (b == null) b = "";
        if (b.isEmpty()) return;
        int[] need = new int[65536]; for (char c : b.toCharArray()) need[c]++;
        int missing = b.length(), left = 0, bestStart = 0, bestLength = Integer.MAX_VALUE;
        for (int right = 0; right < a.length(); right++) {
            if (need[a.charAt(right)]-- > 0) missing--;
            while (missing == 0) {
                if (right - left + 1 < bestLength) { bestLength = right - left + 1; bestStart = left; }
                if (++need[a.charAt(left++)] > 0) missing++;
            }
        }
        if (bestLength != Integer.MAX_VALUE) System.out.print(a.substring(bestStart, bestStart + bestLength));
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
