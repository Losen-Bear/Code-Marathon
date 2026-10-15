# 91-100 Java 完整程序与题解索引

**提交某一题时，复制下面该题 Java 代码块的全部内容。**每段都是独立的 `public class Main` 程序，已包含输入、输出及所有辅助方法。每次只提交一道题的一个代码块。

| 题号 | 题目 | 解题说明 | 完整源文件 |
| --- | --- | --- | --- |
| [091](#submit-091) | 智慧码头 | [题解](091/README.md) | [Main.java](091/Main.java) |
| [092](#submit-092) | 计算数组积分 | [题解](092/README.md) | [Main.java](092/Main.java) |
| [093](#submit-093) | 可怜的小白鼠 | [题解](093/README.md) | [Main.java](093/Main.java) |
| [094](#submit-094) | 收房租 | [题解](094/README.md) | [Main.java](094/Main.java) |
| [095](#submit-095) | 买到所有玩具 | [题解](095/README.md) | [Main.java](095/Main.java) |
| [096](#submit-096) | 滑板 | [题解](096/README.md) | [Main.java](096/Main.java) |
| [097](#submit-097) | 除杂草 | [题解](097/README.md) | [Main.java](097/Main.java) |
| [098](#submit-098) | CPU管理 | [题解](098/README.md) | [Main.java](098/Main.java) |
| [099](#submit-099) | 贪吃鬼 | [题解](099/README.md) | [Main.java](099/Main.java) |
| [100](#submit-100) | 最大的数字 | [题解](100/README.md) | [Main.java](100/Main.java) |

本地批量运行可用 [Solutions091_100.java](Solutions091_100.java)；它需要命令行题号参数，向 OJ 提交时请使用下面的单题完整程序。

<a id="submit-091"></a>

## 091 智慧码头

[解题思路与格式说明](091/README.md) · [独立源文件](091/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        int[] input = integers(read()); int n = input[0]; int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) grid[i][j] = input[1+i*n+j];
        long impossible = Long.MIN_VALUE/4; long[][] dp = new long[n][n]; for (long[] row : dp) Arrays.fill(row,impossible); dp[0][0] = Math.min(2,grid[0][0]);
        for (int step = 1; step <= 2*(n-1); step++) {
            long[][] next = new long[n][n]; for (long[] row : next) Arrays.fill(row,impossible);
            for (int r1 = Math.max(0,step-n+1); r1 <= Math.min(n-1,step); r1++)
                for (int r2 = Math.max(0,step-n+1); r2 <= Math.min(n-1,step); r2++) {
                    long best = impossible;
                    for (int a = 0; a <= 1; a++) for (int b = 0; b <= 1; b++) if (r1-a >= 0 && r2-b >= 0) best = Math.max(best,dp[r1-a][r2-b]);
                    if (best == impossible) continue;
                    int gain = r1 == r2 ? Math.min(2,grid[r1][step-r1]) : Math.min(1,grid[r1][step-r1])+Math.min(1,grid[r2][step-r2]);
                    next[r1][r2] = best+gain;
                }
            dp = next;
        }
        System.out.print(dp[n-1][n-1]);
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

<a id="submit-092"></a>

## 092 计算数组积分

[解题思路与格式说明](092/README.md) · [独立源文件](092/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static long gcd(long a, long b) { while (b != 0) { long r = a%b; a = b; b = r; } return a; }
    public static void main(String[] args) throws Exception {
        long[] a = numbers(read()); int m = a.length; long[][] common = new long[m][m];
        for (int i = 0; i < m; i++) for (int j = i+1; j < m; j++) common[i][j] = gcd(a[i],a[j]);
        long[] dp = new long[1<<m]; Arrays.fill(dp,-1); dp[0] = 0;
        for (int mask = 0; mask < dp.length; mask++) if (dp[mask] >= 0 && (Integer.bitCount(mask)&1) == 0) {
            int operation = Integer.bitCount(mask)/2+1;
            for (int i = 0; i < m; i++) if ((mask & (1<<i)) == 0)
                for (int j = i+1; j < m; j++) if ((mask & (1<<j)) == 0) { int next = mask|(1<<i)|(1<<j); dp[next] = Math.max(dp[next],dp[mask]+operation*common[i][j]); }
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

<a id="submit-093"></a>

## 093 可怜的小白鼠

[解题思路与格式说明](093/README.md) · [独立源文件](093/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        int[] a = integers(read()); int states = a[2]/a[1]+1, answer = 0; long capacity = 1;
        while (capacity < a[0]) { capacity *= states; answer++; } System.out.print(answer);
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

<a id="submit-094"></a>

## 094 收房租

[解题思路与格式说明](094/README.md) · [独立源文件](094/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        long[] a = numbers(read()); int n = (int)a[0]; long older = 0, previous = 0;
        for (int i = 1; i <= n; i++) { long current = Math.max(previous,older+a[i]); older = previous; previous = current; }
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

<a id="submit-095"></a>

## 095 买到所有玩具

[解题思路与格式说明](095/README.md) · [独立源文件](095/Main.java)

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

<a id="submit-096"></a>

## 096 滑板

[解题思路与格式说明](096/README.md) · [独立源文件](096/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        int[] input = integers(read()); int n = input[0], m = input[1]; int[] height = Arrays.copyOfRange(input,2,input.length), degree = new int[n*m];
        int[] dr = {-1,1,0,0}, dc = {0,0,-1,1}; ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int v = 0; v < n*m; v++) {
            for (int d = 0; d < 4; d++) { int r = v/m+dr[d], c = v%m+dc[d]; if (r >= 0 && r < n && c >= 0 && c < m && height[r*m+c] < height[v]) degree[v]++; }
            if (degree[v] == 0) queue.add(v);
        }
        int length = 0;
        while (!queue.isEmpty()) { int size = queue.size(); length++;
            while (size-- > 0) { int v = queue.remove();
                for (int d = 0; d < 4; d++) { int r = v/m+dr[d], c = v%m+dc[d], w = r*m+c;
                    if (r >= 0 && r < n && c >= 0 && c < m && height[w] > height[v] && --degree[w] == 0) queue.add(w);
                }
            }
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

<a id="submit-097"></a>

## 097 除杂草

[解题思路与格式说明](097/README.md) · [独立源文件](097/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        int[] input = integers(read()); int n = input[0]; int[] right = new int[n+1];
        for (int i = 0; i <= n; i++) { int left = Math.max(0,i-input[i+1]); right[left] = Math.max(right[left],Math.min(n,i+input[i+1])); }
        int end = 0, far = 0, count = 0;
        for (int i = 0; i < n; i++) {
            far = Math.max(far,right[i]);
            if (i == end) { if (far <= i) { System.out.print(-1); return; } count++; end = far; if (end >= n) { System.out.print(count); return; } }
        }
        System.out.print(count);
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

<a id="submit-098"></a>

## 098 CPU管理

[解题思路与格式说明](098/README.md) · [独立源文件](098/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        String[] tokens = read().trim().split("\\s+"); int count = Integer.parseInt(tokens[0]), maximum = 0, tied = 0; long cooldown = Long.parseLong(tokens[count+1]);
        HashMap<String,Integer> frequency = new HashMap<>(); for (int i = 1; i <= count; i++) frequency.put(tokens[i],frequency.getOrDefault(tokens[i],0)+1);
        for (int f : frequency.values()) { if (f > maximum) { maximum = f; tied = 1; } else if (f == maximum) tied++; }
        System.out.print(Math.max(count,(maximum-1L)*(cooldown+1)+tied));
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

<a id="submit-099"></a>

## 099 贪吃鬼

[解题思路与格式说明](099/README.md) · [独立源文件](099/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static long solve(long[] a, int left, int right, int count) {
        long impossible = Long.MIN_VALUE/4; long[] older = new long[count+1], previous = new long[count+1]; Arrays.fill(older,impossible); Arrays.fill(previous,impossible); older[0] = previous[0] = 0;
        for (int i = left; i <= right; i++) {
            long[] current = previous.clone();
            for (int j = 1; j <= count; j++) if (older[j-1] != impossible) current[j] = Math.max(current[j],older[j-1]+a[i]);
            older = previous; previous = current;
        }
        return previous[count];
    }
    public static void main(String[] args) throws Exception {
        long[] a = numbers(read()); int count = a.length/3; System.out.print(Math.max(solve(a,0,a.length-2,count),solve(a,1,a.length-1,count)));
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

<a id="submit-100"></a>

## 100 最大的数字

[解题思路与格式说明](100/README.md) · [独立源文件](100/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static int[] count = new int[10]; static char[] answer; static String limit;
    static boolean search(int position, boolean tight) {
        if (position == answer.length) return true;
        if (!tight) {
            int next = position; for (int d = 9; d >= 0; d--) for (int i = 0; i < count[d]; i++) answer[next++] = (char)('0'+d);
            return true;
        }
        int ceiling = limit.charAt(position)-'0';
        for (int d = ceiling; d >= 0; d--) if (count[d] > 0) {
            count[d]--; answer[position] = (char)('0'+d);
            if (search(position+1,d == ceiling)) return true; count[d]++;
        }
        return false;
    }
    public static void main(String[] args) throws Exception {
        String[] t = read().trim().split("\\s+"); String a = new BigInteger(t[0]).toString(), b = new BigInteger(t[1]).toString();
        for (char c : a.toCharArray()) count[c-'0']++; answer = new char[a.length()];
        if (b.length() > a.length()) { int p = 0; for (int d = 9; d >= 0; d--) for (int i = 0; i < count[d]; i++) answer[p++] = (char)('0'+d); }
        else { StringBuilder padded = new StringBuilder(); for (int i = b.length(); i < a.length(); i++) padded.append('0'); limit = padded.append(b).toString(); if (!search(0,true)) { System.out.print(-1); return; } }
        System.out.print(new BigInteger(new String(answer)));
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
