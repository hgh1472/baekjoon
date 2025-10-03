class Solution {
    int[][] board;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        this.board = board;
        return Math.abs(dfsA(aloc[0], aloc[1], bloc[0], bloc[1])) - 1;
    }
    
    int dfsA(int ax, int ay, int bx, int by) {
        if (board[ax][ay] == 0) {
            return -1;
        }
        int result = 0;
        for (int i = 0; i < 4; i++) {
            int nx = ax + dx[i];
            int ny = ay + dy[i];
            if (impossible(nx, ny)) {
                continue;
            }
            board[ax][ay] = 0;
            int res = dfsB(nx, ny, bx, by);
            if (result == 0) {
                result = res;
            }
            else if (res < 0) { // 이길 수 있음
                if (result < 0) { // 이기는 다른 케이스가 있음
                    result = Math.max(result, res);
                }
                else {
                    result = res;
                }
            }
            else { // 지는 케이스
                if (result > 0) { // 지는 케이스밖에 없음
                    result = Math.max(result, res); // 최대한 늦게 지려고 노력
                }
            }
            board[ax][ay] = 1;
        }
        
        if (result == 0) {
            return -1;
        }
        if (result < 0) {
            return -result + 1;
        }
        else {
            return -result - 1;
        }
    }
    
    int dfsB(int ax, int ay, int bx, int by) {
        if (board[bx][by] == 0) {
            return -1;
        }
        int result = 0;
        for (int i = 0; i < 4; i++) {
            int nx = bx + dx[i];
            int ny = by + dy[i];
            if (impossible(nx, ny)) {
                continue;
            }
            
            board[bx][by] = 0;
            int res = dfsA(ax, ay, nx, ny);
            if (result == 0) {
                result = res;
            }
            else if (res < 0) { // 이길 수 있음
                if (result < 0) { // 이기는 다른 케이스가 있다.
                    result = Math.max(result, res);
                }
                else {
                    result = res;
                }
            }
            else { // 지는 상황
                if (result > 0) { // 이길 수 있는 케이스가 있다.
                    result = Math.max(result, res);
                }
            }
            board[bx][by] = 1;
        }
        
        if (result == 0) {
            return -1;
        }
        if (result < 0) {
            return -result + 1;
        }
        else {
            return -result - 1;
        }
    }
    
    boolean impossible(int nx, int ny) {
        if (nx < 0 || board.length <= nx || ny < 0 || board[0].length <= ny) {
            return true;
        }
        if (board[nx][ny] == 0) {
            return true;
        }
        return false;
    }
}