import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, k;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int answer = Integer.MAX_VALUE;
    static boolean[][] isWall;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = input[0];
        m = input[1];
        k = input[2];

        isWall = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            char[] charArray = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                isWall[i][j] = (charArray[j] == '1');
            }
        }

        visited = new boolean[k+1][n][m];

        bfs();

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    static void bfs() {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(0, 0, 1, 0));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();
            if (node.x == n - 1 && node.y == m - 1) {
                answer = Math.min(answer, node.dist);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx < 0 || n <= nx || ny < 0 || m <= ny) {
                    continue;
                }

                if (isWall[nx][ny]) {
                    if (node.layer == k) {
                        continue;
                    }
                    if (visited[node.layer + 1][nx][ny]) {
                        continue;
                    }
                    q.add(new Node(nx, ny, node.dist + 1, node.layer + 1));
                    visited[node.layer + 1][nx][ny] = true;
                }
                else {
                    if (visited[node.layer][nx][ny]) {
                        continue;
                    }
                    q.add(new Node(nx, ny, node.dist + 1, node.layer));
                    visited[node.layer][nx][ny] = true;
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int dist;
        int layer;

        public Node(int x, int y, int dist, int layer) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.layer = layer;
        }

        @Override
        public int compareTo(Node n) {
            return this.dist - n.dist;
        }
    }

}
