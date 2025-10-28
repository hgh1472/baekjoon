import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    static int[][] board;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = input[0];
        int m = input[1];
        int[][] answer = new int[n+1][m+1];
        Spot[] spots = new Spot[n+1];
        for (int i = 1; i <= n; i++) {
            spots[i] = new Spot(i);
        }

        for (int i = 0; i < m; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            spots[input[0]].edges.add(new Edge(spots[input[1]], input[2]));
            spots[input[1]].edges.add(new Edge(spots[input[0]], input[2]));
            answer[input[0]][input[1]] = input[1];
            answer[input[1]][input[0]] = input[0];
        }

        for (int i = 1; i <= n; i++) {
            PriorityQueue<Node> q = new PriorityQueue<>();
            for (Edge edge : spots[i].edges) {
                q.add(new Node(edge.dst.number, edge.dst.number, edge.time));
            }

            int[] visited = new int[n+1];
            while (!q.isEmpty()) {
                Node poll = q.poll();
                if (visited[poll.number] != 0) {
                    continue;
                }
                visited[poll.number] = poll.time;
                answer[i][poll.number] = poll.start;
                for (Edge edge : spots[poll.number].edges) {
                    q.add(new Node(edge.dst.number, poll.start, poll.time + edge.time));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j || answer[i][j] == 0) {
                    sb.append("-");
                }
                else {
                    sb.append(answer[i][j]);
                }
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static class Node implements Comparable<Node> {
        int number;
        int start;
        int time;

        public Node(int number, int start, int time) {
            this.number = number;
            this.start = start;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }

    static class Spot {
        int number;
        List<Edge> edges = new ArrayList<>();

        public Spot(int number) {
            this.number = number;
        }
    }

    static class Edge {
        Spot dst;
        int time;

        public Edge(Spot dst, int time) {
            this.dst = dst;
            this.time = time;
        }
    }
}
