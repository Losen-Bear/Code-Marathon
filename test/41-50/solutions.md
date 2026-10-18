# 041—050 完整 Java 程序汇总

本页已展开本组十道题的完整程序，无需逐个打开题目。点击下表题号可跳到对应代码，再使用代码块右上角的复制按钮复制整份程序。

**提交某一题时，复制下面该题 Java 代码块的全部内容。**每段都是独立的 `public class Main` 程序，已包含输入、输出及所有辅助方法。每次只提交一道题的一个代码块。

| 题号 | 题目 | 解题说明 | 完整源文件 |
| --- | --- | --- | --- |
| [041](#submit-041) | 最好逛吃路线 | [题解](041/README.md) | [Main.java](041/Main.java) |
| [042](#submit-042) | 中缀表达式转后缀表达式 | [题解](042/README.md) | [Main.java](042/Main.java) |
| [043](#submit-043) | a=b | [题解](043/README.md) | [Main.java](043/Main.java) |
| [044](#submit-044) | 串烧烤 | [题解](044/README.md) | [Main.java](044/Main.java) |
| [045](#submit-045) | 拼多多 | [题解](045/README.md) | [Main.java](045/Main.java) |
| [046](#submit-046) | "有效子数组"个数 | [题解](046/README.md) | [Main.java](046/Main.java) |
| [047](#submit-047) | 数字探险 | [题解](047/README.md) | [Main.java](047/Main.java) |
| [048](#submit-048) | 拼拼图 | [题解](048/README.md) | [Main.java](048/Main.java) |
| [049](#submit-049) | 工具怎么下 | [题解](049/README.md) | [Main.java](049/Main.java) |
| [050](#submit-050) | 1应俱全 | [题解](050/README.md) | [Main.java](050/Main.java) |

本地批量运行可用 [Solutions041_050.java](Solutions041_050.java)；它需要命令行题号参数，向 OJ 提交时请使用下面的单题完整程序。

<a id="submit-041"></a>

## 041 最好逛吃路线

[解题思路与格式说明](041/README.md) · [独立源文件](041/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        long[] a = numbers(read()); long bestLeft = a[0], answer = Long.MIN_VALUE;
        for (int j = 1; j < a.length; j++) { answer = Math.max(answer, bestLeft + a[j] - j); bestLeft = Math.max(bestLeft, a[j] + j); }
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

<a id="submit-042"></a>

## 042 中缀表达式转后缀表达式

[解题思路与格式说明](042/README.md) · [独立源文件](042/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static int priority(String s) { return s.equals("+") || s.equals("-") ? 1 : 2; }
    public static void main(String[] args) throws Exception {
        String[] tokens = read().trim().split("\\s+"); ArrayDeque<String> operators = new ArrayDeque<>(); StringBuilder out = new StringBuilder();
        for (String token : tokens) {
            if (token.matches("[+-]?\\d+")) out.append(token).append(' ');
            else if (token.equals("(")) operators.push(token);
            else if (token.equals(")")) { while (!operators.peek().equals("(")) out.append(operators.pop()).append(' '); operators.pop(); }
            else {
                while (!operators.isEmpty() && !operators.peek().equals("(") && priority(operators.peek()) >= priority(token)) out.append(operators.pop()).append(' ');
                operators.push(token);
            }
        }
        while (!operators.isEmpty()) out.append(operators.pop()).append(' ');
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

<a id="submit-043"></a>

## 043 a=b

[解题思路与格式说明](043/README.md) · [独立源文件](043/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static int target; static HashMap<Integer, Integer> memo = new HashMap<>();
    static int solve(int x) {
        if (x <= target) return target - x;
        if (memo.containsKey(x)) return memo.get(x);
        int best = x - target;
        for (int d : new int[]{5, 11}) {
            int r = x % d;
            best = Math.min(best, r + 1 + solve(x / d));
            best = Math.min(best, d - r + 1 + solve(x / d + 1));
        }
        memo.put(x, best); return best;
    }
    public static void main(String[] args) throws Exception { int[] a = integers(read()); target = a[1]; System.out.print(solve(a[0])); }

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

<a id="submit-044"></a>

## 044 串烧烤

[解题思路与格式说明](044/README.md) · [独立源文件](044/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        int odd = 0, even = 0; for (long x : numbers(read())) if ((x & 1) == 0) even++; else odd++;
        System.out.print(Math.min(odd, even));
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

<a id="submit-045"></a>

## 045 拼多多

[解题思路与格式说明](045/README.md) · [独立源文件](045/Main.java)

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

<a id="submit-046"></a>

## 046 "有效子数组"个数

[解题思路与格式说明](046/README.md) · [独立源文件](046/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static int[] tree;
    static void add(int index) { for (; index < tree.length; index += index & -index) tree[index]++; }
    static long query(int index) { long answer = 0; for (; index > 0; index -= index & -index) answer += tree[index]; return answer; }
    public static void main(String[] args) throws Exception {
        String[] tokens = read().trim().split("\\s+"); ArrayList<Integer> a = new ArrayList<>();
        for (String token : tokens) { if (!token.equals("0") && !token.equals("1")) break; a.add(Integer.parseInt(token)); }
        int n = a.size(), offset = n + 1, prefix = 0; tree = new int[2 * n + 3]; add(offset);
        long answer = 0;
        for (int x : a) { prefix += x == 1 ? 1 : -1; answer = (answer + query(prefix + offset - 1)) % 1000000007L; add(prefix + offset); }
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

<a id="submit-047"></a>

## 047 数字探险

[解题思路与格式说明](047/README.md) · [独立源文件](047/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static ArrayList<Integer> path = new ArrayList<>(); static ArrayList<String> result = new ArrayList<>();
    static void search(int start, int k, int sum) {
        if (k == 0) { if (sum == 0) { int[] a = path.stream().mapToInt(Integer::intValue).toArray(); result.add(join(a)); } return; }
        if (10 - start < k || sum < 0) return;
        for (int x = start; x <= 9 && x <= sum; x++) { path.add(x); search(x + 1, k - 1, sum - x); path.remove(path.size() - 1); }
    }
    public static void main(String[] args) throws Exception { int[] a = integers(read()); search(1, a[0], a[1]); System.out.print(String.join("\n", result)); }

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

<a id="submit-048"></a>

## 048 拼拼图

[解题思路与格式说明](048/README.md) · [独立源文件](048/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static int width, height, best; static int[] skyline; static HashMap<Long, Integer> memo = new HashMap<>();
    static void search(int used) {
        if (used >= best) return;
        int left = 0; for (int i = 1; i < width; i++) if (skyline[i] < skyline[left]) left = i;
        int level = skyline[left]; if (level == height) { best = used; return; }
        long key = 0; for (int h : skyline) key = key * 14 + h;
        if (memo.containsKey(key) && memo.get(key) <= used) return; memo.put(key, used);
        int side = 1; while (left + side < width && skyline[left + side] == level && side < height - level) side++;
        for (int size = side; size >= 1; size--) {
            for (int i = left; i < left + size; i++) skyline[i] += size;
            search(used + 1);
            for (int i = left; i < left + size; i++) skyline[i] -= size;
        }
    }
    public static void main(String[] args) throws Exception {
        int[] a = integers(read()); width = Math.min(a[0], a[1]); height = Math.max(a[0], a[1]);
        skyline = new int[width]; best = width * height; search(0); System.out.print(best);
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

<a id="submit-049"></a>

## 049 工具怎么下

[解题思路与格式说明](049/README.md) · [独立源文件](049/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        long n = numbers(read())[0], answer = n, speed = 1;
        for (int t = 0;; t++, speed *= 2) { answer = Math.min(answer, t + (n + speed - 1) / speed); if (speed >= n) break; }
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

<a id="submit-050"></a>

## 050 1应俱全

[解题思路与格式说明](050/README.md) · [独立源文件](050/Main.java)

```java
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        long n = numbers(read())[0], answer = 0;
        for (long factor = 1; factor <= n; factor *= 10) {
            long high = n / factor / 10, current = n / factor % 10, low = n % factor;
            answer += high * factor;
            if (current == 1) answer += low + 1; else if (current > 1) answer += factor;
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
