import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static int[] a; static boolean[][] can; static ArrayList<Integer> path = new ArrayList<>();
    static ArrayList<String> result = new ArrayList<>();
    static void search(int start, int remaining) {
        if (!can[start][remaining]) return;
        if (remaining == 0) {
            int[] values = new int[path.size()];
            for (int i = 0; i < values.length; i++) values[i] = path.get(i);
            result.add(join(values));
        }
        for (int i = start; i < a.length && a[i] <= remaining; i++) {
            if (i > start && a[i] == a[i - 1]) continue;
            path.add(a[i]); search(i + 1, remaining - a[i]); path.remove(path.size() - 1);
        }
    }
    public static void main(String[] args) throws Exception {
        int[] input = integers(read()); int n = input[0], target = input[n + 1];
        a = Arrays.copyOfRange(input, 1, n + 1); Arrays.sort(a);
        can = new boolean[n + 1][target + 1]; can[n][0] = true;
        for (int i = n - 1; i >= 0; i--) for (int sum = 0; sum <= target; sum++)
            can[i][sum] = can[i + 1][sum] || (sum >= a[i] && can[i + 1][sum - a[i]]);
        search(0, target); System.out.print(String.join("\n", result));
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
