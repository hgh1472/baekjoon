import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static int n, m;
    static int count = 100;
    static char[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        board = new char[n][m];
        int[] red = new int[2];
        int[] blue = new int[2];
        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'R') {
                    red[0] = i;
                    red[1] = j;
                    board[i][j] = '.';
                }
                if (board[i][j] == 'B') {
                    blue[0] = i;
                    blue[1] = j;
                    board[i][j] = '.';
                }
            }
        }
        up(red, blue, 1);
        down(red, blue, 1);
        left(red, blue, 1);
        right(red, blue, 1);
        if (count == 100)
            System.out.println(-1);
        else
            System.out.println(count);
    }

    public static void up(int[] red, int[] blue, int stack) {
        boolean isGoal = false;
        int rx = red[0];
        int ry = red[1];
        int bx = blue[0];
        int by = blue[1];
        // 빨간 구슬이 먼저 위로 올려짐
        if (rx < bx) {
            while (board[rx - 1][ry] != '#') {
                rx--;
                if (board[rx][ry] == 'O') {
                    isGoal = true;
                    rx = ry = 0;
                    break;
                }
            }
            while (board[bx - 1][by] != '#') {
                if (rx == bx - 1 && ry == by)
                    break;
                bx--;
                if (board[bx][by] == 'O') {
                    return;
                }
            }
            if (isGoal) {
                count = Math.min(count, stack);
                return;
            }
        }
        else {
            while (board[bx - 1][by] != '#') {
                bx--;
                if (board[bx][by] == 'O')
                    return;
            }
            while (board[rx - 1][ry] != '#') {
                if (rx - 1 == bx && ry == by)
                    break;
                rx--;
                if (board[rx][ry] == 'O') {
                    count = Math.min(count, stack);
                    return;
                }
            }
        }
        // down, left, right
        if (stack <= 9) {
            down(new int[]{rx, ry}, new int[]{bx, by}, stack + 1);
            left(new int[]{rx, ry}, new int[]{bx, by}, stack + 1);
            right(new int[]{rx, ry}, new int[]{bx, by}, stack + 1);
        }
    }

    public static void down(int[] red, int[] blue, int stack) {
        // 빨간색 먼저 내려감
        int rx = red[0];
        int ry = red[1];
        int bx = blue[0];
        int by = blue[1];
        if (rx > bx) {
            boolean isGoal = false;
            while (board[rx + 1][ry] != '#') {
                rx++;
                if (board[rx][ry] == 'O') {
                    isGoal = true;
                    rx = ry = 0;
                    break;
                }
            }
            while (board[bx + 1][by] != '#') {
                if (rx == bx + 1 && ry == by)
                    break;
                bx++;
                if (board[bx][by] == 'O')
                    return;
            }
            if (isGoal) {
                count = Math.min(count, stack);
                return;
            }
        }
        else {
            while (board[bx + 1][by] != '#') {
                bx++;
                if (board[bx][by] == 'O')
                    return;
            }
            while (board[rx + 1][ry] != '#') {
                if (rx + 1 == bx && ry == by)
                    break;
                rx++;
                if (board[rx][ry] == 'O') {
                    count = Math.min(count, stack);
                    return;
                }
            }
        }
        // up, left, right
        if (stack <= 9) {
            up(new int[]{rx, ry}, new int[]{bx, by}, stack+1);
            left(new int[]{rx, ry}, new int[]{bx, by}, stack+1);
            right(new int[]{rx, ry}, new int[]{bx, by}, stack+1);
        }
    }

    public static void left(int[] red, int[] blue, int stack) {
        // 빨간색이 먼저 왼쪽으로 감
        int rx = red[0];
        int ry = red[1];
        int bx = blue[0];
        int by = blue[1];
        if (ry < by) {
            boolean isGoal = false;
            while (board[rx][ry - 1] != '#') {
                ry--;
                if (board[rx][ry] == 'O') {
                    isGoal = true;
                    rx = ry = 0;
                    break;
                }
            }
            while (board[bx][by - 1] != '#') {
                if (bx == rx && by-1 == ry)
                    break;
                by--;
                if (board[bx][by] == 'O')
                    return;
            }
            if (isGoal) {
                count = Math.min(count, stack);
                return;
            }
        }
        else {
            while (board[bx][by - 1] != '#') {
                by--;
                if (board[bx][by] == 'O')
                    return;
            }
            while (board[rx][ry-1] != '#') {
                if (rx == bx && ry - 1 == by)
                    break;
                ry--;
                if (board[rx][ry] == 'O') {
                    count = Math.min(count, stack);
                    return;
                }
            }
        }
        // up, down, right;
        if (stack <= 9) {
            up(new int[]{rx, ry}, new int[]{bx, by}, stack + 1);
            down(new int[]{rx, ry}, new int[]{bx, by}, stack + 1);
            right(new int[]{rx, ry}, new int[]{bx, by}, stack + 1);
        }
    }

    public static void right(int[] red, int[] blue, int stack) {
        // 빨간색이 먼저 오른쪽으로 감
        int rx = red[0];
        int ry = red[1];
        int bx = blue[0];
        int by = blue[1];
        if (ry > by) {
            boolean isGoal = false;
            while (board[rx][ry + 1] != '#') {
                ry++;
                if (board[rx][ry] == 'O') {
                    isGoal = true;
                    rx = ry = 0;
                    break;
                }
            }
            while (board[bx][by + 1] != '#') {
                if (rx == bx && ry == by + 1)
                    break;
                by++;
                if (board[bx][by] == 'O')
                    return;
            }
            if (isGoal) {
                count = Math.min(count, stack);
                return;
            }
        }
        else {
            while (board[bx][by + 1] != '#') {
                by++;
                if (board[bx][by] == 'O')
                    return;
            }
            while (board[rx][ry+1] != '#') {
                if (rx == bx && ry + 1 == by)
                    break;
                ry++;
                if (board[rx][ry] == 'O') {
                    count = Math.min(count, stack);
                    return;
                }
            }
        }
        // up, down, left
        if (stack <= 9) {
            up(new int[]{rx, ry}, new int[]{bx, by}, stack + 1);
            down(new int[]{rx, ry}, new int[]{bx, by}, stack + 1);
            left(new int[]{rx, ry}, new int[]{bx, by}, stack + 1);
        }
    }
}