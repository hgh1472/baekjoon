class Solution {
    int answer;
    public int solution(int[] info, int[][] edges) {
        boolean[] visited = new boolean[info.length];
        visited[0] = true;
        dfs(info, edges, visited, 1, 0);
        return answer;
    }
    
    public void dfs(int[] info, int[][] edges, boolean[] visited, int sheep, int wolf) {
        if (sheep <= wolf) {
            return;
        }
        answer = Math.max(sheep, answer);
        for (int[] edge : edges) {
            if (visited[edge[0]] && !visited[edge[1]]) {
                visited[edge[1]] = true;
                if (info[edge[1]] == 0) {
                    dfs(info, edges, visited, sheep + 1, wolf);
                }
                else {
                    dfs(info, edges, visited, sheep, wolf + 1);
                }
                visited[edge[1]] = false;
            }
        }
    }
}