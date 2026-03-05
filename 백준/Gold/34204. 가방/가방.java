import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, k, c;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = input[0];
        k = input[1];
        c = input[2];
        long[] numbers = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        Arrays.sort(numbers);

        int left = 0;
        int right = Math.min(k, n);
        long steel = 0;
        for (int i = left; i < right; i++) {
            steel += numbers[i];
        }

        long weight = 0;
        int idx = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= c; i++) {
            if (i - weight >= numbers[idx]) {
                if (right < n) {
                    steel += numbers[right];
                    right++;
                    steel -= numbers[left];
                    weight += numbers[left];
                    left++;
                    idx++;
                }
            }
            sb.append(steel).append("\n");
        }
        System.out.print(sb);
    }
}
