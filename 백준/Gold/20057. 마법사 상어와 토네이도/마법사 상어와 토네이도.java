import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int answer = 0;
    static int n;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        Tornado tornado = new Tornado(n / 2, n / 2, 0, 1, 0);

        while (!(tornado.x == 0 && tornado.y == 0)) {
            int direction = tornado.direction;
            tornado.move();
            blowSand(tornado.x, tornado.y, direction, map[tornado.x][tornado.y]);
        }

        System.out.println(answer);

    }

    static private void blowSand(int x, int y, int direction, int sand) {
        if (direction == 0) {
            blowSandLeft(x, y, sand);
            return;
        }
        if (direction == 1) {
            blowSandDown(x, y, sand);
            return;
        }
        if (direction == 2) {
            blowSandRight(x, y, sand);
            return;
        }
        blowSandUp(x, y, sand);
    }

    static private void blowSandLeft(int x, int y, int sand) {
        moveSand(x - 2, y, sand * 2 / 100);
        moveSand(x + 2, y, sand * 2 / 100);
        moveSand(x - 1, y, sand * 7 / 100);
        moveSand(x + 1, y, sand * 7 / 100);
        moveSand(x - 1, y - 1, sand * 10 / 100);
        moveSand(x + 1, y - 1, sand * 10 / 100);
        moveSand(x - 1, y + 1, sand / 100);
        moveSand(x + 1, y + 1, sand / 100);
        moveSand(x, y - 2, sand * 5 / 100);
        moveSand(x, y - 1, sand - ((sand * 2 / 100) * 2 + (sand * 7 / 100) * 2 + (sand * 10 / 100) * 2 + sand / 100 * 2 + sand * 5 / 100));
    }

    static private void blowSandRight(int x, int y, int sand) {
        moveSand(x - 2, y, sand * 2 / 100);
        moveSand(x + 2, y, sand * 2 / 100);
        moveSand(x - 1, y, sand * 7 / 100);
        moveSand(x + 1, y, sand * 7 / 100);
        moveSand(x - 1, y + 1, sand * 10 / 100);
        moveSand(x + 1, y + 1, sand * 10 / 100);
        moveSand(x - 1, y - 1, sand / 100);
        moveSand(x + 1, y - 1, sand / 100);
        moveSand(x, y + 2, sand * 5 / 100);
        moveSand(x, y + 1, sand - ((sand * 2 / 100) * 2 + (sand * 7 / 100) * 2 + (sand * 10 / 100) * 2 + sand / 100 * 2 + sand * 5 / 100));
    }


    static private void blowSandUp(int x, int y, int sand) {
        moveSand(x, y - 2, sand * 2 / 100);
        moveSand(x, y + 2, sand * 2 / 100);
        moveSand(x, y - 1, sand * 7 / 100);
        moveSand(x, y + 1, sand * 7 / 100);
        moveSand(x - 1, y - 1, sand * 10 / 100);
        moveSand(x - 1, y + 1, sand * 10 / 100);
        moveSand(x + 1, y - 1, sand / 100);
        moveSand(x + 1, y + 1, sand / 100);
        moveSand(x - 2, y, sand * 5 / 100);
        moveSand(x - 1, y, sand - ((sand * 2 / 100) * 2 + (sand * 7 / 100) * 2 + (sand * 10 / 100) * 2 + sand / 100 * 2 + sand * 5 / 100));
    }


    static private void blowSandDown(int x, int y, int sand) {
        moveSand(x, y - 2, sand * 2 / 100);
        moveSand(x, y + 2, sand * 2 / 100);
        moveSand(x, y - 1, sand * 7 / 100);
        moveSand(x, y + 1, sand * 7 / 100);
        moveSand(x + 1, y - 1, sand * 10 / 100);
        moveSand(x + 1, y + 1, sand * 10 / 100);
        moveSand(x - 1, y - 1, sand / 100);
        moveSand(x - 1, y + 1, sand / 100);
        moveSand(x + 2, y, sand * 5 / 100);
        moveSand(x + 1, y, sand - ((sand * 2 / 100) * 2 + (sand * 7 / 100) * 2 + (sand * 10 / 100) * 2 + sand / 100 * 2 + sand * 5 / 100));
    }

    static private void moveSand(int x, int y, int sand) {
        if (x < 0 || n <= x || y < 0 || n <= y) {
            answer += sand;
            return;
        }
        map[x][y] += sand;
    }

    static class Tornado {
        int x, y;
        int direction;
        int size;
        int count;

        public Tornado(int x, int y, int direction, int size, int count) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.size = size;
            this.count = count;
        }

        public void move() {
            x = x + dx[direction];
            y = y + dy[direction];
            count++;
            if (count == size) {
                if (direction == 1 || direction == 3) {
                    size++;
                }
                count = 0;
                direction = (direction + 1) % 4;
            }
        }

    }


}
