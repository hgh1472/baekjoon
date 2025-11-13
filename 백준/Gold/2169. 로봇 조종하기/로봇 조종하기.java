import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * NASA에서는 화성 탐사를 위해 화성에 무선 조종 로봇을 보냈다. 실제 화성의 모습은 굉장히 복잡하지만, 로봇의 메모리가 얼마 안 되기 때문에 지형을 N×M 배열로 단순화 하여 생각하기로 한다.
 * 지형의 고저차의 특성상, 로봇은 움직일 때 배열에서 왼쪽, 오른쪽, 아래쪽으로 이동할 수 있지만, 위쪽으로는 이동할 수 없다. 또한 한 번 탐사한 지역(배열에서 하나의 칸)은 탐사하지 않기로 한다.
 * 각각의 지역은 탐사 가치가 있는데, 로봇을 배열의 왼쪽 위 (1, 1)에서 출발시켜 오른쪽 아래 (N, M)으로 보내려고 한다.
 * 이때, 위의 조건을 만족하면서, 탐사한 지역들의 가치의 합이 최대가 되도록 하는 프로그램을 작성하시오.
 *
 * 5 5
 * 10 25 7 8 13
 * 68 24 -78 63 32
 * 12 -69 100 -29 -25
 * -16 -22 -57 -33 99
 * 7 -76 -11 77 15
 *
 * direction == DOWN -> 왼쪽 오른쪽 아래쪽 push
 * direction == LEFT -> 왼쪽 아래쪽 push
 * direction == RIGHT -> 오른쪽 아래쪽 push
 */
public class Main {
    static int n,m;
    static int LEFT = 0;
    static int RIGHT = 1;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        int[][] map = new int[n][m];
        int[][][] visited = new int[2][n][m];
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(visited[i][j], Integer.MIN_VALUE);
            }
        }

        visited[RIGHT][0][0] = map[0][0];
        dp[0][0] = map[0][0];
        for (int i = 1; i < m; i++) {
            visited[RIGHT][0][i] = visited[RIGHT][0][i-1] + map[0][i];
            dp[0][i] = visited[RIGHT][0][i];
        }
        for (int i = 1; i < n; i++) {
            visited[RIGHT][i][0] = dp[i-1][0] + map[i][0];
            visited[LEFT][i][m-1] = dp[i-1][m-1] + map[i][m-1];
            for (int j = 1; j < m; j++) {
                visited[RIGHT][i][j] = Math.max(dp[i-1][j], visited[RIGHT][i][j-1]) + map[i][j];
            }
            for (int j = m-2; j >= 0; j--) {
                visited[LEFT][i][j] = Math.max(dp[i-1][j], visited[LEFT][i][j+1]) + map[i][j];
            }
            for (int j = 0; j < m; j++) {
                dp[i][j] = Math.max(visited[LEFT][i][j], visited[RIGHT][i][j]);
            }
        }
        System.out.println(dp[n-1][m-1]);
    }
}
