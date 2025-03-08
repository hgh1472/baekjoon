class Solution {
    int[][] key;
    int[][] lock;
    
    int[][] need1;
    int[][] need2;
    int[][] need3;
    int[][] need4;
    int x, y;
    
    public boolean solution(int[][] key, int[][] lock) {
        this.key = key;
        this.lock = lock;
        
        int n = lock.length;
        int m = key.length;
        
        int minX = n;
        int maxX = 0;
        int minY = n;
        int maxY = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (lock[i][j] == 0) {
                    minX = Math.min(minX, i);
                    maxX = Math.max(maxX, i);
                    minY = Math.min(minY, j);
                    maxY = Math.max(maxY, j);
                }
            }
        }
        
        if (maxX < minX) {
            return true;
        }
        x = maxX - minX + 1;
        y = maxY - minY + 1;
        
        need1 = new int[x][y];
        need2 = new int[y][x];
        need3 = new int[x][y];
        need4 = new int[y][x];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (lock[i][j] == 0) {
                    need1[i - minX][j - minY] = 1;
                }
            }
        }
        
        
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                need2[i][j] = need1[j][y - 1 - i];
            }
        }
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                need3[i][j] = need1[x - 1 - i][y - 1 -j];
            }
        }
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                need4[i][j] = need2[y - 1 - i][x - 1 -j];
            }
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (i + x <= m && j + y <= m) {
                    if (checkNeed1(i, j)) {
                        return true;
                    }
                    if (checkNeed3(i, j)) {
                        return true;
                    }
                }
                if (i + y <= m && j + x <= m) {
                    if (checkNeed2(i, j)) {
                        return true;
                    }
                    if (checkNeed4(i, j)) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    boolean checkNeed1(int row, int col) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (key[row + i][col + j] != need1[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    boolean checkNeed2(int row, int col) {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (key[row + i][col + j] != need2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    boolean checkNeed3(int row, int col) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (key[row + i][col + j] != need3[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    boolean checkNeed4(int row, int col) {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (key[row + i][col + j] != need4[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}