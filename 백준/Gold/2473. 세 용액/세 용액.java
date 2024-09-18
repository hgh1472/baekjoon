import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        long[] nums = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        Arrays.sort(nums);
        long minSum = Long.MAX_VALUE;
        if (n == 3) {
            System.out.println(nums[0] + " " + nums[1] + " " + nums[2]);
            return;
        }
        long[] ans = new long[3];
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                long find = -(nums[i] + nums[j]);
                int pos = Arrays.binarySearch(nums, j + 1, n, find);
                if (pos > 0) {
                    System.out.println(nums[i] + " " + nums[j] + " " + nums[pos]);
                    return;
                }
                if (pos < 0) {
                    int low = -pos - 2;
                    if (low > j) {
                        long tmp = Math.abs(nums[i] + nums[j] + nums[low]);
                        if (tmp < minSum) {
                            minSum = tmp;
                            ans[0] = nums[i];
                            ans[1] = nums[j];
                            ans[2] = nums[low];
                        }
                    }
                    int high = -pos - 1;
                    if (high <= j || high >= n)
                        continue;
                    long tmp = Math.abs(nums[i] + nums[j] + nums[high]);
                    if (tmp < minSum) {
                        minSum = tmp;
                        ans[0] = nums[i];
                        ans[1] = nums[j];
                        ans[2] = nums[high];
                    }
                }
            }
        }
        System.out.println(ans[0] + " " + ans[1] + " " + ans[2]);
    }
}
