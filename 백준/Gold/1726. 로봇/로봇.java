import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    static int RIGHT = 1;
    static int LEFT = 2;
    static int DOWN = 3;
    static int UP = 4;
    static int[] dx = {0, 0, 0, 1, -1};
    static int[] dy = {0, 1, -1, 0, 0};

    static boolean[][][] visited;
    static boolean[][] isPossible;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int m = Integer.parseInt(input[0]);
        int n = Integer.parseInt(input[1]);

        isPossible = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                if (input[j].equals("0")) {
                    isPossible[i][j] = true;
                } else {
                    isPossible[i][j] = false;
                }
            }
        }

        visited = new boolean[m][n][5];
        PriorityQueue<Node> q = new PriorityQueue<>();
        input = br.readLine().split(" ");
        q.add(new Node(Integer.parseInt(input[0]) - 1, Integer.parseInt(input[1]) - 1, Integer.parseInt(input[2]), 0));

        input = br.readLine().split(" ");
        int endX = Integer.parseInt(input[0]) - 1;
        int endY = Integer.parseInt(input[1]) - 1;
        int endDir = Integer.parseInt(input[2]);

        while (!q.isEmpty()) {
            Node poll = q.poll();
            if (poll.x == endX && poll.y == endY && poll.dir == endDir) {
                System.out.println(poll.cost);
                return;
            }
            if (visited[poll.x][poll.y][poll.dir]) {
                continue;
            }
            visited[poll.x][poll.y][poll.dir] = true;
            int addX = dx[poll.dir];
            int addY = dy[poll.dir];
            if (!isOut(poll.x + addX, poll.y + addY)) {
                q.add(new Node(poll.x + addX, poll.y + addY, poll.dir, poll.cost + 1));
                if (!isOut(poll.x + 2 * addX, poll.y + 2 * addY)) {
                    q.add(new Node(poll.x + 2 * addX, poll.y + 2 * addY, poll.dir, poll.cost + 1));
                    if (!isOut(poll.x + 3 * addX, poll.y + 3 * addY)) {
                        q.add(new Node(poll.x + 3 * addX, poll.y + 3 * addY, poll.dir, poll.cost + 1));
                    }
                }
            }
            q.add(new Node(poll.x, poll.y, left(poll.dir), poll.cost + 1));
            q.add(new Node(poll.x, poll.y, right(poll.dir), poll.cost + 1));
        }
        System.out.println(-1);
    }

    static int left(int dir) {
        if (dir == 1) {
            return 4;
        }
        if (dir == 2) {
            return 3;
        }
        if (dir == 3) {
            return 1;
        }
        return 2;
    }

    static int right(int dir) {
        if (dir == 1) {
            return 3;
        }
        if (dir == 2) {
            return 4;
        }
        if (dir == 3) {
            return 2;
        }
        return 1;
    }

    static boolean isOut(int x, int y) {
        if (x < 0 || isPossible.length <= x || y < 0 || isPossible[0].length <= y) {
            return true;
        }
        return !isPossible[x][y];
    }

    static class Node implements Comparable<Node> {
        int x, y;
        int dir;
        int cost;

        public Node(int x, int y, int dir, int cost) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node node) {
            return this.cost - node.cost;
        }
    }
}
