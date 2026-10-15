# 081 最长字符串 — Java 题解

原题面：[题目记录](../test81-90.md)，原记录标题 `071 最长字符串`。实际题号以本文件标题为准。

## 解题思路

每个字符串转为BitSet字符集合；字符串内部有重复字符则无法选择。回溯枚举选或不选，与已选集合没有交集时才能加入；用剩余字符串长度和作上界剪枝。

## 正确性说明

合法拼接必须每个单词内部无重复、各已选单词字符集合两两不交。两类条件由预处理和集合检查确保；枚举选或不选覆盖全部可行子集，取最大字符数。

## 复杂度

设k为有效字符串数，字符集容量C，最坏时间O(2^k·C/64)，空间O(k·C/64+k)。

## 题面与格式说明

上传文件标为071，按比赛目录归为081。题面未限定字符集，使用UTF-16字符集合，不限定为26个小写字母；有效字符串数量未给上限，最坏搜索指数级。 原题记录中的标题编号为071，本题解按比赛网页编号081归档。

## Java 实现

**提交方式：选择 Java，将下面代码块中的全部代码复制到提交框。**包含 `import`、`public class Main`、`main` 入口和全部辅助方法，无需拼接原题 Hint，也无需修改类名。

源文件：[Main.java](Main.java)。兼容 Java 8 及以上，无包名、无第三方依赖。

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static ArrayList<BitSet> words = new ArrayList<>(); static long[] suffix; static int best;
    static void search(int index, BitSet used, int length) {
        best = Math.max(best,length); if (index == words.size() || length+suffix[index] <= best) return;
        BitSet word = words.get(index);
        if (!used.intersects(word)) { used.or(word); search(index+1,used,length+word.cardinality()); used.andNot(word); }
        search(index+1,used,length);
    }
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new StringReader(read())); int k = Integer.parseInt(reader.readLine().trim());
        for (int i = 0; i < k; i++) {
            String s = reader.readLine(); BitSet mask = new BitSet(); boolean valid = true;
            for (char c : s.toCharArray()) { if (mask.get(c)) valid = false; mask.set(c); }
            if (valid && !s.isEmpty()) words.add(mask);
        }
        suffix = new long[words.size()+1]; for (int i = words.size()-1; i >= 0; i--) suffix[i] = suffix[i+1]+words.get(i).cardinality();
        search(0,new BitSet(),0); System.out.print(best);
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
