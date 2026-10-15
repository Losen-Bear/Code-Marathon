# 028 180度旋转数 — Java 题解

原题面：[本组题目](../test21-30.md)，查找题号 `028`。原记录保留不改。

## 解题思路

从两端向中心填入旋转对应数位(0,0)、(1,1)、(6,9)、(8,8)、(9,6)。最高位不能为0，中心位只能为0、1、8。按左侧数字升序递归，直接得到升序答案。

## 正确性说明

旋转不变等价于每对对称位属于上述五对、中心位属于三种自旋转数字。枚举覆盖所有且不重复，前导0过滤保证长度正确。递归的前缀升序使输出数值升序。

## 复杂度

输出R个数时，时间 O(aR)，空间 O(a)，采用流式输出避免保存全部结果。

## Java 实现

**提交方式：选择 Java，将下面代码块中的全部代码复制到提交框。**包含 `import`、`public class Main`、`main` 入口和全部辅助方法，无需拼接原题 Hint，也无需修改类名。

源文件：[Main.java](Main.java)。兼容 Java 8 及以上，无包名、无第三方依赖。

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static char[] digits; static BufferedWriter writer; static boolean first = true;
    static final char[] LOW = {'0', '1', '6', '8', '9'}, HIGH = {'0', '1', '9', '8', '6'};
    static void generate(int left, int right) throws IOException {
        if (left > right) { if (!first) writer.write(' '); first = false; writer.write(digits); return; }
        if (left == right) {
            for (char c : new char[]{'0', '1', '8'}) { digits[left] = c; generate(left + 1, right - 1); }
            return;
        }
        for (int i = 0; i < LOW.length; i++) {
            if (left == 0 && LOW[i] == '0') continue;
            digits[left] = LOW[i]; digits[right] = HIGH[i]; generate(left + 1, right - 1);
        }
    }
    public static void main(String[] args) throws Exception {
        digits = new char[(int) numbers(read())[0]]; writer = new BufferedWriter(new OutputStreamWriter(System.out));
        generate(0, digits.length - 1); writer.flush();
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
