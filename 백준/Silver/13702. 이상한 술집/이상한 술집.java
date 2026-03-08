import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = input[0];
        int k = input[1];

        long[] li = new long[n];
        long left = 0, right = 0;
        for (int i = 0; i < n; i++) {
            li[i] = Long.parseLong(br.readLine());
            right = Math.max(right, li[i]);
        }
        while (left <= right) {
            long mid = (left + right) / 2;
            long result = 0;
            for (int i = 0; i < n; i++) {
                if (mid == 0) {
                    result += k;
                    continue;
                }
                result += li[i] / mid;
            }
            if (result < k) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        System.out.println(right);
    }
}
