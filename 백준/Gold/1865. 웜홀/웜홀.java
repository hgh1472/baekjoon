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

            int[][] edges = new int[v + 1][v + 1];
            for (int i = 1; i <= v; i++) {
                for (int j = 1; j <= v; j++) {
                    edges[i][j] = Integer.MAX_VALUE;
                }
            }
            for (int i = 0; i < e; i++) {
                s = br.readLine().split(" ");
                int v1 = Integer.parseInt(s[0]);
                int v2 = Integer.parseInt(s[1]);
                int c = Integer.parseInt(s[2]);

                edges[v1][v2] = Math.min(c, edges[v1][v2]);
                edges[v2][v1] = Math.min(c, edges[v2][v1]);
            }

            for (int i = 0; i < w; i++) {
                s = br.readLine().split(" ");
                int v1 = Integer.parseInt(s[0]);
                int v2 = Integer.parseInt(s[1]);
                int c = Integer.parseInt(s[2]);
                edges[v1][v2] = -c;
            }

            check(v, edges);
        }
    }

    private static void check(int v, int[][] edges) {
        int[] visited = new int[v + 1];
        for (int j = 1; j <= v; j++) {
            visited[j] = 500000000;
        }

        visited[1] = 0;

        for (int j = 0; j < v - 1; j++) {
            for (int k = 1; k <= v; k++) {
                for (int t = 1; t <= v; t++) {
                    if (edges[k][t] == Integer.MAX_VALUE)
                        continue;
                    int tmp =  visited[k] + edges[k][t];
                    visited[t] = Math.min(visited[t], tmp);
                }
            }
        }
        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                if (edges[i][j] == Integer.MAX_VALUE)
                    continue;
                int tmp = visited[i] + edges[i][j];
                if (tmp < visited[j]) {
                    System.out.println("YES");
                    return;
                }
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

        public Edge(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
