import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static char[] digits; static BufferedWriter writer; static boolean first = true;
    static final char[] LOW = {'0', '1', '6', '8', '9'}, HIGH = {'0', '1', '9', '8', '6'};
    static void generate(int left, int right) throws IOException {
        if (left > right) { if (!first) writer.write(' '); first = false; writer.write(digits); return; }
        if (left == right) {
            for (char c : new char[]{'0', '1', '8'}) { digits[left] = c; generate(left + 1, right - 1); }
            return;
        }
        for (int i = 0; i < LOW.length; i++) {
            if (left == 0 && LOW[i] == '0') continue;
            digits[left] = LOW[i]; digits[right] = HIGH[i]; generate(left + 1, right - 1);
        }
    }
    public static void main(String[] args) throws Exception {
        digits = new char[(int) numbers(read())[0]]; writer = new BufferedWriter(new OutputStreamWriter(System.out));
        generate(0, digits.length - 1); writer.flush();
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
