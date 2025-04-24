import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int h, w;
    static boolean[][] isWall;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        h = input[0];
        w = input[1];
        isWall = new boolean[h+1][w];

        int[] heights = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < heights[i]; j++) {
                isWall[j][i] = true;
            }
        }

        int answer = 0;
        for (int i = 0; i <= h; i++) {
            boolean isOut = true;
            int water = 0;
            for (int j = 0; j < w; j++) {
                if (isWall[i][j]) {
                    if (isOut) {
                        isOut = false;
                    }
                    else {
                        answer += water;
                        water = 0;
                    }
                }
                else {
                    if (!isOut) {
                        water++;
                    }
                }
            }
        }

        System.out.println(answer);
    }

}
