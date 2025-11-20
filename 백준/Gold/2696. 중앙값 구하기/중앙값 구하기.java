import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;


public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int m = Integer.parseInt(br.readLine());
            long[] nums = new long[m];
            for (int i = 0; i < m; i += 10) {
                String[] input = br.readLine().split(" ");
                for (int j = 0; j < 10 && i + j < m; j++) {
                    nums[i + j] = Long.parseLong(input[j]);
                }
            }
            PriorityQueue<Left> lower = new PriorityQueue<>();
            PriorityQueue<Right> upper = new PriorityQueue<>();
            sb.append(((m-1) / 2) + 1).append("\n");
            long mid = nums[0];
            sb.append(mid).append(" ");
            int count = 1;
            for (int i = 1; i < m; i += 2) {
                if (count == 10) {
                    sb.append("\n");
                    count = 0;
                }
                if (i + 1 >= m) {
                    continue;
                }
                if (mid < nums[i]) {
                    upper.add(new Right(nums[i]));
                } else {
                    lower.add(new Left(nums[i]));
                }
                if (mid < nums[i + 1]) {
                    upper.add(new Right(nums[i + 1]));
                } else {
                    lower.add(new Left(nums[i + 1]));
                }
                if (upper.size() > lower.size()) {
                    lower.add(new Left(mid));
                    mid = upper.poll().number;
                }
                else if (upper.size() < lower.size()) {
                    upper.add(new Right(mid));
                    mid = lower.poll().number;
                }
                sb.append(mid).append(" ");
                count++;
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static class Left implements Comparable<Left> {
        long number;

        public Left(long number) {
            this.number = number;
        }

        @Override
        public int compareTo(Left o) {
            return Long.compare(o.number, this.number);
        }
    }

    static class Right implements Comparable<Right> {
        long number;

        public Right(long number) {
            this.number = number;
        }

        @Override
        public int compareTo(Right o) {
            return Long.compare(this.number, o.number);
        }
    }
}
