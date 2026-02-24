import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int k, n;
    static int MOD = 100000007;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            k = input[0];
            n = input[1];
            System.out.println(solve(n));
        }
    }

    static int solve(int n) {
        long[][] matrix = new long[k+1][k+1];
        matrix[0][0] = 1;
        matrix[0][k]=1;
        if (k == 0) {
            return exp(n);
        }
        if (n == 0) {
            return 1;
        }

        for (int i = 1; i <= k; i++) {
            matrix[i][i-1] = 1;
        }
        return (int) power(matrix, n)[0][0];
    }

    static int exp(int n) {
        long answer = 1;
        long a = 2;
        while (n != 0) {
            if (n % 2 == 1) {
                answer *= a;
                answer %= MOD;
            }
            a *= a;
            a %= MOD;
            n /= 2;
        }
        return (int) answer % MOD;
    }

    static long[][] power(long[][] matrix, int n) {
        if (n == 1) {
            return matrix;
        }
        long[][] half = power(matrix, n / 2);
        long[][] matrix2 = multiply(half, half);
        if (n % 2 == 1) {
            return multiply(matrix2, matrix);
        } else {
            return matrix2;
        }
    }

    static long[][] multiply(long[][] matrix1, long[][] matrix2) {
        long[][] matrix3 = new long[k+1][k+1];
        for (int i = 0; i < k + 1; i++) {
            for (int j = 0; j < k + 1; j++) {
                for (int t = 0; t < k + 1; t++) {
                    matrix3[i][j] += (long) matrix1[i][t] * matrix2[t][j];
                    matrix3[i][j] %= MOD;
                }
            }
        }
        return matrix3;
    }
}
