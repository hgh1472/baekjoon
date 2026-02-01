import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, k;
    static int[] root;
    static boolean[] isSource;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = input[0];
        m = input[1];
        k = input[2];
        root = new int[n+1];
        isSource = new boolean[n+1];
        for (int i = 1; i <= n; i++) {
            root[i] = i;
        }
        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i : input) {
            isSource[i] = true;
        }

        PriorityQueue<Node> q = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            q.add(new Node(input[0], input[1], input[2]));
        }

        int answer = 0;
        while (!q.isEmpty()) {
            Node poll = q.poll();
            int rootA = getRoot(poll.a);
            int rootB = getRoot(poll.b);
            if (rootA == rootB) {
                continue;
            }
            if (isSource[rootA] && isSource[rootB]) {
                continue;
            }
            union(rootA, rootB);
            answer += poll.cost;
        }
        System.out.println(answer);
    }

    static void union(int a, int b) {
        if (isSource[a]) {
            root[b] = a;
        }
        else {
            root[a] = b;
        }
    }

    static int getRoot(int x) {
        if (root[x] != x) {
            root[x] = getRoot(root[x]);
        }
        return root[x];
    }

    static class Node implements Comparable<Node> {
        int a, b, cost;

        public Node(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
