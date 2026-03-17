import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static long[][] arr;
    static long answer = Long.MAX_VALUE;
    static int n;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        arr = new long[n][2];
        for (int i = 0; i < n; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        }

        solve(0, 0, 0);
        System.out.println(answer);
    }

    static void solve(int idx, int pick, int cur) {
        if (idx == n) {
            if (pick == 0 || pick == n) {
                return;
            }
            long result = calculate(cur);
            answer = Math.min(result, answer);
            return;
        }
        solve(idx + 1, pick + 1, cur | (1 << idx));
        solve(idx + 1, pick, cur);
    }

    static long calculate(int cur) {
        boolean[][] visited = new boolean[n][n];
        long a = 0, b = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((cur & (1 << i)) == (1 << i) && (cur & (1 << j)) == (1 << j)) {
                    if (visited[i][j]) {
                        continue;
                    }
                    a += arr[i][j] + arr[j][i];
                    visited[i][j] = true;
                    visited[j][i] = true;
                }
                if ((cur & (1 << i)) == 0 && (cur & (1 << j)) == 0) {
                    if (visited[i][j]) {
                        continue;
                    }
                    b += arr[i][j] + arr[j][i];
                    visited[i][j] = true;
                    visited[j][i] = true;
                }
            }
        }
        return Math.abs(a - b);
    }
}
