import java.io.*;
import java.util.*;

public class Main {
    static int v, e, k;
    static Integer[] distance;
    static List<List<Edge>> graph = new ArrayList<>();
    static List<Edge> edges = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(br.readLine());
        distance = new Integer[v + 1];
        for (int i = 0; i <= v; i++) {
            if (i == k) {
                distance[i] = 0;
            }
            else {
                distance[i] = 4000000;
            }
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int org = Integer.parseInt(st.nextToken());
            int dst = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            Edge edge = new Edge(org, dst, w);
            edges.add(edge);
            graph.get(org).add(edge);
        }
        dijkstra();
        for (int i = 1; i <= v; i++) {
            if (distance[i] >= 4000000) {
                System.out.println("INF");
            } else {
                System.out.println(distance[i]);
            }
        }
    }

    static class Edge implements Comparable<Edge>{
        public int org;
        public int dst;
        public int weight;

        public Edge(int org, int dst, int weight) {
            this.org = org;
            this.dst = dst;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            if (this.weight < e.weight) {
                return -1;
            } else if (this.weight == e.weight) {
                return 0;
            }
            return 1;
        }
    }

    static class Node implements Comparable<Node> {
        int node;
        int d;

        public Node(int node, int d) {
            this.node = node;
            this.d = d;
        }


        @Override
        public int compareTo(Node o) {
            if (this.d < o.d) {
                return -1;
            } else if (this.d == o.d) {
                return 0;
            }
            return 1;
        }
    }
    static void dijkstra() {
        PriorityQueue<Node> heap = new PriorityQueue<>();
        heap.add(new Node(k, 0));

        while (!heap.isEmpty()) {
            Node poll = heap.poll();
            if (poll.d > distance[poll.node]) {
                continue;
            }
            for (Edge edge : graph.get(poll.node)) {
                if (distance[edge.org] + edge.weight < distance[edge.dst]) {
                    distance[edge.dst] = distance[edge.org] + edge.weight;
                    heap.add(new Node(edge.dst, distance[edge.dst]));
                }
            }
        }
    }
}