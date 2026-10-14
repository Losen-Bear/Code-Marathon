import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static int[][] stickers; static HashMap<String, Integer> memo = new HashMap<>();
    static int solve(String remaining) {
        if (remaining.isEmpty()) return 0;
        if (memo.containsKey(remaining)) return memo.get(remaining);
        int[] need = new int[26]; for (char c : remaining.toCharArray()) need[c - 'a']++;
        int best = Integer.MAX_VALUE;
        for (int[] sticker : stickers) {
            if (sticker[remaining.charAt(0) - 'a'] == 0) continue;
            StringBuilder next = new StringBuilder();
            for (int c = 0; c < 26; c++) for (int i = 0; i < Math.max(0, need[c] - sticker[c]); i++) next.append((char) ('a' + c));
            int sub = solve(next.toString()); if (sub >= 0) best = Math.min(best, sub + 1);
        }
        int result = best == Integer.MAX_VALUE ? -1 : best; memo.put(remaining, result); return result;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new StringReader(read()));
        String[] words = reader.readLine().trim().split("\\s+"); char[] target = reader.readLine().trim().toCharArray(); Arrays.sort(target);
        stickers = new int[words.length][26]; for (int i = 0; i < words.length; i++) for (char c : words[i].toCharArray()) stickers[i][c - 'a']++;
        System.out.print(solve(new String(target)));
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
