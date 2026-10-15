# 058 文物修复 — Java 题解

原题面：[本组题目](../test51-60.md)，查找题号 `058`。原记录保留不改。

## 解题思路

迭代加深DFS只搜索0至4次操作。把连续相邻且值恰好+1称为正确连接，包含首尾哨兵。一次移动连续段只改变最多3条连接，断点数的ceil(breaks/3)是剩余操作数下界。枚举交换两个相邻非空连续块，等价于把一段移动到另一位置。

## 正确性说明

任意连续段移动等价于交换该段与它越过的相邻段，因此三切点枚举覆盖所有有效操作。断点下界不会超过真实最少操作数，剪枝安全。迭代加深首次找到的深度最小；搜完4仍未找到则至少5次。

## 复杂度

深度最多4，每层O(n³)个操作，朴素最坏 O(n^13)，记忆化与断点下界显著剪枝；空间由缓存状态数决定，每状态 O(n)。

## Java 实现

**提交方式：选择 Java，将下面代码块中的全部代码复制到提交框。**包含 `import`、`public class Main`、`main` 入口和全部辅助方法，无需拼接原题 Hint，也无需修改类名。

源文件：[Main.java](Main.java)。兼容 Java 8 及以上，无包名、无第三方依赖。

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static int n, limit; static HashMap<Long, Integer> seen;
    static int breaks(int[] a) { int result = a[0] == 1 ? 0 : 1; for (int i = 1; i < n; i++) if (a[i] != a[i - 1] + 1) result++; if (a[n - 1] != n) result++; return result; }
    static long key(int[] a) { long result = 0; for (int x : a) result = (result << 4) | x; return result; }
    static boolean search(int[] a, int depth) {
        int bad = breaks(a); if (bad == 0) return true;
        if (depth + (bad + 2) / 3 > limit) return false;
        long state = key(a); Integer old = seen.get(state); if (old != null && old <= depth) return false; seen.put(state, depth);
        for (int left = 0; left < n - 1; left++) for (int mid = left + 1; mid < n; mid++) for (int right = mid + 1; right <= n; right++) {
            int[] next = a.clone(); int p = left;
            for (int i = mid; i < right; i++) next[p++] = a[i];
            for (int i = left; i < mid; i++) next[p++] = a[i];
            if (search(next, depth + 1)) return true;
        }
        return false;
    }
    public static void main(String[] args) throws Exception {
        int[] input = integers(read()); int tests = input[0], position = 1; StringBuilder out = new StringBuilder();
        for (int test = 0; test < tests; test++) {
            n = input[position++]; int[] a = Arrays.copyOfRange(input, position, position + n); position += n; int answer = 5;
            for (limit = 0; limit < 5; limit++) { seen = new HashMap<>(); if (search(a, 0)) { answer = limit; break; } }
            if (test > 0) out.append('\n'); out.append(answer == 5 ? "5 or more" : Integer.toString(answer));
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
