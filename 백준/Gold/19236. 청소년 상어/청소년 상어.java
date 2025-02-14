import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Fish[] fishes = new Fish[17];
        int[][] info = new int[5][5];

        fishes[0] = new Fish(0, 0, 0, false);
        for (int i = 1; i <= 4; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < 8; j += 2) {
                int num = input[j];
                int direction = input[j + 1] - 1;
                fishes[num] = new Fish(i, j / 2 + 1, direction, true);
                info[i][j / 2 + 1] = num;
            }
        }
        fishes[info[1][1]].isAlive = false;
        int direction = fishes[info[1][1]].direction;
        int num = info[1][1];
        info[1][1] = 0;
        moveFish(info, fishes, new Fish(1, 1, direction, true), num);
        System.out.println(answer);
    }

    static void moveFish(int[][] info, Fish[] fishes, Fish shark, int total) {
        for (int i = 1; i <= 16; i++) {
            if (!fishes[i].isAlive) {
                continue;
            }
            int nx = fishes[i].x + dx[fishes[i].direction];
            int ny = fishes[i].y + dy[fishes[i].direction];
            while (!isPossible(nx, ny, shark)) {
                fishes[i].changeDirection();
                nx = fishes[i].x + dx[fishes[i].direction];
                ny = fishes[i].y + dy[fishes[i].direction];
            }
            changeFish(info, fishes, fishes[i].x, fishes[i].y, nx, ny);
        }
        moveShark(info, fishes, shark, total);
    }

    private static void moveShark(int[][] info, Fish[] fishes, Fish shark, int total) {
        int nx = shark.x;
        int ny = shark.y;
        while (!(nx < 1 || 4 < nx || ny < 1 || 4 < ny)) {
            nx += dx[shark.direction];
            ny += dy[shark.direction];
            if (nx < 1 || 4 < nx || ny < 1 || 4 < ny) {
                break;
            }
            if (info[nx][ny] == 0) {
                continue;
            }
            int[][] tempInfo = new int[5][5];
            for (int i = 1; i <= 4; i++) {
                for (int j = 1; j <= 4; j++) {
                    tempInfo[i][j] = info[i][j];
                }
            }
            tempInfo[nx][ny] = 0;

            Fish[] tempFishes = new Fish[17];
            for (int i = 0; i <= 16; i++) {
                Fish fish = fishes[i];
                tempFishes[i] = new Fish(fish.x, fish.y, fish.direction, fish.isAlive);
            }
            tempFishes[info[nx][ny]].isAlive = false;
            answer = Math.max(answer, total + info[nx][ny]);
            moveFish(tempInfo, tempFishes, new Fish(nx, ny, tempFishes[info[nx][ny]].direction, true), total + info[nx][ny]);
        }
    }

    static boolean isPossible(int nx, int ny, Fish shark) {
        if (nx < 1 || 4 < nx || ny < 1 || 4 < ny) {
            return false;
        }
        if (nx == shark.x && ny == shark.y) {
            return false;
        }
        return true;
    }

    static void changeFish(int[][] info, Fish[] fishes, int x, int y, int nx, int ny) {
        int num1 = info[x][y];
        int num2 = info[nx][ny];
        info[x][y] = num2;
        info[nx][ny] = num1;
        fishes[num1].x = nx;
        fishes[num1].y = ny;
        fishes[num2].x = x;
        fishes[num2].y = y;
    }

    static class Fish {
        int x;
        int y;
        int direction;
        boolean isAlive;

        public Fish(int x, int y, int direction, boolean isAlive) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.isAlive = isAlive;
        }

        public void changeDirection() {
            this.direction = (this.direction + 1) % 8;
        }
    }
}
