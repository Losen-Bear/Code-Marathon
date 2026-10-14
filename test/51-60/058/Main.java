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
