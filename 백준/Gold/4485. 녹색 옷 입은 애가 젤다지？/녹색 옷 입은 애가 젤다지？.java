import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

class Main {
    static int[] dx = {0, 0, -1 ,1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n;
        int c = 1;
        StringBuilder sb = new StringBuilder();
        while ((n = Integer.parseInt(br.readLine())) != 0) {
            int[][] info = new int[n][n];
            int[][] visited = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    visited[i][j] = 1000000000;
                }
            }
            for (int i = 0; i < n; i++) {
                info[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            PriorityQueue<Node> q = new PriorityQueue<>();
            q.add(new Node(0, 0, info[0][0]));
            visited[0][0] = info[0][0];
            while (!q.isEmpty()) {
                Node poll = q.poll();
                if (visited[poll.x][poll.y] < poll.cost)
                    continue;
                for (int i = 0; i < 4; i++) {
                    int nx = poll.x + dx[i];
                    int ny = poll.y + dy[i];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                        continue;
                    int newCost = poll.cost + info[nx][ny];
                    if (visited[nx][ny] > newCost) {
                        q.add(new Node(nx, ny, newCost));
                        visited[nx][ny] = newCost;
                    }
                }
            }
            sb.append("Problem " + c++ + ": " + visited[n-1][n-1] + "\n");
        }
        System.out.print(sb);
    }

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
