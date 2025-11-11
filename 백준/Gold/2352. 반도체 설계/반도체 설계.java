import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] num = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<Integer> numbers = new ArrayList<>();
        numbers.add(num[0]);
        for (int i = 1; i < n; i++) {
            find(numbers, num[i]);
        }
        System.out.println(numbers.size());
    }

    static void find(List<Integer> numbers, int number) {
        if (numbers.get(numbers.size() - 1) < number) {
            numbers.add(number);
            return;
        }
        int left = 0;
        int right = numbers.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int n = numbers.get(mid);
            if (n < number) {
                left = mid + 1;
            }
            else if (n > number) {
                right = mid - 1;
            }
        }
        numbers.set(left, number);
    }
}
