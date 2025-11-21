import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        long k = Long.parseLong(input[2]);

        long[][] dp = new long[n + 1][m + 1];
        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < m + 1; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                if (dp[i][j] > 1000000000) {
                    dp[i][j] = 1000000000;
                }
            }
        }
        if (dp[n][m] < k) {
            System.out.println(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();
        int left = n;
        int right = m;
        while (0 < left && 0 < right) {
            long a = dp[left - 1][right];
            long z = dp[left][right - 1];
            if (k <= a) {
                sb.append("a");
                left -= 1;
            } else {
                sb.append("z");
                k -= a;
                right -= 1;
            }
        }
        if (0 < left && right == 0) {
            sb.append("a".repeat(left));
        }
        if (left == 0 && right > 0) {
            sb.append("z".repeat(right));
        }
        System.out.println(sb);
    }
}
