import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        boolean[] visited = new boolean[n + 1];

        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(0, new ArrayList<>(), n));
        visited[n] = true;
        while (!q.isEmpty()) {
            Node node = q.poll();
            if (node.num == 1) {
                System.out.println(node.history.size());
                StringBuilder sb = new StringBuilder();
                for (Integer i : node.history) {
                    sb.append(i).append(" ");
                }
                sb.append(node.num);
                System.out.println(sb);
                break;
            }
            if (node.num % 3 == 0 && !visited[node.num / 3]) {
                q.add(new Node(node.count + 1, makeClone(node), node.num / 3));
                visited[node.num / 3] = true;
            }
            if (node.num % 2 == 0 && !visited[node.num / 2]) {
                q.add(new Node(node.count + 1, makeClone(node), node.num / 2));
                visited[node.num / 2] = true;
            }
            if (!visited[node.num - 1]) {
                q.add(new Node(node.count + 1, makeClone(node), node.num - 1));
                visited[node.num - 1] = true;
            }
        }
    }

    static List<Integer> makeClone(Node node) {
        List<Integer> list = new ArrayList<>(node.history);
        list.add(node.num);
        return list;
    }

    static class Node implements Comparable<Node> {
        int count;
        List<Integer> history;
        int num;

        public Node(int count, List<Integer> history, int num) {
            this.count = count;
            this.history = history;
            this.num = num;
        }

        public int compareTo(Node o) {
            return this.count - o.count;
        }
    }
}
