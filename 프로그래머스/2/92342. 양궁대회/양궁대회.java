class Solution {
    int diff;
    int[] answer = new int[11];
    public int[] solution(int n, int[] info) {
        diff = -1;
        int[] rion = new int[11];
        dfs(rion, info, 0, n);
        if (diff == -1) {
            return new int[]{-1};
        }
        return answer;
    }
    
    void dfs(int[] rion, int[] info, int depth, int count) {
        if (depth == 10) {
            int rionScore = 0;
            int apeachScore = 0;
            for (int i = 0; i < 10; i++) {
                if (rion[i] > info[i]) {
                    rionScore += 10 - i;
                }
                if (rion[i] < info[i]) {
                    apeachScore += 10 - i;
                }
                if (rion[i] == info[i]) {
                    if (rion[i] == 0) {
                        continue;
                    }
                    apeachScore += 10 - i;
                }
            }
            if (rionScore > apeachScore && diff < rionScore - apeachScore) {
                for (int i = 0; i < 10; i++) {
                    answer[i] = rion[i];
                }
                answer[10] = count;
                diff = rionScore - apeachScore;
            }
            else if (rionScore > apeachScore && diff == rionScore - apeachScore) {
                for (int i = 10; i >= 0; i--) {
                    if (answer[i] > rion[i]) {
                        return;
                    }
                    if (answer[i] < rion[i]) {
                        break;
                    }
                }
                for (int i = 0; i < 10; i++) {
                    answer[i] = rion[i];
                }
                answer[10] = count;
                diff = rionScore - apeachScore;
            }
            return;
        }
        
        // lose
        dfs(rion, info, depth+1, count);
        
        // win
        if (info[depth] + 1 <= count) {
            rion[depth] = info[depth] + 1;
            dfs(rion, info, depth+1, count - (info[depth] + 1));
            rion[depth] = 0;
        }
    }
    
    /**
    라이언이 가장 큰 점수 차이로 우승해야 함 -> 가장 효율적으로 이김
    10점부터 이길지말지 dfs
    */
}