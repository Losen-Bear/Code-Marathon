# 097 除杂草 — Java 题解

原题面：[题目记录](../test91-100.md)，原记录标题 `097 除杂草`。实际题号以本文件标题为准。

## 解题思路

把每个装置转成截到[0,n]的覆盖区间，right[left]记录相同左端点的最远右端。扫描位置维护已选择装置的覆盖终点与可扩展最远点；到覆盖边界时必须新增能延伸最远的装置。

## 正确性说明

覆盖当前前缀后，所有左端≤当前边界的候选都能衔接；选终点最远者保留最大未来可覆盖区间，不会比其他选择使用更多装置。若没有候选延伸，则连续公园存在缺口，返回-1。

## 复杂度

时间O(n)，空间O(n)。

## 题面与格式说明

覆盖连续区间，不能仅检查整数点是否被覆盖；零半径装置不能跨越缺口。

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
        int[] input = integers(read()); int n = input[0]; int[] right = new int[n+1];
        for (int i = 0; i <= n; i++) { int left = Math.max(0,i-input[i+1]); right[left] = Math.max(right[left],Math.min(n,i+input[i+1])); }
        int end = 0, far = 0, count = 0;
        for (int i = 0; i < n; i++) {
            far = Math.max(far,right[i]);
            if (i == end) { if (far <= i) { System.out.print(-1); return; } count++; end = far; if (end >= n) { System.out.print(count); return; } }
        }
        System.out.print(count);
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
