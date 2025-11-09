import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] map;
    static int N, M, T;
    static int sum = 0;
    static int count = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        T = Integer.parseInt(input[2]);

        map = new int[N + 1][M + 1];
        count = N * M;
        for (int i = 1; i <= N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < input.length; j++) {
                map[i][j+1] = Integer.parseInt(input[j]);
                sum += map[i][j+1];
            }
        }

        for (int i = 0; i < T; i++) {
            input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int d = Integer.parseInt(input[1]);
            int k = Integer.parseInt(input[2]);

            for (int j = x; j <= N; j += x) {
                if (d == 0) {
                    rotateClockwise(j, k);
                } else {
                    rotateCounterclockwise(j, k);
                }
            }
            find();
        }
        System.out.println(sum);
    }

    static void find() {
        boolean find = false;
        boolean[][] visited = new boolean[N+1][M+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (visited[i][j]) {
                    continue;
                }
                if (map[i][j] == -1) {
                    continue;
                }
                if (bfs(i, j, visited)) {
                    find = true;
                }
            }
        }
        if (find) {
            return;
        }
        double average = (double) sum / count;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (map[i][j] == -1) {
                    continue;
                }
                if (map[i][j] > average) {
                    map[i][j]--;
                    sum--;
                }
                else if (map[i][j] < average) {
                    map[i][j]++;
                    sum++;
                }
            }
        }
    }

    static boolean bfs(int x, int y, boolean[][] visited) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(x, y));
        int value = map[x][y];
        List<Node> list = new ArrayList<>();

        while (!q.isEmpty()) {
            Node poll = q.poll();
            if (visited[poll.x][poll.y]) {
                continue;
            }
            visited[poll.x][poll.y] = true;
            list.add(poll);
            for (int i = 0; i < 4; i++) {
                int nx = poll.x + dx[i];
                int ny = poll.y + dy[i];
                if (ny == 0) {
                    ny = M;
                }
                if (ny == M + 1) {
                    ny = 1;
                }
                if (nx == 0 || nx == N + 1) {
                    continue;
                }
                if (map[nx][ny] != value) {
                    continue;
                }
                q.add(new Node(nx, ny));
            }
        }

        if (list.size() > 1) {
            for (Node node : list) {
                map[node.x][node.y] = -1;
            }
            sum -= value * list.size();
            count -= list.size();
            return true;
        }
        return false;
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    static void rotateCounterclockwise(int i, int k) {
        int[] change = new int[M+1];
        int idx = 1 + k;
        for (int j = 1; j <= M; j++) {
            if (idx > M) {
                idx = 1;
            }
            change[j] = map[i][idx];
            idx++;
        }
        map[i] = change;
    }

    static void rotateClockwise(int i, int k) {
        int[] change = new int[M+1];
        int idx = M + 1 - k;
        for (int j = 1; j <= M; j++) {
            if (idx > M) {
                idx = 1;
            }
            change[j] = map[i][idx];
            idx++;
        }
        map[i] = change;
    }
}
