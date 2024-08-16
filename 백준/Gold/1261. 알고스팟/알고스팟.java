import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Main {
    static int n, m;
    static char[][] info;
    static int[][] counts;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        m = Integer.parseInt(s[0]);
        n = Integer.parseInt(s[1]);
        info = new char[n][m];
        counts = new int[n][m];

        for (int i = 0; i < n; i++) {
            info[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                counts[i][j] = 10001;
            }
        }
        PriorityQueue<Node> q = new PriorityQueue<>();

        q.add(new Node(0, 0, 0));
        counts[0][0] = 0;

        while (!q.isEmpty()) {
            Node poll = q.poll();
            // 벽을 부순 횟수가 더 많다면 조사하지 않는다.
            if (counts[poll.x][poll.y] < poll.count) {
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int nx = poll.x + dx[i];
                int ny = poll.y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                int c = info[nx][ny] == '1' ? poll.count + 1 : poll.count;
                // 벽을 부순 횟수가 더 적은 경우가 이미 고려되었다면 삽입하지 않는다.
                if (c < counts[nx][ny]) {
                    counts[nx][ny] = c;
                    q.add(new Node(nx, ny, c));
                }
            }
        }
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < m; j++) {
        //         System.out.printf("%d ", counts[i][j]);
        //     }
        //     System.out.println();
        // }
        System.out.println(counts[n-1][m-1]);
    }

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int count;

        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public int compareTo(Node o) {
            return this.count - o.count;
        }
    }
}
