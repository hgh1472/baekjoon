import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int n, m;
    static int[] sum;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i-1] + Integer.parseInt(br.readLine());
        }
        int[][] dp = new int[n+1][m+1];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(dp[i], -32768 * 101);
            dp[i][0] = 0;
        }

        // dp[i][j] = i까지 숫자중에서 j개 그룹으로 나눴을 때 최대값
        dp[1][1] = sum[1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i-1][j];
                if (j == 1) {
                    dp[i][j] = Math.max(dp[i][j], sum[i]);
                }
                for (int k = 0; k <= i - 2; k++)
                    dp[i][j] = Math.max(dp[i][j], dp[k][j-1] + sum[i] - sum[k+1]);
            }
        }

        System.out.println(dp[n][m]);
    }
}
