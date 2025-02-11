import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static long MOD = 1000000;
    static Map<Long, Long> memo = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        memo.put(0L, 0L);
        memo.put(1L, 1L);
        memo.put(2L, 1L);
        memo.put(3L, 2L);
        System.out.println(solve(n) % MOD);
    }

    static long solve(long n) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        long result;
        if (n % 2 == 0) {
            result = multiplyWithMod(solve(n / 2), solve(n / 2))
                    + 2 * multiplyWithMod(solve(n / 2), solve(n / 2 - 1)) % MOD;
        }
        else {
            result = (multiplyWithMod(solve((n+1) / 2), solve((n+1) / 2)) + multiplyWithMod(solve((n+1) / 2 - 1), solve((n+1) / 2 - 1))) % MOD;
        }
        memo.put(n, result);
        return result;
    }

    static long multiplyWithMod(long a, long b) {
        return ((a % MOD) * (b % MOD)) % MOD;
    }
}
