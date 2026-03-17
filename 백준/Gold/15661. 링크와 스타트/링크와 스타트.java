import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static long[][] arr;
    static long answer = Long.MAX_VALUE;
    static int n;
    static boolean[] visited;
    static int max;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        arr = new long[n][n];
        for (int i = 0; i < n; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        }
        visited = new boolean[(1 << n)];
        max = (1 << n) - 1;

        solve(0, 0, 0);
        System.out.println(answer);
    }

    static void solve(int idx, int pick, int cur) {
        if (visited[cur]) {
            return;
        }
        if (idx == n) {
            if (pick == 0 || pick == n) {
                return;
            }
            long result = calculate(cur);
            answer = Math.min(result, answer);
            return;
        }
        solve(idx + 1, pick + 1, cur | (1 << idx));
        visited[cur | (1 << idx)] = true;
        visited[max - (cur | (1 << idx))] = true;
        solve(idx + 1, pick, cur);
        visited[cur] = true;
        visited[max - cur] = true;
    }

    static long calculate(int cur) {
        long a = 0, b = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((cur & (1 << i)) == (1 << i) && (cur & (1 << j)) == (1 << j)) {
                    a += arr[i][j] + arr[j][i];
                }
                if ((cur & (1 << i)) == 0 && (cur & (1 << j)) == 0) {
                    b += arr[i][j] + arr[j][i];
                }
            }
        }
        return Math.abs(a - b);
    }
}
