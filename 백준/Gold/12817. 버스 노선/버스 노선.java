import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static long[] sub;
    static Node[] nodes;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        sub = new long[n+1];
        nodes = new Node[n+1];
        visited = new boolean[n+1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(i);
        }
        for (int i = 0; i < n-1; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            nodes[input[0]].adj.add(nodes[input[1]]);
            nodes[input[1]].adj.add(nodes[input[0]]);
        }
        dfs(1);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            long avoid = 0;
            for (Node node : nodes[i].adj) {
                if (nodes[i].parent == node) {
                    avoid += (n - sub[nodes[i].number]) * (n - sub[nodes[i].number] - 1);
                }
                else {
                    avoid += (sub[node.number]) * (sub[node.number] - 1);
                }
            }
            sb.append((long) n * (n-1) - avoid).append("\n");
        }
        System.out.print(sb);
    }

    static long dfs(int number) {
        sub[number] = 1;
        visited[number] = true;
        for (Node node : nodes[number].adj) {
            if (visited[node.number]) {
                continue;
            }
            sub[number] += dfs(node.number);
            node.parent = nodes[number];
        }
        return sub[number];
    }

    static class Node {
        int number;
        List<Node> adj = new ArrayList<>();
        Node parent;

        public Node(int number) {
            this.number = number;
        }
    }
}
