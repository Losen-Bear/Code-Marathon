# 075 赌神 — Java 题解

原题面：[题目记录](statement.md)，原记录标题 `075 赌神`。实际题号以本文件标题为准。

## 解题思路

用TreeMap统计牌数，反复取当前最小的牌x，必须从x开始组成一组x、x+1……x+groupSize-1，各扣除一张。总数不能整除组大小或缺任何一张都返回false。

## 正确性说明

当前最小牌不可能处在组中间，因为更小的牌已经不存在；它只能是某一组的首牌。因此贪心组的组成是必要的，扣除后对子问题重复即可判断全部牌是否能分组。

## 复杂度

时间O(n log n)，空间O(n)。

## 题面与格式说明

此题在上传记录中缺失，已于2026-10-15从比赛网页075补取，完整题面见statement.md。

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
        String[] lines = read().trim().split("\\R"); long[] hand = numbers(lines[0]); int size = (int) numbers(lines[1])[0];
        if (hand.length % size != 0) { System.out.print("false"); return; }
        TreeMap<Long,Integer> counts = new TreeMap<>(); for (long x : hand) counts.put(x, counts.getOrDefault(x,0)+1);
        while (!counts.isEmpty()) {
            long first = counts.firstKey();
            for (int i = 0; i < size; i++) {
                long x = first + i; Integer count = counts.get(x);
                if (count == null) { System.out.print("false"); return; }
                if (count == 1) counts.remove(x); else counts.put(x,count - 1);
            }
        }
        System.out.print("true");
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
