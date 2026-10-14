import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

/**
 * 031—040题 Java 代码汇总；每题代码位于对应的静态内部类。
 * 编译：javac -encoding UTF-8 Solutions031_040.java
 * 运行示例：java Solutions031_040 031
 * 命令行参数选择题号，题目数据仍从标准输入读取。
 * 向OJ提交单题时，使用题号目录中的Main.java。
 * 由tools/bundle_java.py生成，算法逻辑与独立文件一致。
 */
public class Solutions031_040 {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("用法：java Solutions031_040 <题号031—040>");
            return;
        }
        switch (Integer.parseInt(args[0])) {
            case 31: Problem031.main(new String[0]); break;
            case 32: Problem032.main(new String[0]); break;
            case 33: Problem033.main(new String[0]); break;
            case 34: Problem034.main(new String[0]); break;
            case 35: Problem035.main(new String[0]); break;
            case 36: Problem036.main(new String[0]); break;
            case 37: Problem037.main(new String[0]); break;
            case 38: Problem038.main(new String[0]); break;
            case 39: Problem039.main(new String[0]); break;
            case 40: Problem040.main(new String[0]); break;
            default: throw new IllegalArgumentException("本文件支持的题号为031—040");
        }
    }

    /** 031 吃苹果；原独立文件：031/Main.java */
    static class Problem031 {
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

    /** 032 字符串关系；原独立文件：032/Main.java */
    static class Problem032 {
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

    /** 033 谁是多数元素；原独立文件：033/Main.java */
    static class Problem033 {
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

    /** 034 找特定区间；原独立文件：034/Main.java */
    static class Problem034 {
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

    /** 035 购买两本书；原独立文件：035/Main.java */
    static class Problem035 {
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

    /** 036 寻找字符串；原独立文件：036/Main.java */
    static class Problem036 {
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

    /** 037 超大数运算；原独立文件：037/Main.java */
    static class Problem037 {
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

    /** 038 寻找方案；原独立文件：038/Main.java */
    static class Problem038 {
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

    /** 039 特殊的回文；原独立文件：039/Main.java */
    static class Problem039 {
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

    /** 040 走出网格；原独立文件：040/Main.java */
    static class Problem040 {
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
}
