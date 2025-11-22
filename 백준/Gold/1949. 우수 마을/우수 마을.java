import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Tree DP
 *
 */
public class Main {
    static boolean[] visited;
    static int n;
    static int[] people;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        visited = new boolean[n];
        people = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Node[] nodes = new Node[n];
        dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
        }
        for (int i = 0; i < n - 1; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            nodes[input[0] - 1].subs.add(nodes[input[1] - 1]);
            nodes[input[1] - 1].subs.add(nodes[input[0] - 1]);
        }
        dfs(nodes[0]);

        System.out.println(Math.max(dp[0][0], dp[0][1]));
    }

    static void dfs(Node node) {
        visited[node.number] = true;
        dp[node.number][0] = 0;
        dp[node.number][1] = people[node.number];
        for (Node sub : node.subs) {
            if (visited[sub.number]) {
                continue;
            }
            dfs(sub);
            dp[node.number][0] += Math.max(dp[sub.number][0], dp[sub.number][1]);
            dp[node.number][1] += dp[sub.number][0];
        }
    }

    static class Node {
        int number;
        List<Node> subs = new ArrayList<>();

        public Node(int number) {
            this.number = number;
        }
    }
}
