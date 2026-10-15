# 087 数字和 — Java 题解

原题面：[题目记录](../test81-90.md)，原记录标题 `077 数字和`。实际题号以本文件标题为准。

## 解题思路

完全背包计数组合。外层枚举不同数字（面额），内层从面额到sum升序更新ways[t]+=ways[t-coin]，ways[0]=1。用BigInteger保存未指定上限的组合数。

## 正确性说明

每加入一种面额，只在此前面额组合上追加该面额任意次数；面额外层顺序固定，所以相同数字多重集不会因排列顺序不同重复计数。升序允许重复使用当前数字。

## 复杂度

时间O(k·sum)次大整数加法，空间O(sum)个大整数。

## 题面与格式说明

上传文件标为077，实际087。样例5、[1,2,5]得到4，说明数字可以重复使用且组合不计顺序；按正面额解释，重复面额去重。 原题记录中的标题编号为077，本题解按比赛网页编号087归档。

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
        int[] input = integers(read()); int target = input[0], k = input[1]; TreeSet<Integer> coins = new TreeSet<>();
        for (int i = 0; i < k; i++) { if (input[i+2] <= 0) throw new IllegalArgumentException("数字必须为正整数，零或负值可能造成无限组合"); coins.add(input[i+2]); }
        BigInteger[] ways = new BigInteger[target+1]; Arrays.fill(ways,BigInteger.ZERO); ways[0] = BigInteger.ONE;
        for (int coin : coins) for (int t = coin; t <= target; t++) ways[t] = ways[t].add(ways[t-coin]);
        System.out.print(ways[target]);
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
