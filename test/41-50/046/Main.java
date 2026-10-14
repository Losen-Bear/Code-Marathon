import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static int[] tree;
    static void add(int index) { for (; index < tree.length; index += index & -index) tree[index]++; }
    static long query(int index) { long answer = 0; for (; index > 0; index -= index & -index) answer += tree[index]; return answer; }
    public static void main(String[] args) throws Exception {
        String[] tokens = read().trim().split("\\s+"); ArrayList<Integer> a = new ArrayList<>();
        for (String token : tokens) { if (!token.equals("0") && !token.equals("1")) break; a.add(Integer.parseInt(token)); }
        int n = a.size(), offset = n + 1, prefix = 0; tree = new int[2 * n + 3]; add(offset);
        long answer = 0;
        for (int x : a) { prefix += x == 1 ? 1 : -1; answer = (answer + query(prefix + offset - 1)) % 1000000007L; add(prefix + offset); }
        System.out.print(answer);
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
