# 043 a=b — Java 题解

原题面：[本组题目](../test41-50.md)，查找题号 `043`。原记录保留不改。

## 解题思路

记忆化递归。a≤b时只能靠+1最优，答案b-a；a>b时先考虑直接减到b，再枚举5、11两种除数，分别调整到最近的下方或上方倍数、除一次、递归。

## 正确性说明

最优路径的第一次除法之前只需通过±1调整。针对除数d，只需最近上下倍数：多调整d步才能使除后值多变化1，不如先除后再调整1步。两种最近倍数和无除法路径覆盖最优解，递归取最小。

## 复杂度

记忆化可达状态数记为U，时间、空间 O(U)，U≤O(max(a,b))。

## Java 实现

**提交方式：选择 Java，将下面代码块中的全部代码复制到提交框。**包含 `import`、`public class Main`、`main` 入口和全部辅助方法，无需拼接原题 Hint，也无需修改类名。

源文件：[Main.java](Main.java)。兼容 Java 8 及以上，无包名、无第三方依赖。

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static int target; static HashMap<Integer, Integer> memo = new HashMap<>();
    static int solve(int x) {
        if (x <= target) return target - x;
        if (memo.containsKey(x)) return memo.get(x);
        int best = x - target;
        for (int d : new int[]{5, 11}) {
            int r = x % d;
            best = Math.min(best, r + 1 + solve(x / d));
            best = Math.min(best, d - r + 1 + solve(x / d + 1));
        }
        memo.put(x, best); return best;
    }
    public static void main(String[] args) throws Exception { int[] a = integers(read()); target = a[1]; System.out.print(solve(a[0])); }

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
