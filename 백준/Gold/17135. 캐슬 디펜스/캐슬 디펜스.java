import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int d;
    static int answer = 0;
    static int[] dx = {-1, 0, 0};
    static int[] dy = {0, -1, 1};
    static int[][] info;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream((br.readLine().split(" "))).mapToInt(Integer::parseInt).toArray();
        n = input[0];
        m = input[1];
        d = input[2];
        info = new int[n][m];
        for (int i = 0; i < n; i++) {
            input = Arrays.stream((br.readLine().split(" "))).mapToInt(Integer::parseInt).toArray();
            info[i] = input;
        }

        combination(0, 3, new boolean[m], 0);
        System.out.println(answer);
    }

    static void combination(int d, int f, boolean[] visited, int last) {
        if (d == f) {
            int result = check(visited);
            answer = Math.max(answer, result);
            return;
        }
        for (int i = last; i < m; i++) {
            visited[i] = true;
            combination(d + 1, f, visited, i + 1);
            visited[i] = false;
        }
    }

    static int check(boolean[] visited) {
        boolean[][] deleted = new boolean[n][m];
        for (int i = n - 1; i >= 0; i--) {
            PriorityQueue<Point> q = new PriorityQueue<>();
            boolean[] isEnd = new boolean[3];
            List<Point> willBeDeleted = new ArrayList<>();
            int count = 0;
            for (int j = 0; j < m; j++) {
                if (visited[j]) {
                    q.add(new Point(i, j, 1, count));
                    count++;
                }
            }
            while (!q.isEmpty()) {
                Point p = q.poll();
                if (isEnd[p.arrow]) {
                    continue;
                }
                // 한 번의 턴에서 여러 궁수가 같은 상대를 맞출 수도 있다.
                // isDeleted => 이전 턴에서 죽였는지 확인
                // willBeDeleted => 제거 대상
                // 적이 있는 위치이고 아직 죽이지 않았다면, 제거 대상으로 등록 후 해당 궁수는 활동을 멈춘다.
                if (info[p.x][p.y] == 1 && !deleted[p.x][p.y]) {
                    willBeDeleted.add(p);
                    isEnd[p.arrow] = true;
                    continue;
                }
                if (p.distance == d) {
                    continue;
                }
                for (int j = 0; j < 3; j++) {
                    int nx = p.x + dx[j];
                    int ny = p.y + dy[j];
                    if (nx < 0 || n <= nx || ny < 0 || m <= ny) {
                        continue;
                    }
                    q.add(new Point(nx, ny, p.distance + 1, p.arrow));
                }
            }
            for (Point point : willBeDeleted) {
                deleted[point.x][point.y] = true;
            }
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (deleted[i][j]) {
                    result++;
                }
            }
        }
        return result;
    }


    static class Point implements Comparable<Point> {
        int x;
        int y;
        int distance;
        int arrow;

        public Point(int x, int y, int distance, int arrow) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.arrow = arrow;
        }

        @Override
        public int compareTo(Point o) {
            if (distance == o.distance) {
                return this.y - o.y;
            }
            return this.distance - o.distance;
        }
    }

}