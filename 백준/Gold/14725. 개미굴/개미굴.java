import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Node root = new Node("root");
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            init(root, input);
        }

        for (String food : root.children.keySet()) {
            dfs(root.children.get(food), 0);
        }

        System.out.println(sb);
    }

    static void init(Node root, String[] input) {
        Node cur = root;
        for (int i = 1; i < input.length; i++) {
            if (!cur.children.containsKey(input[i])) {
                cur.children.put(input[i], new Node(input[i]));
            }
            cur = cur.children.get(input[i]);
        }
    }

    static void dfs(Node node, int depth) {
        sb.append("-".repeat(depth * 2)).append(node.food).append("\n");
        for (String food : node.children.keySet()) {
            dfs(node.children.get(food), depth + 1);
        }
    }

    static class Node {
        String food;
        TreeMap<String, Node> children = new TreeMap<>();

        public Node(String food) {
            this.food = food;
        }
    }
}
