# 023 猜猜我在哪 — Java 题解

原题面：[本组题目](../test21-30.md)，查找题号 `023`。原记录保留不改。

## 解题思路

保留输入的数字，枚举将其分为左右坐标的切分点。每段尝试整数和所有小数点位置，排除整数前导0及小数末尾0。笛卡尔组合后用TreeSet进行字符串字典序排序。

## 正确性说明

恢复坐标只需决定两坐标边界和各自的小数点位置，枚举覆盖所有情况。过滤规则与无多余0要求等价，因此只留下有效坐标；TreeSet给出所要求的字典序。

## 复杂度

至多 O(n³) 个候选；含字符串构造和排序时间 O(n⁴ log n)，空间 O(n⁴)。

## Java 实现

**提交方式：选择 Java，将下面代码块中的全部代码复制到提交框。**包含 `import`、`public class Main`、`main` 入口和全部辅助方法，无需拼接原题 Hint，也无需修改类名。

源文件：[Main.java](Main.java)。兼容 Java 8 及以上，无包名、无第三方依赖。

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static List<String> forms(String s) {
        ArrayList<String> result = new ArrayList<>();
        if (s.length() == 1 || s.charAt(0) != '0') result.add(s);
        if (s.charAt(s.length() - 1) != '0')
            for (int i = 1; i < s.length(); i++) if (i == 1 || s.charAt(0) != '0') result.add(s.substring(0, i) + "." + s.substring(i));
        return result;
    }
    public static void main(String[] args) throws Exception {
        String s = read().replaceAll("[^0-9]", ""); TreeSet<String> result = new TreeSet<>();
        for (int i = 1; i < s.length(); i++) for (String x : forms(s.substring(0, i)))
            for (String y : forms(s.substring(i))) result.add(x + ", " + y);
        System.out.print(String.join("\n", result));
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
