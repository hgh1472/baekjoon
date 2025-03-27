import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] dp;
    static boolean[] visited;
    static Node[] people;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        visited = new boolean[n + 1];
        dp = new int[n + 1][2];
        people = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            people[i] = new Node(i);
        }
        for (int i = 0; i < n - 1; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            people[input[0]].add(people[input[1]]);
            people[input[1]].add(people[input[0]]);
        }

        find(1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));

    }

    static void find(int x) {
        if (visited[x]) {
            return;
        }
        visited[x] = true;
        dp[x][1] = 1;

        for (Node friend : people[x].friends) {
            if (visited[friend.number]) {
                continue;
            }
            find(friend.number);
            dp[x][0] += dp[friend.number][1];
            dp[x][1] += Math.min(dp[friend.number][0], dp[friend.number][1]);
        }
    }

    static class Node {
        int number;
        List<Node> friends = new ArrayList<>();

        public Node(int number) {
            this.number = number;
        }

        public void add(Node friend) {
            friends.add(friend);
        }
    }

}
