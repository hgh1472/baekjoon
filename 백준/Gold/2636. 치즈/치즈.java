import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int row, col;
    static int[][] map;
    static int[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        row = Integer.parseInt(split[0]);
        col = Integer.parseInt(split[1]);
        map = new int[row][col];
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < row; i++) {
            split = br.readLine().split(" ");
            for (int j = 0; j < col; j++) {
                map[i][j] = Integer.parseInt(split[j]);
            }
        }
        int time = 0;
        while (true) {
            visited = new int[row][col];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (map[i][j] == 0 && visited[i][j] == 0) {
                        if (dfs(i, j) >= 1) {
                            dfs2(i, j);
                        }
                    }
                }
            }
            count1 = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (map[i][j] == 1) {
                        count1++;
                        for (int k = 0; k < 4; k++) {
                            int nx = i + dx[k];
                            int ny = j + dy[k];
                            if (visited[nx][ny] == 2) {
                                map[i][j] = 0;
                            }
                        }
                    }
                }
            }
            if (count1 == 0) {
                break;
            }
            time++;
            count2 = count1;

        }
        System.out.println(time);
        System.out.println(count2);
    }

    public static void dfs2(int x, int y) {
        visited[x][y] = 2;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= row || ny < 0 || ny >= col) {
                continue;
            }
            if (map[nx][ny] == 0 && visited[nx][ny] == 1) {
                dfs2(nx, ny);
            }
        }
    }

    public static int dfs(int x, int y) {
        int isAir = 0;
        visited[x][y] = 1;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= row || ny < 0 || ny >= col) {
                isAir++;
                continue;
            }
            if (map[nx][ny] == 0 && visited[nx][ny] == 0) {
                isAir += dfs(nx, ny);
            }
        }
        return isAir;
    }
}