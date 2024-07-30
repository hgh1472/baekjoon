import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static long[] nums;
    static int[] remains;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);

        s = br.readLine().split(" ");
        nums = new long[n];
        remains = new int[m];
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Long.parseLong(s[i]);
            nums[i] = sum;
            remains[(int)(sum % (long)m)]++;
        }
        long count = remains[0];
        for (long remain : remains) {
            count += remain * (remain-1) / 2;
        }
        System.out.println(count);
    }
}
