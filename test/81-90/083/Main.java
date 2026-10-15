import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        long[] input = numbers(read()); int n = (int) input[0], m = (int) input[1]; long[][] goods = new long[m][3];
        for (int i = 0; i < m; i++) for (int j = 0; j < 3; j++) goods[i][j] = input[2+i*3+j];
        Arrays.sort(goods,Comparator.comparingLong(x -> x[1])); long[] dp = new long[n+1]; int next = 0;
        for (int x = 1; x <= n; x++) {
            dp[x] = dp[x-1];
            while (next < m && goods[next][1] == x) {
                long[] g = goods[next++]; dp[x] = Math.max(dp[x],dp[(int)g[0]]+g[1]-g[0]+g[2]);
            }
        }
        System.out.print(dp[n]);
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
