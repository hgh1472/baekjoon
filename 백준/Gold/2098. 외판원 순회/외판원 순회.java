import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[][] dp, distance;
    static int n;
    static int INF = 10000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        dp = new int[1 << n][n];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], INF);
        }
        distance = new int[n][n];
        for (int i = 0; i < n; i++) {
            distance[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < n; j++) {
                if (distance[i][j] == 0)
                    distance[i][j] = INF;
            }
        }

        for (int i = 1; i < n; i++) {
            dp[(1 << n) - 1][i] = distance[i][0];
        }

        dfs(1, 0);
        System.out.println(dp[1][0]);
    }

    static int dfs(int visited, int now) {
        if (dp[visited][now] != INF) {
            return dp[visited][now];
        }

        for (int i = 0; i < n; i++) {
            if (distance[now][i] == INF) {
                continue;
            }
            if ((visited & 1 << i) == 1 << i) {
                continue;
            }
            int next = visited | 1 << i;
            dp[visited][now] = Math.min(dp[visited][now], dfs(next, i) + distance[now][i]);
        }
        if (dp[visited][now] == INF) {
            dp[visited][now] = INF + 1;
        }

        return dp[visited][now];
    }
}
