import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

/**
 * 011—020题 Java 代码汇总；每题代码位于对应的静态内部类。
 * 编译：javac -encoding UTF-8 Solutions011_020.java
 * 运行示例：java Solutions011_020 011
 * 命令行参数选择题号，题目数据仍从标准输入读取。
 * 向OJ提交单题时，使用题号目录中的Main.java。
 * 由tools/bundle_java.py生成，算法逻辑与独立文件一致。
 */
public class Solutions011_020 {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("用法：java Solutions011_020 <题号011—020>");
            return;
        }
        switch (Integer.parseInt(args[0])) {
            case 11: Problem011.main(new String[0]); break;
            case 12: Problem012.main(new String[0]); break;
            case 13: Problem013.main(new String[0]); break;
            case 14: Problem014.main(new String[0]); break;
            case 15: Problem015.main(new String[0]); break;
            case 16: Problem016.main(new String[0]); break;
            case 17: Problem017.main(new String[0]); break;
            case 18: Problem018.main(new String[0]); break;
            case 19: Problem019.main(new String[0]); break;
            case 20: Problem020.main(new String[0]); break;
            default: throw new IllegalArgumentException("本文件支持的题号为011—020");
        }
    }

    /** 011 拆分正偶数；原独立文件：011/Main.java */
    static class Problem011 {
        public static void main(String[] args) throws Exception {
            long left = numbers(read())[0]; ArrayList<Long> result = new ArrayList<>();
            if ((left & 1) == 0) {
                for (long x = 2; x <= left; x += 2) { result.add(x); left -= x; }
                if (left > 0) { int last = result.size() - 1; result.set(last, result.get(last) + left); }
            }
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

    /** 012 最小全覆盖子串；原独立文件：012/Main.java */
    static class Problem012 {
        public static void main(String[] args) throws Exception {
            BufferedReader reader = new BufferedReader(new StringReader(read()));
            String a = reader.readLine(), b = reader.readLine(); if (b == null) b = "";
            if (b.isEmpty()) return;
            int[] need = new int[65536]; for (char c : b.toCharArray()) need[c]++;
            int missing = b.length(), left = 0, bestStart = 0, bestLength = Integer.MAX_VALUE;
            for (int right = 0; right < a.length(); right++) {
                if (need[a.charAt(right)]-- > 0) missing--;
                while (missing == 0) {
                    if (right - left + 1 < bestLength) { bestLength = right - left + 1; bestStart = left; }
                    if (++need[a.charAt(left++)] > 0) missing++;
                }
            }
            if (bestLength != Integer.MAX_VALUE) System.out.print(a.substring(bestStart, bestStart + bestLength));
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

    /** 013 这货不是括号匹配；原独立文件：013/Main.java */
    static class Problem013 {
        static String expression; static int position;
        static int parse() {
            int sum = 0, best = 0;
            while (position < expression.length() && expression.charAt(position) != ')') {
                char c = expression.charAt(position++);
                if (c == 'a') sum++;
                else if (c == '(') { sum += parse(); position++; }
                else if (c == '|') { best = Math.max(best, sum); sum = 0; }
            }
            return Math.max(best, sum);
        }
        public static void main(String[] args) throws Exception { expression = read().trim(); System.out.print(parse()); }

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

    /** 014 异或；原独立文件：014/Main.java */
    static class Problem014 {
        public static void main(String[] args) throws Exception {
            long n = numbers(read())[0], answer;
            switch ((int) (n % 4)) { case 0: answer = n; break; case 1: answer = 1; break;
                case 2: answer = n + 1; break; default: answer = 0; }
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

    /** 015 中庸小源；原独立文件：015/Main.java */
    static class Problem015 {
        static int n, m, count, best; static int[] height; static int[][][] edges;
        static int upper(int start, int direction, long used) {
            boolean[][] seen = new boolean[count][2]; boolean[] reached = new boolean[count];
            int[] queue = new int[count * 2]; int head = 0, tail = 0;
            queue[tail++] = start * 2 + direction; seen[start][direction] = true;
            while (head < tail) {
                int state = queue[head++], v = state / 2, d = state % 2;
                for (int w : edges[v][d]) if ((used & (1L << w)) == 0 && !seen[w][1 - d]) {
                    seen[w][1 - d] = true; reached[w] = true; queue[tail++] = w * 2 + 1 - d;
                }
            }
            int result = 0; for (boolean b : reached) if (b) result++; return result;
        }
        static void dfs(int v, int direction, long used, int moves) {
            best = Math.max(best, moves);
            if (best == count - 1 || moves + upper(v, direction, used) <= best) return;
            for (int w : edges[v][direction]) if ((used & (1L << w)) == 0)
                dfs(w, 1 - direction, used | (1L << w), moves + 1);
        }
        public static void main(String[] args) throws Exception {
            int[] input = integers(read()); n = input[0]; m = input[1]; count = n * m;
            height = Arrays.copyOfRange(input, 2, input.length); edges = new int[count][2][];
            int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
            for (int v = 0; v < count; v++) for (int d = 0; d < 2; d++) {
                ArrayList<Integer> next = new ArrayList<>();
                for (int k = 0; k < 4; k++) {
                    int r = v / m + dr[k], c = v % m + dc[k], w = r * m + c;
                    if (r >= 0 && r < n && c >= 0 && c < m && (d == 0 ? height[w] > height[v] : height[w] < height[v])) next.add(w);
                }
                edges[v][d] = next.stream().mapToInt(Integer::intValue).toArray();
            }
            for (int v = 0; v < count && best < count - 1; v++) for (int d = 0; d < 2; d++) dfs(v, d, 1L << v, 0);
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

    /** 016 科学分汤；原独立文件：016/Main.java */
    static class Problem016 {
        static double value(double[][] dp, int a, int b) {
            if (a <= 0 && b <= 0) return 0.5;
            if (a <= 0) return 1;
            if (b <= 0) return 0;
            return dp[a][b];
        }
        public static void main(String[] args) throws Exception {
            long n = numbers(read())[0];
            if (n >= 25000) { System.out.print("1.00000"); return; }
            int units = (int) ((n + 24) / 25); double[][] dp = new double[units + 1][units + 1];
            for (int a = 1; a <= units; a++) for (int b = 1; b <= units; b++)
                dp[a][b] = (value(dp, a - 4, b) + value(dp, a - 3, b - 1)
                    + value(dp, a - 2, b - 2) + value(dp, a - 1, b - 3)) / 4;
            System.out.printf(Locale.ROOT, "%.5f", value(dp, units, units));
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

    /** 017 反转链表；原独立文件：017/Main.java */
    static class Problem017 {
        static class Node { int value; Node next; Node(int value) { this.value = value; } }
        public static void main(String[] args) throws Exception {
            int[] values = integers(read()); Node head = null, tail = null;
            for (int value : values) { Node node = new Node(value); if (head == null) head = node; else tail.next = node; tail = node; }
            Node prev = null;
            while (head != null) { Node next = head.next; head.next = prev; prev = head; head = next; }
            StringBuilder out = new StringBuilder();
            for (Node p = prev; p != null; p = p.next) { if (out.length() > 0) out.append(' '); out.append(p.value); }
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

    /** 018 关于多项式；原独立文件：018/Main.java */
    static class Problem018 {
        static double p, q, r, s;
        static double f(double x) { return ((p * x + q) * x + r) * x + s; }
        static double root(double left, double right) {
            if (f(left) == 0) return left;
            if (f(right) == 0) return right;
            for (int i = 0; i < 100; i++) {
                double mid = (left + right) / 2;
                if ((f(left) < 0) == (f(mid) < 0)) left = mid; else right = mid;
            }
            double result = (left + right) / 2;
            return Math.abs(result) < 0.0000001 ? 0 : result;
        }
        public static void main(String[] args) throws Exception {
            String[] input = read().trim().split("\\s+");
            p = Double.parseDouble(input[0]); q = Double.parseDouble(input[1]); r = Double.parseDouble(input[2]); s = Double.parseDouble(input[3]);
            double d = Math.sqrt(q * q - 3 * p * r);
            double a = (-q - d) / (3 * p), b = (-q + d) / (3 * p);
            double first = Math.min(a, b), second = Math.max(a, b);
            System.out.printf(Locale.ROOT, "%.2f %.2f %.2f", root(-100, first), root(first, second), root(second, 100));
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

    /** 019 最大利益日；原独立文件：019/Main.java */
    static class Problem019 {
        public static void main(String[] args) throws Exception {
            long[] input = numbers(read()); int n = (int) input[0];
            long ending = input[1], best = ending;
            for (int i = 2; i <= n; i++) { ending = Math.max(input[i], ending + input[i]); best = Math.max(best, ending); }
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

    /** 020 最大购书开销；原独立文件：020/Main.java */
    static class Problem020 {
        public static void main(String[] args) throws Exception {
            long[] prices = numbers(read()); Arrays.sort(prices); long answer = 0;
            for (int i = 0; i < prices.length; i++) answer += prices[i] * (i + 1L);
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
