import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[] buildings;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        buildings = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] answer = new int[n];

        for (int i = 0; i < n; i++) {
            int count = 0;

            double ratio = Integer.MAX_VALUE;
            for (int j = i-1; j >= 0; j--) {
                double newRatio = (double) (buildings[i] - buildings[j]) / (i - j);
                if (ratio > newRatio) {
                    count++;
                    ratio = newRatio;
                }
            }

            ratio = Integer.MIN_VALUE;

            for (int j = i + 1; j < n; j++) {
                double newRatio = (double) (buildings[j] - buildings[i]) / (j - i);

                if (ratio < newRatio) {
                    count++;
                    ratio = newRatio;
                }
            }
            answer[i] = count;
        }

        System.out.println(Arrays.stream(answer).max().getAsInt());

    }
}
