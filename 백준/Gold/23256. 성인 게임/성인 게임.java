import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static long[][] dp = new long[1000001][2];
    static int MOD = 1000000007;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        solve(1000000);
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println((dp[n][0] + dp[n][1]) % MOD);
        }
    }

    static void solve(int n) {
        dp[1][0] = 3;
        dp[1][1] = 4;
        for (int i = 2; i <= n; i++) {
            dp[i][0] = dp[i-1][0] + dp[i-1][1] + 2 * (dp[i-1][0]);
            dp[i][1] = 2 * dp[i-1][0] + 2 * (dp[i-1][0] + dp[i-1][1]);
            dp[i][0] %= MOD;
            dp[i][1] %= MOD;
        }
    }
}
