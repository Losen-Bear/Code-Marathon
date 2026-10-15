# 71-80 Java 完整程序与题解索引

**提交某一题时，复制下面该题 Java 代码块的全部内容。**每段都是独立的 `public class Main` 程序，已包含输入、输出及所有辅助方法。每次只提交一道题的一个代码块。

题号采用比赛网页实际编号，上传记录的偏移见[题号校正](NUMBERING.md)。

| 题号 | 题目 | 解题说明 | 完整源文件 |
| --- | --- | --- | --- |
| [071](#submit-071) | 云餐一楼 | [题解](071/README.md) | [Main.java](071/Main.java) |
| [072](#submit-072) | 失落遗迹的宝藏 | [题解](072/README.md) | [Main.java](072/Main.java) |
| [073](#submit-073) | 忘掉原神 | [题解](073/README.md) | [Main.java](073/Main.java) |
| [074](#submit-074) | 大数学家 | [题解](074/README.md) | [Main.java](074/Main.java) |
| [075](#submit-075) | 赌神 | [题解](075/README.md) | [Main.java](075/Main.java) |
| [076](#submit-076) | 最长线段 | [题解](076/README.md) | [Main.java](076/Main.java) |
| [077](#submit-077) | 还记得大明湖畔的排列组合吗？ | [题解](077/README.md) | [Main.java](077/Main.java) |
| [078](#submit-078) | 走地板 | [题解](078/README.md) | [Main.java](078/Main.java) |
| [079](#submit-079) | 找到中位数 | [题解](079/README.md) | [Main.java](079/Main.java) |
| [080](#submit-080) | 最大岛屿 | [题解](080/README.md) | [Main.java](080/Main.java) |

本地批量运行可用 [Solutions071_080.java](Solutions071_080.java)；它需要命令行题号参数，向 OJ 提交时请使用下面的单题完整程序。

<a id="submit-071"></a>

## 071 云餐一楼

[解题思路与格式说明](071/README.md) · [独立源文件](071/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        long[] input = numbers(read()); int n = (int) input[0], length = 0; long[] tails = new long[n];
        for (int i = 1; i <= n; i++) {
            int left = 0, right = length;
            while (left < right) { int mid = (left + right) / 2; if (tails[mid] >= input[i]) right = mid; else left = mid + 1; }
            tails[left] = input[i]; if (left == length) length++;
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

<a id="submit-072"></a>

## 072 失落遗迹的宝藏

[解题思路与格式说明](072/README.md) · [独立源文件](072/Main.java)

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

<a id="submit-073"></a>

## 073 忘掉原神

[解题思路与格式说明](073/README.md) · [独立源文件](073/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        long[] input = numbers(read()); int n = (int) input[0]; long before = Long.MIN_VALUE/4, zero = 0, after = Long.MIN_VALUE/4, best = 0;
        for (int i = 1; i <= n; i++) {
            long x = input[i], oldBefore = before, oldZero = zero, oldAfter = after;
            before = Math.max(x, oldBefore + x); zero = Math.max(0, Math.max(oldBefore, oldZero));
            after = Math.max(oldZero + x, oldAfter + x); best = Math.max(best, Math.max(before, Math.max(zero, after)));
        }
        System.out.print(best);
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

<a id="submit-074"></a>

## 074 大数学家

[解题思路与格式说明](074/README.md) · [独立源文件](074/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        int n = (int) numbers(read())[0]; if (n <= 3) { System.out.print(n - 1); return; }
        long result = 1, mod = 1000000007L;
        while (n > 4) { result = result * 3 % mod; n -= 3; }
        System.out.print(result * n % mod);
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

<a id="submit-075"></a>

## 075 赌神

[解题思路与格式说明](075/README.md) · [独立源文件](075/Main.java)

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

<a id="submit-076"></a>

## 076 最长线段

[解题思路与格式说明](076/README.md) · [独立源文件](076/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static class FastInput {
        BufferedInputStream in = new BufferedInputStream(System.in,1<<16);
        int nextInt() throws IOException {
            int c; do { c = in.read(); } while (c <= 32 && c != -1);
            if (c == -1) throw new EOFException(); int value = 0;
            while (c > 32 && c != -1) { value = value * 10 + c - '0'; c = in.read(); } return value;
        }
    }
    public static void main(String[] args) throws Exception {
        FastInput input = new FastInput(); int m = input.nextInt(), n = input.nextInt(), best = 0;
        int[] vertical = new int[n], diagonal = new int[n], anti = new int[n+1];
        for (int r = 0; r < m; r++) {
            int horizontal = 0, previousDiagonal = 0;
            for (int c = 0; c < n; c++) {
                int oldDiagonal = diagonal[c], value = input.nextInt();
                if (value == 1) { horizontal++; vertical[c]++; diagonal[c] = previousDiagonal + 1; anti[c] = anti[c+1] + 1; }
                else { horizontal = 0; vertical[c] = 0; diagonal[c] = 0; anti[c] = 0; }
                previousDiagonal = oldDiagonal; best = Math.max(best, Math.max(horizontal,Math.max(vertical[c],Math.max(diagonal[c],anti[c]))));
            }
        }
        System.out.print(best);
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

<a id="submit-077"></a>

## 077 还记得大明湖畔的排列组合吗？

[解题思路与格式说明](077/README.md) · [独立源文件](077/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        int[] input = integers(read()); int n = input[0], k = input[1] - 1; int[] factorial = new int[n+1]; factorial[0] = 1;
        ArrayList<Integer> digits = new ArrayList<>(); for (int i = 1; i <= n; i++) { factorial[i] = factorial[i-1]*i; digits.add(i); }
        StringBuilder out = new StringBuilder();
        for (int remaining = n; remaining > 0; remaining--) { int block = factorial[remaining-1]; out.append(digits.remove(k/block)); k %= block; }
        System.out.print(out);
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

<a id="submit-078"></a>

## 078 走地板

[解题思路与格式说明](078/README.md) · [独立源文件](078/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static long[][] grid; static long best = Long.MIN_VALUE, upper;
    static void search(int r, int c, long total) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0 || best == upper) return;
        long value = grid[r][c]; total += value; best = Math.max(best,total); grid[r][c] = 0;
        search(r-1,c,total); search(r+1,c,total); search(r,c-1,total); search(r,c+1,total); grid[r][c] = value;
    }
    public static void main(String[] args) throws Exception {
        long[] input = numbers(read()); int m = (int) input[0], n = (int) input[1]; grid = new long[m][n];
        for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) { grid[i][j] = input[2+i*n+j]; upper += Math.max(0,grid[i][j]); }
        for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) search(i,j,0);
        System.out.print(best == Long.MIN_VALUE ? 0 : best);
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

<a id="submit-079"></a>

## 079 找到中位数

[解题思路与格式说明](079/README.md) · [独立源文件](079/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new StringReader(read())); String line = reader.readLine(); long[] a = numbers(line == null ? "" : line);
        line = reader.readLine(); long[] b = numbers(line == null ? "" : line); int size = a.length+b.length, i = 0, j = 0; long previous = 0, current = 0;
        for (int k = 0; k <= size/2; k++) { previous = current; current = j == b.length || (i < a.length && a[i] <= b[j]) ? a[i++] : b[j++]; }
        if ((size & 1) == 1) System.out.print(current);
        else System.out.print(java.math.BigDecimal.valueOf(previous).add(java.math.BigDecimal.valueOf(current)).divide(java.math.BigDecimal.valueOf(2)).stripTrailingZeros().toPlainString());
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

<a id="submit-080"></a>

## 080 最大岛屿

[解题思路与格式说明](080/README.md) · [独立源文件](080/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        int[] input = integers(read()); int m = input[0], n = input[1], best = 0; int[] dp = new int[n+1];
        for (int r = 0; r < m; r++) { int diagonal = 0;
            for (int c = 1; c <= n; c++) { int old = dp[c]; dp[c] = input[2+r*n+c-1] == 0 ? 0 : Math.min(diagonal,Math.min(dp[c],dp[c-1]))+1; diagonal = old; best = Math.max(best,dp[c]); }
        }
        System.out.print(best*best);
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
