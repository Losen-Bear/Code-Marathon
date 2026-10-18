# 001—010 完整 Java 程序汇总

本页已展开本组十道题的完整程序，无需逐个打开题目。点击下表题号可跳到对应代码，再使用代码块右上角的复制按钮复制整份程序。

**提交某一题时，复制下面该题 Java 代码块的全部内容。**每段都是独立的 `public class Main` 程序，已包含输入、输出及所有辅助方法。每次只提交一道题的一个代码块。

| 题号 | 题目 | 解题说明 | 完整源文件 |
| --- | --- | --- | --- |
| [001](#submit-001) | 光轨城市：信号优化计划 | [题解](001/README.md) | [Main.java](001/Main.java) |
| [002](#submit-002) | 极限削木游戏 | [题解](002/README.md) | [Main.java](002/Main.java) |
| [003](#submit-003) | 解压缩编码问题 | [题解](003/README.md) | [Main.java](003/Main.java) |
| [004](#submit-004) | PUA/小源 | [题解](004/README.md) | [Main.java](004/Main.java) |
| [005](#submit-005) | 泡泡堂 | [题解](005/README.md) | [Main.java](005/Main.java) |
| [006](#submit-006) | 猫和鱼 | [题解](006/README.md) | [Main.java](006/Main.java) |
| [007](#submit-007) | 能量核心 | [题解](007/README.md) | [Main.java](007/Main.java) |
| [008](#submit-008) | 反转加密 | [题解](008/README.md) | [Main.java](008/Main.java) |
| [009](#submit-009) | 进攻火星 | [题解](009/README.md) | [Main.java](009/Main.java) |
| [010](#submit-010) | 原神启动 | [题解](010/README.md) | [Main.java](010/Main.java) |

本地批量运行可用 [Solutions001_010.java](Solutions001_010.java)；它需要命令行题号参数，向 OJ 提交时请使用下面的单题完整程序。

<a id="submit-001"></a>

## 001 光轨城市：信号优化计划

[解题思路与格式说明](001/README.md) · [独立源文件](001/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static boolean possible(String s, int limit, long operations) {
        long needed = 0;
        if (limit == 1) {
            int different = 0;
            for (int i = 0; i < s.length(); i++)
                if (s.charAt(i) - '0' != (i & 1)) different++;
            return Math.min(different, s.length() - different) <= operations;
        }
        for (int i = 0; i < s.length();) {
            int j = i + 1;
            while (j < s.length() && s.charAt(j) == s.charAt(i)) j++;
            needed += (j - i) / (limit + 1);
            if (needed > operations) return false;
            i = j;
        }
        return true;
    }
    public static void main(String[] args) throws Exception {
        String[] input = read().trim().split("\\s+");
        String s = input[0]; long operations = Long.parseLong(input[1]);
        int lo = 1, hi = s.length();
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (possible(s, mid, operations)) hi = mid; else lo = mid + 1;
        }
        System.out.print(lo);
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

<a id="submit-002"></a>

## 002 极限削木游戏

[解题思路与格式说明](002/README.md) · [独立源文件](002/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        int x = Integer.parseInt(read().trim());
        System.out.print(BigInteger.ONE.shiftLeft(Math.addExact(x, 2)).subtract(BigInteger.valueOf(2)));
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

<a id="submit-003"></a>

## 003 解压缩编码问题

[解题思路与格式说明](003/README.md) · [独立源文件](003/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static String s; static int position;
    static String decode() {
        StringBuilder out = new StringBuilder();
        while (position < s.length() && s.charAt(position) != ']') {
            if (s.charAt(position) != '[') { out.append(s.charAt(position++)); continue; }
            position++; int count = 0;
            while (Character.isDigit(s.charAt(position))) count = count * 10 + s.charAt(position++) - '0';
            String part = decode(); position++; // 跳过当前层的右括号
            for (int i = 0; i < count; i++) out.append(part);
        }
        return out.toString();
    }
    public static void main(String[] args) throws Exception { s = read().trim(); System.out.print(decode()); }

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

<a id="submit-004"></a>

## 004 PUA/小源

[解题思路与格式说明](004/README.md) · [独立源文件](004/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static int[] a; static boolean[][] can; static ArrayList<Integer> path = new ArrayList<>();
    static ArrayList<String> result = new ArrayList<>();
    static void search(int start, int remaining) {
        if (!can[start][remaining]) return;
        if (remaining == 0) {
            int[] values = new int[path.size()];
            for (int i = 0; i < values.length; i++) values[i] = path.get(i);
            result.add(join(values));
        }
        for (int i = start; i < a.length && a[i] <= remaining; i++) {
            if (i > start && a[i] == a[i - 1]) continue;
            path.add(a[i]); search(i + 1, remaining - a[i]); path.remove(path.size() - 1);
        }
    }
    public static void main(String[] args) throws Exception {
        int[] input = integers(read()); int n = input[0], target = input[n + 1];
        a = Arrays.copyOfRange(input, 1, n + 1); Arrays.sort(a);
        can = new boolean[n + 1][target + 1]; can[n][0] = true;
        for (int i = n - 1; i >= 0; i--) for (int sum = 0; sum <= target; sum++)
            can[i][sum] = can[i + 1][sum] || (sum >= a[i] && can[i + 1][sum - a[i]]);
        search(0, target); System.out.print(String.join("\n", result));
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

<a id="submit-005"></a>

## 005 泡泡堂

[解题思路与格式说明](005/README.md) · [独立源文件](005/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        String raw = read(); ArrayList<char[]> rows = new ArrayList<>();
        Matcher matcher = Pattern.compile("[\\{\\[]([^{}\\[\\]]*)[\\}\\]]").matcher(raw);
        while (matcher.find()) {
            String row = matcher.group(1).replaceAll("[^EBS]", "");
            if (!row.isEmpty()) rows.add(row.toCharArray());
        }
        if (rows.isEmpty()) throw new IllegalArgumentException("输入应为题面给出的二维字符数组");
        char[][] grid = rows.toArray(new char[0][]);
        int m = grid.length, n = grid[0].length, best = 0, rowCount = 0;
        int[] columnCount = new int[n];
        for (int i = 0; i < m; i++) {
            if (grid[i].length != n) throw new IllegalArgumentException("矩阵行长度不一致");
            for (int j = 0; j < n; j++) {
                if (j == 0 || grid[i][j - 1] == 'B') {
                    rowCount = 0;
                    for (int k = j; k < n && grid[i][k] != 'B'; k++) if (grid[i][k] == 'E') rowCount++;
                }
                if (i == 0 || grid[i - 1][j] == 'B') {
                    columnCount[j] = 0;
                    for (int k = i; k < m && grid[k][j] != 'B'; k++) if (grid[k][j] == 'E') columnCount[j]++;
                }
                if (grid[i][j] == 'S') best = Math.max(best, rowCount + columnCount[j]);
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

<a id="submit-006"></a>

## 006 猫和鱼

[解题思路与格式说明](006/README.md) · [独立源文件](006/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        String[] lines = read().trim().split("\\R");
        long[] first = numbers(lines[0]), second = numbers(lines[1]); int k = (int) numbers(lines[2])[0];
        long answer = 0; long[] delta = new long[first.length];
        for (int i = 0; i < first.length; i++) { answer += second[i]; delta[i] = first[i] - second[i]; }
        Arrays.sort(delta);
        for (int i = delta.length - k; i < delta.length; i++) answer += delta[i];
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

<a id="submit-007"></a>

## 007 能量核心

[解题思路与格式说明](007/README.md) · [独立源文件](007/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        String[] t = read().trim().split("\\s+"); int n = Integer.parseInt(t[0]), m = Integer.parseInt(t[1]);
        char[][] a = new char[n][]; for (int i = 0; i < n; i++) a[i] = t[i + 2].toCharArray();
        int answer = 0; int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) if (a[i][j] != '0') {
            answer++; a[i][j] = '0'; queue.add(i * m + j);
            while (!queue.isEmpty()) {
                int p = queue.remove(), r = p / m, c = p % m;
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d], nc = c + dc[d];
                    if (nr >= 0 && nr < n && nc >= 0 && nc < m && a[nr][nc] != '0') {
                        a[nr][nc] = '0'; queue.add(nr * m + nc);
                    }
                }
            }
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

<a id="submit-008"></a>

## 008 反转加密

[解题思路与格式说明](008/README.md) · [独立源文件](008/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static String target; static int length; static Boolean[][][] memo;
    static boolean search(int left, int lowCarry, int highCarry) {
        int right = length - 1 - left;
        if (left > right) return lowCarry == highCarry;
        if (memo[left][lowCarry][highCarry] != null) return memo[left][lowCarry][highCarry];
        int lowDigit = target.charAt(target.length() - 1 - left) - '0';
        int highDigit = target.charAt(target.length() - 1 - right) - '0';
        boolean result = false;
        if (left == right) {
            for (int d = length == 1 ? 1 : 0; d <= 9; d++)
                if (2 * d + lowCarry == lowDigit + 10 * highCarry) result = true;
        } else {
            for (int low = 0; low <= 9 && !result; low++)
                for (int high = left == 0 ? 1 : 0; high <= 9 && !result; high++) {
                    int sum = low + high;
                    if ((sum + lowCarry) % 10 != lowDigit) continue;
                    int nextLow = (sum + lowCarry) / 10;
                    int nextHigh = highDigit + 10 * highCarry - sum;
                    if (nextHigh >= 0 && nextHigh <= 1) result = search(left + 1, nextLow, nextHigh);
                }
        }
        return memo[left][lowCarry][highCarry] = result;
    }
    public static void main(String[] args) throws Exception {
        target = new BigInteger(read().trim()).toString(); boolean result = target.equals("0");
        for (length = Math.max(1, target.length() - 1); length <= target.length() && !result; length++) {
            int carry = length == target.length() ? 0 : target.charAt(0) - '0';
            if (carry > 1) continue;
            memo = new Boolean[length + 1][2][2]; result = search(0, 0, carry);
        }
        System.out.print(result ? "True" : "False");
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

<a id="submit-009"></a>

## 009 进攻火星

[解题思路与格式说明](009/README.md) · [独立源文件](009/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        long[] value = numbers(read()); int n = value.length;
        long[] take = new long[n], skip = new long[n];
        for (int i = n - 1; i >= 0; i--) {
            take[i] = value[i];
            for (int child = 2 * i + 1; child <= 2 * i + 2 && child < n; child++) {
                take[i] += skip[child]; skip[i] += Math.max(take[child], skip[child]);
            }
        }
        System.out.print(n == 0 ? 0 : Math.max(take[0], skip[0]));
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

<a id="submit-010"></a>

## 010 原神启动

[解题思路与格式说明](010/README.md) · [独立源文件](010/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        int[] input = integers(read()); int n = input[0], target = input[1], max = 0;
        long total = 0;
        for (int i = 0; i < n; i++) { max = Math.max(max, input[3 + i * 2]); total += (long) input[2 + i * 2] * input[3 + i * 2]; }
        if (total < target) { System.out.print("op"); return; }
        int limit = target + max - 1; int[] remaining = new int[limit + 1];
        Arrays.fill(remaining, -1); remaining[0] = 0;
        for (int i = 0; i < n; i++) {
            int count = input[2 + i * 2], weight = input[3 + i * 2];
            for (int sum = 0; sum <= limit; sum++) {
                if (remaining[sum] >= 0) remaining[sum] = count;
                else if (sum < weight || remaining[sum - weight] <= 0) remaining[sum] = -1;
                else remaining[sum] = remaining[sum - weight] - 1;
            }
        }
        for (int sum = target; sum <= limit; sum++) if (remaining[sum] >= 0) { System.out.print(sum - target); return; }
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
