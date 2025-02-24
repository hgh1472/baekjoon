import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static Point[] points;
    static int[] root;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        points = new Point[n];
        root = new int[n];
        for (int i = 0; i < n; i++) {
            root[i] = i;
        }
        for (int i = 0; i < n; i++) {
            double[] input = Arrays.stream(br.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
            points[i] = new Point(input[0], input[1]);
        }

        PriorityQueue<Edge> q = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double d = Math.sqrt(Math.pow(Math.abs(points[i].x - points[j].x), 2) + Math.pow(Math.abs(points[i].y - points[j].y), 2));
                q.add(new Edge(i, j, d));
            }
        }

        int count = 0;
        double answer = 0;
        while (count < n - 1) {
            Edge e = q.poll();
            int x = find(e.start);
            int y = find(e.end);
            if (x == y) {
                continue;
            }
            union(x, y);
            count++;
            answer += e.distance;
        }
        System.out.printf("%.2f\n", answer);
    }

    static int find(int x) {
        if (root[x] != x) {
            root[x] = find(root[x]);
        }
        return root[x];
    }

    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) {
            return;
        }
        root[rootX] = rootY;
    }

    static class Point {
        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {
        int start;
        int end;
        double distance;

        public Edge(int start, int end, double distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.distance, o.distance);
        }
    }
}
