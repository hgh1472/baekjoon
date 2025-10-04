class Solution {
    public int solution(int[][] board, int[][] skill) {
        int[][] sums = new int[board.length][board[0].length];
        for (int[] s : skill) {
            int add = (s[0] == 1) ? -s[5] : s[5];
            sums[s[1]][s[2]] += add;
            if (s[3] + 1 < board.length) {
                sums[s[3]+1][s[2]] -= add;
            }
            if (s[4] + 1 < board[0].length) {
                sums[s[1]][s[4]+1] -= add;
            }
            if (s[3] + 1 < board.length && s[4] + 1 < board[0].length) {
                sums[s[3]+1][s[4]+1] += add;
            }
        }
        
        for (int i = 0; i < board.length; i++) {
            int sum = sums[i][0];
            for (int j = 1; j < board[0].length; j++) {
                sum += sums[i][j];
                sums[i][j] = sum;
            }
        }
        
        for (int i = 0; i < board[0].length; i++) {
            int sum = sums[0][i];
            for (int j = 1; j < board.length; j++) {
                sum += sums[j][i];
                sums[j][i] = sum;
            }
        }
        
        int answer = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] + sums[i][j] > 0) {
                    answer++;
                }
            }
        }
        return answer;
    }
}