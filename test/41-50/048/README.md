# 048 拼拼图 — Java 题解

原题面：[本组题目](../test41-50.md)，查找题号 `048`。原记录保留不改。

## 解题思路

轮廓线回溯。每列height记录已填充高度，找到最低且最左的一列，在连续同高列上枚举可放正方形的边长，优先尝试大正方形。记录每个轮廓出现的最少使用数，并用当前最优答案剪枝。

## 正确性说明

最低最左未覆盖格子必须属于下一块正方形，其左下角被已覆盖轮廓和边界限定，所有可选边长都被枚举。逐块填充覆盖任意合法铺法；相同轮廓只保留更少已用块数不会损失最优解。

## 复杂度

状态数粗略上界(h+1)^w，每状态至多w个分支；时间 O(w²(h+1)^w)，空间 O(w(h+1)^w)。w=min(n,m)≤13，实际由剪枝大幅压缩。

## 题面与格式说明

不能只用横切/竖切的矩形DP，它会漏掉11×13等需要非贯穿切割的最优铺法。

## Java 实现

**提交方式：选择 Java，将下面代码块中的全部代码复制到提交框。**包含 `import`、`public class Main`、`main` 入口和全部辅助方法，无需拼接原题 Hint，也无需修改类名。

源文件：[Main.java](Main.java)。兼容 Java 8 及以上，无包名、无第三方依赖。

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static int width, height, best; static int[] skyline; static HashMap<Long, Integer> memo = new HashMap<>();
    static void search(int used) {
        if (used >= best) return;
        int left = 0; for (int i = 1; i < width; i++) if (skyline[i] < skyline[left]) left = i;
        int level = skyline[left]; if (level == height) { best = used; return; }
        long key = 0; for (int h : skyline) key = key * 14 + h;
        if (memo.containsKey(key) && memo.get(key) <= used) return; memo.put(key, used);
        int side = 1; while (left + side < width && skyline[left + side] == level && side < height - level) side++;
        for (int size = side; size >= 1; size--) {
            for (int i = left; i < left + size; i++) skyline[i] += size;
            search(used + 1);
            for (int i = left; i < left + size; i++) skyline[i] -= size;
        }
    }
    public static void main(String[] args) throws Exception {
        int[] a = integers(read()); width = Math.min(a[0], a[1]); height = Math.max(a[0], a[1]);
        skyline = new int[width]; best = width * height; search(0); System.out.print(best);
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
