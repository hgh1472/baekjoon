import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);
        StringBuilder sb = new StringBuilder();
        char[] nums = br.readLine().toCharArray();
        Stack<Integer> stack = new Stack<>();
        int willBeRemoved = k;
        for (int i = 0; i < n; i++) {
            int num = nums[i] - '0';
            if (stack.isEmpty()) {
                stack.push(num);
                continue;
            }
            // 버릴지 말지 결정한다.
            if (stack.peek() < num) {
                // 자리수를 더 높일 수 있다면 버린다.
                while (!stack.isEmpty() && stack.peek() < num) {
                    stack.pop();
                    willBeRemoved--;

                    // 더 이상 버릴 숫자가 없다면 종료
                    if (willBeRemoved == 0) {
                        while (!stack.isEmpty()) {
                            sb.insert(0, stack.pop());
                        }
                        for (int j = i; j < n; j++) {
                            sb.append(nums[j]);
                        }
                        i = n;
                    }
                }
            }
            stack.push(num);
        }

        if (willBeRemoved > 0) {
            for (int i = 0; i < willBeRemoved; i++) {
                stack.pop();
            }
            while (!stack.isEmpty()) {
                sb.insert(0, stack.pop());
            }
        }
        System.out.println(sb);
    }
}
