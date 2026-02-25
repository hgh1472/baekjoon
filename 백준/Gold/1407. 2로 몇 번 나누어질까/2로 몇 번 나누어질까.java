import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int k, n;
    static int MOD = 100000007;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long[] input = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        System.out.println(solve(input[0], input[1]));
    }

    static long solve(long a, long b) {
        return sum(b) - sum(a - 1);
    }

    static long sum(long num) {
        if (num == 0) {
            return 0;
        }
        if (num == 1) {
            return 1;
        }
        if (num % 2 == 1) {
            return num / 2 + 1 + 2 * sum(num / 2);
        }
        else {
            return num / 2 + 2 * sum(num / 2);
        }
    }
}
