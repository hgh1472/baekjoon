import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Main {
    static int[][] dp;
    static List<Homework> homeworks;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        dp = new int[n+1][n+1];
        homeworks = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            homeworks.add(new Homework(input[0], input[1]));
        }

        Collections.sort(homeworks);

        for (int i = 1; i <= n; i++) {
            dp[i][1] = Math.max(dp[i-1][1], homeworks.get(i-1).reward);
            for (int j = 2; j <= n; j++) {
                if (j > homeworks.get(i-1).day) {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                    continue;
                }
                dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1] + homeworks.get(i-1).reward);
            }
        }

//        for (int[] ints : dp) {
//            for (int anInt : ints) {
//                System.out.print(anInt + " ");
//            }
//            System.out.println();
//        }
        System.out.println(dp[n][n]);
    }

    static class Homework implements Comparable<Homework> {
        int day;
        int reward;

        public Homework(int day, int reword) {
            this.day = day;
            this.reward = reword;
        }

        @Override
        public int compareTo(Homework o) {
            if (this.day == o.day) {
                return this.reward - o.reward;
            }
            return this.day - o.day;
        }
    }
}
