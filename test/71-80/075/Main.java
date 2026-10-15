import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        String[] lines = read().trim().split("\\R"); long[] hand = numbers(lines[0]); int size = (int) numbers(lines[1])[0];
        if (hand.length % size != 0) { System.out.print("false"); return; }
        TreeMap<Long,Integer> counts = new TreeMap<>(); for (long x : hand) counts.put(x, counts.getOrDefault(x,0)+1);
        while (!counts.isEmpty()) {
            long first = counts.firstKey();
            for (int i = 0; i < size; i++) {
                long x = first + i; Integer count = counts.get(x);
                if (count == null) { System.out.print("false"); return; }
                if (count == 1) counts.remove(x); else counts.put(x,count - 1);
            }
        }
        System.out.print("true");
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
