import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[] degrees;
    static Node[] nodes;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nodes = new Node[n+1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(i);
        }
        degrees = new int[n+1];
        for (int i = 0; i < n; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            nodes[input[0]].edges.add(nodes[input[1]]);
            nodes[input[1]].edges.add(nodes[input[0]]);
            degrees[input[0]]++;
            degrees[input[1]]++;
        }

        ArrayDeque<Node> q = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (degrees[i] == 1) {
                q.add(nodes[i]);
            }
        }
        while (!q.isEmpty()) {
            Node poll = q.poll();
            for (Node edge : poll.edges) {
                degrees[edge.number]--;
                if (degrees[edge.number] == 1) {
                    q.add(edge);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(bfs(nodes[i])).append(" ");
        }
        System.out.println(sb);
    }

    static int bfs(Node start) {
        ArrayDeque<Info> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n+1];
        q.add(new Info(start.number, 0));
        while (!q.isEmpty()) {
            Info poll = q.poll();
            if (visited[poll.node]) {
                continue;
            }
            visited[poll.node] = true;
            if (degrees[poll.node] == 2) {
                return poll.distance;
            }
            Node node = nodes[poll.node];
            for (Node edge : node.edges) {
                if (visited[edge.number]) {
                    continue;
                }
                q.add(new Info(edge.number, poll.distance+1));
            }
        }
        return -1;
    }

    static class Info {
        int node;
        int distance;

        public Info(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    static class Node {
        int number;
        List<Node> edges = new ArrayList<>();

        public Node(int number) {
            this.number = number;
        }
    }
}
