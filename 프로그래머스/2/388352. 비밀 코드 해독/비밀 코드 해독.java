import java.util.*;

class Solution {
    int[][] q;
    int[] ans;
    int answer = 0;
    public int solution(int n, int[][] q, int[] ans) {
        this.q = q;
        this.ans = ans;
        dfs(n, 0, 1, new int[5]);
        return answer;
    }
    
    public void dfs(int n, int depth, int index, int[] result) {
        if (depth == 5) {
            calculate(result);
            return;
        }
        for (int i = index; i <= n; i++) {
            result[depth] = i;
            dfs(n, depth+1, i, result);
        }
    }
    
    public void calculate(int[] result) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            set.add(result[i]);
        }
        if (set.size() != 5) {
            return;
        }
        for (int i = 0; i < q.length; i++) {
            int dup = 0;
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (q[i][j] == result[k]) {
                        dup++;
                    }
                }
            }
            if (dup != ans[i]) {
                return;
            }
        }
        answer++;
    }
  
}