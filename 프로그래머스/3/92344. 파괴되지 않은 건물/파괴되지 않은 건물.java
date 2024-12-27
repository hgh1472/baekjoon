class Solution {
    public int solution(int[][] board, int[][] skill) {
        int[][] temp = new int[board.length + 1][board[0].length + 1];
        
        for (int i = 0; i < skill.length; i++) {
            if (skill[i][0] == 1) {
                temp[skill[i][1]][skill[i][2]] -= skill[i][5];
                temp[skill[i][3] + 1][skill[i][2]] += skill[i][5];
                temp[skill[i][1]][skill[i][4] + 1] += skill[i][5];
                temp[skill[i][3] + 1][skill[i][4] + 1] -= skill[i][5];
            }
            else {
                temp[skill[i][1]][skill[i][2]] += skill[i][5];
                temp[skill[i][3] + 1][skill[i][2]] -= skill[i][5];
                temp[skill[i][1]][skill[i][4] + 1] -= skill[i][5];
                temp[skill[i][3] + 1][skill[i][4] + 1] += skill[i][5];
            }
        }
        
        for (int i = 0; i < temp[0].length; i++) {
            int sum = temp[0][i];
            for (int j = 1; j < temp.length; j++) {
                sum += temp[j][i];
                temp[j][i] = sum;
            }
        }
        for (int i = 0; i < temp.length; i++) {
            int sum = temp[i][0];
            for (int j = 1; j < temp[i].length; j++) {
                sum += temp[i][j];
                temp[i][j] = sum;
            }
        }
        
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] += temp[i][j];
                if (board[i][j] >= 1) count++;
            }
        }
        
        return count;
    }
}