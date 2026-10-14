import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static long n, best = Long.MAX_VALUE; static int[] counts = new int[10];
    static void generate(long prefix, int remaining) {
        if (remaining == 0) { if (prefix > n) best = Math.min(best, prefix); return; }
        for (int d = 1; d <= 9; d++) if (counts[d] > 0) { counts[d]--; generate(prefix * 10 + d, remaining - 1); counts[d]++; }
    }
    public static void main(String[] args) throws Exception {
        n = numbers(read())[0]; int minLength = Long.toString(n).length();
        for (int mask = 1; mask < (1 << 9); mask++) {
            Arrays.fill(counts, 0); int length = 0;
            for (int d = 1; d <= 9; d++) if ((mask & (1 << (d - 1))) != 0) { counts[d] = d; length += d; }
            if (length >= minLength && length <= 9) generate(0, length);
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
