import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static int[] count = new int[10]; static char[] answer; static String limit;
    static boolean search(int position, boolean tight) {
        if (position == answer.length) return true;
        if (!tight) {
            int next = position; for (int d = 9; d >= 0; d--) for (int i = 0; i < count[d]; i++) answer[next++] = (char)('0'+d);
            return true;
        }
        int ceiling = limit.charAt(position)-'0';
        for (int d = ceiling; d >= 0; d--) if (count[d] > 0) {
            count[d]--; answer[position] = (char)('0'+d);
            if (search(position+1,d == ceiling)) return true; count[d]++;
        }
        return false;
    }
    public static void main(String[] args) throws Exception {
        String[] t = read().trim().split("\\s+"); String a = new BigInteger(t[0]).toString(), b = new BigInteger(t[1]).toString();
        for (char c : a.toCharArray()) count[c-'0']++; answer = new char[a.length()];
        if (b.length() > a.length()) { int p = 0; for (int d = 9; d >= 0; d--) for (int i = 0; i < count[d]; i++) answer[p++] = (char)('0'+d); }
        else { StringBuilder padded = new StringBuilder(); for (int i = b.length(); i < a.length(); i++) padded.append('0'); limit = padded.append(b).toString(); if (!search(0,true)) { System.out.print(-1); return; } }
        System.out.print(new BigInteger(new String(answer)));
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
