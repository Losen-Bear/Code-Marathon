# 003 解压缩编码问题 — Java 题解

原题面：[本组题目](../test1_10.md)，查找题号 `003`。原记录保留不改。

## 解题思路

递归下降解析。遇到普通字母追加；遇到 [ 先读取完整的重复次数，再递归解析到 ]，将得到的子串追加指定次数。

## 正确性说明

递归层只负责自己的括号内部；内层先解码，再按次数复制，正好对应编码规则。合法输入确保每次递归都有匹配的右括号。

## 复杂度

时间 O(dL+|s|)，空间 O(dL)，L 是解码后长度，d≤10 是嵌套深度。

## 题面与格式说明

样例2与编码规则不一致：A[2BC[3D]] 应解码成 ABCDDDBCDDD，原记录为 ABCDDBCDCDD。实现遵循题目规则，不硬编码错误样例。

## Java 实现

**提交方式：选择 Java，将下面代码块中的全部代码复制到提交框。**包含 `import`、`public class Main`、`main` 入口和全部辅助方法，无需拼接原题 Hint，也无需修改类名。

源文件：[Main.java](Main.java)。兼容 Java 8 及以上，无包名、无第三方依赖。

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static String s; static int position;
    static String decode() {
        StringBuilder out = new StringBuilder();
        while (position < s.length() && s.charAt(position) != ']') {
            if (s.charAt(position) != '[') { out.append(s.charAt(position++)); continue; }
            position++; int count = 0;
            while (Character.isDigit(s.charAt(position))) count = count * 10 + s.charAt(position++) - '0';
            String part = decode(); position++; // 跳过当前层的右括号
            for (int i = 0; i < count; i++) out.append(part);
        }
        return out.toString();
    }
    public static void main(String[] args) throws Exception { s = read().trim(); System.out.print(decode()); }

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
