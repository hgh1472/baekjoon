import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static long[][] arr;
    static long answer = 100_000_000_000L;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        arr = new long[n][2];

        for (int i = 0; i < n; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        }

        solve(0, 0, n, new boolean[n]);
        System.out.println(answer);
    }

    static void solve(int idx, int count, int n, boolean[] visited) {
        if (idx == n) {
            if (count == 0) {
                return;
            }
            long result = calculate(visited, n);
            answer = Math.min(answer, result);
            return;
        }
        visited[idx] = true;
        solve(idx + 1, count + 1, n, visited);
        visited[idx] = false;
        solve(idx + 1, count, n, visited);
    }

    static long calculate(boolean[] visited, int n) {
        long a = 1;
        long b = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                continue;
            }
            a *= arr[i][0];
            b += arr[i][1];
        }

        return Math.abs(a - b);
    }
}
