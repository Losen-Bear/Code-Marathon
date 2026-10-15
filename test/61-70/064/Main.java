import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static final String[] SMALL = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    static final String[] TENS = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    static String part(int n) {
        ArrayList<String> words = new ArrayList<>();
        if (n >= 100) { words.add(SMALL[n / 100]); words.add("Hundred"); n %= 100; }
        if (n >= 20) { words.add(TENS[n / 10]); n %= 10; }
        if (n > 0) words.add(SMALL[n]); return String.join(" ", words);
    }
    public static void main(String[] args) throws Exception {
        long n = numbers(read())[0]; if (n == 0) { System.out.print("Zero"); return; }
        int[] units = {1000000000, 1000000, 1000, 1}; String[] names = {"Billion", "Million", "Thousand", ""};
        ArrayList<String> words = new ArrayList<>();
        for (int i = 0; i < units.length; i++) if (n >= units[i]) {
            words.add(part((int) (n / units[i]))); if (!names[i].isEmpty()) words.add(names[i]); n %= units[i];
        }
        System.out.print(String.join(" ", words));
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
