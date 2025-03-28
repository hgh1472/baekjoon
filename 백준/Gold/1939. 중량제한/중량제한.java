import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = input[0];
        Map<Integer, Integer>[] edges = new Map[n+1];
        for (int i = 1; i <= n; i++) {
            edges[i] = new HashMap<>();
        }
        int m = input[1];

        for (int i = 0; i < m; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (edges[input[0]].containsKey(input[1])) {
                Integer cost = edges[input[0]].get(input[1]);
                if (cost < input[2]) {
                    edges[input[0]].put(input[1], input[2]);
                    edges[input[1]].put(input[0], input[2]);
                }
            }
            else {
                edges[input[0]].put(input[1], input[2]);
                edges[input[1]].put(input[0], input[2]);
            }
        }

        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int start = input[0];
        int end = input[1];

        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(start, Integer.MAX_VALUE));

        boolean[] visited = new boolean[n + 1];
        while (!q.isEmpty()) {
            Node node = q.poll();
            if (node.number == end) {
                System.out.println(node.cost);
                return;
            }
            if (visited[node.number]) {
                continue;
            }
            visited[node.number] = true;
            for (Integer island : edges[node.number].keySet()) {
                Integer cost = edges[node.number].get(island);
                q.add(new Node(island, Math.min(node.cost, cost)));
            }
        }

    }

    static class Node implements Comparable<Node> {
        int number;
        int cost;

        public Node(int number, int cost) {
            this.number = number;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node node) {
            return node.cost - this.cost;
        }
    }

}
