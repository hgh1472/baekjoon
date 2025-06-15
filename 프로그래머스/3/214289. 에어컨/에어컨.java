import java.util.*;

class Solution {
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int[][] dp = new int[onboard.length][70];
        for (int i = 0; i < onboard.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][temperature+15] = 0;
        for (int i = 0; i < onboard.length-1; i++) {
            int start = Math.min(t1, temperature);
            int end = Math.max(t2, temperature);
            if (onboard[i] == 1) {
                start = t1;
                end = t2;
            }
            for (int j = start+15; j <= end+15; j++) {
                if (dp[i][j] == Integer.MAX_VALUE) {
                    continue;
                }
                if (j == temperature+15) {
                    dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]);
                    dp[i+1][j+1] = Math.min(dp[i+1][j+1], dp[i][j]+a);
                    dp[i+1][j-1] = Math.min(dp[i+1][j-1], dp[i][j]+a);
                }
                else if (j < temperature+15) {
                    dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]+b);
                    dp[i+1][j+1] = Math.min(dp[i+1][j+1], dp[i][j]);
                    dp[i+1][j-1] = Math.min(dp[i+1][j-1], dp[i][j]+a);
                }
                else {
                    dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]+b);
                    dp[i+1][j+1] = Math.min(dp[i+1][j+1], dp[i][j]+a);
                    dp[i+1][j-1] = Math.min(dp[i+1][j-1], dp[i][j]);
                }
            }
        }
        
        int answer = Integer.MAX_VALUE;
        int start = (onboard[onboard.length-1] == 1) ? t1 : Math.min(t1, temperature);
        int end = (onboard[onboard.length-1] == 1) ? t2 : Math.max(t2, temperature);
        
        for (int i = start+15; i <= end+15; i++) {
            answer = Math.min(dp[onboard.length-1][i], answer);
        }
        return answer;
    }
}