import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[] root;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        boolean[] isMan = new boolean[n+1];
        root = new int[n+1];
        input = br.readLine().split(" ");
        for (int i = 0; i < input.length; i++) {
            if (input[i].equals("M")) {
                isMan[i+1] = true;
            }
        }
        for (int i = 1; i <= n; i++) {
            root[i] = i;
        }

        PriorityQueue<Edge> q = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            int c = Integer.parseInt(input[2]);
            if (isMan[a] == isMan[b]) {
                continue;
            }
            q.add(new Edge(a, b, c));
        }

        int answer = 0;
        while (!q.isEmpty()) {
            Edge poll = q.poll();
            if (getRoot(poll.a) == getRoot(poll.b)) {
                continue;
            }
            answer += poll.distance;
            union(poll.a, poll.b);
        }

        int allRoot = getRoot(1);
        for (int i = 2; i <= n; i++) {
            if (getRoot(i) != allRoot) {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(answer);
    }

    static int getRoot(int x) {
        if (root[x] != x) {
            root[x] = getRoot(root[x]);
        }
        return root[x];
    }

    static void union(int a, int b) {
        int rootA = getRoot(a);
        int rootB = getRoot(b);

        root[rootA] = rootB;
    }

    static class Edge implements Comparable<Edge> {
        int a, b;
        int distance;

        public Edge(int a, int b, int distance) {
            this.a = a;
            this.b = b;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge o) {
            return this.distance - o.distance;
        }
    }
}
