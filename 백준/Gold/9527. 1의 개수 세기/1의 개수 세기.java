import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static long[] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        dp = new long[64];
        long a = Long.parseLong(input[0]);
        long b = Long.parseLong(input[1]);

        dp[0] = 1;
        for (int i = 1; i < 64; i++) {
            dp[i] = 2 * dp[i-1] + (1L << i);
        }

        System.out.println(getCount(b) - getCount(a-1));
    }

    static long getCount(long n) {
        int size = (int) (Math.log(n) / Math.log(2));
        long count = n & 1;
        for (int i = size; i > 0; i--) {
            if ((n & (1L << i)) != 0L) {
                count += dp[i-1] + (n - (1L << i) + 1);
                n -= (1L << i);
            }
        }
        return count;
    }
}
