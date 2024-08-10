import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    static int[][] sudoku = new int[9][9];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            sudoku[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        makeSudoku(0,0);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (j != 8) {
                    sb.append(sudoku[i][j] + " ");
                } else {
                    sb.append(sudoku[i][j] + "\n");
                }
            }
        }
        System.out.print(sb);
    }

    static boolean makeSudoku(int x, int y) {
        boolean[] isUsed = new boolean[10];
        int nx = x;
        int ny = y + 1;
        if (ny >= 9) {
            nx = x + 1;
            ny = 0;
        }
        if (sudoku[x][y] != 0) {
            if (x == 8 && y == 8) {
                return true;
            }
            return makeSudoku(nx, ny);
        }
        check(x, y, isUsed);
        for (int i = 1; i <= 9; i++) {
            if (isUsed[i])
                continue;
            sudoku[x][y] = i;
            if (x == 8 && y == 8) {
                return true;
            }
            if (makeSudoku(nx, ny)) {
                return true;
            }
        }
        sudoku[x][y] = 0;
        return false;
    }

    public static void check(int x, int y, boolean[] isUsed) {
        for (int i = 0; i < 9; i++) {
            isUsed[sudoku[x][i]] = true;
        }
        for (int i = 0; i < 9; i++) {
            isUsed[sudoku[i][y]] = true;
        }

        // 0 ~ 2, 3 ~ 5, 6 ~ 8
        int dx = x / 3 * 3;
        int dy = y / 3 * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                isUsed[sudoku[dx + i][dy + j]] = true;
            }
        }
    }
}
