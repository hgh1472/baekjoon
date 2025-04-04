import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Map<Integer, Set<Node>> levels = new HashMap<>();
    static Node[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int h = (int) (Math.log(n) / Math.log(2));
        int[][] parents = new int[n+1][h+1];
        boolean[] visited = new boolean[n + 1];
        nodes = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < n-1; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            nodes[input[0]].addEdge(nodes[input[1]]);
            nodes[input[1]].addEdge(nodes[input[0]]);
        }

        Node root = nodes[1];
        root.level = 1;
        Queue<Node> q = new ArrayDeque<>();
        q.add(root);
        visited[root.number] = true;
        parents[1][0] = 1;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            for (Node node : cur.edges) {
                if (visited[node.number]) {
                    continue;
                }
                q.add(node);
                visited[node.number] = true;
                cur.addChildren(node);
                node.level = cur.level + 1;
                node.parent = cur;
                parents[node.number][0] = cur.number;
            }
        }

        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= n; j++) {
                parents[j][i] = parents[parents[j][i-1]][i-1];
            }
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Node n1 = nodes[input[0]];
            Node n2 = nodes[input[1]];

            if (n1.level < n2.level) {
                Node tmp = n1;
                n1 = n2;
                n2 = tmp;
            }

            if (n1.level != n2.level) {
                for (int j = h; j >= 0; j--) {
                    if (nodes[parents[n1.number][j]].level >= n2.level) {
                        n1 = nodes[parents[n1.number][j]];
                    }
                }
            }

            if (n1.number == n2.number) {
                System.out.println(n1.number);
                continue;
            }
            for (int j = h; j >= 0; j--) {
                if (parents[n1.number][j] != parents[n2.number][j]) {
                    n1 = nodes[parents[n1.number][j]];
                    n2 = nodes[parents[n2.number][j]];
                }
            }

            System.out.println(parents[n1.number][0]);
        }

    }

    static class Node {
        int number;
        int level;
        Node parent;
        List<Node> edges = new ArrayList<>();
        List<Node> childeren = new ArrayList<>();

        public Node(int number) {
            this.number = number;
        }

        public void addEdge(Node node) {
            this.edges.add(node);
        }

        public void addChildren(Node node) {
            this.childeren.add(node);
        }
    }

}
