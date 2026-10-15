# 034 找特定区间 — Java 题解

原题面：[本组题目](../test31-40.md)，查找题号 `034`。原记录保留不改。

## 解题思路

区间和等于两个前缀和之差。对前缀和归并排序；合并时，对左半每个值用两个单调指针统计右半差值在[lower,upper]的数量，再执行普通归并。

## 正确性说明

递归统计左右半内部的合法下标对，合并统计跨半对。跨半下标天然满足先后顺序，两个指针精确圈出差值范围，因此所有区间恰好计数一次。

## 复杂度

时间 O(n log n)，空间 O(n)。前缀和及答案使用long。

## Java 实现

**提交方式：选择 Java，将下面代码块中的全部代码复制到提交框。**包含 `import`、`public class Main`、`main` 入口和全部辅助方法，无需拼接原题 Hint，也无需修改类名。

源文件：[Main.java](Main.java)。兼容 Java 8 及以上，无包名、无第三方依赖。

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static long[] prefix, temp; static long low, high;
    static long count(int left, int right) {
        if (right - left <= 1) return 0; int mid = (left + right) / 2;
        long answer = count(left, mid) + count(mid, right); int a = mid, b = mid;
        for (int i = left; i < mid; i++) {
            while (a < right && prefix[a] - prefix[i] < low) a++;
            while (b < right && prefix[b] - prefix[i] <= high) b++;
            answer += b - a;
        }
        int i = left, j = mid, k = left;
        while (i < mid || j < right) temp[k++] = j == right || (i < mid && prefix[i] <= prefix[j]) ? prefix[i++] : prefix[j++];
        System.arraycopy(temp, left, prefix, left, right - left); return answer;
    }
    public static void main(String[] args) throws Exception {
        long[] input = numbers(read()); low = input[0]; high = input[1]; int n = (int) input[2];
        prefix = new long[n + 1]; temp = new long[n + 1];
        for (int i = 0; i < n; i++) prefix[i + 1] = prefix[i] + input[i + 3];
        System.out.print(count(0, n + 1));
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
