import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static char[][] board; static boolean[][] visited; static String word;
    static boolean dfs(int r, int c, int index) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || visited[r][c] || board[r][c] != word.charAt(index)) return false;
        if (index + 1 == word.length()) return true;
        visited[r][c] = true; boolean found = false;
        for (int dr = -1; dr <= 1 && !found; dr++) for (int dc = -1; dc <= 1 && !found; dc++)
            if (dr != 0 || dc != 0) found = dfs(r + dr, c + dc, index + 1);
        visited[r][c] = false; return found;
    }
    public static void main(String[] args) throws Exception {
        String[] t = read().trim().split("\\s+"); int m = Integer.parseInt(t[0]), n = Integer.parseInt(t[1]);
        board = new char[m][n]; visited = new boolean[m][n]; int position = 2;
        for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) board[i][j] = t[position++].charAt(0);
        int k = Integer.parseInt(t[position++]); TreeSet<String> result = new TreeSet<>();
        for (int index = 0; index < k; index++) {
            word = t[position++]; if (word.length() > m * n) continue; boolean found = false;
            for (int i = 0; i < m && !found; i++) for (int j = 0; j < n && !found; j++) found = dfs(i, j, 0);
            if (found) result.add(word);
        }
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
