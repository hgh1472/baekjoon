class Solution {
    static int[][] globalBoard;
    static int[] dx = new int[]{0, 0, 1, -1};
    static int[] dy = new int[]{-1,1,0,0};
    static int m, n;
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        globalBoard = board;
        m = board.length;
        n = board[0].length;
        int ans = dfs(aloc[0], aloc[1], bloc[0], bloc[1]);
        return ans;
    }
    
    public int dfs(int x, int y, int r, int c) {
        int result = 0;
        if (globalBoard[x][y] == 0)
            return 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (0 > nx || nx >= m || 0 > ny || ny >= n || globalBoard[nx][ny] == 0)
                continue;
            globalBoard[x][y] = 0;
            int val = dfs(r, c, nx, ny) + 1;
            globalBoard[x][y] = 1;
            
            if (val % 2 == 0 && result % 2 == 0)
                result = Math.max(result, val);
            else if (val % 2 == 1 && result % 2 == 0)
                result = val;
            else if (val % 2 == 1 && result % 2 == 1)
                result = Math.min(result, val);
        }
        return result;
    }
}