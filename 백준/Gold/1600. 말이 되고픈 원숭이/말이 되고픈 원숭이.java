import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

class Main {
    static int[] dx = {0, 0, -1 ,1};
    static int[] dy = {1, -1, 0, 0};
    static int[] hx = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] hy = {-1, 1, -2, 2, -2, 2, -1, 1};
    static int[][] info;
    static boolean[][][] visited;
    static int w, h;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());
        String[] s = br.readLine().split(" ");
        w = Integer.parseInt(s[0]);
        h = Integer.parseInt(s[1]);

        if (w == 1 && h == 1) {
            System.out.println(0);
            return;
        }
        info = new int[h][w];
        visited = new boolean[h][w][k + 1];

        for (int i = 0; i < h; i++) {
            info[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(0, 0, 0, k));
        while (!q.isEmpty()) {
            Node poll = q.poll();
            if (visited[poll.x][poll.y][poll.horse]) {
                continue;
            }
            visited[poll.x][poll.y][poll.horse] = true;
            for (int i = 0; i < 4; i++) {
                int nx = poll.x + dx[i];
                int ny = poll.y + dy[i];
                if (!isPossible(nx ,ny)) {
                    continue;
                }
                if (visited[nx][ny][poll.horse]) {
                    continue;
                }
                q.add(new Node(nx, ny, poll.count + 1, poll.horse));
                if (nx == h - 1 && ny == w - 1) {
                    System.out.println(poll.count + 1);
                    return;
                }
            }
            if (poll.horse == 0) {
                continue;
            }
            for (int i = 0; i < 8; i++) {
                int nx = poll.x + hx[i];
                int ny = poll.y + hy[i];
                if (!isPossible(nx, ny)) {
                    continue;
                }
                if (visited[nx][ny][poll.horse - 1]) {
                    continue;
                }
                q.add(new Node(nx, ny, poll.count + 1, poll.horse - 1));
                if (nx == h - 1 && ny == w - 1) {
                    System.out.println(poll.count + 1);
                    return;
                }
            }
        }
        System.out.println(-1);
    }

    static boolean isPossible(int x, int y) {
        if (x < 0 || h <= x || y < 0 || w <= y) {
            return false;
        }
        if (info[x][y] == 1) {
            return false;
        }
        return true;
    }

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int count;
        int horse;

        public Node(int x, int y, int count, int horse) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.horse = horse;
        }

        @Override
        public int compareTo(Node o) {
            return this.count - o.count;
        }
    }
}
