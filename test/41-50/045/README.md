# 045 拼多多 — Java 题解

原题面：[本组题目](../test41-50.md)，查找题号 `045`。原记录保留不改。

## 解题思路

将每张贴纸转为26维字母计数。记忆化状态为排序后的尚缺字母串，选择含首个尚缺字母的贴纸，扣除能覆盖的字母并递归，取1+子问题最小值。空串返回0，无可用贴纸返回-1。

## 正确性说明

任意完整方案都必须用某张贴纸覆盖首个尚缺字母；贴纸使用顺序不影响最终字母计数，因此可把这张贴纸放在第一步。枚举这些贴纸并递归覆盖全部最优方案，规范化余串消除状态重复。

## 复杂度

设R=∏(目标中每种字母频率+1)，时间 O(R·s·(26+L))，空间 O(R·L+s·26)。L≤15时R≤2^15；目标长度50时仍正确但最坏状态可能很大。

## 题面与格式说明

文字中的“每个贴纸上的字母数量是无限的”与样例2矛盾；采用每张贴纸拥有单词实际字母数量、每种贴纸可反复使用的解释。目标长度上限15和50也互相矛盾，此实现不使用固定15位数组。

## Java 实现

**提交方式：选择 Java，将下面代码块中的全部代码复制到提交框。**包含 `import`、`public class Main`、`main` 入口和全部辅助方法，无需拼接原题 Hint，也无需修改类名。

源文件：[Main.java](Main.java)。兼容 Java 8 及以上，无包名、无第三方依赖。

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static int[][] stickers; static HashMap<String, Integer> memo = new HashMap<>();
    static int solve(String remaining) {
        if (remaining.isEmpty()) return 0;
        if (memo.containsKey(remaining)) return memo.get(remaining);
        int[] need = new int[26]; for (char c : remaining.toCharArray()) need[c - 'a']++;
        int best = Integer.MAX_VALUE;
        for (int[] sticker : stickers) {
            if (sticker[remaining.charAt(0) - 'a'] == 0) continue;
            StringBuilder next = new StringBuilder();
            for (int c = 0; c < 26; c++) for (int i = 0; i < Math.max(0, need[c] - sticker[c]); i++) next.append((char) ('a' + c));
            int sub = solve(next.toString()); if (sub >= 0) best = Math.min(best, sub + 1);
        }
        int result = best == Integer.MAX_VALUE ? -1 : best; memo.put(remaining, result); return result;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new StringReader(read()));
        String[] words = reader.readLine().trim().split("\\s+"); char[] target = reader.readLine().trim().toCharArray(); Arrays.sort(target);
        stickers = new int[words.length][26]; for (int i = 0; i < words.length; i++) for (char c : words[i].toCharArray()) stickers[i][c - 'a']++;
        System.out.print(solve(new String(target)));
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
