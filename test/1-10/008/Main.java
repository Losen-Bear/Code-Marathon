import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static String target; static int length; static Boolean[][][] memo;
    static boolean search(int left, int lowCarry, int highCarry) {
        int right = length - 1 - left;
        if (left > right) return lowCarry == highCarry;
        if (memo[left][lowCarry][highCarry] != null) return memo[left][lowCarry][highCarry];
        int lowDigit = target.charAt(target.length() - 1 - left) - '0';
        int highDigit = target.charAt(target.length() - 1 - right) - '0';
        boolean result = false;
        if (left == right) {
            for (int d = length == 1 ? 1 : 0; d <= 9; d++)
                if (2 * d + lowCarry == lowDigit + 10 * highCarry) result = true;
        } else {
            for (int low = 0; low <= 9 && !result; low++)
                for (int high = left == 0 ? 1 : 0; high <= 9 && !result; high++) {
                    int sum = low + high;
                    if ((sum + lowCarry) % 10 != lowDigit) continue;
                    int nextLow = (sum + lowCarry) / 10;
                    int nextHigh = highDigit + 10 * highCarry - sum;
                    if (nextHigh >= 0 && nextHigh <= 1) result = search(left + 1, nextLow, nextHigh);
                }
        }
        return memo[left][lowCarry][highCarry] = result;
    }
    public static void main(String[] args) throws Exception {
        target = new BigInteger(read().trim()).toString(); boolean result = target.equals("0");
        for (length = Math.max(1, target.length() - 1); length <= target.length() && !result; length++) {
            int carry = length == target.length() ? 0 : target.charAt(0) - '0';
            if (carry > 1) continue;
            memo = new Boolean[length + 1][2][2]; result = search(0, 0, carry);
        }
        System.out.print(result ? "True" : "False");
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
