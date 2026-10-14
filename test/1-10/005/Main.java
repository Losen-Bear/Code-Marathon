import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        String raw = read(); ArrayList<char[]> rows = new ArrayList<>();
        Matcher matcher = Pattern.compile("[\\{\\[]([^{}\\[\\]]*)[\\}\\]]").matcher(raw);
        while (matcher.find()) {
            String row = matcher.group(1).replaceAll("[^EBS]", "");
            if (!row.isEmpty()) rows.add(row.toCharArray());
        }
        if (rows.isEmpty()) throw new IllegalArgumentException("输入应为题面给出的二维字符数组");
        char[][] grid = rows.toArray(new char[0][]);
        int m = grid.length, n = grid[0].length, best = 0, rowCount = 0;
        int[] columnCount = new int[n];
        for (int i = 0; i < m; i++) {
            if (grid[i].length != n) throw new IllegalArgumentException("矩阵行长度不一致");
            for (int j = 0; j < n; j++) {
                if (j == 0 || grid[i][j - 1] == 'B') {
                    rowCount = 0;
                    for (int k = j; k < n && grid[i][k] != 'B'; k++) if (grid[i][k] == 'E') rowCount++;
                }
                if (i == 0 || grid[i - 1][j] == 'B') {
                    columnCount[j] = 0;
                    for (int k = i; k < m && grid[k][j] != 'B'; k++) if (grid[k][j] == 'E') columnCount[j]++;
                }
                if (grid[i][j] == 'S') best = Math.max(best, rowCount + columnCount[j]);
            }
        }
        System.out.print(best);
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
