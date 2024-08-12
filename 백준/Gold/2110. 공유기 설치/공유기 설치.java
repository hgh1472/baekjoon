import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int c = Integer.parseInt(s[1]);

        int[] num = new int[n];
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(num);

        int lo = 1;
        int hi = num[n-1] - num[0];
        int result = 0;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            int count = 1;
            int before = num[0];
            for (int i = 0; i < n; i++) {
                if (num[i] - before >= mid) {
                    count++;
                    before = num[i];
                }
            }
            if (count >= c) {
                result = Math.max(result, mid);
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        System.out.println(result);
    }
}
