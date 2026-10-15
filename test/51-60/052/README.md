# 052 前两个数之和 — Java 题解

原题面：[本组题目](../test51-60.md)，查找题号 `052`。原记录保留不改。

## 解题思路

枚举前两个数的切分位置，排除多位数前导0。用BigInteger相加，检查余串是否以和的十进制表示开头，然后继续向后验证，至少出现三个数且恰好覆盖整串才成功。

## 正确性说明

前两个数确定后所有后续数唯一，由逐项相加决定。枚举所有合法前两数并逐项比较就覆盖全部可能的累加序列；完全消费字符串及长度检查保证没有遗漏字符或仅两项的假答案。

## 复杂度

粗略时间 O(n³)，空间 O(n)，包含大整数与字符串处理成本。

## 题面与格式说明

单独的0合法，00等多位前导0形式非法；BigInteger避免超长串相加溢出。

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
        String s = read().trim(); int n = s.length(); boolean found = false;
        for (int i = 1; i < n - 1 && !found; i++) {
            if (s.charAt(0) == '0' && i > 1) break;
            for (int j = i + 1; j < n && !found; j++) {
                if (s.charAt(i) == '0' && j - i > 1) break;
                BigInteger a = new BigInteger(s.substring(0, i)), b = new BigInteger(s.substring(i, j));
                int position = j, count = 2;
                while (position < n) {
                    BigInteger c = a.add(b); String next = c.toString();
                    if (!s.startsWith(next, position)) break;
                    position += next.length(); count++; a = b; b = c;
                }
                found = position == n && count >= 3;
            }
        }
        System.out.print(found);
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
