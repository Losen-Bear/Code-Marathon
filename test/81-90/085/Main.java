import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static int[][] grid; static boolean[][] visited; static int endRow,endColumn;
    static int[] path; static BufferedWriter writer; static boolean found;
    static void search(int r, int c, int length) throws IOException {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0 || visited[r][c]) return;
        path[length++] = r*grid[0].length+c;
        if (r == endRow && c == endColumn) {
            if (found) writer.newLine(); found = true;
            for (int i = 0; i < length; i++) {
                if (i > 0) writer.write("->"); int p = path[i];
                writer.write("("+(p/grid[0].length+1)+","+(p%grid[0].length+1)+")");
            }
            return;
        }
        visited[r][c] = true;
        search(r,c-1,length); search(r-1,c,length); search(r,c+1,length); search(r+1,c,length);
        visited[r][c] = false;
    }
    public static void main(String[] args) throws Exception {
        int[] a = integers(read()); int m = a[0], n = a[1], position = 2; grid = new int[m][n]; visited = new boolean[m][n]; path = new int[m*n];
        for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) grid[i][j] = a[position++];
        int startRow = a[position++]-1, startColumn = a[position++]-1; endRow = a[position++]-1; endColumn = a[position]-1;
        writer = new BufferedWriter(new OutputStreamWriter(System.out)); search(startRow,startColumn,0);
        if (!found) writer.write("-1"); writer.flush();
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
