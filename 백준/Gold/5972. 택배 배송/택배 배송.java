import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {
    static int n, m;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        n = input[0];
        m = input[1];
        Vertex[] vertices = new Vertex[n+1];
        for (int i = 1; i <= n; i++) {
            vertices[i] = new Vertex(i);
        }

        for (int i = 0; i < m; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            vertices[input[0]].add(input[1], input[2]);
            vertices[input[1]].add(input[0], input[2]);
        }

        Node node = new Node(1, 0);
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(node);
        int[] distance = new int[n+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 1;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            Vertex vertex = vertices[cur.number];
            for (Integer v : vertex.edges.keySet()) {
                if (cur.cost + vertex.edges.get(v) < distance[v]) {
                    distance[v] = cur.cost + vertex.edges.get(v);
                    q.add(new Node(v, distance[v]));
                }
            }
        }

        System.out.println(distance[n]);

    }

    static class Node implements Comparable<Node> {
        int number;
        int cost;

        public Node(int number, int cost) {
            this.number = number;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static class Vertex {
        int number;
        Map<Integer, Integer> edges = new HashMap<>();

        public Vertex(int number) {
            this.number = number;
        }

        public void add(int number, int cost) {
            if (edges.containsKey(number)) {
                edges.put(number, Math.min(cost, edges.get(number)));
                return;
            }
            edges.put(number, cost);
        }
    }

}
