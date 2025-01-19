import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int h;
    static int m;

    static boolean[][] ladder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream((br.readLine().split(" "))).mapToInt(Integer::parseInt).toArray();
        n = input[0];
        m = input[1];
        h = input[2];

        ladder = new boolean[h + 1][2 * n];
        for (int j = 1; j < 2 * n; j += 2) {
            for (int i = 1; i <= h; i++) {
                ladder[i][j] = true;
            }
        }
        for (int i = 0; i < m; i++) {
            input = Arrays.stream((br.readLine().split(" "))).mapToInt(Integer::parseInt).toArray();
            ladder[input[0]][2 * input[1]] = true;
        }

        if (execute()) {
            System.out.println(0);
            return;
        }
        for (int i = 1; i <= 3; i++) {
            if (dfs(0, i, 1, 2)) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }

    public static boolean dfs(int d, int f, int x, int y) {
        if (d == f) {
            return execute();
        }

        for (int i = x; i <= h; i++) {
            int ny = (i == x) ? y : 2;
            for (int j = ny; j <= 2 * (n - 1); j += 2) {
                if (2 < j && ladder[i][j - 2]) continue;
                if (j < 2 * (n - 1) && ladder[i][j + 2]) continue;
                if (ladder[i][j])
                    continue;
                ladder[i][j] = true;
                if (dfs(d + 1, f, x, y + 2))
                    return true;
                ladder[i][j] = false;
            }
        }
        return false;
    }

    public static boolean execute() {
        for (int j = 1; j < 2 * n; j += 2) {
            int start = j;
            int y = j;
            int x = 1;
            for (int i = 1; i <= h; i++) {
                if (y + 1 < 2 * n && ladder[i][y + 1]) {
                    y += 2;
                }
                else if (2 <= y - 1 && ladder[i][y - 1]) {
                    y -= 2;
                }
            }
            if (start != y) {
                return false;
            }
        }
        return true;
    }
}
