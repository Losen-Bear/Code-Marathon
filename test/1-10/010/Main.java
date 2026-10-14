import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        int[] input = integers(read()); int n = input[0], target = input[1], max = 0;
        long total = 0;
        for (int i = 0; i < n; i++) { max = Math.max(max, input[3 + i * 2]); total += (long) input[2 + i * 2] * input[3 + i * 2]; }
        if (total < target) { System.out.print("op"); return; }
        int limit = target + max - 1; int[] remaining = new int[limit + 1];
        Arrays.fill(remaining, -1); remaining[0] = 0;
        for (int i = 0; i < n; i++) {
            int count = input[2 + i * 2], weight = input[3 + i * 2];
            for (int sum = 0; sum <= limit; sum++) {
                if (remaining[sum] >= 0) remaining[sum] = count;
                else if (sum < weight || remaining[sum - weight] <= 0) remaining[sum] = -1;
                else remaining[sum] = remaining[sum - weight] - 1;
            }
        }
        for (int sum = target; sum <= limit; sum++) if (remaining[sum] >= 0) { System.out.print(sum - target); return; }
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
