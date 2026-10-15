# 026 三元组和为零 — Java 题解

原题面：[本组题目](../test21-30.md)，查找题号 `026`。原记录保留不改。

## 解题思路

排序后枚举第一个元素，剩余区间用左右指针寻找和为0的配对。第一个元素和指针移动都跳过重复值，按枚举顺序输出。

## 正确性说明

有序数组中和过小只能右移左指针，和过大只能左移右指针，这不会漏过可行配对。固定首元素并去重覆盖全部不同三元组，枚举顺序保证字典序。

## 复杂度

时间 O(n²)，排序空间 O(log n)，输入和输出空间 O(n+答案长度)。

## 题面与格式说明

每行三元组最后保留一个空格，这是题面明确要求。

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
        int[] input = integers(read()); int[] a = Arrays.copyOfRange(input, 1, input[0] + 1); Arrays.sort(a);
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < a.length - 2; i++) {
            if (i > 0 && a[i] == a[i - 1]) continue;
            int left = i + 1, right = a.length - 1;
            while (left < right) {
                long sum = (long) a[i] + a[left] + a[right];
                if (sum < 0) left++; else if (sum > 0) right--;
                else {
                    if (out.length() > 0) out.append('\n');
                    out.append(a[i]).append(' ').append(a[left]).append(' ').append(a[right]).append(' ');
                    int x = a[left], y = a[right];
                    while (left < right && a[left] == x) left++;
                    while (left < right && a[right] == y) right--;
                }
            }
        }
        System.out.print(out);
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
