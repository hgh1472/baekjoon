import java.util.*;

class Solution {
    static int[][] edgeInfo;
    static int[] nodeInfo;
    static boolean[] visited;
    static int answer = 1;
    public int solution(int[] info, int[][] edges) {
        edgeInfo = edges;
        nodeInfo = info;
        visited = new boolean[info.length];
        visited[0] = true;
        dfs(1, 0);
        return answer;
    }
    
    public void dfs(int sheep, int wolf) {
        if (sheep <= wolf) {
            return;
        }
        answer = Math.max(answer, sheep);
        for (int[] edge : edgeInfo) {
            if (visited[edge[0]] && !visited[edge[1]]) {
                visited[edge[1]] = true;
                if (nodeInfo[edge[1]] == 0) {
                    dfs(sheep + 1, wolf);
                }
                else {
                    dfs(sheep, wolf + 1);
                }
                visited[edge[1]] = false;
            }
        }
    }
    
}