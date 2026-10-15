import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static class FastInput {
        BufferedInputStream in = new BufferedInputStream(System.in,1<<16);
        int nextInt() throws IOException {
            int c; do { c = in.read(); } while (c <= 32 && c != -1);
            if (c == -1) throw new EOFException(); int value = 0;
            while (c > 32 && c != -1) { value = value * 10 + c - '0'; c = in.read(); } return value;
        }
    }
    public static void main(String[] args) throws Exception {
        FastInput input = new FastInput(); int m = input.nextInt(), n = input.nextInt(), best = 0;
        int[] vertical = new int[n], diagonal = new int[n], anti = new int[n+1];
        for (int r = 0; r < m; r++) {
            int horizontal = 0, previousDiagonal = 0;
            for (int c = 0; c < n; c++) {
                int oldDiagonal = diagonal[c], value = input.nextInt();
                if (value == 1) { horizontal++; vertical[c]++; diagonal[c] = previousDiagonal + 1; anti[c] = anti[c+1] + 1; }
                else { horizontal = 0; vertical[c] = 0; diagonal[c] = 0; anti[c] = 0; }
                previousDiagonal = oldDiagonal; best = Math.max(best, Math.max(horizontal,Math.max(vertical[c],Math.max(diagonal[c],anti[c]))));
            }
        }
        System.out.print(best);
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
