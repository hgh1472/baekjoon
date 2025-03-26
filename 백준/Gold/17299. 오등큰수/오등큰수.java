import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] answer = new int[n];
        int[] result = new int[n];

        Map<Integer, Integer> numCount = new HashMap<>();
        for (int i : input) {
            numCount.put(i, numCount.getOrDefault(i, 0) + 1);
        }

        for (int i = 0; i < n; i++) {
            result[i] = numCount.get(input[i]);
        }

        Stack<Node> st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && st.peek().count <= result[i]) {
                st.pop();
            }
            if (st.isEmpty()) {
                answer[i] = -1;
            }
            else {
                answer[i] = st.peek().number;
            }
            st.push(new Node(input[i], result[i]));
        }

        StringBuilder sb = new StringBuilder();
        for (int i : answer) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    static class Node {
        int number;
        int count;

        public Node(int number, int count) {
            this.number = number;
            this.count = count;
        }
    }

}
