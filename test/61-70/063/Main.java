import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static long[] fruits, loads; static long best;
    static void search(int index, long maximum) {
        if (maximum >= best) return;
        if (index < 0) { best = maximum; return; }
        HashSet<Long> tried = new HashSet<>();
        for (int child = 0; child < loads.length; child++) {
            if (!tried.add(loads[child])) continue;
            loads[child] += fruits[index]; search(index - 1, Math.max(maximum, loads[child])); loads[child] -= fruits[index];
        }
    }
    public static void main(String[] args) throws Exception {
        String[] lines = read().trim().split("\\R"); fruits = numbers(lines[0]); loads = new long[(int) numbers(lines[1])[0]];
        Arrays.sort(fruits); best = 0; for (long x : fruits) best += x;
        search(fruits.length - 1, 0); System.out.print(best);
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
