import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static int priority(String s) { return s.equals("+") || s.equals("-") ? 1 : 2; }
    public static void main(String[] args) throws Exception {
        String[] tokens = read().trim().split("\\s+"); ArrayDeque<String> operators = new ArrayDeque<>(); StringBuilder out = new StringBuilder();
        for (String token : tokens) {
            if (token.matches("[+-]?\\d+")) out.append(token).append(' ');
            else if (token.equals("(")) operators.push(token);
            else if (token.equals(")")) { while (!operators.peek().equals("(")) out.append(operators.pop()).append(' '); operators.pop(); }
            else {
                while (!operators.isEmpty() && !operators.peek().equals("(") && priority(operators.peek()) >= priority(token)) out.append(operators.pop()).append(' ');
                operators.push(token);
            }
        }
        while (!operators.isEmpty()) out.append(operators.pop()).append(' ');
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
