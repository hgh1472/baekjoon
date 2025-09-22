import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        Node[] nodes = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            int num1 = Integer.parseInt(input[0]);
            int num2 = Integer.parseInt(input[1]);
            nodes[num1].add(nodes[num2]);
        }

        for (int i = 1; i <= n; i++) {
            Node start = nodes[i];
            boolean[] visited = new boolean[n + 1];
            int out = 0;
            for (Node outNode : start.edges) {
                out += dfs(outNode, visited);
            }
            start.out = out;
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (nodes[i].in + nodes[i].out == n - 1) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    static int dfs(Node start, boolean[] visited) {
        if (visited[start.number]) {
            return 0;
        }
        int out = 1;
        start.in++;
        visited[start.number] = true;
        for (Node node : start.edges) {
            out += dfs(node, visited);
        }
        return out;
    }

    static class Node {
        int number;
        List<Node> edges = new ArrayList<>();
        int in = 0;
        int out = 0;

        Node(int number) {
            this.number = number;
        }

        void add(Node node) {
            this.edges.add(node);
        }
    }
}