import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[][] map = new char[12][6];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 12; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int count = 0;
        while (true) {
            boolean isBoom = false;
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (map[i][j] != '.') {
                        List<Node> nodes = find(i, j);
                        if (nodes.size() >= 4) {
                            for (Node node : nodes) {
                                map[node.x][node.y] = '.';
                            }
                            isBoom = true;
                        }
                    }
                }
            }

            if (!isBoom) {
                break;
            }
            count++;

            for (int i = 11; i >= 0; i--) {
                for (int j = 0; j < 6; j++) {
                    if (map[i][j] == '.') {
                        for (int k = i-1; k >= 0; k--) {
                            if (map[k][j] != '.') {
                                map[i][j] = map[k][j];
                                map[k][j] = '.';
                                break;
                            }
                        }
                    }
                }
            }
        }

        System.out.println(count);

    }

    static List<Node> find(int x, int y) {
        boolean[][] visited = new boolean[12][6];
        List<Node> nodes = new ArrayList<>();

        visited[x][y] = true;
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.add(new Node(x, y));
        while (!q.isEmpty()) {
            Node cur = q.poll();
            nodes.add(cur);
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= 12 || ny < 0 || ny >= 6) {
                    continue;
                }
                if (map[nx][ny] != map[x][y]) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                Node n = new Node(nx, ny);
                q.add(n);
                visited[nx][ny] = true;
            }
        }

        return nodes;
    }

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
