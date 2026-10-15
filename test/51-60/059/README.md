# 059 治理洪水 — Java 题解

原题面：[本组题目](../test51-60.md)，查找题号 `059`。原记录保留不改。

## 解题思路

记录每个湖泊上次下雨日，TreeSet保存还未使用的晴天。某湖再下雨时，选其上次下雨之后最早的空闲晴天抽干它；不存在则返回[]。未使用的晴天任选湖1。

## 正确性说明

两次同湖降雨之间必须安排一次抽水。优先用最早可用晴天，为其他更晚开始的需求保留较晚晴天；任何可行方案若选了更晚日，可以与该最早日交换而不破坏已有需求，所以贪心可行且不会错判。

## 复杂度

时间 O(n log n)，空间 O(n)。

## 题面与格式说明

题目允许任意可行解，测试应检查方案是否合法，而不只与某个数组文本比较。

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
        int[] rains = integers(read()), answer = new int[rains.length]; Arrays.fill(answer, 1);
        TreeSet<Integer> dryDays = new TreeSet<>(); HashMap<Integer, Integer> lastRain = new HashMap<>();
        for (int i = 0; i < rains.length; i++) {
            int lake = rains[i]; if (lake == 0) { dryDays.add(i); continue; } answer[i] = -1;
            if (lastRain.containsKey(lake)) {
                Integer day = dryDays.higher(lastRain.get(lake));
                if (day == null) { System.out.print("[]"); return; }
                answer[day] = lake; dryDays.remove(day);
            }
            lastRain.put(lake, i);
        }
        System.out.print(Arrays.toString(answer));
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
