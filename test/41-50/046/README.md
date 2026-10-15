# 046 "有效子数组"个数 — Java 题解

原题面：[本组题目](../test41-50.md)，查找题号 `046`。原记录保留不改。

## 解题思路

将1变成+1、0变成-1，有效子数组等价于其和>0。前缀和p[j]>p[i]时[i,j)有效。用树状数组动态统计此前严格小于当前前缀和的数量。

## 正确性说明

转换后的区间和就是1数量减0数量，故严格为正与有效条件等价。树状数组每次查询只包含此前前缀和，严格小于的计数准确对应所有以当前点结束的有效区间。

## 复杂度

时间 O(n log n)，空间 O(n)，答案对10^9+7取模。

## 题面与格式说明

遇到非0/1 token停止读取，兼容题目中的q结束标记；不会把q输出。

## Java 实现

**提交方式：选择 Java，将下面代码块中的全部代码复制到提交框。**包含 `import`、`public class Main`、`main` 入口和全部辅助方法，无需拼接原题 Hint，也无需修改类名。

源文件：[Main.java](Main.java)。兼容 Java 8 及以上，无包名、无第三方依赖。

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static int[] tree;
    static void add(int index) { for (; index < tree.length; index += index & -index) tree[index]++; }
    static long query(int index) { long answer = 0; for (; index > 0; index -= index & -index) answer += tree[index]; return answer; }
    public static void main(String[] args) throws Exception {
        String[] tokens = read().trim().split("\\s+"); ArrayList<Integer> a = new ArrayList<>();
        for (String token : tokens) { if (!token.equals("0") && !token.equals("1")) break; a.add(Integer.parseInt(token)); }
        int n = a.size(), offset = n + 1, prefix = 0; tree = new int[2 * n + 3]; add(offset);
        long answer = 0;
        for (int x : a) { prefix += x == 1 ? 1 : -1; answer = (answer + query(prefix + offset - 1)) % 1000000007L; add(prefix + offset); }
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
