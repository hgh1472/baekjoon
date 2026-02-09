import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[] root;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        root = new int[n];
        for (int i = 0; i < n; i++) {
            root[i] = i;
        }

        PriorityQueue<Edge> q = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < input.length(); j++) {
                char c = input.charAt(j);
                if (c == '0') {
                    continue;
                }
                if ('a' <= c && c <= 'z') {
                    q.add(new Edge(i, j, c - 'a' + 1));
                }
                else {
                    q.add(new Edge(i, j, c - 'A' + 27));
                }
            }
        }

        int answer = 0;
        int count = 0;
        while (!q.isEmpty()) {
            Edge poll = q.poll();
            int rootA = getRoot(poll.from);
            int rootB = getRoot(poll.to);
            if (rootA == rootB) {
                answer += poll.cost;
                continue;
            }
            union(rootA, rootB);
            count++;
        }
        if (count != n - 1) {
            System.out.println(-1);
        }
        else {
            System.out.println(answer);
        }
    }

    static int getRoot(int x) {
        if (root[x] != x) {
            root[x] = getRoot(root[x]);
        }
        return root[x];
    }

    static void union(int a, int b) {
        root[a] = b;
    }

    static class Edge implements Comparable<Edge> {
        int from, to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
}
