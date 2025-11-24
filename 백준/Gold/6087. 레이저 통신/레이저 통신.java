import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * BFS
 * 같은 위치에 변화 수가 더 많은 채로 방문 -> 제거
 * 같은 위치에 변화수가 같은 채로 방문 -> 같은 방향으로 방문했을 때만 제거
 */
public class Main {
    static int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[] dir = {'U', 'D', 'L', 'R'};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int w = Integer.parseInt(input[0]);
        int h = Integer.parseInt(input[1]);

        char[][] map = new char[h][w];
        int startX = 0, startY = 0, endX = 0, endY = 0;
        boolean flag = false;
        for (int i = 0; i < h; i++) {
            String s = br.readLine();
            for (int j = 0; j < w; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'C') {
                    if (!flag) {
                        startX = i;
                        startY = j;
                        flag = true;
                    } else {
                        endX = i;
                        endY = j;
                    }
                }
            }
        }

        PriorityQueue<Node> q = new PriorityQueue<>();
        int[][] visited = new int[h][w];
        char[][] directions = new char[h][w];
        visited[startX][startY] = -1;
        for (int i = 0; i < 4; i++) {
            int nx = startX + dx[i];
            int ny = startY + dy[i];
            if (isOut(nx, ny, w, h)) {
                continue;
            }
            if (map[nx][ny] == '*') {
                continue;
            }
            q.add(new Node(nx, ny, i, 0));
            directions[nx][ny] = dir[i];
            visited[nx][ny] = 0;
        }

        for (int i = 0; i < h; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }
        while (!q.isEmpty()) {
            Node poll = q.poll();
            if (visited[poll.x][poll.y] < poll.change) {
                continue;
            }
            if (visited[poll.x][poll.y] == poll.change && directions[poll.x][poll.y] == dir[poll.direction]) {
                continue;
            }
            visited[poll.x][poll.y] = poll.change;
            directions[poll.x][poll.y] = dir[poll.direction];
            for (int i = 0; i < 4; i++) {
                if (isReverse(poll.direction, i)) {
                    continue;
                }
                int nx = poll.x + dx[i];
                int ny = poll.y + dy[i];
                if (isOut(nx, ny, w, h)) {
                    continue;
                }
                if (map[nx][ny] == '*') {
                    continue;
                }
                int change = poll.change;
                if (poll.direction != i) {
                    change++;
                }
                if (visited[nx][ny] < poll.change) {
                    continue;
                }
                q.add(new Node(nx, ny, i, change));
            }
        }

        System.out.println(visited[endX][endY]);
    }

    static boolean isReverse(int from, int to) {
        if (from == UP && to == DOWN) {
            return true;
        }
        if (from == DOWN && to == UP) {
            return true;
        }
        if (from == LEFT && to == RIGHT) {
            return true;
        }
        if (from == RIGHT && to == LEFT) {
            return true;
        }
        return false;
    }

    static boolean isOut(int x, int y, int w, int h) {
        return x < 0 || h <= x || y < 0 || w <= y;
    }

    static class Node implements Comparable<Node> {
        int x, y;
        int direction;
        int change;

        public Node(int x, int y, int direction, int change) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.change = change;
        }

        @Override
        public int compareTo(Node o) {
            return this.change - o.change;
        }
    }
}
