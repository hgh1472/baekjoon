import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int n, s, answer = 0;
    static int[] num;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = input[0];
        s = input[1];

        num = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 1; i <= n; i++) {
            build(0, new int[i], i, 0);
        }
        System.out.println(answer);
    }

    static void build(int idx, int[] arr, int length, int count) {
        if (count == length) {
            int sum = 0;
            for (int i = 0; i < length; i++) {
                sum += arr[i];
            }
            if (sum == s) {
                answer++;
            }
            return;
        }

        if (idx >= n) {
            return;
        }
        build(idx + 1, arr, length, count);
        arr[count] = num[idx];
        build(idx + 1, arr, length, count + 1);
    }
}
