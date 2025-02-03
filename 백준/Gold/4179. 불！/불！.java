import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int r;
    static int c;
    static char[][] info;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static PriorityQueue<Node> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        r = Integer.parseInt(input[0]);
        c = Integer.parseInt(input[1]);
        info = new char[r][c];

        q = new PriorityQueue<>();

        for (int i = 0; i < r; i++) {
            info[i] = br.readLine().toCharArray();
            for (int j = 0; j < c; j++) {
                if (info[i][j] == 'J') {
                    q.add(new Node(i, j, 'J', 0));
                    info[i][j] = '.';
                }
                if (info[i][j] == 'F') {
                    q.add(new Node(i, j, 'F', 0));
                    info[i][j] = '.';
                }
            }
        }
        int result = solution();
        if (result == -1) {
            System.out.println("IMPOSSIBLE");
            return;
        }
        System.out.println(result);
    }

    static int solution() {
        while (!q.isEmpty()) {
            Node n = q.poll();
            if (info[n.x][n.y] != '.') {
                continue;
            }
            info[n.x][n.y] = n.type;
            for (int i = 0; i < 4; i++) {
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];
                if (isOutOfBounds(nx, ny)) {
                    if (n.type == 'J') {
                        return n.time + 1;
                    }
                    continue;
                }
                if (info[nx][ny] != '.') {
                    continue;
                }
                q.add(new Node(nx, ny, n.type, n.time + 1));
            }
        }
        return -1;
    }

    static boolean isOutOfBounds(int x, int y) {
        return x < 0 || r <= x || y < 0 || c <= y;
    }

    static class Node implements Comparable<Node> {
        int x;
        int y;
        char type;
        int time;

        public Node(int x, int y, char type, int time) {
            this.x = x;
            this.y = y;
            this.type = type;
            this.time = time;
        }

        @Override
        public int compareTo(Node node) {
            if (this.time == node.time) {
                if (this.type == node.type) {
                    return 0;
                } else if (this.type == 'F') {
                    return -1;
                } else {
                    return 1;
                }
            }
            return this.time - node.time;
        }
    }
}
