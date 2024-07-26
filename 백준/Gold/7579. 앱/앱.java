import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);

        String[] bytes = br.readLine().split(" ");
        String[] costs = br.readLine().split(" ");

        int[][] dp = new int[n+1][100001];
        int min = 100001;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 100001; j++) {
                dp[i][j] = dp[i-1][j];
                // 넣을 수 있는 무게
                int cost = Integer.parseInt(costs[i - 1]);
                int b = Integer.parseInt(bytes[i - 1]);
                if (j >= cost) {
                    dp[i][j] = Math.max(dp[i-1][j-cost] + b, dp[i-1][j]);
                }
                if (dp[i][j] >= m) {
                    min = Math.min(min, j);
                }
            }
        }
        System.out.println(min);
    }

}