import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        double[] num = Arrays.stream(br.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();

        if (n == 2 && num[0] == num[1]) {
            System.out.println((int)num[0]);
            return;
        }
        if (n < 3) {
            System.out.println("A");
            return;
        }

        if (num[1] == num[0]) {
            for (double v : num) {
                if (v != num[0]) {
                    System.out.println("B");
                    return;
                }
            }
            System.out.println((int) num[1]);
            return;
        }
        double a = (num[2] - num[1]) / (num[1] - num[0]);
        double b = (num[1] * num[1] - num[0] * num[2]) / (num[1] - num[0]);
        if (a % 1 != 0 || b % 1 != 0) {
            System.out.println("B");
            return;
        }
        for (int i = 1; i < n; i++) {
            double now = num[i-1] * a + b;
            if (num[i] != now) {
                System.out.println("B");
                return;
            }
        }
        System.out.println((int) (a * num[n-1] + b));
    }
}
