import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int m, n, k;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        k = Integer.parseInt(input[2]);
        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        Dice dice = new Dice(1, 6, 2, 5, 3, 4, 3);
        int answer = 0;
        for (int i = 0; i < k; i++) {
            int nx = dice.x + dx[dice.dir];
            int ny = dice.y + dy[dice.dir];
            if (isOut(nx, ny)) {
                dice.reverse();
                nx = dice.x + dx[dice.dir];
                ny = dice.y + dy[dice.dir];
            }
            dice.roll();
            dice.x = nx;
            dice.y = ny;
            answer += map[dice.x][dice.y] * bfs(dice, map);
            if (dice.down > map[dice.x][dice.y]) {
                dice.rotate(true);
            } else if (dice.down < map[dice.x][dice.y]) {
                dice.rotate(false);
            }
        }
        System.out.println(answer);
    }

    static int bfs(Dice dice, int[][] map) {
        boolean[][] visited = new boolean[n][m];
        Queue<Node> q = new ArrayDeque<>();

        int value = map[dice.x][dice.y];
        q.add(new Node(dice.x, dice.y));
        int count = 0;
        while (!q.isEmpty()) {
            Node poll = q.poll();
            if (visited[poll.x][poll.y]) {
                continue;
            }
            visited[poll.x][poll.y] = true;
            count++;
            for (int i = 0; i < 4; i++) {
                int nx = poll.x + dx[i];
                int ny = poll.y + dy[i];
                if (isOut(nx, ny)) {
                    continue;
                }
                if (value != map[nx][ny]) {
                    continue;
                }
                q.add(new Node(nx, ny));
            }
        }
        return count;
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean isOut(int x, int y) {
        return x < 0 || n <= x || y < 0 || m <= y;
    }

    static class Dice {
        int x, y;
        int up, down;
        int north, south, east, west;
        int dir;

        public Dice(int up, int down, int north, int south, int east, int west, int dir) {
            this.x = 0;
            this.y = 0;
            this.up = up;
            this.down = down;
            this.north = north;
            this.south = south;
            this.east = east;
            this.west = west;
            this.dir = dir;
        }

        public void rotate(boolean isClock) {
            if (isClock) {
                if (this.dir == 0) {
                    this.dir = 3;
                } else if (this.dir == 1) {
                    this.dir = 2;
                } else if (this.dir == 2) {
                    this.dir = 0;
                } else {
                    this.dir = 1;
                }
            }
            else {
                if (this.dir == 0) {
                    this.dir = 2;
                } else if (this.dir == 1) {
                    this.dir = 3;
                } else if (this.dir == 2) {
                    this.dir = 1;
                } else {
                    this.dir = 0;
                }
            }
        }

        public void reverse() {
            if (this.dir == 0) {
                this.dir = 1;
            } else if (this.dir == 1) {
                this.dir = 0;
            } else if (this.dir == 2) {
                this.dir = 3;
            } else {
                this.dir = 2;
            }
        }

        public void roll() {
            if (this.dir == 0) {
                rollUp();
            } else if (this.dir == 1) {
                rollDown();
            } else if (this.dir == 2) {
                rollLeft();
            }
            else {
                rollRight();
            }
        }

        public void rollRight() {
            int tmp = up;
            up = west;
            west = down;
            down = east;
            east = tmp;
        }

        public void rollLeft() {
            int tmp = up;
            up = east;
            east = down;
            down = west;
            west = tmp;
        }

        public void rollUp() {
            int tmp = up;
            up = south;
            south = down;
            down = north;
            north = tmp;
        }

        public void rollDown() {
            int tmp = up;
            up = north;
            north = down;
            down = south;
            south = tmp;
        }
    }
}
