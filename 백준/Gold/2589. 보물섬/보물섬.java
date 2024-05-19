import java.util.*;
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static char [][]map;
    static int dx[] = {-1 ,1, 0, 0};
    static int dy[] = {0 ,0, -1, 1};
    static int a, b;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        int minDistance = 0;
        map = new char[a][b];
        int[][] visited = new int[a][b];
        for (int i = 0; i < a; i++) {
            input = br.readLine();
            for (int j = 0; j < b; j++) {
                map[i][j] = input.charAt(j);
            }
        }
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                if (map[i][j] == 'L' && visited[i][j] == 0) {
                    visited[i][j] = 1;
                    int temp = bfs(i, j);
                    if (minDistance < temp) {
                        minDistance = temp;
                    }
                }
            }
        }
        System.out.println(minDistance);
    }

    public static int bfs(int x, int y) {
        int[][] visited = new int[a][b];
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x, y, 0));
        visited[x][y] = 1;
        int minDistance = 0;
        while (q.size() != 0) {
            Point poll = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = poll.x + dx[i];
                int ny = poll.y + dy[i];
                if ((0 <= nx && nx < a) && (0 <= ny && ny < b) && map[nx][ny] == 'L' && visited[nx][ny] == 0) {
                    q.offer(new Point(nx, ny, poll.distance + 1));
                    if (minDistance < poll.distance + 1) {
                        minDistance = poll.distance + 1;
                    }
                    visited[nx][ny] = 1;
                }
            }
        }
        return minDistance;
    }

    static class Point {
        int x;
        int y;
        int distance;
        Point(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}