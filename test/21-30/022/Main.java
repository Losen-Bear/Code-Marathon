import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
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
