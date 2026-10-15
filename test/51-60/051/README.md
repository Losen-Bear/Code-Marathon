# 051 寻找最小M个数 — Java 题解

原题面：[本组题目](../test51-60.md)，查找题号 `051`。原记录保留不改。

## 解题思路

大小最多为M的最大堆保存当前最小M个数字。新数字比堆顶小时替换堆顶；最终将堆内元素排序并按数组格式输出。M=0直接返回[]。

## 正确性说明

堆顶是已保留M个数中的最大值，只有比它更小的新数才能改善最小M项。逐项归纳得到堆内恰好是当前前缀最小M个数，最后排序获得所需顺序。

## 复杂度

时间 O(n log(M+1)+M log M)，算法空间 O(M)，输入解析空间 O(n)。

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
        String[] lines = read().trim().split("\\R"); int[] a = integers(lines[0]); int m = (int) numbers(lines[1])[0];
        if (m == 0) { System.out.print("[]"); return; }
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int x : a) { if (heap.size() < m) heap.add(x); else if (x < heap.peek()) { heap.remove(); heap.add(x); } }
        int[] result = new int[heap.size()]; for (int i = result.length - 1; i >= 0; i--) result[i] = heap.remove();
        System.out.print(Arrays.toString(result));
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
