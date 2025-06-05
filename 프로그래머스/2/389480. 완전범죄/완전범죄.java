import java.util.*;

class Solution {
    static int n, m;
    static Set<String> visited = new HashSet<>();
    static int answer = 121;
    public int solution(int[][] info, int n, int m) {
        this.n = n;
        this.m = m;
        
        dfs(info, 0, 0, 0);
        return (answer == 121) ? -1 : answer;
        
    }
    
    public void dfs(int[][] info, int i, int a, int b) {
        if (a >= n || b >= m) {
            return;
        }
        if (i == info.length) {
            answer = Math.min(answer, a);
            return;
        }
        
        String first = String.valueOf(i).concat(",").concat(String.valueOf(a+info[i][0])).concat(",").concat(String.valueOf(b));
        if (!visited.contains(first)) {
            visited.add(first);
            dfs(info, i+1, a+info[i][0], b);
        }
        
        String second = String.valueOf(i).concat(",").concat(String.valueOf(a)).concat(",").concat(String.valueOf(b + info[i][1]));
        if (!visited.contains(second)) {
            visited.add(second);
            dfs(info, i+1, a, b+info[i][1]);
        }
    }
    
}