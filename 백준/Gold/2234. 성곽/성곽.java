import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class Main {
    static int[][] rooms;
    static int[][] info;
    static Map<Integer, Integer> spaces;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] bit = {1, 3, 0, 2};
    static int m, n;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        spaces = new HashMap<>();
        info = new int[m][n];
        rooms = new int[m][n];
        visited = new boolean[m][n];


        for (int i = 0; i < m; i++) {
            info[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }


        int[] answer = new int[3];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j]) {
                    continue;
                }
                answer[0]++;
                int space = dfs(i, j, answer[0]);
                spaces.put(answer[0], space);
                answer[1] = Math.max(answer[1], space);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (isOutOfRange(nx, ny)) {
                        continue;
                    }
                    if (rooms[i][j] == rooms[nx][ny]) {
                        continue;
                    }
                    answer[2] = Math.max(answer[2], spaces.get(rooms[i][j]) + spaces.get(rooms[nx][ny]));
                }
            }
        }

        System.out.println(answer[0]);
        System.out.println(answer[1]);
        System.out.println(answer[2]);
    }

    public static int dfs(int x, int y, int num) {
        if (visited[x][y]) {
            return 0;
        }
        visited[x][y] = true;
        rooms[x][y] = num;
        int result = 1;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isOutOfRange(nx, ny)) {
                continue;
            }
            if ((info[x][y] & 1 << bit[i]) == 1 << bit[i]) {
                continue;
            }
            result += dfs(nx, ny, num);
        }
        return result;
    }

    public static boolean isOutOfRange(int x, int y) {
        return x < 0 || m <= x || y < 0 || n <= y;
    }
}
