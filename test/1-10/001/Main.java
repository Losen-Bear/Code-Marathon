import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static boolean possible(String s, int limit, long operations) {
        long needed = 0;
        if (limit == 1) {
            int different = 0;
            for (int i = 0; i < s.length(); i++)
                if (s.charAt(i) - '0' != (i & 1)) different++;
            return Math.min(different, s.length() - different) <= operations;
        }
        for (int i = 0; i < s.length();) {
            int j = i + 1;
            while (j < s.length() && s.charAt(j) == s.charAt(i)) j++;
            needed += (j - i) / (limit + 1);
            if (needed > operations) return false;
            i = j;
        }
        return true;
    }
    public static void main(String[] args) throws Exception {
        String[] input = read().trim().split("\\s+");
        String s = input[0]; long operations = Long.parseLong(input[1]);
        int lo = 1, hi = s.length();
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (possible(s, mid, operations)) hi = mid; else lo = mid + 1;
        }
        System.out.print(lo);
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
