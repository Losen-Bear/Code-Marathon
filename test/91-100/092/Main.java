import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static long gcd(long a, long b) { while (b != 0) { long r = a%b; a = b; b = r; } return a; }
    public static void main(String[] args) throws Exception {
        long[] a = numbers(read()); int m = a.length; long[][] common = new long[m][m];
        for (int i = 0; i < m; i++) for (int j = i+1; j < m; j++) common[i][j] = gcd(a[i],a[j]);
        long[] dp = new long[1<<m]; Arrays.fill(dp,-1); dp[0] = 0;
        for (int mask = 0; mask < dp.length; mask++) if (dp[mask] >= 0 && (Integer.bitCount(mask)&1) == 0) {
            int operation = Integer.bitCount(mask)/2+1;
            for (int i = 0; i < m; i++) if ((mask & (1<<i)) == 0)
                for (int j = i+1; j < m; j++) if ((mask & (1<<j)) == 0) { int next = mask|(1<<i)|(1<<j); dp[next] = Math.max(dp[next],dp[mask]+operation*common[i][j]); }
        }
        System.out.print(dp[dp.length-1]);
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
