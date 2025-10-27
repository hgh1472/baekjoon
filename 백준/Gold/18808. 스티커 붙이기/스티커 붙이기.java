import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[][] board;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        int k = Integer.parseInt(input[2]);

        board = new int[n][m];

        for (int i = 0; i < k; i++) {
            input = br.readLine().split(" ");
            int[][] sticker = new int[Integer.parseInt(input[0])][Integer.parseInt(input[1])];
            for (int j = 0; j < sticker.length; j++) {
                sticker[j] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            find(sticker);
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 1) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    static void find(int[][] sticker) {
        int[][] tmp = sticker;
        for (int j = 0; j < 4; j++) {
            tmp = rotate(tmp, j);
            for (int x = 0; x < board.length; x++) {
                for (int y = 0; y < board[0].length; y++) {
                    if (possible(tmp, x, y)) {
                        attach(tmp, x, y);
                        return;
                    }
                }
            }
        }
    }

    static int[][] rotate(int[][] sticker, int mode) {
        if (mode == 0) {
            return sticker;
        }
        int[][] result = new int[sticker[0].length][sticker.length];
        for (int i = 0; i < sticker[0].length; i++) {
            for (int j = 0; j < sticker.length; j++) {
                result[i][j] = sticker[sticker.length - 1 - j][i];
            }
        }
        return result;
    }

    static void attach(int[][] sticker, int x, int y) {
        for (int i = 0; i < sticker.length; i++) {
            for (int j = 0; j < sticker[0].length; j++) {
                if (sticker[i][j] == 0) {
                    continue;
                }
                board[x + i][y + j] = 1;
            }
        }
    }

    static boolean possible(int[][] sticker, int x, int y) {
        for (int i = 0; i < sticker.length; i++) {
            for (int j = 0; j < sticker[0].length; j++) {
                if (sticker[i][j] == 0) {
                    continue;
                }
                if (board.length <= x + i || board[0].length <= y + j) {
                    return false;
                }
                if (board[x + i][y + j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
