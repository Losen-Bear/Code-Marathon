# 096 滑板 — Java 题解

原题面：[题目记录](../test91-100.md)，原记录标题 `096 滑板`。实际题号以本文件标题为准。

## 解题思路

把严格下坡路径反向看作严格上坡的有向无环图。每格入度为更低邻格数，从局部最低点开始拓扑BFS，按层删除并更新更高邻格；总层数是最长路径的格子数。

## 正确性说明

严格高度变化排除环。一个节点只能在所有较低前驱被处理后入队，因此其所在层为最长前驱路径长度加1。全图最大层数就是最长上坡路径长度，反向等于最长下坡。

## 复杂度

时间O(nm)，空间O(nm)。迭代实现避免长度10000的递归栈。

## 题面与格式说明

按样例长度是经过格子数，不是移动次数；相同高度不能连续滑行。

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
        int[] input = integers(read()); int n = input[0], m = input[1]; int[] height = Arrays.copyOfRange(input,2,input.length), degree = new int[n*m];
        int[] dr = {-1,1,0,0}, dc = {0,0,-1,1}; ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int v = 0; v < n*m; v++) {
            for (int d = 0; d < 4; d++) { int r = v/m+dr[d], c = v%m+dc[d]; if (r >= 0 && r < n && c >= 0 && c < m && height[r*m+c] < height[v]) degree[v]++; }
            if (degree[v] == 0) queue.add(v);
        }
        int length = 0;
        while (!queue.isEmpty()) { int size = queue.size(); length++;
            while (size-- > 0) { int v = queue.remove();
                for (int d = 0; d < 4; d++) { int r = v/m+dr[d], c = v%m+dc[d], w = r*m+c;
                    if (r >= 0 && r < n && c >= 0 && c < m && height[w] > height[v] && --degree[w] == 0) queue.add(w);
                }
            }
        }
        System.out.print(length);
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
