import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static String s; static int position;
    static String decode() {
        StringBuilder out = new StringBuilder();
        while (position < s.length() && s.charAt(position) != ']') {
            if (s.charAt(position) != '[') { out.append(s.charAt(position++)); continue; }
            position++; int count = 0;
            while (Character.isDigit(s.charAt(position))) count = count * 10 + s.charAt(position++) - '0';
            String part = decode(); position++; // 跳过当前层的右括号
            for (int i = 0; i < count; i++) out.append(part);
        }
        return out.toString();
    }
    public static void main(String[] args) throws Exception { s = read().trim(); System.out.print(decode()); }

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
