# 51-60 Java 完整程序与题解索引

**提交某一题时，复制下面该题 Java 代码块的全部内容。**每段都是独立的 `public class Main` 程序，已包含输入、输出及所有辅助方法。每次只提交一道题的一个代码块。

| 题号 | 题目 | 解题说明 | 完整源文件 |
| --- | --- | --- | --- |
| [051](#submit-051) | 寻找最小M个数 | [题解](051/README.md) | [Main.java](051/Main.java) |
| [052](#submit-052) | 前两个数之和 | [题解](052/README.md) | [Main.java](052/Main.java) |
| [053](#submit-053) | 下一个漂亮数 | [题解](053/README.md) | [Main.java](053/Main.java) |
| [054](#submit-054) | 解平铺数 | [题解](054/README.md) | [Main.java](054/Main.java) |
| [055](#submit-055) | 玩游戏的一天 | [题解](055/README.md) | [Main.java](055/Main.java) |
| [056](#submit-056) | 寻找“理想数对” | [题解](056/README.md) | [Main.java](056/Main.java) |
| [057](#submit-057) | 楼梯有多少种爬法 | [题解](057/README.md) | [Main.java](057/Main.java) |
| [058](#submit-058) | 文物修复 | [题解](058/README.md) | [Main.java](058/Main.java) |
| [059](#submit-059) | 治理洪水 | [题解](059/README.md) | [Main.java](059/Main.java) |
| [060](#submit-060) | 找数游戏 | [题解](060/README.md) | [Main.java](060/Main.java) |

本地批量运行可用 [Solutions051_060.java](Solutions051_060.java)；它需要命令行题号参数，向 OJ 提交时请使用下面的单题完整程序。

<a id="submit-051"></a>

## 051 寻找最小M个数

[解题思路与格式说明](051/README.md) · [独立源文件](051/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        String[] lines = read().trim().split("\\R"); int[] a = integers(lines[0]); int m = (int) numbers(lines[1])[0];
        if (m == 0) { System.out.print("[]"); return; }
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int x : a) { if (heap.size() < m) heap.add(x); else if (x < heap.peek()) { heap.remove(); heap.add(x); } }
        int[] result = new int[heap.size()]; for (int i = result.length - 1; i >= 0; i--) result[i] = heap.remove();
        System.out.print(Arrays.toString(result));
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

<a id="submit-052"></a>

## 052 前两个数之和

[解题思路与格式说明](052/README.md) · [独立源文件](052/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        String s = read().trim(); int n = s.length(); boolean found = false;
        for (int i = 1; i < n - 1 && !found; i++) {
            if (s.charAt(0) == '0' && i > 1) break;
            for (int j = i + 1; j < n && !found; j++) {
                if (s.charAt(i) == '0' && j - i > 1) break;
                BigInteger a = new BigInteger(s.substring(0, i)), b = new BigInteger(s.substring(i, j));
                int position = j, count = 2;
                while (position < n) {
                    BigInteger c = a.add(b); String next = c.toString();
                    if (!s.startsWith(next, position)) break;
                    position += next.length(); count++; a = b; b = c;
                }
                found = position == n && count >= 3;
            }
        }
        System.out.print(found);
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

<a id="submit-053"></a>

## 053 下一个漂亮数

[解题思路与格式说明](053/README.md) · [独立源文件](053/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static long n, best = Long.MAX_VALUE; static int[] counts = new int[10];
    static void generate(long prefix, int remaining) {
        if (remaining == 0) { if (prefix > n) best = Math.min(best, prefix); return; }
        for (int d = 1; d <= 9; d++) if (counts[d] > 0) { counts[d]--; generate(prefix * 10 + d, remaining - 1); counts[d]++; }
    }
    public static void main(String[] args) throws Exception {
        n = numbers(read())[0]; int minLength = Long.toString(n).length();
        for (int mask = 1; mask < (1 << 9); mask++) {
            Arrays.fill(counts, 0); int length = 0;
            for (int d = 1; d <= 9; d++) if ((mask & (1 << (d - 1))) != 0) { counts[d] = d; length += d; }
            if (length >= minLength && length <= 9) generate(0, length);
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

<a id="submit-054"></a>

## 054 解平铺数

[解题思路与格式说明](054/README.md) · [独立源文件](054/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        int n = (int) numbers(read())[0]; if (n <= 2) { System.out.print(n == 2 ? 2 : 1); return; }
        long a = 1, b = 1, c = 2;
        for (int i = 3; i <= n; i++) { long next = (2 * c + a) % 1000000007L; a = b; b = c; c = next; }
        System.out.print(c);
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

<a id="submit-055"></a>

## 055 玩游戏的一天

[解题思路与格式说明](055/README.md) · [独立源文件](055/Main.java)

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
        visited[r][c] = true; boolean found = false;
        for (int dr = -1; dr <= 1 && !found; dr++) for (int dc = -1; dc <= 1 && !found; dc++)
            if (dr != 0 || dc != 0) found = dfs(r + dr, c + dc, index + 1);
        visited[r][c] = false; return found;
    }
    public static void main(String[] args) throws Exception {
        String[] t = read().trim().split("\\s+"); int m = Integer.parseInt(t[0]), n = Integer.parseInt(t[1]);
        board = new char[m][n]; visited = new boolean[m][n]; int position = 2;
        for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) board[i][j] = t[position++].charAt(0);
        int k = Integer.parseInt(t[position++]); TreeSet<String> result = new TreeSet<>();
        for (int index = 0; index < k; index++) {
            word = t[position++]; if (word.length() > m * n) continue; boolean found = false;
            for (int i = 0; i < m && !found; i++) for (int j = 0; j < n && !found; j++) found = dfs(i, j, 0);
            if (found) result.add(word);
        }
        System.out.print(String.join("\n", result));
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

<a id="submit-056"></a>

## 056 寻找“理想数对”

[解题思路与格式说明](056/README.md) · [独立源文件](056/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static int[] a, temp;
    static long count(int left, int right) {
        if (right - left <= 1) return 0; int mid = (left + right) / 2; long answer = count(left, mid) + count(mid, right);
        int j = mid;
        for (int i = left; i < mid; i++) { while (j < right && (long) a[i] > 2L * a[j]) j++; answer += j - mid; }
        int i = left, k = left; j = mid;
        while (i < mid || j < right) temp[k++] = j == right || (i < mid && a[i] <= a[j]) ? a[i++] : a[j++];
        System.arraycopy(temp, left, a, left, right - left); return answer;
    }
    public static void main(String[] args) throws Exception { a = integers(read()); temp = new int[a.length]; System.out.print(count(0, a.length)); }

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

<a id="submit-057"></a>

## 057 楼梯有多少种爬法

[解题思路与格式说明](057/README.md) · [独立源文件](057/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        int n = (int) numbers(read())[0]; long a = 1, b = 1;
        for (int i = 2; i <= n; i++) { long c = a + b; a = b; b = c; }
        System.out.print(b);
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

<a id="submit-058"></a>

## 058 文物修复

[解题思路与格式说明](058/README.md) · [独立源文件](058/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static int n, limit; static HashMap<Long, Integer> seen;
    static int breaks(int[] a) { int result = a[0] == 1 ? 0 : 1; for (int i = 1; i < n; i++) if (a[i] != a[i - 1] + 1) result++; if (a[n - 1] != n) result++; return result; }
    static long key(int[] a) { long result = 0; for (int x : a) result = (result << 4) | x; return result; }
    static boolean search(int[] a, int depth) {
        int bad = breaks(a); if (bad == 0) return true;
        if (depth + (bad + 2) / 3 > limit) return false;
        long state = key(a); Integer old = seen.get(state); if (old != null && old <= depth) return false; seen.put(state, depth);
        for (int left = 0; left < n - 1; left++) for (int mid = left + 1; mid < n; mid++) for (int right = mid + 1; right <= n; right++) {
            int[] next = a.clone(); int p = left;
            for (int i = mid; i < right; i++) next[p++] = a[i];
            for (int i = left; i < mid; i++) next[p++] = a[i];
            if (search(next, depth + 1)) return true;
        }
        return false;
    }
    public static void main(String[] args) throws Exception {
        int[] input = integers(read()); int tests = input[0], position = 1; StringBuilder out = new StringBuilder();
        for (int test = 0; test < tests; test++) {
            n = input[position++]; int[] a = Arrays.copyOfRange(input, position, position + n); position += n; int answer = 5;
            for (limit = 0; limit < 5; limit++) { seen = new HashMap<>(); if (search(a, 0)) { answer = limit; break; } }
            if (test > 0) out.append('\n'); out.append(answer == 5 ? "5 or more" : Integer.toString(answer));
        }
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

<a id="submit-059"></a>

## 059 治理洪水

[解题思路与格式说明](059/README.md) · [独立源文件](059/Main.java)

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

<a id="submit-060"></a>

## 060 找数游戏

[解题思路与格式说明](060/README.md) · [独立源文件](060/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        long impossible = Long.MIN_VALUE / 4; long[] dp = {0, impossible, impossible};
        for (long value : numbers(read())) {
            long[] next = dp.clone();
            for (int r = 0; r < 3; r++) if (dp[r] != impossible) { int rem = (int) ((r + value) % 3); next[rem] = Math.max(next[rem], dp[r] + value); }
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
