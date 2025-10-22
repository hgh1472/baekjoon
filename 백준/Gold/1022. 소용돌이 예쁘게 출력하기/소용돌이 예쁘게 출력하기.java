import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        int r1 = Integer.parseInt(input[0]);
        int c1 = Integer.parseInt(input[1]);
        int r2 = Integer.parseInt(input[2]);
        int c2 = Integer.parseInt(input[3]);

        int[][] info = new int[r2-r1+1][c2-c1+1];

        int max = 0;
        for (int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {
                info[i-r1][j-c1] = get(i, j);
                max = Math.max(max, info[i-r1][j-c1]);
            }
        }

        StringBuilder sb = new StringBuilder();
        int length = String.valueOf(max).length();
        for (int i = 0; i < info.length; i++) {
            for (int j = 0; j < info[0].length; j++) {
                String s = String.valueOf(info[i][j]);
                int count = length - s.length();
                sb.append(" ".repeat(count)).append(s).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static int get(int x, int y) {
        int n = Math.max(Math.abs(x), Math.abs(y));
        if (y == n) {
            if (x == n) {
                return (int) Math.pow(2 * n + 1, 2);
            }
            return (int) (Math.pow(2*n-1, 2)) + 1 + (n-1) - x;
        } else if (x == -n) {
            return (int) Math.pow(2*n-1, 2) + 1 + (2*n-1) + n - y;
        } else if (y == -n) {
            return (int) Math.pow(2*n-1,2) + 1 + (2*n-1) + 2*n + x + n;
        }
        else {
            return (int) Math.pow(2*n-1,2) + 1 + (2*n-1) + 4*n + y + n;
        }
    }
}
