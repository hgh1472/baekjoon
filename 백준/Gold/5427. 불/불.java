import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;

public class Main {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int w = input[0];
            int h = input[1];

            char[][] map = new char[h][w];
            for (int j = 0; j < h; j++) {
                map[j] = br.readLine().toCharArray();
            }

            int result = dfs(map, w, h);
            if (result == -1) {
                System.out.println("IMPOSSIBLE");
            }
            else {
                System.out.println(result);
            }
        }

    }

    static int dfs(char[][] map, int w, int h) {
        boolean[][] visited = new boolean[h][w];
        PriorityQueue<Point> fire = new PriorityQueue<>();
        PriorityQueue<Point> person = new PriorityQueue<>();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (map[i][j] == '*') {
                    fire.add(new Point(i, j, 0));
                }
                if (map[i][j] == '@') {
                    person.add(new Point(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }

        int count = 0;
        while (!person.isEmpty()) {
            while (!fire.isEmpty() && fire.peek().count == count) {
                Point pop = fire.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = pop.x + dx[i];
                    int ny = pop.y + dy[i];

                    if (isOutRange(nx, ny, w, h)) {
                        continue;
                    }
                    if (!isEmptySpace(map, nx, ny)) {
                        continue;
                    }
                    fire.add(new Point(nx, ny, count + 1));
                    map[nx][ny] = '*';
                }
            }

            while (!person.isEmpty() && person.peek().count == count) {
                Point pop = person.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = pop.x + dx[i];
                    int ny = pop.y + dy[i];

                    if (isOutRange(nx, ny, w, h)) {
                        return count + 1;
                    }
                    if (!isEmptySpace(map, nx, ny)) {
                        continue;
                    }
                    if (visited[nx][ny]) {
                        continue;
                    }
                    person.add(new Point(nx, ny, count + 1));
                    visited[nx][ny] = true;
                }
            }
            count++;
        }
        return -1;
    }

    static boolean isOutRange(int x, int y, int w, int h) {
        return x < 0 || x >= h || y < 0 || y >= w;
    }

    static boolean isEmptySpace(char[][] map, int x, int y) {
        return map[x][y] == '.';
    }

    static class Point implements Comparable<Point> {
        int x, y;
        int count;

        public Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public int compareTo(Point o) {
            return this.count - o.count;
        }
    }
}
