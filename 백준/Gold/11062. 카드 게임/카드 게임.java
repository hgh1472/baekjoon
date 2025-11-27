import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * dp[i][j] = Math.max(num[i] + (sum[j]-sum[i]) - dp[i+1][j], num[j] + (sum[j-1]-sum[i-1]) - dp[i][j-1])
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] card = new int[n+1];
            String[] input = br.readLine().split(" ");
            for (int i = 1; i <= n; i++) {
                card[i] = Integer.parseInt(input[i - 1]);
            }
            int[] sum = new int[n+1];
            int[][] dp = new int[n+1][n+1];
            for (int i = 1; i <= n; i++) {
                sum[i] = sum[i-1] + card[i];
                dp[i][i] = card[i];
            }
            for (int i = 1; i < n; i++) { // i = 구간의 길이
                for (int j = 1; j <= n - i; j++) {
                    dp[j][j + i] = Math.max(card[j] + (sum[j+i]-sum[j]) - dp[j+1][j+i], card[j+i] + (sum[j+i-1]-sum[j-1]) - dp[j][j+i-1]);
                }
            }
            System.out.println(dp[1][n]);
        }
    }
}
