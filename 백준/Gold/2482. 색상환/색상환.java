import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static long[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        if (2 * k > n) {
            System.out.println(0);
            return;
        }

        dp = new long[n+1][k+1];

        for (int i = 1; i <= n; i++) {
            dp[i][1] = i;
            dp[i][0] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= k; j++) {
                dp[i][j] = (dp[i-2][j-1] + dp[i-1][j]) % 1000000003;
            }
        }
        System.out.println((dp[n-3][k-1] + dp[n-1][k]) % 1000000003);
    }
}
