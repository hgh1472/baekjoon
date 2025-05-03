import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int left = 0, right = 1;

        long answer = 1;
        Set<Integer> set = new HashSet<>();
        set.add(numbers[0]);
        while (right < n) {
            if (set.contains(numbers[right])) {
                while (set.contains(numbers[right])) {
                    set.remove(numbers[left]);
                    left++;
                }
                set.add(numbers[right]);
                answer += set.size();
            }
            else {
                set.add(numbers[right]);
                answer += set.size();
            }
            right++;
        }

        System.out.println(answer);

    }

}
