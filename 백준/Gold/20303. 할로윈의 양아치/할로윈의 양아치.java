import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Group> list = new ArrayList<>();
    static boolean[] visited;
    static int n, m, k;
    static int[] counts;
    static Node[] nodes;
    static int idx = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = input[0];
        m = input[1];
        k = input[2];
        nodes = new Node[n];
        visited = new boolean[n];

        counts = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i, counts[i]);
        }

        for (int i = 0; i < m; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            nodes[input[0]-1].nodes.add(nodes[input[1]-1]);
            nodes[input[1]-1].nodes.add(nodes[input[0]-1]);
        }

        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            Group group = bfs(i);
            list.add(group);
        }

        int[][] sack = new int[list.size()+1][k];
        for (int i = 0; i < list.size(); i++) {
            Group group = list.get(i);
            for (int j = 1; j < k; j++) {
                if (j - group.size < 0) {
                    sack[i+1][j] = sack[i][j];
                    continue;
                }
                sack[i+1][j] = Math.max(sack[i][j], sack[i][j - group.size] + group.count);
            }
        }

        System.out.println(sack[list.size()][k-1]);
    }

    static Group bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        int totalCount = 0;
        int totalSize = 0;

        q.add(start);
        while (!q.isEmpty()) {
            Node poll = nodes[q.poll()];
            if (visited[poll.number]) {
                continue;
            }
            visited[poll.number] = true;
            totalCount += poll.count;
            totalSize++;
            for (Node node : poll.nodes) {
                if (visited[node.number]) {
                    continue;
                }
                q.add(node.number);
            }
        }

        return new Group(totalSize, totalCount);
    }

    static class Node {
        int number;
        int count;
        List<Node> nodes = new ArrayList<>();

        public Node(int number, int count) {
            this.number = number;
            this.count = count;
        }
    }

    static class Group {
        int size;
        int count;

        public Group(int size, int count) {
            this.size = size;
            this.count = count;
        }
    }
}
