import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Main {
    static Map<Long, Long> memo = new HashMap<>();
    static Long MOD = 1000000007L;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Long n = Long.parseLong(br.readLine());
        memo.put(0L, 0L);
        memo.put(1L, 1L);
        memo.put(2L, 1L);

        Long answer = solution(n);
        System.out.println(answer);
    }

    public static Long solution(Long n) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        if (n % 2 == 0) {
            Long a = solution(n / 2 + 1) % MOD;
            Long b = solution(n / 2) % MOD;
            Long c = solution(n / 2 - 1) % MOD;
            memo.put(n, ((a * b % MOD) + (b * c % MOD)) % MOD);
        }
        else {
            Long a = solution(n / 2 + 1) % MOD;
            Long b = solution(n / 2) % MOD;
            memo.put(n, ((a * a % MOD) + (b * b % MOD)) % MOD);
        }
        return memo.get(n);
    }
}
