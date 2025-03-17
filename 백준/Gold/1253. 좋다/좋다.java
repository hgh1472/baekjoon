import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(numbers);

        int answer = 0;
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = n-1;
            while (left < right) {
                if (left == i) {
                    left++;
                    continue;
                }
                if (right == i) {
                    right--;
                    continue;
                }
                int sum = numbers[left] + numbers[right];
                if (sum == numbers[i]) {
                    answer++;
                    break;
                }
                if (sum < numbers[i]) {
                    left++;
                }
                if (sum > numbers[i]) {
                    right--;
                }
            }
        }
        System.out.println(answer);
    }
}
