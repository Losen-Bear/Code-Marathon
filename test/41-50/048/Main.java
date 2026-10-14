import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static int width, height, best; static int[] skyline; static HashMap<Long, Integer> memo = new HashMap<>();
    static void search(int used) {
        if (used >= best) return;
        int left = 0; for (int i = 1; i < width; i++) if (skyline[i] < skyline[left]) left = i;
        int level = skyline[left]; if (level == height) { best = used; return; }
        long key = 0; for (int h : skyline) key = key * 14 + h;
        if (memo.containsKey(key) && memo.get(key) <= used) return; memo.put(key, used);
        int side = 1; while (left + side < width && skyline[left + side] == level && side < height - level) side++;
        for (int size = side; size >= 1; size--) {
            for (int i = left; i < left + size; i++) skyline[i] += size;
            search(used + 1);
            for (int i = left; i < left + size; i++) skyline[i] -= size;
        }
    }
    public static void main(String[] args) throws Exception {
        int[] a = integers(read()); width = Math.min(a[0], a[1]); height = Math.max(a[0], a[1]);
        skyline = new int[width]; best = width * height; search(0); System.out.print(best);
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
