class Solution {
    int[] answer = new int[11];
    int maxDiff = -1;
    public int[] solution(int n, int[] info) {
        dfs(n, 0, info, new int[11], 0);
        if (maxDiff == -1) {
            return new int[]{-1};
        }
        return answer;
        
    }
    
    void dfs(int n, int i, int[] info, int[] arrow, int count) {
        if (i == 10) {
            calculate(info, arrow, count, n);
            return;
        }
        if (info[i] < n-count) {
            arrow[i] = info[i]+1;
            dfs(n, i+1, info, arrow, count+info[i]+1);
        }
        arrow[i] = 0;
        dfs(n, i+1, info, arrow, count);
    }
    
    void calculate(int[] info, int[] arrow, int count, int n) {
        int lion = 0;
        int peach = 0;
        for (int i = 0; i < 10; i++) {
            // 둘 다 못맞춘 상황
            if (arrow[i] == 0 && info[i] == 0) {
                continue;
            }
            // 라이언이 이김
            if (arrow[i] != 0) {
                lion += 10-i;
                continue;
            }
            peach += 10-i;
        }
        arrow[10] = n-count;
        int diff = lion - peach;
        // 라이언이 진 상황
        if (diff <= 0 || diff < maxDiff) {
            return;
        }
        if (diff == maxDiff) {
            for (int i = 10; i >= 0; i--) {
                if (answer[i] > arrow[i]) {
                    return;
                }
                if (answer[i] < arrow[i]) {
                    break;
                }
            }
        }
        for (int i = 0; i < 11; i++) {
            answer[i] = arrow[i];
        }
        maxDiff = diff;
    }
}