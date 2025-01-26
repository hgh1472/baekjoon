import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] info = new int[10][10];
    static int[] paper = {0,5,5,5,5,5};
    static int answer = 26;
    static int needCover;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            info[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < 10; j++) {
                if (info[i][j] == 1) {
                    needCover++;
                }
            }
        }
        solve(0, 0, 0);
        if (answer == 26) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    static void solve(int x, int y, int cover) {
        for (int i = x; i < 10; i++) {
            int ny = (i == x) ? y : 0;
            for (int j = ny; j < 10; j++) {
                if (info[i][j] == 1) {
                    for (int k = 1; k <= 5; k++) {
                        if (isInPaper(i, j, k)) {
                            fillPaper(i, j, k);
                            solve(i, j, cover + k * k);
                            deletePaper(i, j, k);
                        }
                    }
                    return;
                }
            }
        }

        if (cover == needCover) {
            int count = 0;
            for (int i = 1; i <= 5; i++)
                count += (5 - paper[i]);
            answer = Math.min(count, answer);
        }
    }

    private static void deletePaper(int x, int y, int p) {
        paper[p] += 1;
        for (int i = x; i < x + p; i++) {
            for (int j = y; j < y + p; j++) {
                info[i][j] = 1;
            }
        }
    }

    private static boolean isInPaper(int i, int j, int p) {
        if (paper[p] == 0) {
            return false;
        }
        for (int x = i; x < i + p; x++) {
            for (int y = j; y < j + p; y++) {
                if (isBroken(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean isBroken(int x, int y) {
        if (x < 0 || 10 <= x || y < 0 || 10 <= y) {
            return true;
        }
        if (info[x][y] != 1) {
            return true;
        }
        return false;
    }

    static void fillPaper(int x, int y, int p) {
        paper[p] -= 1;
        for (int i = x; i < x + p; i++) {
            for (int j = y; j < y + p; j++) {
                info[i][j] = 0;
            }
        }
    }
}
