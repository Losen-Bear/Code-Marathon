import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static ArrayList<BitSet> words = new ArrayList<>(); static long[] suffix; static int best;
    static void search(int index, BitSet used, int length) {
        best = Math.max(best,length); if (index == words.size() || length+suffix[index] <= best) return;
        BitSet word = words.get(index);
        if (!used.intersects(word)) { used.or(word); search(index+1,used,length+word.cardinality()); used.andNot(word); }
        search(index+1,used,length);
    }
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new StringReader(read())); int k = Integer.parseInt(reader.readLine().trim());
        for (int i = 0; i < k; i++) {
            String s = reader.readLine(); BitSet mask = new BitSet(); boolean valid = true;
            for (char c : s.toCharArray()) { if (mask.get(c)) valid = false; mask.set(c); }
            if (valid && !s.isEmpty()) words.add(mask);
        }
        suffix = new long[words.size()+1]; for (int i = words.size()-1; i >= 0; i--) suffix[i] = suffix[i+1]+words.get(i).cardinality();
        search(0,new BitSet(),0); System.out.print(best);
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
