import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        String[] lines = read().trim().split("\\R"); long[] a = numbers(lines[0]); int k = (int) numbers(lines[1])[0];
        HashMap<Long, Integer> frequency = new HashMap<>(); for (long x : a) frequency.put(x, frequency.getOrDefault(x, 0) + 1);
        ArrayList<Long> values = new ArrayList<>(frequency.keySet());
        values.sort((x,y) -> { int c = Integer.compare(frequency.get(y), frequency.get(x)); return c != 0 ? c : Long.compare(x,y); });
        ArrayList<Long> chosen = new ArrayList<>(values.subList(0,k)); Collections.sort(chosen); StringBuilder out = new StringBuilder();
        for (long x : chosen) { if (out.length() > 0) out.append(','); out.append(x); } System.out.print(out);
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
