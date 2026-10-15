import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static long[][] grid; static long best = Long.MIN_VALUE, upper;
    static void search(int r, int c, long total) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0 || best == upper) return;
        long value = grid[r][c]; total += value; best = Math.max(best,total); grid[r][c] = 0;
        search(r-1,c,total); search(r+1,c,total); search(r,c-1,total); search(r,c+1,total); grid[r][c] = value;
    }
    public static void main(String[] args) throws Exception {
        long[] input = numbers(read()); int m = (int) input[0], n = (int) input[1]; grid = new long[m][n];
        for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) { grid[i][j] = input[2+i*n+j]; upper += Math.max(0,grid[i][j]); }
        for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) search(i,j,0);
        System.out.print(best == Long.MIN_VALUE ? 0 : best);
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
