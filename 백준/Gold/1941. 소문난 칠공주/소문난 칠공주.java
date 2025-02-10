import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static char[][] info = new char[5][5];
    static int answer = 0;
    static boolean[][] visited = new boolean[5][5];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            info[i] = br.readLine().toCharArray();
        }

        combination(new ArrayList<>(), 0, 7, 0, 0);
        System.out.println(answer);
    }

    static void combination(List<Point> girls, int d, int f, int x, int y) {
        if (d == f) {
            if (possible(girls)) {
                answer++;
            }
            return;
        }
        for (int i = x; i < 5; i++) {
            for (int j = (i == x) ? y : 0; j < 5; j++) {
                girls.add(new Point(i, j));
                visited[i][j] = true;
                combination(girls, d + 1, f, i, j + 1);
                visited[i][j] = false;
                girls.remove(d);
            }
        }
    }

    private static boolean possible(List<Point> girls) {
        int count = 0;
        Queue<Point> q = new ArrayDeque<>();
        boolean[][] isDuplicated = new boolean[5][5];
        isDuplicated[girls.get(0).x][girls.get(0).y] = true;
        q.add(girls.get(0));
        while (!q.isEmpty()) {
            Point g = q.poll();
            count++;
            for (int i = 0; i < 4; i++) {
                int nx = g.x + dx[i];
                int ny = g.y  +dy[i];
                if (nx < 0 || 5 <= nx || ny < 0 || 5 <= ny) {
                    continue;
                }
                if (visited[nx][ny] && !isDuplicated[nx][ny]) {
                    q.add(new Point(nx, ny));
                    isDuplicated[nx][ny] = true;
                }
            }
        }
        if (count != 7) {
            return false;
        }
        count = 0;
        for (Point girl : girls) {
            if (info[girl.x][girl.y] == 'S') {
                count++;
            }
        }
        if (count < 4) {
            return false;
        }
        return true;
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
