import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
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
