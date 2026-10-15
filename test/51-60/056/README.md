# 056 寻找“理想数对” — Java 题解

原题面：[本组题目](../test51-60.md)，查找题号 `056`。原记录保留不改。

## 解题思路

归并排序统计。左右半段分别有序后，对左侧每个值用单调指针找到右侧满足a[i]>2a[j]的前缀长度，累加，再普通合并。比较时用long乘2。

## 正确性说明

递归统计同半区理想数对，合并统计跨半区数对；排序后右侧满足条件的值构成前缀，指针不会回退，计数精确。两类互斥覆盖全部i<j。

## 复杂度

时间 O(n log n)，空间 O(n)。

## Java 实现

**提交方式：选择 Java，将下面代码块中的全部代码复制到提交框。**包含 `import`、`public class Main`、`main` 入口和全部辅助方法，无需拼接原题 Hint，也无需修改类名。

源文件：[Main.java](Main.java)。兼容 Java 8 及以上，无包名、无第三方依赖。

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static int[] a, temp;
    static long count(int left, int right) {
        if (right - left <= 1) return 0; int mid = (left + right) / 2; long answer = count(left, mid) + count(mid, right);
        int j = mid;
        for (int i = left; i < mid; i++) { while (j < right && (long) a[i] > 2L * a[j]) j++; answer += j - mid; }
        int i = left, k = left; j = mid;
        while (i < mid || j < right) temp[k++] = j == right || (i < mid && a[i] <= a[j]) ? a[i++] : a[j++];
        System.arraycopy(temp, left, a, left, right - left); return answer;
    }
    public static void main(String[] args) throws Exception { a = integers(read()); temp = new int[a.length]; System.out.print(count(0, a.length)); }

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
