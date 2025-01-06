import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import org.w3c.dom.Node;

class Main {
    static int n, m, r;
    static int[] items;
    static Map<Integer, List<Edge>> edges = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        r = Integer.parseInt(input[2]);

        input = br.readLine().split(" ");
        items = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(input[i-1]);
            edges.put(i, new ArrayList<>());
        }

        for (int i = 0; i < r; i++) {
            int[] e = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            edges.get(e[0]).add(new Edge(e[1], e[2]));
            edges.get(e[1]).add(new Edge(e[0], e[2]));
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            answer = Math.max(answer, calculate(i));
        }
        System.out.println(answer);
    }

    static int calculate(int start) {
        int[] visited = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            visited[i] = Integer.MAX_VALUE;
        }
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(start, 0));
        int item = items[start];
        while (!q.isEmpty()) {
            Node node = q.poll();
            if (node.distance > visited[node.num])
                continue;
            visited[node.num] = node.distance;
            for (Edge e : edges.get(node.num)) {
                int d = node.distance + e.weight;
                if (d > m)
                    continue;
                if (visited[e.dest] == Integer.MAX_VALUE) {
                    item += items[e.dest];
                    visited[e.dest] = d;
                }
                q.add(new Node(e.dest, d));
            }
        }
        return item;
    }

    static class Node implements Comparable<Node> {
        int num;
        int distance;

        public Node(int num, int distance) {
            this.num = num;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node node) {
            return this.distance - node.distance;
        }
    }

    static class Edge {
        int dest;
        int weight;

        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }
}
