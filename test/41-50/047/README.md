# 047 数字探险 — Java 题解

原题面：[本组题目](../test41-50.md)，查找题号 `047`。原记录保留不改。

## 解题思路

递归选择严格递增的1到9数字，状态记录下一候选起点、还缺几个数字、剩余目标和。选满k个且剩余和为0时输出。

## 正确性说明

严格递增保证每个数只用一次、每个组合只生成一次。枚举所有候选覆盖全部组合，递归的数值升序自然产生字典序。

## 复杂度

时间 O(k·2^9)，空间 O(k+输出长度)。

## Java 实现

**提交方式：选择 Java，将下面代码块中的全部代码复制到提交框。**包含 `import`、`public class Main`、`main` 入口和全部辅助方法，无需拼接原题 Hint，也无需修改类名。

源文件：[Main.java](Main.java)。兼容 Java 8 及以上，无包名、无第三方依赖。

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static ArrayList<Integer> path = new ArrayList<>(); static ArrayList<String> result = new ArrayList<>();
    static void search(int start, int k, int sum) {
        if (k == 0) { if (sum == 0) { int[] a = path.stream().mapToInt(Integer::intValue).toArray(); result.add(join(a)); } return; }
        if (10 - start < k || sum < 0) return;
        for (int x = start; x <= 9 && x <= sum; x++) { path.add(x); search(x + 1, k - 1, sum - x); path.remove(path.size() - 1); }
    }
    public static void main(String[] args) throws Exception { int[] a = integers(read()); search(1, a[0], a[1]); System.out.print(String.join("\n", result)); }

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
