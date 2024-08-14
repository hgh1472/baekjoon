import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main {
    static List<Integer> nums = new ArrayList<>();
    static int length;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = "";
        while ((input = br.readLine()) != null && !input.isEmpty()) {
            nums.add(Integer.parseInt(input));
        }
        length = nums.size();
        Node root = new Node(nums.get(0));
        init(root, 1, 1000000);

        search(root);
    }

    static class Node {
        int num;
        Node right;
        Node left;

        public Node(int num) {
            this.num = num;
            right = null;
            left = null;
        }
    }

    public static int init(Node node, int idx, int ceiling) {
        if (idx >= length) {
            return idx;
        }
        Integer num = nums.get(idx);
        // System.out.println("node = " +  node.num + ", num = " + num);
        if (num < node.num) {
            Node n = new Node(num);
            node.left = n;
            idx = init(n, idx + 1, node.num);
        }
        if (idx >= length) {
            return idx;
        }
        num = nums.get(idx);
        // 부모 노드 값보다 큰 경우 => 나의 오른쪽 자식이 아님
        if (num > ceiling) {
            return idx;
        }
        // System.out.println("node = " +  node.num + ", num = " + num);
        if (node.num < num) {
            Node n = new Node(num);
            node.right = n;
            idx = init(n, idx + 1, ceiling);
        }
        return idx;
    }

    public static void search(Node root) {
        if (root.left != null) {
            search(root.left);
        }
        if (root.right != null) {
            search(root.right);
        }
        System.out.println(root.num);
    }
}
