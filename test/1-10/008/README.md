# 008 反转加密 — Java 题解

原题面：[本组题目](../test1_10.md)，查找题号 `008`。原记录保留不改。

## 解题思路

按 m 的位数枚举（n的位数或少一位），从两端向中间做数位DP。每对数位的和同时约束 n 的低位和高位，用状态 (位置,低侧进位,高侧进位) 检查能否一致；最外层禁止 m 的首位为0。

## 正确性说明

m与反转数在对称位置贡献同一个数位和。低位的进位向内推进，高位的进位反向推进；枚举一对数字就能同时满足两端加法约束。相遇时检查进位或中心数字，恰好覆盖所有可能的m，避免枚举0到n。

## 复杂度

时间 O(d·100)，空间 O(d)，d为n的十进制位数。

## Java 实现

**提交方式：选择 Java，将下面代码块中的全部代码复制到提交框。**包含 `import`、`public class Main`、`main` 入口和全部辅助方法，无需拼接原题 Hint，也无需修改类名。

源文件：[Main.java](Main.java)。兼容 Java 8 及以上，无包名、无第三方依赖。

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static String target; static int length; static Boolean[][][] memo;
    static boolean search(int left, int lowCarry, int highCarry) {
        int right = length - 1 - left;
        if (left > right) return lowCarry == highCarry;
        if (memo[left][lowCarry][highCarry] != null) return memo[left][lowCarry][highCarry];
        int lowDigit = target.charAt(target.length() - 1 - left) - '0';
        int highDigit = target.charAt(target.length() - 1 - right) - '0';
        boolean result = false;
        if (left == right) {
            for (int d = length == 1 ? 1 : 0; d <= 9; d++)
                if (2 * d + lowCarry == lowDigit + 10 * highCarry) result = true;
        } else {
            for (int low = 0; low <= 9 && !result; low++)
                for (int high = left == 0 ? 1 : 0; high <= 9 && !result; high++) {
                    int sum = low + high;
                    if ((sum + lowCarry) % 10 != lowDigit) continue;
                    int nextLow = (sum + lowCarry) / 10;
                    int nextHigh = highDigit + 10 * highCarry - sum;
                    if (nextHigh >= 0 && nextHigh <= 1) result = search(left + 1, nextLow, nextHigh);
                }
        }
        return memo[left][lowCarry][highCarry] = result;
    }
    public static void main(String[] args) throws Exception {
        target = new BigInteger(read().trim()).toString(); boolean result = target.equals("0");
        for (length = Math.max(1, target.length() - 1); length <= target.length() && !result; length++) {
            int carry = length == target.length() ? 0 : target.charAt(0) - '0';
            if (carry > 1) continue;
            memo = new Boolean[length + 1][2][2]; result = search(0, 0, carry);
        }
        System.out.print(result ? "True" : "False");
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
