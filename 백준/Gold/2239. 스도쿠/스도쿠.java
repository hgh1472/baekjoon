import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int[][] sudoku;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sudoku = new int[9][9];
        for (int i = 0; i <= 8; i++) {
            char[] charArray = br.readLine().toCharArray();
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = Integer.parseInt(String.valueOf(charArray[j]));
            }
        }
        dfs(0, 0);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j]);
            }
            System.out.println();
        }
    }

    public static boolean dfs(int x, int y) {
        int nx, ny;
        if (y == 8) {
            nx = x + 1;
            ny = 0;
        }
        else {
            nx = x;
            ny = y + 1;
        }
        if (sudoku[x][y] != 0) {
            if (x == 8 && y == 8)
                return true;
            return dfs(nx, ny);
        }
        else {
            boolean[] notPossibleNumbers = getNotPossibleNumbers(x, y);
            for (int i = 1; i <= 9; i++) {
                if (notPossibleNumbers[i])
                    continue;
                sudoku[x][y] = i;
                if (x == 8 && y == 8)
                    return true;
                if (dfs(nx, ny))
                    return true;
            }
            sudoku[x][y] = 0;
            return false;
        }
    }

    public static boolean[] getNotPossibleNumbers(int x, int y) {
        boolean[] isNotPossible = new boolean[10];
        int section = checkSection(x, y);
        getPossibleNumbersInSection(section, isNotPossible);
        getPossibleNumbersInLine(x, y, isNotPossible);
        return isNotPossible;
    }

    private static void getPossibleNumbersInLine(int x, int y, boolean[] isNotPossible) {
        for (int i = 0; i < 9; i++) {
            isNotPossible[sudoku[x][i]] = true;
        }
        for (int i = 0; i < 9; i++) {
            if (i == x)
                continue;
            isNotPossible[sudoku[i][y]] = true;
        }
    }

    private static void getPossibleNumbersInSection(int section, boolean[] isNotPossible) {
        if (section == 1) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++)
                    isNotPossible[sudoku[i][j]] = true;
            }
        }
        else if (section == 2) {
            for (int i = 0; i < 3; i++) {
                for (int j = 3; j < 5; j++)
                    isNotPossible[sudoku[i][j]] = true;
            }
        }
        else if (section == 3) {
            for (int i = 0; i < 3; i++) {
                for (int j = 6; j < 9; j++)
                    isNotPossible[sudoku[i][j]] = true;
            }
        }
        else if (section == 4) {
            for (int i = 3; i < 6; i++) {
                for (int j = 0; j < 3; j++)
                    isNotPossible[sudoku[i][j]] = true;
            }
        }else if (section == 5) {
            for (int i = 3; i < 6; i++) {
                for (int j = 3; j < 6; j++)
                    isNotPossible[sudoku[i][j]] = true;
            }
        }else if (section == 6) {
            for (int i = 3; i < 6; i++) {
                for (int j = 6; j < 9; j++)
                    isNotPossible[sudoku[i][j]] = true;
            }
        }else if (section == 7) {
            for (int i = 6; i < 9; i++) {
                for (int j = 0; j < 3; j++)
                    isNotPossible[sudoku[i][j]] = true;
            }
        }else if (section == 8) {
            for (int i = 6; i < 9; i++) {
                for (int j = 3; j < 6; j++)
                    isNotPossible[sudoku[i][j]] = true;
            }
        }else {
            for (int i = 6; i < 9; i++) {
                for (int j = 6; j < 9; j++)
                    isNotPossible[sudoku[i][j]] = true;
            }
        }
    }

    public static int checkSection(int x, int y) {
        if (x < 3) {
            if (y < 3)
                return 1;
            if (y < 6)
                return 2;
            return 3;
        }else if (x < 6) {
            if (y < 3)
                return 4;
            if (y < 6)
                return 5;
            return 6;
        }else {
            if (y < 3)
                return 7;
            if (y < 6)
                return 8;
            return 9;
        }
    }
}
