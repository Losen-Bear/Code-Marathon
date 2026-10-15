# 024 自行车调度工 — Java 题解

原题面：[本组题目](../test21-30.md)，查找题号 `024`。原记录保留不改。

## 解题思路

状态压缩DP。mask记录已分配的自行车，已安排工人数为bitCount(mask)。将下一个工人分配给任意未使用自行车，增加曼哈顿距离，取所有分配了M辆自行车的状态最小值。

## 正确性说明

按工人顺序安排不限制最终配对。任意合法匹配对应唯一状态转移链，mask确保自行车互不相同，DP取最小值即最小总距离。

## 复杂度

时间 O(N·2^N)，空间 O(2^N)。

## 题面与格式说明

标准输入每行只有逗号分隔的坐标数字；样例括号中的工人位置说明不作为输入。

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
        String[] lines = read().trim().split("\\R"); int[] workers = integers(lines[0]), bikes = integers(lines[1]);
        int m = workers.length / 2, n = bikes.length / 2; long[] dp = new long[1 << n];
        Arrays.fill(dp, Long.MAX_VALUE / 4); dp[0] = 0; long answer = Long.MAX_VALUE;
        for (int mask = 0; mask < dp.length; mask++) {
            int i = Integer.bitCount(mask); if (i == m) { answer = Math.min(answer, dp[mask]); continue; }
            if (i > m || dp[mask] == Long.MAX_VALUE / 4) continue;
            for (int j = 0; j < n; j++) if ((mask & (1 << j)) == 0) {
                long distance = Math.abs((long) workers[2 * i] - bikes[2 * j]) + Math.abs((long) workers[2 * i + 1] - bikes[2 * j + 1]);
                int next = mask | (1 << j); dp[next] = Math.min(dp[next], dp[mask] + distance);
            }
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
