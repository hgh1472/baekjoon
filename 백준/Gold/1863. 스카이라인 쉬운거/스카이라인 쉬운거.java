import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Deque<Integer> stack = new ArrayDeque<>();

        stack.push(0);
        int answer = 0;
        for (int i = 0; i < n; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int height = input[1];
            while (!stack.isEmpty() && stack.peek() > height) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                answer++;
            }
            else {
                if (stack.peek() == height) {
                    continue;
                }
                answer++;
            }
            stack.push(height);
        }

        System.out.println(answer);

    }

}
