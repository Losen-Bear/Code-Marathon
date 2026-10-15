# 81-90 Java 完整程序与题解索引

**提交某一题时，复制下面该题 Java 代码块的全部内容。**每段都是独立的 `public class Main` 程序，已包含输入、输出及所有辅助方法。每次只提交一道题的一个代码块。

题号采用比赛网页实际编号，上传记录的偏移见[题号校正](NUMBERING.md)。

| 题号 | 题目 | 解题说明 | 完整源文件 |
| --- | --- | --- | --- |
| [081](#submit-081) | 最长字符串 | [题解](081/README.md) | [Main.java](081/Main.java) |
| [082](#submit-082) | 翻越障碍物 | [题解](082/README.md) | [Main.java](082/Main.java) |
| [083](#submit-083) | 货物运输 | [题解](083/README.md) | [Main.java](083/Main.java) |
| [084](#submit-084) | 拟合 | [题解](084/README.md) | [Main.java](084/Main.java) |
| [085](#submit-085) | 机器人迷宫探索 | [题解](085/README.md) | [Main.java](085/Main.java) |
| [086](#submit-086) | 回到原点 | [题解](086/README.md) | [Main.java](086/Main.java) |
| [087](#submit-087) | 数字和 | [题解](087/README.md) | [Main.java](087/Main.java) |
| [088](#submit-088) | 完美排列 | [题解](088/README.md) | [Main.java](088/Main.java) |
| [089](#submit-089) | 字母映射 | [题解](089/README.md) | [Main.java](089/Main.java) |
| [090](#submit-090) | 采矿 | [题解](090/README.md) | [Main.java](090/Main.java) |

本地批量运行可用 [Solutions081_090.java](Solutions081_090.java)；它需要命令行题号参数，向 OJ 提交时请使用下面的单题完整程序。

<a id="submit-081"></a>

## 081 最长字符串

[解题思路与格式说明](081/README.md) · [独立源文件](081/Main.java)

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

<a id="submit-082"></a>

## 082 翻越障碍物

[解题思路与格式说明](082/README.md) · [独立源文件](082/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        long[] input = numbers(read()); int n = (int) input[0], ladders = (int) input[n+2]; long bricks = input[n+1];
        PriorityQueue<Long> heap = new PriorityQueue<>();
        for (int i = 0; i < n-1; i++) {
            long rise = input[i+2]-input[i+1]; if (rise > 0) heap.add(rise);
            if (heap.size() > ladders) bricks -= heap.remove();
            if (bricks < 0) { System.out.print(i); return; }
        }
        System.out.print(n-1);
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

<a id="submit-083"></a>

## 083 货物运输

[解题思路与格式说明](083/README.md) · [独立源文件](083/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        long[] input = numbers(read()); int n = (int) input[0], m = (int) input[1]; long[][] goods = new long[m][3];
        for (int i = 0; i < m; i++) for (int j = 0; j < 3; j++) goods[i][j] = input[2+i*3+j];
        Arrays.sort(goods,Comparator.comparingLong(x -> x[1])); long[] dp = new long[n+1]; int next = 0;
        for (int x = 1; x <= n; x++) {
            dp[x] = dp[x-1];
            while (next < m && goods[next][1] == x) {
                long[] g = goods[next++]; dp[x] = Math.max(dp[x],dp[(int)g[0]]+g[1]-g[0]+g[2]);
            }
        }
        System.out.print(dp[n]);
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

<a id="submit-084"></a>

## 084 拟合

[解题思路与格式说明](084/README.md) · [独立源文件](084/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        long[] input = numbers(read()); int n = (int) input[0], m = (int) input[1]; long[] dp = new long[m+1];
        for (int j = 1; j <= m; j++) dp[j] = dp[j-1]+Math.abs(input[1+n+j]);
        for (int i = 1; i <= n; i++) {
            long a = input[1+i]; long[] next = new long[m+1]; next[0] = dp[0]+Math.abs(a);
            for (int j = 1; j <= m; j++) {
                long b = input[1+n+j]; next[j] = Math.min(dp[j]+Math.abs(a),Math.min(next[j-1]+Math.abs(b),dp[j-1]+Math.abs(a-b)));
            }
            dp = next;
        }
        System.out.print(dp[m]);
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

<a id="submit-085"></a>

## 085 机器人迷宫探索

[解题思路与格式说明](085/README.md) · [独立源文件](085/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static int[][] grid; static boolean[][] visited; static int endRow,endColumn;
    static int[] path; static BufferedWriter writer; static boolean found;
    static void search(int r, int c, int length) throws IOException {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0 || visited[r][c]) return;
        path[length++] = r*grid[0].length+c;
        if (r == endRow && c == endColumn) {
            if (found) writer.newLine(); found = true;
            for (int i = 0; i < length; i++) {
                if (i > 0) writer.write("->"); int p = path[i];
                writer.write("("+(p/grid[0].length+1)+","+(p%grid[0].length+1)+")");
            }
            return;
        }
        visited[r][c] = true;
        search(r,c-1,length); search(r-1,c,length); search(r,c+1,length); search(r+1,c,length);
        visited[r][c] = false;
    }
    public static void main(String[] args) throws Exception {
        int[] a = integers(read()); int m = a[0], n = a[1], position = 2; grid = new int[m][n]; visited = new boolean[m][n]; path = new int[m*n];
        for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) grid[i][j] = a[position++];
        int startRow = a[position++]-1, startColumn = a[position++]-1; endRow = a[position++]-1; endColumn = a[position]-1;
        writer = new BufferedWriter(new OutputStreamWriter(System.out)); search(startRow,startColumn,0);
        if (!found) writer.write("-1"); writer.flush();
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

<a id="submit-086"></a>

## 086 回到原点

[解题思路与格式说明](086/README.md) · [独立源文件](086/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        long[] a = numbers(read()); int steps = Math.toIntExact(a[0]), width = (int)Math.min(a[1],steps/2L+1); long mod = 1000000007L;
        long[] dp = new long[width]; dp[0] = 1;
        for (int step = 0; step < steps; step++) {
            long[] next = new long[width];
            for (int i = 0; i < width; i++) { next[i] = dp[i]; if (i > 0) next[i] += dp[i-1]; if (i+1 < width) next[i] += dp[i+1]; next[i] %= mod; }
            dp = next;
        }
        System.out.print(dp[0]);
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

<a id="submit-087"></a>

## 087 数字和

[解题思路与格式说明](087/README.md) · [独立源文件](087/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        int[] input = integers(read()); int target = input[0], k = input[1]; TreeSet<Integer> coins = new TreeSet<>();
        for (int i = 0; i < k; i++) { if (input[i+2] <= 0) throw new IllegalArgumentException("数字必须为正整数，零或负值可能造成无限组合"); coins.add(input[i+2]); }
        BigInteger[] ways = new BigInteger[target+1]; Arrays.fill(ways,BigInteger.ZERO); ways[0] = BigInteger.ONE;
        for (int coin : coins) for (int t = coin; t <= target; t++) ways[t] = ways[t].add(ways[t-coin]);
        System.out.print(ways[target]);
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

<a id="submit-088"></a>

## 088 完美排列

[解题思路与格式说明](088/README.md) · [独立源文件](088/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        int n = (int)numbers(read())[0]; long[] dp = new long[1<<n]; dp[0] = 1;
        for (int mask = 0; mask < dp.length; mask++) {
            int position = Integer.bitCount(mask)+1;
            for (int x = 1; x <= n; x++) if ((mask & (1<<(x-1))) == 0 && (position%x == 0 || x%position == 0)) dp[mask|(1<<(x-1))] += dp[mask];
        }
        System.out.print(dp[dp.length-1]);
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

<a id="submit-089"></a>

## 089 字母映射

[解题思路与格式说明](089/README.md) · [独立源文件](089/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        String s = read().trim(); if (s.isEmpty()) { System.out.print(0); return; }
        long older = 1, previous = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 1; i < s.length(); i++) {
            long current = s.charAt(i) != '0' ? previous : 0; int pair = (s.charAt(i-1)-'0')*10+s.charAt(i)-'0';
            if (pair >= 10 && pair <= 26) current += older; older = previous; previous = current;
        }
        System.out.print(previous);
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

<a id="submit-090"></a>

## 090 采矿

[解题思路与格式说明](090/README.md) · [独立源文件](090/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        long[] input = numbers(read()); int capacity = (int) input[0], n = (int) input[1]; long[] dp = new long[capacity + 1];
        for (int i = 0; i < n; i++) {
            int time = (int) input[2 + i*2]; long value = input[3 + i*2];
            for (int t = capacity; t >= time; t--) dp[t] = Math.max(dp[t], dp[t - time] + value);
        }
        System.out.print(dp[capacity]);
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
