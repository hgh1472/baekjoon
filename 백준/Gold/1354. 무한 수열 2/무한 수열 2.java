import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.*;

public class Main {
    static Map<Long, Long> memo = new HashMap<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        long[] input = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        long answer = solve(input[0], input[1], input[2], input[3], input[4]);

        System.out.println(answer);
    }

    static long solve(long n, long p, long q, long x, long y) {
        if (n <= 0) {
            memo.put(n, 1L);
            return 1;
        }
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        long left = n / p - x;
        long right = n / q - y;
        if (!memo.containsKey(left)) {
            memo.put(left, solve(left, p, q, x, y));
        }
        if (!memo.containsKey(right)) {
            memo.put(right, solve(right, p, q, x, y));
        }
        memo.put(n, memo.get(left) + memo.get(right));
        return memo.get(n);
    }
}
