import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

/**
 * 061—070题 Java 代码汇总；每题代码位于对应的静态内部类。
 * 编译：javac -encoding UTF-8 Solutions061_070.java
 * 运行示例：java Solutions061_070 061
 * 命令行参数选择题号，题目数据仍从标准输入读取。
 * 向OJ提交单题时，使用题号目录中的Main.java。
 * 由tools/bundle_java.py生成，算法逻辑与独立文件一致。
 */
public class Solutions061_070 {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("用法：java Solutions061_070 <题号061—070>");
            return;
        }
        switch (Integer.parseInt(args[0])) {
            case 61: Problem061.main(new String[0]); break;
            case 62: Problem062.main(new String[0]); break;
            case 63: Problem063.main(new String[0]); break;
            case 64: Problem064.main(new String[0]); break;
            case 65: Problem065.main(new String[0]); break;
            case 66: Problem066.main(new String[0]); break;
            case 67: Problem067.main(new String[0]); break;
            case 68: Problem068.main(new String[0]); break;
            case 69: Problem069.main(new String[0]); break;
            case 70: Problem070.main(new String[0]); break;
            default: throw new IllegalArgumentException("本文件支持的题号为061—070");
        }
    }

    /** 061 填运算符；原独立文件：061/Main.java */
    static class Problem061 {
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

    /** 062 寻找反向对；原独立文件：062/Main.java */
    static class Problem062 {
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

    /** 063 一碗水端平；原独立文件：063/Main.java */
    static class Problem063 {
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

    /** 064 智能翻译；原独立文件：064/Main.java */
    static class Problem064 {
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

    /** 065 逆序对的总数；原独立文件：065/Main.java */
    static class Problem065 {
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

    /** 066 字符串变形；原独立文件：066/Main.java */
    static class Problem066 {
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

    /** 067 抽奖；原独立文件：067/Main.java */
    static class Problem067 {
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

    /** 068 找到高频数；原独立文件：068/Main.java */
    static class Problem068 {
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

    /** 069 无聊的小明；原独立文件：069/Main.java */
    static class Problem069 {
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

    /** 070 多多参加采访；原独立文件：070/Main.java */
    static class Problem070 {
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
}
