import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int k;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = input[0];
        k = input[1];

        Cell[] cells = new Cell[2 * n];
        int up = 0;
        int down = n - 1;

        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < 2 * n; i++) {
            cells[i] = new Cell(input[i]);
        }

        int round = 0;
        while (answer < k) {
            round++;
            // rotate
            up = up - 1;
            if (up < 0) {
                up = 2*n-1;
            }
            down = down - 1;
            if (down < 0) {
                down = 2*n-1;
            }
            cells[down].hasRobot = false;

            moveRobots(cells, up, down);
            upRobot(cells, up);
        }
        System.out.println(round);
    }

    static void upRobot(Cell[] cells, int up) {
        if (cells[up].life < 1) {
            return;
        }
        cells[up].hasRobot = true;
        cells[up].life -= 1;
        if (cells[up].life == 0) {
            answer++;
        }
    }

    static void moveRobots(Cell[] cells, int up, int down) {
        int count = n-1;
        while (--count > 0) {
            int idx = (up + count) % (2*n);
            int next = (idx + 1) % (2*n);
            if (!cells[idx].hasRobot) {
                continue;
            }
            if (cells[next].hasRobot || cells[next].life < 1) {
                continue;
            }
            cells[next].hasRobot = true;
            if (next == down) {
                cells[next].hasRobot = false;
            }
            cells[next].life -= 1;
            if (cells[next].life == 0) {
                answer++;
            }
            cells[idx].hasRobot = false;
        }
    }

    static class Cell {
        int life;
        boolean hasRobot;

        public Cell(int life) {
            this.life = life;
            this.hasRobot = false;
        }
    }
}
