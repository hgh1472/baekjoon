import java.util.*;

class Solution {
    int answer = Integer.MAX_VALUE;
    int stage;
    int[][] cost;
    int[][] hint;
    
    public int solution(int[][] cost, int[][] hint) {
        stage = cost.length;
        this.cost = cost;
        this.hint = hint;
        
        next(0, 0, new int[cost.length]);
        return answer;
    }
    
    void next(int idx, int c, int[] count) {
        if (idx == stage) {
            answer = Math.min(answer, c);
            return;
        }
        
        int hintCount = Math.min(cost[idx].length - 1, count[idx]);
        int nextCost = c + cost[idx][hintCount];
        int purchase = 0;
        // 구매
        if (idx != stage - 1) {
            purchase = hint[idx][0];
            for (int i = 1; i < hint[idx].length; i++) {
                count[hint[idx][i] - 1]++;
            }
        }
        next(idx + 1, nextCost + purchase, count);
        
        if (idx != stage - 1) {
            for (int i = 1; i < hint[idx].length; i++) {
                count[hint[idx][i] - 1]--;
            }
        }
        
        next(idx + 1, nextCost, count);
    }
}