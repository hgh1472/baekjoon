import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, k;
    static int RIGHT = 1, LEFT = 2, UP = 3, DOWN = 4;
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};
    static List<Node>[][] info;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        k = Integer.parseInt(input[1]);
        int[][] map = new int[n][n];
        info = new List[n][n];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < n; j++) {
                info[i][j] = new ArrayList<>();
            }
        }

        Node[] nodes = new Node[k];
        for (int i = 0; i < k; i++) {
            input = br.readLine().split(" ");
            nodes[i] = new Node(Integer.parseInt(input[0])-1, Integer.parseInt(input[1])-1, Integer.parseInt(input[2]));
            info[nodes[i].x][nodes[i].y].add(nodes[i]);
        }

        for (int i = 1; i <= 1000; i++) {
            for (int j = 0; j < k; j++) {
                Node node = nodes[j];
                List<Node> list = info[node.x][node.y];
                int idx = 0;
                while (list.get(idx) != node) {
                    idx++;
                }
                int nx = node.x + dx[node.direction];
                int ny = node.y + dy[node.direction];
                if (isOut(nx, ny) || map[nx][ny] == 2) {
                    node.reverse();
                    nx = node.x + dx[node.direction];
                    ny = node.y + dy[node.direction];
                    if (isOut(nx, ny) || map[nx][ny] == 2) {
                        continue;
                    }
                }
                int length = list.size();
                if (map[nx][ny] == 1) {
                    for (int k = length - 1; k >= idx; k--) {
                        Node n = list.get(k);
                        n.x = nx;
                        n.y = ny;
                        info[n.x][n.y].add(n);
                        list.remove(k);
                    }
                }
                else {
                    for (int k = idx; k < length; k++) {
                        Node n = list.get(idx);
                        n.x = nx;
                        n.y = ny;
                        info[n.x][n.y].add(n);
                        list.remove(idx);
                    }
                }
                if (info[nx][ny].size() >= 4) {
                    System.out.println(i);
                    return;
                }
            }
        }
        System.out.println(-1);
    }

    static boolean isOut(int x, int y) {
        return x < 0 || n <= x || y < 0 || n <= y;
    }

    static class Node {
        int x, y;
        int direction;

        public Node(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        public void reverse() {
            if (this.direction == LEFT) {
                this.direction = RIGHT;
            } else if (this.direction == RIGHT) {
                this.direction = LEFT;
            } else if (this.direction == UP) {
                this.direction = DOWN;
            } else {
                this.direction = UP;
            }
        }

        public void move() {
            this.x = this.x + dx[direction];
            this.y = this.y + dy[direction];
        }
    }
}
