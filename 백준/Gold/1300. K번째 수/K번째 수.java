import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        System.out.println(solve(n, k));
    }

    static long solve(int n, int k) {
        long left = 0;
        long right = k;
        long answer = 0;
        while (left <= right) {
            long count = 0;
            long mid = (left + right) / 2;

            for (int i = 1; i <= n; i++) {
                count += Math.min(n, mid / i);
            }

            if (count >= k) {
                right = mid - 1;
                answer = mid;
            }
            else {
                left = mid + 1;
            }
        }
        return answer;
    }
}
