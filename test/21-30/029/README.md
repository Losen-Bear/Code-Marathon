# 029 分治排序 — Java 题解

原题面：[本组题目](../test21-30.md)，查找题号 `029`。原记录保留不改。

## 解题思路

归并排序。递归排序左右半段，再用两个指针合并到临时数组，拷贝回原区间。

## 正确性说明

单元素区间天然有序。若两半已排序，每次取两半当前较小值就能得到全局下一小值，合并结果有序；归纳得到完整数组有序。

## 复杂度

时间 O(n log n)，空间 O(n)。

## 题面与格式说明

输入没有数组长度，第一项也是数组元素。

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
    static void sort(int left, int right) {
        if (right - left <= 1) return; int mid = (left + right) / 2;
        sort(left, mid); sort(mid, right); int i = left, j = mid, k = left;
        while (i < mid || j < right) temp[k++] = j == right || (i < mid && a[i] <= a[j]) ? a[i++] : a[j++];
        System.arraycopy(temp, left, a, left, right - left);
    }
    public static void main(String[] args) throws Exception { a = integers(read()); temp = new int[a.length]; sort(0, a.length); System.out.print(join(a)); }

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
