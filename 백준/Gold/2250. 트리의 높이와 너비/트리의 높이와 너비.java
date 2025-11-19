import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static Node[] nodes;
    static Deque<Node> q;
    static int maxLevel = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        q = new ArrayDeque<>();
        nodes = new Node[n+1];
        boolean[] hasParent = new boolean[n+1];
        for (int i = 0; i < n; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            nodes[input[0]] = new Node(input[0], input[1], input[2]);
            if (input[1] != -1) {
                hasParent[input[1]] = true;
            }
            if (input[2] != -1) {
                hasParent[input[2]] = true;
            }
        }

        int root = 0;
        for (int i = 1; i <= n; i++) {
            if (!hasParent[i]) {
                root = i;
                break;
            }
        }

        dfs(nodes[root], 1);

        int[][] info = new int[maxLevel+1][2];
        for (int i = 1; i <= maxLevel; i++) {
            info[i][0] = Integer.MAX_VALUE;
            info[i][1] = Integer.MIN_VALUE;
        }
        int count = 1;
        while (!q.isEmpty()) {
            Node poll = q.poll();
            if (info[poll.level][0] > count) {
                info[poll.level][0] = count;
            }
            if (info[poll.level][1] < count) {
                info[poll.level][1] = count;
            }
            count++;
        }

        int answer = 0;
        int answerLevel = 0;
        for (int i = 1; i <= maxLevel; i++) {
            if (answer < info[i][1] - info[i][0] + 1) {
                answer = info[i][1] - info[i][0] + 1;
                answerLevel = i;
            }
        }

        System.out.println(answerLevel + " " + answer);
    }

    static void dfs(Node node, int level) {
        node.level = level;
        maxLevel = Math.max(maxLevel, level);
        if (node.left != -1) {
            dfs(nodes[node.left], level + 1);
        }
        q.add(node);
        if (node.right != -1) {
            dfs(nodes[node.right], level + 1);
        }
    }

    static class Node {
        int number;
        int left, right;
        int level;

        public Node(int number, int left, int right) {
            this.number = number;
            this.left = left;
            this.right = right;
        }
    }
}
