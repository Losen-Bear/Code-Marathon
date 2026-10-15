import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        int[] input = integers(read()); int n = input[0]; int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) grid[i][j] = input[1+i*n+j];
        long impossible = Long.MIN_VALUE/4; long[][] dp = new long[n][n]; for (long[] row : dp) Arrays.fill(row,impossible); dp[0][0] = Math.min(2,grid[0][0]);
        for (int step = 1; step <= 2*(n-1); step++) {
            long[][] next = new long[n][n]; for (long[] row : next) Arrays.fill(row,impossible);
            for (int r1 = Math.max(0,step-n+1); r1 <= Math.min(n-1,step); r1++)
                for (int r2 = Math.max(0,step-n+1); r2 <= Math.min(n-1,step); r2++) {
                    long best = impossible;
                    for (int a = 0; a <= 1; a++) for (int b = 0; b <= 1; b++) if (r1-a >= 0 && r2-b >= 0) best = Math.max(best,dp[r1-a][r2-b]);
                    if (best == impossible) continue;
                    int gain = r1 == r2 ? Math.min(2,grid[r1][step-r1]) : Math.min(1,grid[r1][step-r1])+Math.min(1,grid[r2][step-r2]);
                    next[r1][r2] = best+gain;
                }
            dp = next;
        }
        System.out.print(dp[n-1][n-1]);
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
