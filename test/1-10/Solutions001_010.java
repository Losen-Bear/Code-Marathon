import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

/**
 * 001—010题 Java 代码汇总；每题代码位于对应的静态内部类。
 * 编译：javac -encoding UTF-8 Solutions001_010.java
 * 运行示例：java Solutions001_010 001
 * 命令行参数选择题号，题目数据仍从标准输入读取。
 * 向OJ提交单题时，使用题号目录中的Main.java。
 * 由tools/bundle_java.py生成，算法逻辑与独立文件一致。
 */
public class Solutions001_010 {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("用法：java Solutions001_010 <题号001—010>");
            return;
        }
        switch (Integer.parseInt(args[0])) {
            case 1: Problem001.main(new String[0]); break;
            case 2: Problem002.main(new String[0]); break;
            case 3: Problem003.main(new String[0]); break;
            case 4: Problem004.main(new String[0]); break;
            case 5: Problem005.main(new String[0]); break;
            case 6: Problem006.main(new String[0]); break;
            case 7: Problem007.main(new String[0]); break;
            case 8: Problem008.main(new String[0]); break;
            case 9: Problem009.main(new String[0]); break;
            case 10: Problem010.main(new String[0]); break;
            default: throw new IllegalArgumentException("本文件支持的题号为001—010");
        }
    }

    /** 001 光轨城市：信号优化计划；原独立文件：001/Main.java */
    static class Problem001 {
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

    /** 002 极限削木游戏；原独立文件：002/Main.java */
    static class Problem002 {
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

    /** 003 解压缩编码问题；原独立文件：003/Main.java */
    static class Problem003 {
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

    /** 004 PUA/小源；原独立文件：004/Main.java */
    static class Problem004 {
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

    /** 005 泡泡堂；原独立文件：005/Main.java */
    static class Problem005 {
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

    /** 006 猫和鱼；原独立文件：006/Main.java */
    static class Problem006 {
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

    /** 007 能量核心；原独立文件：007/Main.java */
    static class Problem007 {
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

    /** 008 反转加密；原独立文件：008/Main.java */
    static class Problem008 {
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

    /** 009 进攻火星；原独立文件：009/Main.java */
    static class Problem009 {
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

    /** 010 原神启动；原独立文件：010/Main.java */
    static class Problem010 {
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
}
