import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] solution = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(solution);

        int left = 0;
        int right = n-1;
        int min = Integer.MAX_VALUE;
        int one = solution[0];
        int two = solution[1];
        while (left < right) {
            int diff = solution[right] + solution[left];
            if (Math.abs(diff) < min) {
                one = solution[left];
                two = solution[right];
                min = Math.abs(diff);
            }
            if (0 < diff) {
                right--;
            } else if (diff < 0) {
                left++;
            }
            else {
                break;
            }
        }
        System.out.println(one + " " + two);
    }
}
