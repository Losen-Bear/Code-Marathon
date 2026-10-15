# 015 中庸小源 — Java 题解

原题面：[本组题目](../test11-20.md)，查找题号 `015`。原记录保留不改。

## 解题思路

每个格子枚举为起点，分别尝试先上坡和先下坡。DFS状态包含当前格子、下次坡度方向、已经访问的格子集合；不能只用二维DP，因为禁止重复经过。用64位位集保存访问情况，并根据剩余可达格子数剪枝。

## 正确性说明

DFS每步只走到未访问且坡度方向正确的邻格，因此所有枚举路径合法；枚举所有起点及首步方向覆盖全部合法路径。剪枝中的可达集合允许未来重复访问，仅放宽限制，因此其大小是剩余路径长度的上界，不会误删最优解。

## 复杂度

最坏时间指数级，粗略上界 O(V²·3^V)，空间 O(V²)，V≤64。此题含最长简单路径限制，不存在这里可直接使用的多项式DP。

## 题面与格式说明

输出是移动次数，等于经过格子数减1。回溯算法最坏复杂度较高，尚未经过OJ完整数据性能验证。

## Java 实现

**提交方式：选择 Java，将下面代码块中的全部代码复制到提交框。**包含 `import`、`public class Main`、`main` 入口和全部辅助方法，无需拼接原题 Hint，也无需修改类名。

源文件：[Main.java](Main.java)。兼容 Java 8 及以上，无包名、无第三方依赖。

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static int n, m, count, best; static int[] height; static int[][][] edges;
    static int upper(int start, int direction, long used) {
        boolean[][] seen = new boolean[count][2]; boolean[] reached = new boolean[count];
        int[] queue = new int[count * 2]; int head = 0, tail = 0;
        queue[tail++] = start * 2 + direction; seen[start][direction] = true;
        while (head < tail) {
            int state = queue[head++], v = state / 2, d = state % 2;
            for (int w : edges[v][d]) if ((used & (1L << w)) == 0 && !seen[w][1 - d]) {
                seen[w][1 - d] = true; reached[w] = true; queue[tail++] = w * 2 + 1 - d;
            }
        }
        int result = 0; for (boolean b : reached) if (b) result++; return result;
    }
    static void dfs(int v, int direction, long used, int moves) {
        best = Math.max(best, moves);
        if (best == count - 1 || moves + upper(v, direction, used) <= best) return;
        for (int w : edges[v][direction]) if ((used & (1L << w)) == 0)
            dfs(w, 1 - direction, used | (1L << w), moves + 1);
    }
    public static void main(String[] args) throws Exception {
        int[] input = integers(read()); n = input[0]; m = input[1]; count = n * m;
        height = Arrays.copyOfRange(input, 2, input.length); edges = new int[count][2][];
        int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
        for (int v = 0; v < count; v++) for (int d = 0; d < 2; d++) {
            ArrayList<Integer> next = new ArrayList<>();
            for (int k = 0; k < 4; k++) {
                int r = v / m + dr[k], c = v % m + dc[k], w = r * m + c;
                if (r >= 0 && r < n && c >= 0 && c < m && (d == 0 ? height[w] > height[v] : height[w] < height[v])) next.add(w);
            }
            edges[v][d] = next.stream().mapToInt(Integer::intValue).toArray();
        }
        for (int v = 0; v < count && best < count - 1; v++) for (int d = 0; d < 2; d++) dfs(v, d, 1L << v, 0);
        System.out.print(best);
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
