import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.*;

public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        boolean[][] isWhite = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < n; j++) {
                isWhite[i][j] = input.charAt(j) == '1';
            }
        }

        ArrayDeque<Node> q = new ArrayDeque<>();

        q.add(new Node(0, 0, 0));
        int answer = Integer.MAX_VALUE;
        boolean[][][] visited = new boolean[2 * n][n][n];
        for (int i = 0; i < 2* n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(visited[i][j], false);
            }
        }
        while (!q.isEmpty()) {
            Node poll = q.poll();
            if (visited[poll.count][poll.x][poll.y]) {
                continue;
            }
            if (poll.x == n - 1 && poll.y == n - 1) {
                answer = Math.min(answer, poll.count);
            }
            visited[poll.count][poll.x][poll.y] = true;
            for (int i = 0; i < 4; i++) {
                int nx = poll.x + dx[i];
                int ny = poll.y + dy[i];
                if (nx < 0 || n <= nx || ny < 0 || n <= ny) {
                    continue;
                }
                if (isWhite[nx][ny]) {
                    q.add(new Node(nx, ny, poll.count));
                }
                else {
                    if (poll.count + 1 >= 2 * n) {
                        continue;
                    }
                    q.add(new Node(nx, ny, poll.count + 1));
                }
            }
        }
        System.out.println(answer);
    }

    static class Node {
        int x, y;
        int count;

        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}
