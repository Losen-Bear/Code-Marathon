import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

/**
 * 021—030题 Java 代码汇总；每题代码位于对应的静态内部类。
 * 编译：javac -encoding UTF-8 Solutions021_030.java
 * 运行示例：java Solutions021_030 021
 * 命令行参数选择题号，题目数据仍从标准输入读取。
 * 向OJ提交单题时，使用题号目录中的Main.java。
 * 由tools/bundle_java.py生成，算法逻辑与独立文件一致。
 */
public class Solutions021_030 {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("用法：java Solutions021_030 <题号021—030>");
            return;
        }
        switch (Integer.parseInt(args[0])) {
            case 21: Problem021.main(new String[0]); break;
            case 22: Problem022.main(new String[0]); break;
            case 23: Problem023.main(new String[0]); break;
            case 24: Problem024.main(new String[0]); break;
            case 25: Problem025.main(new String[0]); break;
            case 26: Problem026.main(new String[0]); break;
            case 27: Problem027.main(new String[0]); break;
            case 28: Problem028.main(new String[0]); break;
            case 29: Problem029.main(new String[0]); break;
            case 30: Problem030.main(new String[0]); break;
            default: throw new IllegalArgumentException("本文件支持的题号为021—030");
        }
    }

    /** 021 金字塔挑战；原独立文件：021/Main.java */
    static class Problem021 {
        public static void main(String[] args) throws Exception {
            long[] values = numbers(read()); long total = 1;
            for (long x : values) total += x;
            if (total <= 0) { System.out.print(-1); return; }
            PriorityQueue<Long> negatives = new PriorityQueue<>(); long health = 1; int answer = 0;
            for (long x : values) {
                health += x; if (x < 0) negatives.add(x);
                if (health <= 0) { health -= negatives.remove(); answer++; }
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

    /** 022 魔法师分组；原独立文件：022/Main.java */
    static class Problem022 {
        static long[] sums; static long target; static int groups;
        static HashMap<Long, Boolean> memo = new HashMap<>();
        static boolean partition(int mask, int k) {
            if (Integer.bitCount(mask) < k) return false;
            if (k == 1) return sums[mask] == target;
            long key = (long) mask * (groups + 1) + k;
            if (memo.containsKey(key)) return memo.get(key);
            int fixed = mask & -mask;
            for (int subset = mask; subset > 0; subset = (subset - 1) & mask)
                if ((subset & fixed) != 0 && sums[subset] == target && partition(mask ^ subset, k - 1)) {
                    memo.put(key, true); return true;
                }
            memo.put(key, false); return false;
        }
        public static void main(String[] args) throws Exception {
            String[] lines = read().trim().split("\\R"); long[] a = numbers(lines[0]); groups = (int) numbers(lines[1])[0];
            long total = 0; for (long x : a) total += x;
            if (groups < 1 || groups > a.length || total % groups != 0) { System.out.print("false"); return; }
            target = total / groups; sums = new long[1 << a.length];
            for (int mask = 1; mask < sums.length; mask++) { int bit = mask & -mask; sums[mask] = sums[mask ^ bit] + a[Integer.numberOfTrailingZeros(bit)]; }
            System.out.print(partition(sums.length - 1, groups));
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

    /** 023 猜猜我在哪；原独立文件：023/Main.java */
    static class Problem023 {
        static List<String> forms(String s) {
            ArrayList<String> result = new ArrayList<>();
            if (s.length() == 1 || s.charAt(0) != '0') result.add(s);
            if (s.charAt(s.length() - 1) != '0')
                for (int i = 1; i < s.length(); i++) if (i == 1 || s.charAt(0) != '0') result.add(s.substring(0, i) + "." + s.substring(i));
            return result;
        }
        public static void main(String[] args) throws Exception {
            String s = read().replaceAll("[^0-9]", ""); TreeSet<String> result = new TreeSet<>();
            for (int i = 1; i < s.length(); i++) for (String x : forms(s.substring(0, i)))
                for (String y : forms(s.substring(i))) result.add(x + ", " + y);
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

    /** 024 自行车调度工；原独立文件：024/Main.java */
    static class Problem024 {
        public static void main(String[] args) throws Exception {
            String[] lines = read().trim().split("\\R"); int[] workers = integers(lines[0]), bikes = integers(lines[1]);
            int m = workers.length / 2, n = bikes.length / 2; long[] dp = new long[1 << n];
            Arrays.fill(dp, Long.MAX_VALUE / 4); dp[0] = 0; long answer = Long.MAX_VALUE;
            for (int mask = 0; mask < dp.length; mask++) {
                int i = Integer.bitCount(mask); if (i == m) { answer = Math.min(answer, dp[mask]); continue; }
                if (i > m || dp[mask] == Long.MAX_VALUE / 4) continue;
                for (int j = 0; j < n; j++) if ((mask & (1 << j)) == 0) {
                    long distance = Math.abs((long) workers[2 * i] - bikes[2 * j]) + Math.abs((long) workers[2 * i + 1] - bikes[2 * j + 1]);
                    int next = mask | (1 << j); dp[next] = Math.min(dp[next], dp[mask] + distance);
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

    /** 025 固定范围内最大值；原独立文件：025/Main.java */
    static class Problem025 {
        public static void main(String[] args) throws Exception {
            int[] input = integers(read()); int n = input[0], k = input[n + 1];
            if (k <= 0 || k > n) return;
            ArrayDeque<Integer> queue = new ArrayDeque<>(); StringBuilder out = new StringBuilder();
            for (int i = 0; i < n; i++) {
                while (!queue.isEmpty() && queue.peekFirst() <= i - k) queue.removeFirst();
                while (!queue.isEmpty() && input[queue.peekLast() + 1] <= input[i + 1]) queue.removeLast();
                queue.addLast(i);
                if (i + 1 >= k) { if (out.length() > 0) out.append(' '); out.append(input[queue.peekFirst() + 1]); }
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

    /** 026 三元组和为零；原独立文件：026/Main.java */
    static class Problem026 {
        public static void main(String[] args) throws Exception {
            int[] input = integers(read()); int[] a = Arrays.copyOfRange(input, 1, input[0] + 1); Arrays.sort(a);
            StringBuilder out = new StringBuilder();
            for (int i = 0; i < a.length - 2; i++) {
                if (i > 0 && a[i] == a[i - 1]) continue;
                int left = i + 1, right = a.length - 1;
                while (left < right) {
                    long sum = (long) a[i] + a[left] + a[right];
                    if (sum < 0) left++; else if (sum > 0) right--;
                    else {
                        if (out.length() > 0) out.append('\n');
                        out.append(a[i]).append(' ').append(a[left]).append(' ').append(a[right]).append(' ');
                        int x = a[left], y = a[right];
                        while (left < right && a[left] == x) left++;
                        while (left < right && a[right] == y) right--;
                    }
                }
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

    /** 027 节点加和；原独立文件：027/Main.java */
    static class Problem027 {
        static class Node { int value; Node next; Node(int value) { this.value = value; } }
        public static void main(String[] args) throws Exception {
            String[] lines = read().trim().split("\\R"); int[] a = integers(lines[0]), b = integers(lines[1]);
            Node dummy = new Node(0), tail = dummy; int carry = 0;
            for (int i = 0; i < Math.max(a.length, b.length) || carry != 0; i++) {
                int sum = carry + (i < a.length ? a[i] : 0) + (i < b.length ? b[i] : 0);
                tail.next = new Node(sum % 10); tail = tail.next; carry = sum / 10;
            }
            StringBuilder out = new StringBuilder();
            for (Node p = dummy.next; p != null; p = p.next) { if (out.length() > 0) out.append(' '); out.append(p.value); }
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

    /** 028 180度旋转数；原独立文件：028/Main.java */
    static class Problem028 {
        static char[] digits; static BufferedWriter writer; static boolean first = true;
        static final char[] LOW = {'0', '1', '6', '8', '9'}, HIGH = {'0', '1', '9', '8', '6'};
        static void generate(int left, int right) throws IOException {
            if (left > right) { if (!first) writer.write(' '); first = false; writer.write(digits); return; }
            if (left == right) {
                for (char c : new char[]{'0', '1', '8'}) { digits[left] = c; generate(left + 1, right - 1); }
                return;
            }
            for (int i = 0; i < LOW.length; i++) {
                if (left == 0 && LOW[i] == '0') continue;
                digits[left] = LOW[i]; digits[right] = HIGH[i]; generate(left + 1, right - 1);
            }
        }
        public static void main(String[] args) throws Exception {
            digits = new char[(int) numbers(read())[0]]; writer = new BufferedWriter(new OutputStreamWriter(System.out));
            generate(0, digits.length - 1); writer.flush();
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

    /** 029 分治排序；原独立文件：029/Main.java */
    static class Problem029 {
        static int[] a, temp;
        static void sort(int left, int right) {
            if (right - left <= 1) return; int mid = (left + right) / 2;
            sort(left, mid); sort(mid, right); int i = left, j = mid, k = left;
            while (i < mid || j < right) temp[k++] = j == right || (i < mid && a[i] <= a[j]) ? a[i++] : a[j++];
            System.arraycopy(temp, left, a, left, right - left);
        }
        public static void main(String[] args) throws Exception { a = integers(read()); temp = new int[a.length]; sort(0, a.length); System.out.print(join(a)); }

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

    /** 030 后缀表达式求值；原独立文件：030/Main.java */
    static class Problem030 {
        public static void main(String[] args) throws Exception {
            String[] tokens = read().trim().split("\\s+"); ArrayDeque<BigInteger> stack = new ArrayDeque<>();
            for (String token : tokens) {
                if (!token.matches("[+*/-]")) { stack.push(new BigInteger(token)); continue; }
                BigInteger right = stack.pop(), left = stack.pop();
                switch (token) { case "+": stack.push(left.add(right)); break; case "-": stack.push(left.subtract(right)); break;
                    case "*": stack.push(left.multiply(right)); break; default: stack.push(left.divide(right)); }
            }
            System.out.print(stack.pop());
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
