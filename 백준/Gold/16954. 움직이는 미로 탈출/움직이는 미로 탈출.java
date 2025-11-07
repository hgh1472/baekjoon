import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dx = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] map = new char[8][8];
        boolean[][][] visited = new boolean[500][8][8];
        List<Node> walls = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            String s = br.readLine();
            for (int j = 0; j < 8; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == '#') {
                    walls.add(new Node(0, i, j));
                }
            }
        }

        Deque<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 7, 0));

        int level = 1;
        while (!q.isEmpty()) {
            while (!q.isEmpty() && q.peek().level < level) {
                Node poll = q.poll();
                if (map[poll.x][poll.y] == '#') {
                    continue;
                }
                if (visited[poll.level][poll.x][poll.y]) {
                    continue;
                }
                visited[poll.level][poll.x][poll.y] = true;
                for (int i = 0; i < 9; i++) {
                    int nx = poll.x + dx[i];
                    int ny = poll.y + dy[i];
                    if (isOut(nx, ny)) {
                        continue;
                    }
                    if (map[nx][ny] == '#') {
                        continue;
                    }
                    if (nx == 0 && ny == 7) {
                        System.out.println(1);
                        return;
                    }
                    q.add(new Node(poll.level + 1, nx, ny));
                }
            }

            for (Node wall : walls) {
                if (wall.x <= 7) {
                    map[wall.x][wall.y] = '.';
                }
                wall.x++;
            }
            for (Node wall : walls) {
                if (wall.x <= 7) {
                    map[wall.x][wall.y] = '#';
                }
            }
            level++;
        }
        System.out.println(0);
    }

    static boolean isOut(int x, int y) {
        return x < 0 || 8 <= x || y < 0 || 8 <= y;
    }

    static class Node {
        int level;
        int x, y;

        public Node(int level, int x, int y) {
            this.level = level;
            this.x = x;
            this.y = y;
        }
    }
}
