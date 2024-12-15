import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            String[] input = br.readLine().split(" ");
            int start = Integer.parseInt(input[0]);
            int end = Integer.parseInt(input[1]);
            int cost = Integer.parseInt(input[2]);
            graph.get(start).add(new Edge(start, end, cost));
        }
        String[] input = br.readLine().split(" ");
        int start = Integer.parseInt(input[0]);
        int end = Integer.parseInt(input[1]);

        PriorityQueue<Node> q = new PriorityQueue<>();
        boolean[] isVisited = new boolean[n + 1];
        q.offer(new Node(start, 0, new ArrayList<>()));
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.position == end) {
                System.out.println(cur.cost);
                System.out.println(cur.history.size());
                List<Integer> history = cur.history;
                StringBuilder sb = new StringBuilder();
                for (Integer i : history) {
                    sb.append(i);
                    sb.append(" ");
                }
                System.out.println(sb);
                return;
            }
            if (isVisited[cur.position]) continue;
            isVisited[cur.position] = true;
            List<Edge> edges = graph.get(cur.position);
            for (Edge edge : edges) {
                Node node = new Node(edge.end, cur.cost + edge.cost, cur.history);
                if (isVisited[edge.end]) continue;
                q.offer(node);
            }
        }
    }

    static class Node implements Comparable<Node> {
        int position;
        int cost;
        List<Integer> history;

        public Node(int position, int cost, List<Integer> history) {
            this.position = position;
            this.cost = cost;
            this.history = new ArrayList<>();
            this.history.addAll(history);
            this.history.add(position);
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
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
