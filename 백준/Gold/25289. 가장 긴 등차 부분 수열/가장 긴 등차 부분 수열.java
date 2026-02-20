import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.*;

public class Main {
    static Map<Long, Long> memo = new HashMap<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(solve(n, input));
    }

    static int solve(int n, int[] numbers) {
        int answer = 0;
        for (int diff = -99; diff <= 99; diff++) {
            int[] count = new int[101];
            for (int i = 0; i < n; i++) {
                int num = numbers[i];
                if (num - diff < 0 || num - diff > 100) {
                    count[num] = Math.max(1, count[num]);
                    continue;
                }
                count[num] = count[num - diff] + 1;
            }

            for (int i = 0; i <= 100; i++) {
                answer = Math.max(answer, count[i]);
            }
        }
        return answer;
    }
}
