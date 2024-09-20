import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            String[] s = br.readLine().split(" ");
            int v = Integer.parseInt(s[0]);
            int e = Integer.parseInt(s[1]);
            int w = Integer.parseInt(s[2]);

            List<Edge> edges = new ArrayList<>();
            for (int i = 0; i < e; i++) {
                s = br.readLine().split(" ");
                int v1 = Integer.parseInt(s[0]);
                int v2 = Integer.parseInt(s[1]);
                int c = Integer.parseInt(s[2]);

                edges.add(new Edge(v1, v2, c));
                edges.add(new Edge(v2, v1, c));
            }

            for (int i = 0; i < w; i++) {
                s = br.readLine().split(" ");
                int v1 = Integer.parseInt(s[0]);
                int v2 = Integer.parseInt(s[1]);
                int c = Integer.parseInt(s[2]);

                edges.add(new Edge(v1, v2, -c));
            }

            check(v, edges);
        }
    }

    private static void check(int v, List<Edge> edges) {
        int[] visited = new int[v + 1];
        for (int j = 1; j <= v; j++) {
            visited[j] = 500000000;
        }

        visited[1] = 0;

        for (int j = 0; j < v - 1; j++) {
            for (Edge edge : edges) {
                visited[edge.end] = Math.min(visited[edge.end], visited[edge.start] + edge.cost);
            }
        }

        for (Edge edge : edges) {
            if (visited[edge.end] > visited[edge.start] + edge.cost) {
                System.out.println("YES");
                return;
            }
        }

        System.out.println("NO");
    }

    static class Vertex {
        int num;
        Map<Integer, Integer> edges = new HashMap<>();
        Map<Integer, Integer> wormholes = new HashMap<>();

        public Vertex(int num) {
            this.num = num;
        }

        @Override
        public String toString() {
            return "Vertex{" +
                "num=" + num +
                ", edges=" + edges +
                ", wormholes=" + wormholes +
                '}';
        }
    }

    static class Edge {
        int start;
        int end;
        int cost;

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
}
