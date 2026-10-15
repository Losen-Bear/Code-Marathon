# 022 魔法师分组 — Java 题解

原题面：[本组题目](../test21-30.md)，查找题号 `022`。原记录保留不改。

## 解题思路

n≤16，使用子集状态。先计算每个mask的元素和，再递归从剩余集合中取和等于target的非空组。每次强制当前组包含剩余集合的最低位元素，消除组顺序造成的重复。记忆化(mask,剩余组数)。

## 正确性说明

任何合法分组都有且只有一个组包含指定的最低位元素，枚举该组的所有可能子集不会漏解。删除该组后的子问题保持相同条件，递归直到最后一组检查元素和。非空和剩余元素数量检查确保恰好k组。

## 复杂度

最坏时间 O(k·3^n)，空间 O(k·2^n)。本实现也支持题面未排除的负数与0。

## Java 实现

**提交方式：选择 Java，将下面代码块中的全部代码复制到提交框。**包含 `import`、`public class Main`、`main` 入口和全部辅助方法，无需拼接原题 Hint，也无需修改类名。

源文件：[Main.java](Main.java)。兼容 Java 8 及以上，无包名、无第三方依赖。

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static long[] sums; static long target; static int groups;
    static HashMap<Long, Boolean> memo = new HashMap<>();
    static boolean partition(int mask, int k) {
        if (Integer.bitCount(mask) < k) return false;
        if (k == 1) return sums[mask] == target;
        long key = (long) mask * (groups + 1) + k;
        if (memo.containsKey(key)) return memo.get(key);
        int fixed = mask & -mask;
        for (int subset = mask; subset > 0; subset = (subset - 1) & mask)
            if ((subset & fixed) != 0 && sums[subset] == target && partition(mask ^ subset, k - 1)) {
                memo.put(key, true); return true;
            }
        memo.put(key, false); return false;
    }
    public static void main(String[] args) throws Exception {
        String[] lines = read().trim().split("\\R"); long[] a = numbers(lines[0]); groups = (int) numbers(lines[1])[0];
        long total = 0; for (long x : a) total += x;
        if (groups < 1 || groups > a.length || total % groups != 0) { System.out.print("false"); return; }
        target = total / groups; sums = new long[1 << a.length];
        for (int mask = 1; mask < sums.length; mask++) { int bit = mask & -mask; sums[mask] = sums[mask ^ bit] + a[Integer.numberOfTrailingZeros(bit)]; }
        System.out.print(partition(sums.length - 1, groups));
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
