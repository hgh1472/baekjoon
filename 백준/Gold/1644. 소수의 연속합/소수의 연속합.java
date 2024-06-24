import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        boolean[] isNotPrime = new boolean[n+1];

        int count = 0;
        for (int i = 2; i <= n; i++) {
            if (isNotPrime[i])
                continue;
            for (int j = 2 * i; j <= n; j += i) {
                isNotPrime[j] = true;
            }
        }

        for (int i = 2; i <= n; i++) {
            if (isNotPrime[i])
                continue;
            int sum = 0;
            for (int j = i; j <= n; j++) {
                if (isNotPrime[j])
                    continue;
                sum += j;
                if (sum >= n)
                    break;
            }
            if (sum == n)
                count++;
        }
        System.out.println(count);
    }
}