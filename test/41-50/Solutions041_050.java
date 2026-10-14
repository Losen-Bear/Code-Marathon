import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

/**
 * 041—050题 Java 代码汇总；每题代码位于对应的静态内部类。
 * 编译：javac -encoding UTF-8 Solutions041_050.java
 * 运行示例：java Solutions041_050 041
 * 命令行参数选择题号，题目数据仍从标准输入读取。
 * 向OJ提交单题时，使用题号目录中的Main.java。
 * 由tools/bundle_java.py生成，算法逻辑与独立文件一致。
 */
public class Solutions041_050 {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("用法：java Solutions041_050 <题号041—050>");
            return;
        }
        switch (Integer.parseInt(args[0])) {
            case 41: Problem041.main(new String[0]); break;
            case 42: Problem042.main(new String[0]); break;
            case 43: Problem043.main(new String[0]); break;
            case 44: Problem044.main(new String[0]); break;
            case 45: Problem045.main(new String[0]); break;
            case 46: Problem046.main(new String[0]); break;
            case 47: Problem047.main(new String[0]); break;
            case 48: Problem048.main(new String[0]); break;
            case 49: Problem049.main(new String[0]); break;
            case 50: Problem050.main(new String[0]); break;
            default: throw new IllegalArgumentException("本文件支持的题号为041—050");
        }
    }

    /** 041 最好逛吃路线；原独立文件：041/Main.java */
    static class Problem041 {
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

    /** 042 中缀表达式转后缀表达式；原独立文件：042/Main.java */
    static class Problem042 {
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

    /** 043 a=b；原独立文件：043/Main.java */
    static class Problem043 {
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

    /** 044 串烧烤；原独立文件：044/Main.java */
    static class Problem044 {
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

    /** 045 拼多多；原独立文件：045/Main.java */
    static class Problem045 {
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

    /** 046 "有效子数组"个数；原独立文件：046/Main.java */
    static class Problem046 {
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

    /** 047 数字探险；原独立文件：047/Main.java */
    static class Problem047 {
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

    /** 048 拼拼图；原独立文件：048/Main.java */
    static class Problem048 {
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

    /** 049 工具怎么下；原独立文件：049/Main.java */
    static class Problem049 {
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

    /** 050 1应俱全；原独立文件：050/Main.java */
    static class Problem050 {
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
}
