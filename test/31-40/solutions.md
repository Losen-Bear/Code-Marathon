# 31-40 Java 完整程序与题解索引

**提交某一题时，复制下面该题 Java 代码块的全部内容。**每段都是独立的 `public class Main` 程序，已包含输入、输出及所有辅助方法。每次只提交一道题的一个代码块。

| 题号 | 题目 | 解题说明 | 完整源文件 |
| --- | --- | --- | --- |
| [031](#submit-031) | 吃苹果 | [题解](031/README.md) | [Main.java](031/Main.java) |
| [032](#submit-032) | 字符串关系 | [题解](032/README.md) | [Main.java](032/Main.java) |
| [033](#submit-033) | 谁是多数元素 | [题解](033/README.md) | [Main.java](033/Main.java) |
| [034](#submit-034) | 找特定区间 | [题解](034/README.md) | [Main.java](034/Main.java) |
| [035](#submit-035) | 购买两本书 | [题解](035/README.md) | [Main.java](035/Main.java) |
| [036](#submit-036) | 寻找字符串 | [题解](036/README.md) | [Main.java](036/Main.java) |
| [037](#submit-037) | 超大数运算 | [题解](037/README.md) | [Main.java](037/Main.java) |
| [038](#submit-038) | 寻找方案 | [题解](038/README.md) | [Main.java](038/Main.java) |
| [039](#submit-039) | 特殊的回文 | [题解](039/README.md) | [Main.java](039/Main.java) |
| [040](#submit-040) | 走出网格 | [题解](040/README.md) | [Main.java](040/Main.java) |

本地批量运行可用 [Solutions031_040.java](Solutions031_040.java)；它需要命令行题号参数，向 OJ 提交时请使用下面的单题完整程序。

<a id="submit-031"></a>

## 031 吃苹果

[解题思路与格式说明](031/README.md) · [独立源文件](031/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static HashMap<Long, Integer> memo = new HashMap<>();
    static int days(long n) {
        if (n <= 1) return (int) n;
        if (memo.containsKey(n)) return memo.get(n);
        int result = 1 + Math.min((int) (n % 2) + days(n / 2), (int) (n % 3) + days(n / 3));
        memo.put(n, result); return result;
    }
    public static void main(String[] args) throws Exception { System.out.print(days(numbers(read())[0])); }

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

<a id="submit-032"></a>

## 032 字符串关系

[解题思路与格式说明](032/README.md) · [独立源文件](032/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new StringReader(read()));
        String a = reader.readLine(), b = reader.readLine(); if (a == null) a = ""; if (b == null) b = "";
        int i = 0; for (int j = 0; j < b.length() && i < a.length(); j++) if (a.charAt(i) == b.charAt(j)) i++;
        System.out.print(i == a.length());
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

<a id="submit-033"></a>

## 033 谁是多数元素

[解题思路与格式说明](033/README.md) · [独立源文件](033/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        int candidate = 0, count = 0;
        for (int value : integers(read())) { if (count == 0) candidate = value; count += value == candidate ? 1 : -1; }
        System.out.print(candidate);
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

<a id="submit-034"></a>

## 034 找特定区间

[解题思路与格式说明](034/README.md) · [独立源文件](034/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static long[] prefix, temp; static long low, high;
    static long count(int left, int right) {
        if (right - left <= 1) return 0; int mid = (left + right) / 2;
        long answer = count(left, mid) + count(mid, right); int a = mid, b = mid;
        for (int i = left; i < mid; i++) {
            while (a < right && prefix[a] - prefix[i] < low) a++;
            while (b < right && prefix[b] - prefix[i] <= high) b++;
            answer += b - a;
        }
        int i = left, j = mid, k = left;
        while (i < mid || j < right) temp[k++] = j == right || (i < mid && prefix[i] <= prefix[j]) ? prefix[i++] : prefix[j++];
        System.arraycopy(temp, left, prefix, left, right - left); return answer;
    }
    public static void main(String[] args) throws Exception {
        long[] input = numbers(read()); low = input[0]; high = input[1]; int n = (int) input[2];
        prefix = new long[n + 1]; temp = new long[n + 1];
        for (int i = 0; i < n; i++) prefix[i + 1] = prefix[i] + input[i + 3];
        System.out.print(count(0, n + 1));
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

<a id="submit-035"></a>

## 035 购买两本书

[解题思路与格式说明](035/README.md) · [独立源文件](035/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        String[] lines = read().trim().split("\\R"); long first = Long.MAX_VALUE, second = Long.MAX_VALUE;
        for (long x : numbers(lines[0])) { if (x < first) { second = first; first = x; } else if (x < second) second = x; }
        long money = numbers(lines[1])[0]; System.out.print(first + second <= money ? money - first - second : money);
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

<a id="submit-036"></a>

## 036 寻找字符串

[解题思路与格式说明](036/README.md) · [独立源文件](036/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static char[][] board; static boolean[][] visited; static String word;
    static boolean dfs(int r, int c, int index) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || visited[r][c] || board[r][c] != word.charAt(index)) return false;
        if (index + 1 == word.length()) return true;
        visited[r][c] = true;
        boolean found = dfs(r - 1, c, index + 1) || dfs(r + 1, c, index + 1) || dfs(r, c - 1, index + 1) || dfs(r, c + 1, index + 1);
        visited[r][c] = false; return found;
    }
    public static void main(String[] args) throws Exception {
        String[] t = read().trim().split("\\s+"); word = t[0]; int m = Integer.parseInt(t[1]), n = Integer.parseInt(t[2]);
        board = new char[m][n]; visited = new boolean[m][n];
        for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) board[i][j] = t[3 + i * n + j].charAt(0);
        if (word.length() > m * n) { System.out.print("false"); return; }
        for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) if (dfs(i, j, 0)) { System.out.print("true"); return; }
        System.out.print("false");
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

<a id="submit-037"></a>

## 037 超大数运算

[解题思路与格式说明](037/README.md) · [独立源文件](037/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static int power(int a, int b) { int result = 1; while (b > 0) { if ((b & 1) != 0) result = result * a % 1337; a = a * a % 1337; b >>= 1; } return result; }
    public static void main(String[] args) throws Exception {
        int[] input = integers(read()); int a = input[0] % 1337, result = 1;
        for (int i = 0; i < input[1]; i++) result = power(result, 10) * power(a, input[i + 2]) % 1337;
        System.out.print(result);
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

<a id="submit-038"></a>

## 038 寻找方案

[解题思路与格式说明](038/README.md) · [独立源文件](038/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static long choose(int n, int r) { r = Math.min(r, n - r); long result = 1; for (int i = 1; i <= r; i++) result = result * (n - r + i) / i; return result; }
    public static void main(String[] args) throws Exception {
        long k = numbers(read())[0], answer = 0;
        for (int j = 0; j <= 31; j++) { long d = (1L << j) - k; if (d >= 0 && d <= j + 1) answer += choose(j + 1, (int) d); }
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

<a id="submit-039"></a>

## 039 特殊的回文

[解题思路与格式说明](039/README.md) · [独立源文件](039/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        String s = read().trim(); int left = 0, right = s.length(), answer = 0;
        while (left < right) {
            boolean matched = false;
            for (int len = 1; len * 2 <= right - left; len++)
                if (s.regionMatches(left, s, right - len, len)) { answer += 2; left += len; right -= len; matched = true; break; }
            if (!matched) { answer++; break; }
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

<a id="submit-040"></a>

## 040 走出网格

[解题思路与格式说明](040/README.md) · [独立源文件](040/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        int[] a = integers(read()); int m = a[0], n = a[1], moves = a[2], row = a[3], column = a[4];
        long mod = 1000000007L, answer = 0; long[][] dp = new long[m][n]; dp[row][column] = 1;
        int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
        for (int step = 0; step < moves; step++) {
            long[][] next = new long[m][n];
            for (int r = 0; r < m; r++) for (int c = 0; c < n; c++) for (int d = 0; d < 4; d++) {
                int nr = r + dr[d], nc = c + dc[d];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) answer = (answer + dp[r][c]) % mod;
                else next[nr][nc] = (next[nr][nc] + dp[r][c]) % mod;
            }
            dp = next;
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
