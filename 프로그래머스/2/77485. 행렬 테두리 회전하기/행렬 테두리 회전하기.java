class Solution {
    int[][] map;
    public int[] solution(int rows, int columns, int[][] queries) {
        map = new int[rows+1][columns+1];
        int idx = 1;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                map[i][j] = idx++;
            }
        }
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            answer[i] = rotate(query[0], query[1], query[2], query[3]);
        }
        return answer;
    }
    
    int rotate(int x1, int y1, int x2, int y2) {
        int tmp = map[x1][y1];
        int min = tmp;
        for (int nx = x1; nx < x2; nx++) {
            map[nx][y1] = map[nx+1][y1];
            min = Math.min(min, map[nx][y1]);
        }
        for (int ny = y1; ny < y2; ny++) {
            map[x2][ny] = map[x2][ny+1];
            min = Math.min(min, map[x2][ny]);
        }
        for (int nx = x2; x1 < nx; nx--) {
            map[nx][y2] = map[nx-1][y2];
            min = Math.min(min, map[nx][y2]);
        }
        for (int ny = y2; y1 < ny; ny--) {
            map[x1][ny] = map[x1][ny-1];
            min = Math.min(min, map[x1][ny]);
        }
        if (y1 == y2) {
            return min;
        }
        map[x1][y1+1] = tmp;
        return min;
    }
}