import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * DP
 * DP[i][j] = i번째 소형기관차가 j번째 칸까지 고려했을 때, 최대 인원 수
 * - j번째 칸을 마지막으로 하는 소형기관차를 넣거나/안넣거나
 * - 넣으면 -> i-1번째 소형기관차가 j-k번째까지의 최대값 + j번째 칸을 마지막으로 하는 소형기관차의 인원 수
 * - 안넣으면 -> 이전 최대값
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] num = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] sums = new int[n + 1];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += num[i];
            sums[i + 1] = sum;
        }
        int k = Integer.parseInt(br.readLine());

        int[][] dp = new int[4][n + 1];
        for (int i = 1; i <= 3; i++) {
            for (int j = k * i; j <= n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - k] + sums[j] - sums[j - k]);
            }
        }

        System.out.println(dp[3][n]);
    }

}
