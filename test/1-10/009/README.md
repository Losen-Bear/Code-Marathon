# 009 进攻火星 — Java 题解

原题面：[本组题目](../test1_10.md)，查找题号 `009`。原记录保留不改。

## 解题思路

对完全二叉树做树形DP：take[i] 表示选节点i，skip[i]表示不选。选i时两个孩子只能不选；不选i时每个孩子可以自行取两种状态最大值。层序数组从后往前计算。

## 正确性说明

两个子树间没有边，唯一限制来自父子同时被选。两种状态分别列举所有合法选择，因此转移取最大值后根节点的最优状态就是最大收益。

## 复杂度

时间 O(n)，空间 O(n)。

## 题面与格式说明

按题面“满二叉树”及层序数组解释，0是资源值为0的真实节点，不作为null占位符。样例输出后的解释文字不属于实际输出。

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
        long[] value = numbers(read()); int n = value.length;
        long[] take = new long[n], skip = new long[n];
        for (int i = n - 1; i >= 0; i--) {
            take[i] = value[i];
            for (int child = 2 * i + 1; child <= 2 * i + 2 && child < n; child++) {
                take[i] += skip[child]; skip[i] += Math.max(take[child], skip[child]);
            }
        }
        System.out.print(n == 0 ? 0 : Math.max(take[0], skip[0]));
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
