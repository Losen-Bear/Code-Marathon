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
