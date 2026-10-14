import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
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
