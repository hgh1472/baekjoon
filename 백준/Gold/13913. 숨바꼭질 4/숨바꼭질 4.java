import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

//
class Main {
    static StringBuilder sb;
    static boolean[] isVisited = new boolean[200000];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);

        if (n > k) {
            System.out.println(n - k);
            for (int i = n; i >= k; i--) {
                sb.append(i + " ");
            }
        } else if (n == k) {
            System.out.println(0);
            sb.append(n);
        } else {
            Node result = bfs(n, k);
            System.out.println(result.time);
            printRoot(result);
        }
        System.out.println(sb);
    }

    private static Node bfs(int n, int k) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(n, 0, null));
        isVisited[n] = true;
        while (!q.isEmpty()) {
            Node poll = q.poll();
            if (poll.pos > 0 && !isVisited[poll.pos - 1]) {
                Node node = new Node(poll.pos - 1, poll.time + 1, poll);
                if (node.pos == k) {
                    return node;
                }
                q.add(node);
                isVisited[node.pos] = true;
            }

            if (poll.pos < k && !isVisited[poll.pos + 1]) {
                Node node = new Node(poll.pos + 1, poll.time + 1, poll);
                if (node.pos == k)
                    return node;
                q.add(node);
                isVisited[node.pos] = true;
            }

            if (poll.pos < k && !isVisited[poll.pos * 2]) {
                Node node = new Node(poll.pos * 2, poll.time + 1, poll);
                if (node.pos == k)
                    return node;
                q.add(node);
                isVisited[node.pos] = true;
            }
        }
        return null;
    }

    public static void printRoot(Node node) {
        if (node.root != null) {
            printRoot(node.root);
        }
        sb.append(node.pos + " ");
    }

    static class Node implements Comparable<Node> {
        int pos;
        int time;
        Node root;

        public Node(int pos, int time, Node root) {
            this.pos = pos;
            this.time = time;
            this.root = root;
        }

        @Override
        public int compareTo(Node o) {
            return time - o.time;
        }
    }
}
