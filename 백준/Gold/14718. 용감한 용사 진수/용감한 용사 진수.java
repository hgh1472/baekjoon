import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = input[0];
        int win = input[1];
        int[][] stats = new int[n][3];
        int[] answers = new int[3];
        int answer = Integer.MAX_VALUE;
        Arrays.fill(answers, Integer.MAX_VALUE);

        for (int i = 0; i < n; i++) {
            stats[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    int count = 0;
                    int str = stats[i][0];
                    int lck = stats[j][1];
                    int intel = stats[k][2];
                    if (answers[0] <= str && answers[1] <= lck && answers[2] <= intel) {
                        continue;
                    }
                    for (int t = 0; t < n; t++) {
                        if (str >= stats[t][0] && lck >= stats[t][1] && intel >= stats[t][2]) {
                            count++;
                        }
                    }
                    if (count >= win && answer > str + lck + intel) {
                        answer = str + lck + intel;
                        answers[0] = str;
                        answers[1] = lck;
                        answers[2] = intel;
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
