import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new StringReader(read())); String line = reader.readLine(); long[] a = numbers(line == null ? "" : line);
        line = reader.readLine(); long[] b = numbers(line == null ? "" : line); int size = a.length+b.length, i = 0, j = 0; long previous = 0, current = 0;
        for (int k = 0; k <= size/2; k++) { previous = current; current = j == b.length || (i < a.length && a[i] <= b[j]) ? a[i++] : b[j++]; }
        if ((size & 1) == 1) System.out.print(current);
        else System.out.print(java.math.BigDecimal.valueOf(previous).add(java.math.BigDecimal.valueOf(current)).divide(java.math.BigDecimal.valueOf(2)).stripTrailingZeros().toPlainString());
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
