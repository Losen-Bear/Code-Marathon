# 095 买到所有玩具 — Java 题解

原题面：[题目记录](../test91-100.md)，原记录标题 `095 买到所有玩具`。实际题号以本文件标题为准。

## 解题思路

按价格降序每三件分组，支付前两件，第三件免费。余下不足三件全部付费。实现时升序排序再从末尾扫描，每第三件跳过。

## 正确性说明

每个赠品必须配两件不更便宜的付费品。按降序对最贵的三件分组时，前两件必需付费或能替换对应更贵付费品；取第三件免费可获得最大的当前合法折扣。重复交换可将最优方案整理成相邻三件分组。

## 复杂度

时间O(n log n)，空间O(n)。

## 题面与格式说明

按原题输入输出格式实现。

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
        int[] input = integers(read()); int[] a = Arrays.copyOfRange(input,1,input[0]+1); Arrays.sort(a); long answer = 0;
        for (int i = a.length-1, position = 0; i >= 0; i--,position++) if (position%3 != 2) answer += a[i];
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
