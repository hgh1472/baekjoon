import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int UP = 0, DOWN = 1, RIGHT = 2, LEFT = 3;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int r, c;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        r = input[0];
        c = input[1];
        int m = input[2];

        int[][] map = new int[r+1][c+1];
        for (int i = 0; i <= r; i++) {
            Arrays.fill(map[i], -1);
        }
        Shark[] sharks = new Shark[m];
        for (int i = 0; i < m; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            sharks[i] = new Shark(input[0], input[1], input[2], input[3]-1, input[4]);
            map[input[0]][input[1]] = i;
        }

        int answer = 0;
        for (int i = 1; i <= c; i++) {
            for (int j = 1; j <= r; j++) {
                if (map[j][i] != -1) {
                    answer += sharks[map[j][i]].size;
                    sharks[map[j][i]] = null;
                    map[j][i] = -1;
                    break;
                }
            }

            for (int j = 0; j <= r; j++) {
                Arrays.fill(map[j], -1);
            }
            for (int j = 0; j < m; j++) {
                if (sharks[j] == null) {
                    continue;
                }
                sharks[j].move();
            }
            for (int j = 0; j < m; j++) {
                Shark shark = sharks[j];
                if (shark == null) {
                    continue;
                }
                if (map[shark.x][shark.y] == -1) {
                    map[shark.x][shark.y] = j;
                } else {
                    if (sharks[map[shark.x][shark.y]].size > shark.size) {
                        sharks[j] = null;
                    }
                    else {
                        sharks[map[shark.x][shark.y]] = null;
                        map[shark.x][shark.y] = j;
                    }
                }
            }
        }

        System.out.println(answer);
    }

    static class Shark {
        int x, y;
        int speed;
        int dir;
        int size;

        public Shark(int x, int y, int speed, int dir, int size) {
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }

        public void move() {
            int count = speed;
            while (count != 0) {
                int next = 0;
                int nextDir = dir;
                if (dir == UP) {
                    next = Math.min(count, x-1);
                    if (next == x - 1) {
                        nextDir = DOWN;
                    }
                } else if (dir == DOWN) {
                    next = Math.min(count, r - x);
                    if (next == r - x) {
                        nextDir = UP;
                    }
                } else if (dir == LEFT) {
                    next = Math.min(count, y - 1);
                    if (next == y - 1) {
                        nextDir = RIGHT;
                    }
                } else {
                    next = Math.min(count, c - y);
                    if (next == c - y) {
                        nextDir = LEFT;
                    }
                }
                x = x + dx[dir] * next;
                y = y + dy[dir] * next;
                dir = nextDir;
                count -= next;
            }
        }
    }
}
