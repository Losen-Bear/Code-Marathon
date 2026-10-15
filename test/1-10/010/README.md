# 010 原神启动 — Java 题解

原题面：[本组题目](../test1_10.md)，查找题号 `010`。原记录保留不改。

## 解题思路

有界背包。只需计算总回血量0到m+max(h)-1的可达性；每种食物用 remaining[sum] 表示当前种类还可使用的数量，升序扫描完成 O(上界) 的有界转移。最终找最小的可达 sum≥m。

## 正确性说明

最优回血组合删除任意一个已选食物后必然不足m，否则原组合不最优，所以最优总量严格小于m+max(h)。remaining的三种分支分别表示不使用当前食物、无法到达、由sum-h再使用一个，恰好满足库存约束。

## 复杂度

时间 O(n·(m+H))，空间 O(m+H)，H为最大单个回血量。

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
        int[] input = integers(read()); int n = input[0], target = input[1], max = 0;
        long total = 0;
        for (int i = 0; i < n; i++) { max = Math.max(max, input[3 + i * 2]); total += (long) input[2 + i * 2] * input[3 + i * 2]; }
        if (total < target) { System.out.print("op"); return; }
        int limit = target + max - 1; int[] remaining = new int[limit + 1];
        Arrays.fill(remaining, -1); remaining[0] = 0;
        for (int i = 0; i < n; i++) {
            int count = input[2 + i * 2], weight = input[3 + i * 2];
            for (int sum = 0; sum <= limit; sum++) {
                if (remaining[sum] >= 0) remaining[sum] = count;
                else if (sum < weight || remaining[sum - weight] <= 0) remaining[sum] = -1;
                else remaining[sum] = remaining[sum - weight] - 1;
            }
        }
        for (int sum = target; sum <= limit; sum++) if (remaining[sum] >= 0) { System.out.print(sum - target); return; }
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
