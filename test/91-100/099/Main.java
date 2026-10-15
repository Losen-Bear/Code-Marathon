import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static long solve(long[] a, int left, int right, int count) {
        long impossible = Long.MIN_VALUE/4; long[] older = new long[count+1], previous = new long[count+1]; Arrays.fill(older,impossible); Arrays.fill(previous,impossible); older[0] = previous[0] = 0;
        for (int i = left; i <= right; i++) {
            long[] current = previous.clone();
            for (int j = 1; j <= count; j++) if (older[j-1] != impossible) current[j] = Math.max(current[j],older[j-1]+a[i]);
            older = previous; previous = current;
        }
        return previous[count];
    }
    public static void main(String[] args) throws Exception {
        long[] a = numbers(read()); int count = a.length/3; System.out.print(Math.max(solve(a,0,a.length-2,count),solve(a,1,a.length-1,count)));
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
