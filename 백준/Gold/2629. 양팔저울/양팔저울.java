import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean[] isPossible = new boolean[400001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] weights = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int beadCount = Integer.parseInt(br.readLine());
        int[] beads = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        calculate(weights);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < beadCount; i++) {
            if (isPossible[beads[i]]) {
                sb.append("Y ");
            }
            else {
                sb.append("N ");
            }
        }

        System.out.println(sb);

    }

    static void calculate(int[] weights) {
        int maxWeight = Arrays.stream(weights).sum();

        isPossible[0] = true;

        for (int i = 0; i < weights.length; i++) {
            for (int j = maxWeight; 0 <= j; j--) {
                if (isPossible[j]) {
                    isPossible[j + weights[i]] = true;
                }
            }
            for (int j = 0; j <= maxWeight; j++) {
                if (isPossible[j]) {
                    isPossible[Math.abs(j - weights[i])] = true;
                }
            }
        }

    }

}
