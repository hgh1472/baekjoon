import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int c = Integer.parseInt(input[0]);
        int n = Integer.parseInt(input[1]);
        int[][] info = new int[n][2];

        int max = 0;
        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            info[i][0] = Integer.parseInt(input[0]);
            info[i][1] = Integer.parseInt(input[1]);
            max = Math.max(max, info[i][1]);
        }

        int[] dp = new int[c + max + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        int answer = Integer.MAX_VALUE;
        dp[0] = 0;
        for (int i = 0; i < c; i++) {
            if (dp[i] == Integer.MAX_VALUE) {
                continue;
            }
            for (int j = 0; j < n; j++) {
                int next = i + info[j][1];
                int cost = dp[i] + info[j][0];
                dp[next] = Math.min(dp[next], cost);
                if (next >= c) {
                    answer = Math.min(answer, cost);
                }
            }
        }
        System.out.println(answer);
    }
}
