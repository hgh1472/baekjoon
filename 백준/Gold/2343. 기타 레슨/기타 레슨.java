import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int n, m;
    static int[] sum;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        int[] num = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int left = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            left = Math.max(left, num[i]);
        }

        int right = 1000000000;
        while (left < right) {
            int mid = (left + right) / 2;
            int sum = 0;
            int count = 1;
            for (int i = 0; i < n; i++) {
                sum += num[i];
                if (sum > mid) {
                    count++;
                    sum = num[i];
                }
            }
            if (count > m) { // 블루레이 크기를 더 높여야 함
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }
        System.out.println(left);
    }
}
