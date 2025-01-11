import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Main {
    static boolean[][] visited;
    static int r;
    static int c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        r = Integer.parseInt(input[0]);
        c = Integer.parseInt(input[1]);

        visited = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            String s = br.readLine();
            for (int j = 0; j < c; j++) {
                if (s.charAt(j) == 'x') {
                    visited[i][j] = true;
                }
            }
        }

        int count = 0;
        for (int i = 0; i < r; i++) {
            if (find(i, 0)) {
                count++;
            }
        }
        System.out.println(count);
    }

    public static boolean find(int x, int y) {
        visited[x][y] = true;
        boolean isFind = false;
        if (y == c - 1) {
            return true;
        }
        if (0 <= x - 1 && !visited[x - 1][y + 1]) {
            if (find(x - 1, y + 1)) {
                isFind = true;
            }
        } if (!isFind && !visited[x][y + 1]) {
            if (find(x, y + 1)) {
                isFind = true;
            }
        } if (!isFind && x + 1 < r && !visited[x + 1][y + 1]) {
            if (find(x + 1, y + 1)) {
                isFind = true;
            }
        }
        return isFind;
    }
}
