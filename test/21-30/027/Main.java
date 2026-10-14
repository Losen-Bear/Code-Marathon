import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static class Node { int value; Node next; Node(int value) { this.value = value; } }
    public static void main(String[] args) throws Exception {
        String[] lines = read().trim().split("\\R"); int[] a = integers(lines[0]), b = integers(lines[1]);
        Node dummy = new Node(0), tail = dummy; int carry = 0;
        for (int i = 0; i < Math.max(a.length, b.length) || carry != 0; i++) {
            int sum = carry + (i < a.length ? a[i] : 0) + (i < b.length ? b[i] : 0);
            tail.next = new Node(sum % 10); tail = tail.next; carry = sum / 10;
        }
        StringBuilder out = new StringBuilder();
        for (Node p = dummy.next; p != null; p = p.next) { if (out.length() > 0) out.append(' '); out.append(p.value); }
        System.out.print(out);
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
