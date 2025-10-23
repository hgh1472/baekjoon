import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] heights = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] counts = new int[n];
        int[] nums = new int[n];
        Deque<Node> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && stack.peek().height <= heights[i]) {
                stack.removeFirst();
            }
            counts[i] = stack.size();
            if (counts[i] != 0) {
                nums[i] = stack.peek().number;
            }
            stack.push(new Node(i + 1, heights[i]));
        }

        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek().height <= heights[i]) {
                stack.removeFirst();
            }
            counts[i] += stack.size();
            if (stack.size() != 0) {
                if (nums[i] == 0) {
                    nums[i] = stack.peek().number;
                } else {
                    if (Math.abs(i+1 - stack.peek().number) < Math.abs(i+1 - nums[i])) {
                        nums[i] = stack.peek().number;
                    }
                }
            }
            stack.push(new Node(i+1, heights[i]));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(counts[i]);
            if (counts[i] != 0) {
                sb.append(" ").append(nums[i]);
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    static class Node {
        int number;
        int height;

        public Node(int number, int height) {
            this.number = number;
            this.height = height;
        }
    }
}
