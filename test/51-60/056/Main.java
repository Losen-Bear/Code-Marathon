import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static int[] a, temp;
    static long count(int left, int right) {
        if (right - left <= 1) return 0; int mid = (left + right) / 2; long answer = count(left, mid) + count(mid, right);
        int j = mid;
        for (int i = left; i < mid; i++) { while (j < right && (long) a[i] > 2L * a[j]) j++; answer += j - mid; }
        int i = left, k = left; j = mid;
        while (i < mid || j < right) temp[k++] = j == right || (i < mid && a[i] <= a[j]) ? a[i++] : a[j++];
        System.arraycopy(temp, left, a, left, right - left); return answer;
    }
    public static void main(String[] args) throws Exception { a = integers(read()); temp = new int[a.length]; System.out.print(count(0, a.length)); }

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
