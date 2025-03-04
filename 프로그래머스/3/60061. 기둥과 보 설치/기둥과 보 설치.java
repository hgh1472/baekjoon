import java.util.*;

class Solution {
    boolean[][] vertical;
    boolean[][] horizon;
    int n;
    public int[][] solution(int n, int[][] build_frame) {
        this.n = n;
        vertical = new boolean[n+1][n+1];
        horizon = new boolean[n+1][n+1];
        int count = 0;
        
        for (int[] build : build_frame) {
            int x = n - build[1];
            int y = build[0];
            
            if (build[2] == 0) {
                if (build[3] == 1) {
                    if (isPossibleVertical(x, y)) {
                        vertical[x][y] = true;
                        count++;
                    }
                }
                if (build[3] == 0) {
                    count--;
                    vertical[x][y] = false;
                    if (!check()) {
                        vertical[x][y] = true;
                        count++;
                    }
                }
            }
            else {
                if (build[3] == 1) {
                    if (isPossibleHorizon(x, y)) {
                        count++;
                        horizon[x][y] = true;
                    }
                }
                if (build[3] == 0) {
                    count--;
                    horizon[x][y] = false;
                    if (!check()) {
                        horizon[x][y] = true;
                        count++;
                    }
                }
            }
        }
        int idx = 0;
        int[][] answer = new int[count][3];
        for (int j = 0; j <= n; j++) {
            for (int i = n; i >= 0; i--) {
                if (vertical[i][j]) {
                    answer[idx][0] = j;
                    answer[idx][1] = n - i;
                    answer[idx][2] = 0;
                    idx++;
                }
                if (horizon[i][j]) {
                    answer[idx][0] = j;
                    answer[idx][1] = n - i;
                    answer[idx][2] = 1;
                    idx++;
                }
            }
        }
        return answer;
    }
    
    boolean isPossibleVertical(int x, int y) {
        if (x == n) {
            return true;
        }
        if (vertical[x+1][y]) {
            return true;
        }
        if (horizon[x][y]) {
            return true;
        }
        if (0 < y && horizon[x][y-1]) {
            return true;
        }
        return false;
    }
    
    boolean isPossibleHorizon(int x, int y) {
        if (vertical[x+1][y] || vertical[x+1][y+1]) {
            return true;
        }
        if ((0 < y && horizon[x][y-1]) && horizon[x][y+1]) {
            return true;
        }
        return false;
    }
    
    boolean check() {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (vertical[i][j] && !isPossibleVertical(i, j)) {
                    return false;
                }
                if (horizon[i][j] && !isPossibleHorizon(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
}