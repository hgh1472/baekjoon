import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = input[0];
        int r = input[1];
        int q = input[2];
        Node[] nodes = new Node[n+1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < n - 1; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            nodes[input[0]].subs.add(nodes[input[1]]);
            nodes[input[1]].subs.add(nodes[input[0]]);
        }

        visited = new boolean[n+1];
        dfs(nodes[r]);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            int target = Integer.parseInt(br.readLine());
            sb.append(nodes[target].count).append("\n");
        }
        System.out.print(sb);
    }

    static int dfs(Node node) {
        visited[node.number] = true;
        int count = 0;
        for (Node sub : node.subs) {
            if (visited[sub.number]) {
                continue;
            }
            count += dfs(sub);
        }
        node.count += count;
        return node.count;
    }

    static class Node {
        int number;
        int count = 1;
        List<Node> subs = new ArrayList<>();

        public Node(int number) {
            this.number = number;
        }
    }
}
