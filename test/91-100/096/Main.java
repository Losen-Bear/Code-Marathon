import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        int[] input = integers(read()); int n = input[0], m = input[1]; int[] height = Arrays.copyOfRange(input,2,input.length), degree = new int[n*m];
        int[] dr = {-1,1,0,0}, dc = {0,0,-1,1}; ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int v = 0; v < n*m; v++) {
            for (int d = 0; d < 4; d++) { int r = v/m+dr[d], c = v%m+dc[d]; if (r >= 0 && r < n && c >= 0 && c < m && height[r*m+c] < height[v]) degree[v]++; }
            if (degree[v] == 0) queue.add(v);
        }
        int length = 0;
        while (!queue.isEmpty()) { int size = queue.size(); length++;
            while (size-- > 0) { int v = queue.remove();
                for (int d = 0; d < 4; d++) { int r = v/m+dr[d], c = v%m+dc[d], w = r*m+c;
                    if (r >= 0 && r < n && c >= 0 && c < m && height[w] > height[v] && --degree[w] == 0) queue.add(w);
                }
            }
        }
        System.out.print(length);
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
