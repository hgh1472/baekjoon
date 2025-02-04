import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] destinations;
    static int s, g, h;
    static int n, m, t;
    static Crossroad[] crossroads;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());
        for (int i = 0; i < test; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            n = input[0];
            m = input[1];
            t = input[2];

            crossroads = new Crossroad[n + 1];
            destinations = new int[t + 1];
            for (int j = 1; j <= n; j++) {
                crossroads[j] = new Crossroad(j);
            }
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            s = input[0];
            g = input[1];
            h = input[2];
            for (int j = 0; j < m; j++) {
                input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                crossroads[input[0]].nodes.add(new Node(input[1], input[2]));
                crossroads[input[1]].nodes.add(new Node(input[0], input[2]));
            }
            for (int j = 1; j <= t; j++) {
                destinations[j] = Integer.parseInt(br.readLine());
            }

            int[] ds = dijkstra(s);
            int[] dg = dijkstra(g);
            int[] dh = dijkstra(h);

            List<Integer> answer = new ArrayList<>();
            for (int j = 1; j <= t; j++) {
                if (ds[destinations[j]] >= 50000000) {
                    continue;
                }
                if (ds[g] + dg[h] + dh[destinations[j]] == ds[destinations[j]] || ds[h] + dh[g] + dg[destinations[j]] == ds[destinations[j]]) {
                    answer.add(destinations[j]);
                }
            }
            Collections.sort(answer);
            StringBuilder sb = new StringBuilder();
            for (Integer integer : answer) {
                sb.append(integer).append(" ");
            }
            System.out.println(sb);
        }
    }

    static int[] dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] distance = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            distance[i] = 50000000;
        }
        pq.add(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (distance[node.num] <= node.cost) {
                continue;
            }
            distance[node.num] = node.cost;
            for (Node n : crossroads[node.num].nodes) {
                int d = node.cost + n.cost;
                if (d < distance[n.num]) {
                    pq.add(new Node(n.num, d));
                }
            }
        }
        return distance;
    }

    static class Node implements Comparable<Node> {
        int num;
        int cost;

        public Node(int dst, int cost) {
            this.num = dst;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static class Crossroad {
        int num;
        List<Node> nodes = new ArrayList<>();

        public Crossroad(int num) {
            this.num = num;
        }
    }
}
