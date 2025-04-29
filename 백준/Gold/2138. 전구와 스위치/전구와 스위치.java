import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static boolean[] isOn;
    static boolean[] dst;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        isOn = new boolean[n];
        dst = new boolean[n];

        String input = br.readLine();
        for (int i = 0; i < input.length(); i++) {
            isOn[i] = input.charAt(i) == '1';
        }
        input = br.readLine();
        for (int i = 0; i < input.length(); i++) {
            dst[i] = input.charAt(i) == '1';
        }

        boolean[] tmp = Arrays.copyOf(isOn, n);
        tmp[0] = !isOn[0];
        tmp[1] = !isOn[1];
        calculate(1, isOn, 0);
        calculate(1, tmp, 1);

        answer = (answer == Integer.MAX_VALUE) ? -1 : answer;
        System.out.println(answer);

    }

    static void calculate(int idx, boolean[] arr, int cnt) {
        if (idx == arr.length - 1) {
            if (arr[idx - 1] == dst[idx - 1]) {
                if (arr[idx] == dst[idx]) {
                    answer = Math.min(answer, cnt);
                }
            }
            if (arr[idx - 1] != dst[idx - 1]) {
                if (arr[idx] != dst[idx]) {
                    answer = Math.min(answer, cnt + 1);
                }
            }
            return;
        }

        if (arr[idx - 1] == dst[idx - 1]) {
            calculate(idx + 1, arr, cnt);
            return;
        }
        arr[idx - 1] = !arr[idx - 1];
        arr[idx] = !arr[idx];
        arr[idx + 1] = !arr[idx + 1];
        calculate(idx + 1, arr, cnt + 1);
    }
}
