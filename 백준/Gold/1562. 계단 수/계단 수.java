import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // int[][][] stair = new int[n+1][10][10];
        int[][][] dp = new int[n+1][10][1024];
        // stair[10][9][0] = 1;
        // stair[11][9][1] = 1;
        // stair[11][8][0] = 1;
        // stair[11][1][9] = 1;
        int ans = 0;

        for (int i = 1; i < 10; i++) {
            dp[1][i][1 << i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                // k = 마지막에 방문한 숫자
                for (int k = 0; k < 1024; k++) {
                    int bit = k | (1 << j); // bit = 이전 방문 기록에 현재 방문 숫자 추가
                    // bit는 임의의 방문기록 k에서 j를 방문한 경우의 숫자 집합이다.
                    if (j - 1 >= 0)
                        dp[i][j][bit] += (dp[i-1][j-1][k] % 1000000000);
                    if (j + 1 <= 9)
                        dp[i][j][bit] += (dp[i-1][j+1][k] % 1000000000);
                    dp[i][j][bit] %= 1000000000;
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            ans += dp[n][i][1023];
            ans %= 1000000000;
        }

        System.out.println(ans);

        // for (int i = 12; i < n + 1; i++) {
        //     for (int j = 0; j < 10; j++) {
        //         for (int k = 0; k < 10; k++) {
        //             stair[i][j][k] += stair[i-2][j][k] * (i - 3);
        //             if (k != 9) {
        //                 stair[i][j][k] += stair[i-1][j][k+1];
        //             }
        //             if (k != 0) {
        //                 stair[i][j][k] += stair[i-1][j][k-1];
        //             }
        //             if (j != 9) {
        //                 stair[i][j][k] += stair[i-1][j+1][k];
        //             }
        //             if (j != 0) {
        //                 stair[i][j][k] += stair[i-1][j-1][k];
        //             }
        //         }
        //     }
        // }
        //
        // for (int i = 0; i < 10; i++) {
        //     for (int j = 0; j < 10; j++) {
        //         ans += stair[n][i][j] % 1000000000;
        //         ans %= 1000000000;
        //     }
        // }
        // System.out.println(ans);
    }
}
