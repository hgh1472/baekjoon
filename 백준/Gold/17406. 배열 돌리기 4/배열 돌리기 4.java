import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, k;
    static int[][] arr;
    static Rotation[] rotations;
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = input[0];
        m = input[1];
        k = input[2];
        arr = new int[n][m];
        rotations = new Rotation[k];

        for (int i = 0; i < n; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        for (int i = 0; i < k; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            rotations[i] = new Rotation(input[0]-1, input[1]-1, input[2]);
        }

        permutation(0, new boolean[k], new int[k]);
        System.out.println(answer);
    }

    static void permutation(int depth, boolean[] visited, int[] per) {
        if (depth == k) {
            int min = rotate(per);
            answer = Math.min(answer, min);
            return;
        }
        for (int i = 0; i < k; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            per[depth] = i;
            permutation(depth + 1, visited, per);
            visited[i] = false;
        }
    }
    static int rotate(int[] per) {
        int[][] copy = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copy[i][j] = arr[i][j];
            }
        }
        for (int i = 0; i < per.length; i++) {
            Rotation r = rotations[per[i]];
            for (int j = 1; j <= r.s; j++)
                rotateRec(copy, r.r-j, r.c-j, r.r+ j, r.c+j);
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int local = 0;
            for (int j = 0; j < m; j++) {
                local += copy[i][j];
            }
            min = Math.min(min, local);
        }
        return min;
    }

    static void rotateRec(int[][] ar, int x1, int y1, int x2, int y2) {
        int tmp = ar[x1][y1];
        for (int i = x1; i < x2; i++) {
            ar[i][y1] = ar[i+1][y1];
        }
        for (int i = y1; i < y2; i++) {
            ar[x2][i] = ar[x2][i+1];
        }
        for (int i = x2; i > x1; i--) {
            ar[i][y2] = ar[i-1][y2];
        }
        for (int i = y2; i > y1; i--) {
            if (i == y1 + 1) {
                ar[x1][i] = tmp;
            }
            else {
                ar[x1][i] = ar[x1][i-1];
            }
        }
    }

    static class Rotation {
        int r, c, s;

        public Rotation(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }
}
