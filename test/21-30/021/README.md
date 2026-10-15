# 021 金字塔挑战 — Java 题解

原题面：[本组题目](../test21-30.md)，查找题号 `021`。原记录保留不改。

## 解题思路

先检查1+所有房间影响之和是否为正，否则返回-1。顺序遍历，将遇到的负数放入最小堆；若血量不再为正，就把影响最负的房间移到末尾，并从当前血量中撤销其影响。

## 正确性说明

总血量不为正时最后必死。出现不合法前缀时必须至少移走一个此前负数，移走损失最大的房间能使当前和后续前缀血量最大，不会比其他选择需要更多调整。如此贪心获得最少次数；总血量为正保证延后的房间最后能够访问。

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
    public static void main(String[] args) throws Exception {
        long[] values = numbers(read()); long total = 1;
        for (long x : values) total += x;
        if (total <= 0) { System.out.print(-1); return; }
        PriorityQueue<Long> negatives = new PriorityQueue<>(); long health = 1; int answer = 0;
        for (long x : values) {
            health += x; if (x < 0) negatives.add(x);
            if (health <= 0) { health -= negatives.remove(); answer++; }
        }
        System.out.print(answer);
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
