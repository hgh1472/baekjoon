class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int maxAlp = alp, maxCop = cop;
        for (int i = 0; i < problems.length; i++) {
            maxAlp = Math.max(maxAlp, problems[i][0]);
            maxCop = Math.max(maxCop, problems[i][1]);
        }
        
        int[][] dp = new int[maxAlp + 2][maxCop + 2];
        for (int i = alp; i <= maxAlp; i++) {
            for (int j = cop; j <= maxCop; j++) {
                dp[i][j] = 500;
            }
        }
        dp[alp][cop] = 0;
        
        for (int i = alp; i <= maxAlp; i++) {
            for (int j = cop; j <= maxCop; j++) {
                if (dp[i][j] == 500) {
                    continue;
                }
                dp[i+1][j] = Math.min(dp[i][j] + 1, dp[i+1][j]);
                dp[i][j+1] = Math.min(dp[i][j] + 1, dp[i][j+1]);
                
                for (int[] problem : problems) {
                    if (problem[0] > i || problem[1] > j) {
                        continue;
                    }
                    int nextAlp = Math.min(maxAlp, i + problem[2]);
                    int nextCop = Math.min(maxCop, j + problem[3]);
                    dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], dp[i][j] + problem[4]);
                }
            }
        }
        
        return dp[maxAlp][maxCop];
    }
    /**
    목표: 최단 시간으로 (max alp, max cop) 달성
    완탐: (102)^100 -> 시간 초과
    dp[i][j] = (i, j)를 갖추는데 필요한 최소 시간
    int[maxAlp][maxCop]
    i = alp ~ maxAlp
      j = cop ~ maxCop
        if (dp[i][j] == 500) {
          continue;
        }
        dp[i+1][j] = Math.min(dp[i][j] + 1, dp[i+1][j]);
        dp[i][j+1] = Math.min(dp[i][j] + 1, dp[i][j+1]);
        
        for (int[] problem : problems) {
          if (풀수있음) {
            nextAlp = Math.min(maxAlp, i + 보상알고력);
            nextCop = Math.min(maxCop, j + 보상코딩력);
            dp[nextAlp][nextCop] = dp[i][j] + 문제소요시간;
          }
        }
        return dp[maxAlp][maxCop];
    */
}