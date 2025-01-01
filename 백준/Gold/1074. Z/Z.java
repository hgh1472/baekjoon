import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int a = Integer.parseInt(input[1]);
        int b = Integer.parseInt(input[2]);
        System.out.println(solution(n, a, b));
    }

    public static int solution(int n, int a, int b) {
        if (n == 0) {
            return 0;
        }
        int base = 0;
        if (Math.pow(2, n - 1) <= a && Math.pow(2, n - 1) <= b) {
            a -= (int) Math.pow(2, n - 1);
            b -= (int) Math.pow(2, n - 1);
            base = (int) Math.pow(2, 2 * n - 2) * 3;
        } else if (Math.pow(2, n - 1) <= a && Math.pow(2, n - 1) > b) {
            a -= (int) Math.pow(2, n - 1);
            base = (int) Math.pow(2, 2 * n - 1);
        } else if (Math.pow(2, n - 1) > a && Math.pow(2, n - 1) <= b) {
            b -= (int) Math.pow(2, n - 1);
            base = (int) Math.pow(2, 2 * n - 2);
        }
        return base + solution(n - 1, a, b);
    }
}
