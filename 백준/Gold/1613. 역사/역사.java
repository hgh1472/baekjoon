import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    static Node[] nodes;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        nodes = new Node[n+1];
        Map<Integer, Map<Integer, Boolean>> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(i);
            map.put(i, new HashMap<>());
        }
        for (int i = 0; i < k; i++) {
            input = br.readLine().split(" ");
            int first = Integer.parseInt(input[0]);
            int second = Integer.parseInt(input[1]);
            map.get(first).put(second, true);
            nodes[first].next.add(nodes[second]);
        }

        for (int i = 1; i <= n; i++) {
            boolean[] visited = new boolean[n+1];
            dfs(visited, nodes[i], map, i);
        }

        int s = Integer.parseInt(br.readLine());
        for (int i = 0; i < s; i++) {
            input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);

            boolean isFind = false;
            Map<Integer, Boolean> aNext = map.get(a);
            if (aNext.containsKey(b)) {
                System.out.println(-1);
                continue;
            }
            Map<Integer, Boolean> bNext = map.get(b);
            if (bNext.containsKey(a)) {
                System.out.println(1);
                continue;
            }
            System.out.println(0);
        }
    }

    static void dfs(boolean[] visited, Node node, Map<Integer, Map<Integer, Boolean>> map, int start) {
        if (visited[node.number]) {
            return;
        }
        visited[node.number] = true;
        map.get(start).put(node.number, true);
        for (Node n : node.next) {
            dfs(visited, n, map, start);
        }
    }

    static class Node {
        int number;
        List<Node> next = new ArrayList<>();

        Node(int number) {
            this.number = number;
        }
    }
}
