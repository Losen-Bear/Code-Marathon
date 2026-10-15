# 61-70 Java 完整程序与题解索引

**提交某一题时，复制下面该题 Java 代码块的全部内容。**每段都是独立的 `public class Main` 程序，已包含输入、输出及所有辅助方法。每次只提交一道题的一个代码块。

| 题号 | 题目 | 解题说明 | 完整源文件 |
| --- | --- | --- | --- |
| [061](#submit-061) | 填运算符 | [题解](061/README.md) | [Main.java](061/Main.java) |
| [062](#submit-062) | 寻找反向对 | [题解](062/README.md) | [Main.java](062/Main.java) |
| [063](#submit-063) | 一碗水端平 | [题解](063/README.md) | [Main.java](063/Main.java) |
| [064](#submit-064) | 智能翻译 | [题解](064/README.md) | [Main.java](064/Main.java) |
| [065](#submit-065) | 逆序对的总数 | [题解](065/README.md) | [Main.java](065/Main.java) |
| [066](#submit-066) | 字符串变形 | [题解](066/README.md) | [Main.java](066/Main.java) |
| [067](#submit-067) | 抽奖 | [题解](067/README.md) | [Main.java](067/Main.java) |
| [068](#submit-068) | 找到高频数 | [题解](068/README.md) | [Main.java](068/Main.java) |
| [069](#submit-069) | 无聊的小明 | [题解](069/README.md) | [Main.java](069/Main.java) |
| [070](#submit-070) | 多多参加采访 | [题解](070/README.md) | [Main.java](070/Main.java) |

本地批量运行可用 [Solutions061_070.java](Solutions061_070.java)；它需要命令行题号参数，向 OJ 提交时请使用下面的单题完整程序。

<a id="submit-061"></a>

## 061 填运算符

[解题思路与格式说明](061/README.md) · [独立源文件](061/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static class Fraction {
        BigInteger numerator, denominator;
        Fraction(BigInteger n, BigInteger d) {
            if (d.signum() < 0) { n = n.negate(); d = d.negate(); }
            BigInteger gcd = n.gcd(d); numerator = n.divide(gcd); denominator = d.divide(gcd);
        }
        Fraction add(Fraction b) { return new Fraction(numerator.multiply(b.denominator).add(b.numerator.multiply(denominator)), denominator.multiply(b.denominator)); }
        Fraction multiply(Fraction b) { return new Fraction(numerator.multiply(b.numerator), denominator.multiply(b.denominator)); }
        Fraction divide(Fraction b) { return new Fraction(numerator.multiply(b.denominator), denominator.multiply(b.numerator)); }
    }
    static String[] values; static BigInteger target; static char[] operators = new char[4];
    static ArrayList<String> result = new ArrayList<>();
    static void enumerate(int position) {
        if (position < 4) { for (char c : new char[]{'+','-','*','/'}) { operators[position] = c; enumerate(position + 1); } return; }
        Fraction sum = new Fraction(BigInteger.ZERO, BigInteger.ONE);
        Fraction term = new Fraction(new BigInteger(values[0]), BigInteger.ONE);
        for (int i = 0; i < 4; i++) {
            Fraction next = new Fraction(new BigInteger(values[i + 1]), BigInteger.ONE);
            switch (operators[i]) {
                case '+': sum = sum.add(term); term = next; break;
                case '-': sum = sum.add(term); term = new Fraction(next.numerator.negate(), next.denominator); break;
                case '*': term = term.multiply(next); break;
                default: if (next.numerator.signum() == 0) return; term = term.divide(next);
            }
        }
        sum = sum.add(term);
        if (sum.numerator.equals(target.multiply(sum.denominator))) {
            StringBuilder out = new StringBuilder(values[0]);
            for (int i = 0; i < 4; i++) out.append(operators[i]).append(values[i + 1]);
            result.add(out.append('=').append(target).toString());
        }
    }
    public static void main(String[] args) throws Exception {
        values = read().trim().split("\\s+"); target = new BigInteger(values[5]); enumerate(0);
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

<a id="submit-062"></a>

## 062 寻找反向对

[解题思路与格式说明](062/README.md) · [独立源文件](062/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        int[] input = integers(read()); int n = input[0], k = input[1]; long mod = 1000000007L;
        if (k > (long) n * (n - 1) / 2) { System.out.print(0); return; }
        long[] dp = new long[k + 1]; dp[0] = 1;
        for (int size = 1; size <= n; size++) {
            long[] next = new long[k + 1]; long window = 0;
            for (int j = 0; j <= k; j++) {
                window = (window + dp[j]) % mod;
                if (j >= size) window = (window - dp[j - size] + mod) % mod;
                next[j] = window;
            }
            dp = next;
        }
        System.out.print(dp[k]);
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

<a id="submit-063"></a>

## 063 一碗水端平

[解题思路与格式说明](063/README.md) · [独立源文件](063/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static long[] fruits, loads; static long best;
    static void search(int index, long maximum) {
        if (maximum >= best) return;
        if (index < 0) { best = maximum; return; }
        HashSet<Long> tried = new HashSet<>();
        for (int child = 0; child < loads.length; child++) {
            if (!tried.add(loads[child])) continue;
            loads[child] += fruits[index]; search(index - 1, Math.max(maximum, loads[child])); loads[child] -= fruits[index];
        }
    }
    public static void main(String[] args) throws Exception {
        String[] lines = read().trim().split("\\R"); fruits = numbers(lines[0]); loads = new long[(int) numbers(lines[1])[0]];
        Arrays.sort(fruits); best = 0; for (long x : fruits) best += x;
        search(fruits.length - 1, 0); System.out.print(best);
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

<a id="submit-064"></a>

## 064 智能翻译

[解题思路与格式说明](064/README.md) · [独立源文件](064/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static final String[] SMALL = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    static final String[] TENS = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    static String part(int n) {
        ArrayList<String> words = new ArrayList<>();
        if (n >= 100) { words.add(SMALL[n / 100]); words.add("Hundred"); n %= 100; }
        if (n >= 20) { words.add(TENS[n / 10]); n %= 10; }
        if (n > 0) words.add(SMALL[n]); return String.join(" ", words);
    }
    public static void main(String[] args) throws Exception {
        long n = numbers(read())[0]; if (n == 0) { System.out.print("Zero"); return; }
        int[] units = {1000000000, 1000000, 1000, 1}; String[] names = {"Billion", "Million", "Thousand", ""};
        ArrayList<String> words = new ArrayList<>();
        for (int i = 0; i < units.length; i++) if (n >= units[i]) {
            words.add(part((int) (n / units[i]))); if (!names[i].isEmpty()) words.add(names[i]); n %= units[i];
        }
        System.out.print(String.join(" ", words));
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

<a id="submit-065"></a>

## 065 逆序对的总数

[解题思路与格式说明](065/README.md) · [独立源文件](065/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static long[] a, temp;
    static long count(int left, int right) {
        if (right - left <= 1) return 0; int mid = (left + right) / 2;
        long answer = count(left, mid) + count(mid, right); int i = left, j = mid, k = left;
        while (i < mid && j < right) {
            if (a[i] <= a[j]) temp[k++] = a[i++];
            else { answer += mid - i; temp[k++] = a[j++]; }
        }
        while (i < mid) temp[k++] = a[i++]; while (j < right) temp[k++] = a[j++];
        System.arraycopy(temp, left, a, left, right - left); return answer;
    }
    public static void main(String[] args) throws Exception { a = numbers(read()); temp = new long[a.length]; System.out.print(count(0, a.length)); }

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

<a id="submit-066"></a>

## 066 字符串变形

[解题思路与格式说明](066/README.md) · [独立源文件](066/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static boolean next(char[] a) {
        int i = a.length - 2; while (i >= 0 && a[i] >= a[i + 1]) i--; if (i < 0) return false;
        int j = a.length - 1; while (a[j] <= a[i]) j--; char t = a[i]; a[i] = a[j]; a[j] = t;
        for (int l = i + 1, r = a.length - 1; l < r; l++, r--) { t = a[l]; a[l] = a[r]; a[r] = t; }
        return true;
    }
    public static void main(String[] args) throws Exception {
        char[] a = read().trim().toCharArray(); Arrays.sort(a); BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out, java.nio.charset.StandardCharsets.UTF_8));
        boolean first = true;
        do { if (!first) out.newLine(); first = false; out.write(a); } while (next(a)); out.flush();
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

<a id="submit-067"></a>

## 067 抽奖

[解题思路与格式说明](067/README.md) · [独立源文件](067/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        long[] input = numbers(read()); int n = (int) input[0]; HashSet<Long> values = new HashSet<>();
        for (int i = 1; i <= n; i++) values.add(input[i]); long answer = 0;
        for (int i = 1; i <= n; i++) for (int j = i + 1; j <= n; j++) if (values.contains(input[i] + input[j])) answer++;
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

<a id="submit-068"></a>

## 068 找到高频数

[解题思路与格式说明](068/README.md) · [独立源文件](068/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        String[] lines = read().trim().split("\\R"); long[] a = numbers(lines[0]); int k = (int) numbers(lines[1])[0];
        HashMap<Long, Integer> frequency = new HashMap<>(); for (long x : a) frequency.put(x, frequency.getOrDefault(x, 0) + 1);
        ArrayList<Long> values = new ArrayList<>(frequency.keySet());
        values.sort((x,y) -> { int c = Integer.compare(frequency.get(y), frequency.get(x)); return c != 0 ? c : Long.compare(x,y); });
        ArrayList<Long> chosen = new ArrayList<>(values.subList(0,k)); Collections.sort(chosen); StringBuilder out = new StringBuilder();
        for (long x : chosen) { if (out.length() > 0) out.append(','); out.append(x); } System.out.print(out);
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

<a id="submit-069"></a>

## 069 无聊的小明

[解题思路与格式说明](069/README.md) · [独立源文件](069/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        long n = numbers(read())[0]; HashSet<Long> seen = new HashSet<>();
        while (n != 1 && seen.add(n)) { long next = 0; while (n > 0) { long d = n % 10; next += d * d; n /= 10; } n = next; }
        System.out.print(n == 1 ? "yes" : "no");
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

<a id="submit-070"></a>

## 070 多多参加采访

[解题思路与格式说明](070/README.md) · [独立源文件](070/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        long[] input = numbers(read()); int n = (int) input[0]; long[][] a = new long[n][2];
        for (int i = 0; i < n; i++) { a[i][0] = input[2*i+1]; a[i][1] = input[2*i+2]; }
        Arrays.sort(a, Comparator.comparingLong(x -> x[1])); long end = Long.MIN_VALUE; int accepted = 0;
        for (long[] interval : a) if (interval[0] > end) { accepted++; end = interval[1]; }
        System.out.print(n - accepted);
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
