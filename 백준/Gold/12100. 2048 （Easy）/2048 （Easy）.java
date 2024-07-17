import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int n;
    static int[][] board;
    static int maxNum = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] s = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(s[j]);
                maxNum = Math.max(maxNum, board[i][j]);
            }
        }
        dfs(board, 0);
        System.out.println(maxNum);

//        int[][] copy;
//        System.out.println("up");
//        copy = up(board);
//        printBoard(copy);
//        System.out.println("----");
////
//        System.out.println("down");
//        copy = down(board);
//        printBoard(copy);
//        System.out.println("----");
//
//
//        System.out.println("left");
//        copy = left(board);
//        printBoard(copy);
//        System.out.println("----");
//
//        System.out.println("right");
//        copy = right(board);
//        printBoard(copy);
//        System.out.println("----");

    }

    public static void printBoard(int[][] map) {
        for (int[] b : map) {
            for (int i : b)
                System.out.print(i+" ");
            System.out.println();
        }
    }

    public static int[][] up(int[][] boards) {
        int[][] copy = new int[n][n];
        boolean[][] isCombined = new boolean[n][n];
        for (int i = 0; i < n; i++)
            copy[i] = boards[i].clone();
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int pos = i;
                while (pos >= 1) {
                    if (copy[pos-1][j] == 0) {
                        copy[pos-1][j] = copy[pos][j];
                        copy[pos][j] = 0;
                        isCombined[pos-1][j] = isCombined[pos][j];
                        isCombined[pos][j] = false;
                    }
                    else if (copy[pos][j] == 0)
                        break;
                    else if (copy[pos-1][j] == copy[pos][j] && !isCombined[pos-1][j] && !isCombined[pos][j]) {
                        copy[pos-1][j] *= 2;
                        maxNum = Math.max(maxNum, copy[pos-1][j]);
                        isCombined[pos-1][j] = true;
                        copy[pos][j] = 0;
                        isCombined[pos][j] = false;
                    }
                    else
                        break;
                    pos -= 1;
                }
            }
        }
        return copy;
    }

    public static int[][] down(int[][] boards) {
        int[][] copy = new int[n][n];
        boolean[][] isCombined = new boolean[n][n];
        for (int i = 0; i < n; i++)
            copy[i] = boards[i].clone();
        for (int i = n-2; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                int pos = i;
                while (pos <= n-2) {
                    if (copy[pos+1][j] == 0) {
                        copy[pos+1][j] = copy[pos][j];
                        copy[pos][j] = 0;
                        isCombined[pos+1][j] = isCombined[pos][j];
                        isCombined[pos][j] = false;
                    }
                    else if (copy[pos][j] == 0)
                        break;
                    else if (copy[pos+1][j] == copy[pos][j] && !isCombined[pos+1][j] && !isCombined[pos][j]) {
                        copy[pos+1][j] *= 2;
                        maxNum = Math.max(maxNum, copy[pos+1][j]);
                        isCombined[pos+1][j] = true;
                        copy[pos][j] = 0;
                        isCombined[pos][j] = false;
                    }
                    else
                        break;
                    pos++;
                }
            }
        }
        return copy;
    }

    public static int[][] left(int[][] boards) {
        int[][] copy = new int[n][n];
        boolean[][] isCombined = new boolean[n][n];
        for (int i = 0; i < n; i++)
            copy[i] = boards[i].clone();
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                int pos = j;
                while (pos >= 1) {
                    if (copy[i][pos - 1] == 0) {
                        copy[i][pos-1] = copy[i][pos];
                        copy[i][pos] = 0;
                        isCombined[i][pos-1] = isCombined[i][pos];
                        isCombined[i][pos] = false;
                    }
                    else if (copy[i][pos] == 0)
                        break;
                    else if (copy[i][pos-1] == copy[i][pos] && !isCombined[i][pos-1] && !isCombined[i][pos]) {
                        copy[i][pos-1] *= 2;
                        isCombined[i][pos-1] = true;
                        maxNum = Math.max(maxNum, copy[i][pos-1]);
                        copy[i][pos] = 0;
                        isCombined[i][pos] = false;
                    }
                    else
                        break;
                    pos--;
                }
            }
        }
        return copy;
    }

    public static int[][] right(int[][] boards) {
        int[][] copy = new int[n][n];
        boolean[][] isCombined = new boolean[n][n];
        for (int i = 0; i < n; i++)
            copy[i] = boards[i].clone();
        for (int i = 0; i < n; i++) {
            for (int j = n-2; j >= 0; j--) {
                int pos = j;
                while (pos <= n-2) {
                    if (copy[i][pos + 1] == 0) {
                        copy[i][pos+1] = copy[i][pos];
                        copy[i][pos] = 0;
                        isCombined[i][pos+1] = isCombined[i][pos];
                        isCombined[i][pos] = false;
                    }
                    else if (copy[i][pos] == 0)
                        break;
                    else if (copy[i][pos+1] == copy[i][pos] && !isCombined[i][pos+1] && !isCombined[i][pos]) {
                        copy[i][pos+1] *= 2;
                        isCombined[i][pos+1] = true;
                        maxNum = Math.max(maxNum, copy[i][pos+1]);
                        copy[i][pos] = 0;
                        isCombined[i][pos] = false;
                    }
                    else
                        break;
                    pos++;
                }
            }
        }
        return copy;
    }
    public static void dfs(int[][] boards, int stack) {
        if (stack == 5)
            return;
        dfs(up(boards), stack + 1);
        dfs(down(boards), stack + 1);
        dfs(left(boards), stack + 1);
        dfs(right(boards), stack + 1);
    }
}