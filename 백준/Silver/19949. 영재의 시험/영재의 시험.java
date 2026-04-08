import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[] input;
    static int result;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        result = 0;
        build(0, new int[10], 0, 0);
        System.out.println(result);
    }

    static void build(int count, int[] arr, int before, int dup) {
        if (count == 10) {
            int answer = 0;
            for (int i = 0; i < 10; i++) {
                if (arr[i] == input[i]) {
                    answer++;
                }
            }
            if (answer >= 5) {
                result++;
            }
            return;
        }
        for (int i = 1; i <= 5; i++) {
            if (before == i && dup == 2) {
                continue;
            }
            arr[count] = i;
            if (before == i) {
                build(count + 1, arr, i, 2);
            } else {
                build(count + 1, arr, i, 1);
            }
        }
    }
}
