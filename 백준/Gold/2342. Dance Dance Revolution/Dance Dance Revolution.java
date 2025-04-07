import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][][] dp;
    static int[] command;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        command = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        dp = new int[5][5][400000];

        solve(0, 0, 0);
        System.out.println(dp[0][0][0]);
    }

    static int solve(int x, int y, int commandCount) {
        if (commandCount == command.length - 1) {
            return 0;
        }
        if (dp[x][y][commandCount] != 0) {
            return dp[x][y][commandCount];
        }
        int left = calculateCost(x, command[commandCount]) + solve(command[commandCount], y, commandCount + 1);
        int right = calculateCost(y, command[commandCount]) + solve(x, command[commandCount], commandCount + 1);

        dp[x][y][commandCount] = Math.min(left, right);
        return dp[x][y][commandCount];
    }

    static int calculateCost(int foot, int target) {
        if (foot == target) {
            return 1;
        }
        if (foot == 0) {
            return 2;
        }
        if ((foot == 1 && target == 3) || (foot == 2 && target == 4) || (foot == 3 && target == 1) || (foot == 4 && target == 2)) {
            return 4;
        }
        return 3;
    }

}
