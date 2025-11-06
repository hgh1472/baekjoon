import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int m, n;
    static int[][] map;
    static int answer;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        m = Integer.parseInt(input[0]);
        n = Integer.parseInt(input[1]);

        map = new int[m][n];
        for (int i = 0; i < m; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        answer = 0;
        dfs(new boolean[m][n], 0);
        System.out.println(answer);
    }

    public static void dfs(boolean[][] visited, int score) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j]) {
                    continue;
                }
                visited[i][j] = true;
                dfs(visited, score + map[i][j]);
                int nx = i + 1;
                int add = map[i][j];
                while (inRange(nx, j) && !visited[nx][j]) {
                    visited[nx][j] = true;
                    add = add * 10 + map[nx][j];
                    dfs(visited, score + add);
                    nx++;
                }
                for (int k = i + 1; k < nx; k++) {
                    visited[k][j] = false;
                }
                add = map[i][j];
                int ny = j + 1;
                while (inRange(i, ny) && !visited[i][ny]) {
                    visited[i][ny] = true;
                    add = add * 10 + map[i][ny];
                    dfs(visited, score + add);
                    ny++;
                }
                for (int k = j + 1; k < ny; k++) {
                    visited[i][k] = false;
                }
                visited[i][j] = false;
                return;
            }
        }
        answer = Math.max(answer, score);
    }

    public static boolean inRange(int x, int y) {
        return !(x < 0 || m <= x || y < 0 || n <= y);
    }
}
