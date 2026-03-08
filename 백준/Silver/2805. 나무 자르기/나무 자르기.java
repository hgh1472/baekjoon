import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = input[0];
        int m = input[1];

        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int left = 0, right = 0;
        for (int i = 0; i < n; i++) {
            right = Math.max(right, input[i]);
        }

        while (left <= right) {
            int mid = (left + right) / 2;

            long result = 0;
            for (int i = 0; i < n; i++) {
                result += Math.max(0, input[i] - mid);
            }
            if (result >= m) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        System.out.println(right);
    }
}
