import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class Main {
    static List<Integer> order;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);

        List<Node> problem = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            problem.add(new Node(i));
        }
        for (int i = 0; i < m; i++) {
            s = br.readLine().split(" ");
            int first = Integer.parseInt(s[0]);
            int second = Integer.parseInt(s[1]);
            problem.get(second - 1).count++;
            problem.get(first - 1).nodes.add(problem.get(second - 1));
        }
        Queue<Node> q = new PriorityQueue<>();
        order = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Node node = problem.get(i);
            if (node.count != 0)
                continue;
            q.add(node);
        }
        while (!q.isEmpty()) {
            Node node = q.poll();
            order.add(node.number);
            while (!node.nodes.isEmpty()) {
                Node poll = node.nodes.poll();
                poll.count--;
                if (poll.count == 0) {
                    q.add(poll);
                }
            }
        }
        for (int i = 0; i < order.size() - 1; i++)
            System.out.print(order.get(i) + " ");
        System.out.println(order.get(n - 1));
    }

    static class Node implements Comparable<Node> {
        public int number;
        public int count;
        public Queue<Node> nodes;

        public Node(int number) {
            this.number = number;
            count = 0;
            this.nodes = new PriorityQueue<>();
        }

        @Override
        public int compareTo(Node o) {
            return this.number - o.number;
        }
    }
}