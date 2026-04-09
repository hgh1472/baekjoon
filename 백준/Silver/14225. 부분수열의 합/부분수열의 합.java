import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static boolean[] visited = new boolean[2000001];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 1; i <= arr.length; i++) {
            build(0, i, arr, 0, 0);
        }
        for (int i = 1; i <= 2000000; i++) {
            if (!visited[i]) {
                System.out.println(i);
                return;
            }
        }
    }

    static void build(int now, int dst, int[] arr, int sum, int idx) {
        if (now == dst) {
            visited[sum] = true;
            return;
        }
        if (dst - now > arr.length - idx) {
            return;
        }
        build(now, dst, arr, sum, idx + 1);
        build(now + 1, dst, arr, sum + arr[idx], idx + 1);
    }
}
