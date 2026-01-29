import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] parent;
    static int[][] capacity;
    static int[][] flow;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Node[] nodes = new Node[52];
        capacity = new int[52][52];
        flow = new int[52][52];
        parent = new int[52];
        for (int i = 0; i < 52; i++) {
            nodes[i] = new Node(i);
        }
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            int a = getNumber(input[0].charAt(0));
            int b = getNumber(input[1].charAt(0));
            capacity[a][b] += Integer.parseInt(input[2]);
            capacity[b][a] += Integer.parseInt(input[2]);

            nodes[a].edges.add(nodes[b]);
            nodes[b].edges.add(nodes[a]);
        }

        int answer = 0;
        while (true) {
            parent = new int[52];
            Arrays.fill(parent, -1);
            parent[0] = 0;
            ArrayDeque<Node> q = new ArrayDeque<>();
            q.add(nodes[0]);
            while (!q.isEmpty() && parent[25] == -1) {
                Node poll = q.poll();
                for (Node edge : poll.edges) {
                    if (parent[edge.num] == -1 && isFlowable(poll.num, edge.num)) {
                        parent[edge.num] = poll.num;
                        q.add(edge);
                    }
                }
            }
            if (parent[25] == -1) {
                break;
            }

            int bottleneck = Integer.MAX_VALUE;
            int v = 25;
            while (v != 0) {
                int u = parent[v];
                bottleneck = Math.min(bottleneck, capacity[u][v] - flow[u][v]);
                v = u;
            }

            v = 25;
            while (v != 0) {
                int u = parent[v];
                flow[u][v] += bottleneck;
                flow[v][u] -= bottleneck;
                v = u;
            }
            answer += bottleneck;
        }
        System.out.println(answer);
    }

    static boolean isFlowable(int from, int to) {
        return capacity[from][to] - flow[from][to] > 0;
    }

    static int getNumber(char c) {
        if ('A' <= c && c <= 'Z') {
            return c - 'A';
        }
        if ('a' <= c && c <= 'z') {
            return c - 'a' + 26;
        }
        return -1;
    }

    static class Node {
        int num;
        List<Node> edges = new ArrayList<>();

        public Node(int num) {
            this.num = num;
        }
    }
}
