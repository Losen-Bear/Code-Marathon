# 032 字符串关系 — Java 题解

原题面：[本组题目](../test31-40.md)，查找题号 `032`。原记录保留不改。

## 解题思路

双指针。依次扫描str2，只在与str1当前待匹配字符相等时推进str1指针；扫描结束检查是否匹配完全部str1。

## 正确性说明

每次选择最早可匹配位置，为剩余字符保留最大后缀。若存在子序列匹配，贪心匹配位置不会晚于该方案的对应位置，因此不会错过可行解。

## 复杂度

时间 O(|str2|)，算法额外空间 O(1)。

## 题面与格式说明

题面称为“子字符串”，但定义实际是子序列，不要求连续。

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
        String a = reader.readLine(), b = reader.readLine(); if (a == null) a = ""; if (b == null) b = "";
        int i = 0; for (int j = 0; j < b.length() && i < a.length(); j++) if (a.charAt(i) == b.charAt(j)) i++;
        System.out.print(i == a.length());
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
