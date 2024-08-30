import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

//
class Main {
    static int n;
    static int[][] a;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        long b = Long.parseLong(s[1]);

        a = new int[n][n];
        for (int i = 0; i < n; i++) {
            s = br.readLine().split(" ");
            a[i] = Arrays.stream(s).mapToInt(Integer::parseInt).toArray();
        }

        int[][] ans = cal(a, b);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%d ", ans[i][j] % 1000);
            }
            System.out.println();
        }
    }

    public static int[][] mul(int[][] arr1, int[][] arr2) {
        int[][] tmp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    tmp[i][j] += ((arr1[i][k] % 1000) * (arr2[k][j] % 1000));
                }
                tmp[i][j] %= 1000;
            }
        }
        return tmp;
    }

    public static int[][] cal(int[][] arr, long b) {
        if (b == 1) {
            return arr;
        }
        int[][] tmp;
        if (b % 2 == 0) {
            tmp = cal(arr, b / 2);
            return mul(tmp, tmp);
        } else {
            tmp = cal(arr, b - 1);
            return mul(tmp, a);
        }
    }
}
