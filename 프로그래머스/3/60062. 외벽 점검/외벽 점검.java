class Solution {
    int[][] weakCase;
    int[] dist;
    int[] position;
    int[] weak;
    boolean[] visited;
    int n;
    boolean result = false;
    public int solution(int n, int[] weak, int[] dist) {
        this.n = n;
        this.dist = dist;
        this.weak = weak;
        this.weakCase = new int[weak.length][weak.length];
        
        for (int i = 0; i < weak.length; i++) {
            int idx = 0;
            for (int j = i; j < weak.length; j++) {
                weakCase[i][idx++] = weak[j];
            }
            for (int j = 0; j < i; j++) {
                weakCase[i][idx++] = n + weak[j];
            }
        }
        
        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= dist.length; i++) {
            this.visited = new boolean[dist.length];
            position = new int[i];
            permutation(0, i);
            if (result) {
                answer = i;
                break;
            }
        }
        return (answer == Integer.MAX_VALUE) ? -1 : answer;
    }
    
    public void permutation(int d, int f) {
        if (d == f) {
            isPossible(f);
            return;
        }
        for (int i = 0; i < dist.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            position[d] = dist[i];
            permutation(d + 1, f);
            visited[i] = false;
        }
    }
    
    public void isPossible(int num) {
        for (int[] wCase : weakCase) {
            boolean[] isDeleted = new boolean[wCase.length];
            int idx = 0;
            for (int i = 0; i < num; i++) {
                int start = wCase[idx];
                int end = wCase[idx] + position[i];
                for (int j = idx; j < wCase.length; j++) {
                    if (start <= wCase[j] && wCase[j] <= end) {
                        isDeleted[j] = true;
                        idx++;
                    }
                    else {
                        break;
                    }
                }
            }
            if (isAllDeleted(isDeleted)) {
                result = true;
                return;
            }
            
        }
        return;
    }
    
    boolean isAllDeleted(boolean[] isDeleted) {
        for (boolean b : isDeleted) {
            if (!b) {
                return false;
            }
        }
        return true;
    }
}