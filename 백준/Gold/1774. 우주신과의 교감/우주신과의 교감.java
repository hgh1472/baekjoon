import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[] root;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = input[0];
        m = input[1];
        root = new int[n+1];
        for (int i = 1; i <= n; i++) {
            root[i] = i;
        }

        Node[] nodes = new Node[n+1];
        for (int i = 1; i <= n; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            nodes[i] = new Node(input[0], input[1]);
        }

        PriorityQueue<Edge> q = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                double distance = Math.sqrt(Math.pow(Math.abs(nodes[i].x - nodes[j].x), 2) + Math.pow(Math.abs(nodes[i].y - nodes[j].y), 2));
                q.add(new Edge(i, j, distance));
            }
        }

        for (int i = 0; i < m; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            union(input[0], input[1]);
        }

        double result = 0;
        while (!q.isEmpty() && count < n - 1) {
            Edge e = q.poll();
            if (find(e.start) == find(e.end)) {
                continue;
            }
            union(e.start, e.end);
            result += e.cost;
        }
        System.out.printf("%.2f\n", result);
    }

    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return;
        }
        count++;
        root[rootX] = rootY;
    }

    static int find(int x) {
        if (root[x] != x) {
            root[x] = find(root[x]);
        }
        return root[x];
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {
        int start, end;
        double cost;

        public Edge(int start, int end, double cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e) {
            return Double.compare(this.cost, e.cost);
        }
    }

}
