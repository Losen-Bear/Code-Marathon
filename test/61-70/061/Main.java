import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static class Fraction {
        BigInteger numerator, denominator;
        Fraction(BigInteger n, BigInteger d) {
            if (d.signum() < 0) { n = n.negate(); d = d.negate(); }
            BigInteger gcd = n.gcd(d); numerator = n.divide(gcd); denominator = d.divide(gcd);
        }
        Fraction add(Fraction b) { return new Fraction(numerator.multiply(b.denominator).add(b.numerator.multiply(denominator)), denominator.multiply(b.denominator)); }
        Fraction multiply(Fraction b) { return new Fraction(numerator.multiply(b.numerator), denominator.multiply(b.denominator)); }
        Fraction divide(Fraction b) { return new Fraction(numerator.multiply(b.denominator), denominator.multiply(b.numerator)); }
    }
    static String[] values; static BigInteger target; static char[] operators = new char[4];
    static ArrayList<String> result = new ArrayList<>();
    static void enumerate(int position) {
        if (position < 4) { for (char c : new char[]{'+','-','*','/'}) { operators[position] = c; enumerate(position + 1); } return; }
        Fraction sum = new Fraction(BigInteger.ZERO, BigInteger.ONE);
        Fraction term = new Fraction(new BigInteger(values[0]), BigInteger.ONE);
        for (int i = 0; i < 4; i++) {
            Fraction next = new Fraction(new BigInteger(values[i + 1]), BigInteger.ONE);
            switch (operators[i]) {
                case '+': sum = sum.add(term); term = next; break;
                case '-': sum = sum.add(term); term = new Fraction(next.numerator.negate(), next.denominator); break;
                case '*': term = term.multiply(next); break;
                default: if (next.numerator.signum() == 0) return; term = term.divide(next);
            }
        }
        sum = sum.add(term);
        if (sum.numerator.equals(target.multiply(sum.denominator))) {
            StringBuilder out = new StringBuilder(values[0]);
            for (int i = 0; i < 4; i++) out.append(operators[i]).append(values[i + 1]);
            result.add(out.append('=').append(target).toString());
        }
    }
    public static void main(String[] args) throws Exception {
        values = read().trim().split("\\s+"); target = new BigInteger(values[5]); enumerate(0);
        System.out.print(String.join("\n", result));
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
