import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.BigInteger;

public class Main {
    static int n, m, count, best; static int[] height; static int[][][] edges;
    static int upper(int start, int direction, long used) {
        boolean[][] seen = new boolean[count][2]; boolean[] reached = new boolean[count];
        int[] queue = new int[count * 2]; int head = 0, tail = 0;
        queue[tail++] = start * 2 + direction; seen[start][direction] = true;
        while (head < tail) {
            int state = queue[head++], v = state / 2, d = state % 2;
            for (int w : edges[v][d]) if ((used & (1L << w)) == 0 && !seen[w][1 - d]) {
                seen[w][1 - d] = true; reached[w] = true; queue[tail++] = w * 2 + 1 - d;
            }
        }
        int result = 0; for (boolean b : reached) if (b) result++; return result;
    }
    static void dfs(int v, int direction, long used, int moves) {
        best = Math.max(best, moves);
        if (best == count - 1 || moves + upper(v, direction, used) <= best) return;
        for (int w : edges[v][direction]) if ((used & (1L << w)) == 0)
            dfs(w, 1 - direction, used | (1L << w), moves + 1);
    }
    public static void main(String[] args) throws Exception {
        int[] input = integers(read()); n = input[0]; m = input[1]; count = n * m;
        height = Arrays.copyOfRange(input, 2, input.length); edges = new int[count][2][];
        int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
        for (int v = 0; v < count; v++) for (int d = 0; d < 2; d++) {
            ArrayList<Integer> next = new ArrayList<>();
            for (int k = 0; k < 4; k++) {
                int r = v / m + dr[k], c = v % m + dc[k], w = r * m + c;
                if (r >= 0 && r < n && c >= 0 && c < m && (d == 0 ? height[w] > height[v] : height[w] < height[v])) next.add(w);
            }
            edges[v][d] = next.stream().mapToInt(Integer::intValue).toArray();
        }
        for (int v = 0; v < count && best < count - 1; v++) for (int d = 0; d < 2; d++) dfs(v, d, 1L << v, 0);
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
