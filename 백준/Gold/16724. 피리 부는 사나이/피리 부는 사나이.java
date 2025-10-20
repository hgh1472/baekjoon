import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static char[][] info;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        info = new char[n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                info[i][j] = s.charAt(j);
            }
        }

        map = new int[n][m];

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 0) {
                    continue;
                }
                int result = dfs(i, j, count + 1);
                if (result == count + 1) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }

    public static int dfs(int x, int y, int num) {
        if (map[x][y] != 0) {
            return map[x][y];
        }
        map[x][y] = num;
        int nx = x, ny = y;
        if (info[x][y] == 'U') {
            nx--;
        } else if (info[x][y] == 'D') {
            nx++;
        } else if (info[x][y] == 'L') {
            ny--;
        } else {
            ny++;
        }
        int result = dfs(nx, ny, num);
        if (num != result) {
            map[x][y] = result;
        }
        return result;
    }
}
