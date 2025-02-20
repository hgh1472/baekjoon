import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dp;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        boolean[] visited = new boolean[n + 1];
        dp = new int[n + 1];

        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(0, n));
        visited[n] = true;
        while (!q.isEmpty()) {
            Node node = q.poll();
            if (node.num == 1) {
                Stack<Integer> stack = new Stack<>();
                dfs(stack, 1);
                System.out.println(stack.size() - 1);
                StringBuilder sb = new StringBuilder();
                while (!stack.isEmpty()) {
                    sb.append(stack.pop()).append(" ");
                }
                System.out.println(sb);
                break;
            }
            if (node.num % 3 == 0 && !visited[node.num / 3]) {
                q.add(new Node(node.count + 1, node.num / 3));
                dp[node.num / 3] = node.num;
                visited[node.num / 3] = true;
            }
            if (node.num % 2 == 0 && !visited[node.num / 2]) {
                q.add(new Node(node.count + 1, node.num / 2));
                dp[node.num / 2] = node.num;
                visited[node.num / 2] = true;
            }
            if (!visited[node.num - 1]) {
                q.add(new Node(node.count + 1, node.num - 1));
                dp[node.num - 1] = node.num;
                visited[node.num - 1] = true;
            }
        }
    }

    static void dfs(Stack<Integer> stack, int num) {
        stack.push(num);
        if (num == n) {
            return;
        }
        dfs(stack, dp[num]);
    }

    static class Node implements Comparable<Node> {
        int count;
        int num;

        public Node(int count, int num) {
            this.count = count;
            this.num = num;
        }

        public int compareTo(Node o) {
            return this.count - o.count;
        }
    }
}
