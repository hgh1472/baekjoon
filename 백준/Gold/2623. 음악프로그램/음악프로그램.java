import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = input[0];
        int m = input[1];
        Node[] nodes = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < m; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 1; j < input.length; j++) {
                Node current = nodes[input[j]];
                for (int k = j + 1 ; k < input.length; k++) {
                    current.out.add(nodes[input[k]]);
                }
                current.in += j - 1;
            }
        }

        PriorityQueue<Node> q = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            if (nodes[i].in == 0) {
                q.add(nodes[i]);
            }
        }

        int[] visited = new int[n];
        int count = 0;
        while (!q.isEmpty()) {
            Node node = q.poll();
            visited[count++] = node.num;
            for (Node out : node.out) {
                out.in--;
                if (out.in == 0) {
                    q.add(out);
                }
            }
        }

        if (count != n) {
            System.out.println(0);
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(visited[i]).append("\n");
        }
        System.out.println(sb);
    }

    static class Node implements Comparable<Node> {
        int num;
        List<Node> out = new ArrayList<>();
        int in;

        public Node(int num) {
            this.num = num;
        }

        @Override
        public int compareTo(Node o) {
            return this.in - o.in;
        }
    }
}
