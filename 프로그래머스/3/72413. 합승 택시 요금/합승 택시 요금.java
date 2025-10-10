import java.util.*;

class Solution {
    int[][] distance;
    int NOT_CONNECT = 50000000;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        distance = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(distance[i], NOT_CONNECT);
            distance[i][i] = 0;
        }
        for (int[] fare : fares) {
            distance[fare[0]][fare[1]] = fare[2];
            distance[fare[1]][fare[0]] = fare[2];
        }
        
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (distance[i][k] == NOT_CONNECT || distance[k][j] == NOT_CONNECT) {
                        continue;
                    }
                    distance[i][j] = Math.min(distance[i][k] + distance[k][j], distance[i][j]);
                    distance[j][i] = distance[i][j];
                }
            }
        }
        
        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            int common = distance[s][i];
            if (common == NOT_CONNECT) {
                continue;
            }
            int cost1 = distance[i][a];
            int cost2 = distance[i][b];
            answer = Math.min(answer, common + cost1 + cost2);
        }
        return answer;
    }
}