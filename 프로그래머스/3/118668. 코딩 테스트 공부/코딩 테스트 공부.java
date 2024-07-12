class Solution {
    static int alpDst = 0;
    static int copDst = 0;
    static int[][] dp;
    public int solution(int alp, int cop, int[][] problems) {
        for (int[] problem : problems) {
            if (alpDst < problem[0])
                alpDst = problem[0];
            if (copDst < problem[1])
                copDst = problem[1];
        }
        if (cop >= copDst && alp >= alpDst)
            return 0;
        if (cop >= copDst)
            cop = copDst;
        if (alp >= alpDst)
            alp = alpDst;
        dp = new int[alpDst+1][copDst+1];
        for (int i = alp; i <= alpDst; i++) {
            for (int j = cop; j <= copDst; j++) {
                dp[i][j] = 2000000000;
            }
        }
        dp[alp][cop] = 0;
        
        for (int i = alp; i <= alpDst; i++) {
            for (int j = cop; j <= copDst; j++) {
                if (j + 1 <= copDst)
                    dp[i][j + 1] = Math.min(dp[i][j] + 1, dp[i][j+1]);
                if (i+1 <= alpDst)
                    dp[i+1][j] = Math.min(dp[i][j] + 1, dp[i+1][j]);
                for (int[] p : problems) {
                    if (i >= p[0] && j >= p[1]) { // 문제 풀 수 있음
                        if (i + p[2] <= alpDst && j + p[3] <= copDst) // 목표치 내
                            dp[i+p[2]][j+p[3]] = Math.min(dp[i][j]+p[4], dp[i+p[2]][j+p[3]]);
                        else if (i + p[2] <= alpDst && j+p[3] > copDst) // j가 초과
                            dp[i+p[2]][copDst] = Math.min(dp[i][j]+p[4],dp[i+p[2]][copDst]);
                        else if (i + p[2] > alpDst && j+p[3] <= copDst)
                            dp[alpDst][j+p[3]] = Math.min(dp[i][j]+p[4], dp[alpDst][j+p[3]]);
                        else
                            dp[alpDst][copDst] = Math.min(dp[i][j]+p[4], dp[alpDst][copDst]);
                    }
                }
            }
        }
        return dp[alpDst][copDst];
    }
}