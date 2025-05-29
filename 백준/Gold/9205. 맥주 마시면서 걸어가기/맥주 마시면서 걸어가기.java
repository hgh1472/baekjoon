import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[][] map = new char[12][6];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Node[] nodes = new Node[n+2];
            nodes[0] = new Node(input[0], input[1]);
            for (int j = 0; j < n; j++) {
                input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                nodes[1+j] = new Node(input[0], input[1]);
            }
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            nodes[n+1] = new Node(input[0], input[1]);

            boolean result = isPossible(n, nodes);
            if (result) {
                sb.append("happy\n");
            }
            else {
                sb.append("sad\n");
            }

        }

        System.out.println(sb);

    }

    private static boolean isPossible(int n, Node[] nodes) {
        boolean[] visited = new boolean[n +2];

        Queue<Node> q = new ArrayDeque<>();
        q.add(nodes[0]);
        visited[0] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();
            for (int j = 0; j <n +2; j++) {
                Node next = nodes[j];
                if (Math.abs(node.x - next.x) + Math.abs(node.y - next.y) > 1000) {
                    continue;
                }
                if (j == n +1) {
                    return true;
                }
                if (visited[j]) {
                    continue;
                }
                q.add(next);
                visited[j] = true;
            }
        }
        return false;
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
