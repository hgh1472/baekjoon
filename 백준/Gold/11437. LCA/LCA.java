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
            }
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Node n1 = nodes[input[0]];
            Node n2 = nodes[input[1]];

            int minLevel = Math.min(n1.level, n2.level);
            while (n1.level != minLevel) {
                n1 = n1.parent;
            }
            while (n2.level != minLevel) {
                n2 = n2.parent;
            }

            while (!n1.equals(n2)) {
                n1 = n1.parent;
                n2 = n2.parent;
            }

            System.out.println(n1.number);
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
