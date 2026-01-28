import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Node[] tree;
    static int[] nums;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = input[i];
        }
        tree = new Node[4 * n];
        init(0, n-1, 1);

        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (input[0] == 2) {
                sb.append(tree[1].position + 1).append("\n");
            }
            else {
                update(1, 0, n-1, input[1]-1, input[2]);
            }
        }
        System.out.print(sb);
    }

    static void update(int node, int start, int end, int idx, int value) {
        if (idx < start || idx > end) {
            return;
        }
        if (start == end) {
            tree[node].position = idx;
            tree[node].number = value;
            return;
        }
        int mid = (start + end) / 2;
        update(node * 2, start, mid, idx, value);
        update(node * 2 + 1, mid + 1, end, idx, value);
        if (tree[node * 2].compareTo(tree[node * 2 + 1]) < 0) {
            tree[node] = tree[node * 2];
        }
        else {
            tree[node] = tree[node * 2 + 1];
        }
    }

    static Node init(int start, int end, int index) {
        if (start == end) {
            tree[index] = new Node(nums[start], start);
            return tree[index];
        }
        int mid = (start + end) / 2;
        Node left = init(start, mid, index * 2);
        Node right = init(mid + 1, end, index * 2 + 1);
        if (left.compareTo(right) < 0) {
            tree[index] = left;
        }
        else {
            tree[index] = right;
        }
        return tree[index];
    }

    static class Node implements Comparable<Node> {
        int number;
        int position;

        public Node(int number, int position) {
            this.number = number;
            this.position = position;
        }

        @Override
        public int compareTo(Node o) {
            if (this.number == o.number) {
                return this.position - o.position;
            }
            return this.number - o.number;
        }
    }
}
