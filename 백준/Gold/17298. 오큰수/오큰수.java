import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] nge = new int[n];
        Stack<Node> stack = new Stack<>();
        stack.push(new Node(numbers[0], 0));

        for (int i = 1; i < numbers.length; i++) {
            if (stack.peek().num > numbers[i]) {
                stack.push(new Node(numbers[i], i));
            } else {
                while (!stack.isEmpty() && stack.peek().num < numbers[i]) {
                    nge[stack.pop().pos] = numbers[i];
                }
                stack.push(new Node(numbers[i], i));
            }
        }

        while (!stack.isEmpty()) {
            nge[stack.pop().pos] = -1;
        }

        StringBuilder sb = new StringBuilder();
        for (int i : nge) {
            sb.append(i + " ");
        }
        System.out.println(sb);
    }

    static class Node {
        int num;
        int pos;

        public Node(int num, int pos) {
            this.num = num;
            this.pos = pos;
        }
    }
}