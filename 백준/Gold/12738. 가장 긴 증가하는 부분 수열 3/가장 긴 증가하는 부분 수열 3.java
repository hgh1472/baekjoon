import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Integer> numbers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        numbers.add(input[0]);
        for (int i = 1; i < n; i++) {
            if (numbers.get(numbers.size() - 1) < input[i]) {
                numbers.add(input[i]);
                continue;
            }
            int p = binarySearch(input[i]);
            numbers.set(p, input[i]);
        }
        System.out.println(numbers.size());
    }

    static int binarySearch(int number) {
        int left = 0;
        int right = numbers.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (numbers.get(mid) < number) {
                left = mid + 1;
            }
            else if (numbers.get(mid) > number) {
                right = mid - 1;
            }
            else {
                return mid;
            }
        }
        return left;
    }
}
