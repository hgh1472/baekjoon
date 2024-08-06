import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String[] s = br.readLine().split(" ");
        Stack<Node> stack = new Stack<>();
        stack.push(new Node(0, 0));
        for (int i = 0; i < n; i++) {
            int tower = Integer.parseInt(s[i]);
            while (!stack.isEmpty() && stack.peek().number < tower) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                System.out.print("0 ");
            } else {
                System.out.print(stack.peek().pos + " ");
            }
            stack.push(new Node(tower, i + 1));
        }
    }

    static class Node {
        int number;
        int pos;

        public Node(int number, int pos) {
            this.number = number;
            this.pos = pos;
        }
    }
}