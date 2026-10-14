import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
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
