import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int g = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        long count = 0;

        for (long i = 2; i <= 50000; i++) {
            long left = 1;
            long right = i - 1;
            while (left <= right) {
                long mid = (left + right) / 2;
                long value = mid * mid;
                if (i * i - value < g) {
                    right = mid - 1;
                } else if (i * i - value > g) {
                    left = mid + 1;
                }
                else {
                    sb.append(i).append("\n");
                    count++;
                    break;
                }
            }
        }
        if (count == 0) {
            System.out.println(-1);
        }
        else {
            System.out.print(sb);
        }
    }
}
