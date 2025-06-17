import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < stones.length; i++) {
            while (!q.isEmpty() && stones[q.getLast()] <= stones[i]) {
                q.removeLast();
            }
            q.addLast(i);
            if (q.getFirst() == i-k) {
                q.removeFirst();
            }
            if (i >= k-1) {
                answer = Math.min(answer, stones[q.getFirst()]);                
            }
        }
        return answer;
    }
    
}