import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static ArrayList<Integer> path = new ArrayList<>(); static ArrayList<String> result = new ArrayList<>();
    static void search(int start, int k, int sum) {
        if (k == 0) { if (sum == 0) { int[] a = path.stream().mapToInt(Integer::intValue).toArray(); result.add(join(a)); } return; }
        if (10 - start < k || sum < 0) return;
        for (int x = start; x <= 9 && x <= sum; x++) { path.add(x); search(x + 1, k - 1, sum - x); path.remove(path.size() - 1); }
    }
    public static void main(String[] args) throws Exception { int[] a = integers(read()); search(1, a[0], a[1]); System.out.print(String.join("\n", result)); }

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
