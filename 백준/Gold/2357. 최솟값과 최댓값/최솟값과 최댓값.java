import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    static int[] arr;
    static Node[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        tree = new Node[4 * n];
        init(0, n-1, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            int start = Integer.parseInt(input[0]);
            int end = Integer.parseInt(input[1]);
            Node node = get(0, n - 1, 1, start-1, end-1);
            sb.append(node.min).append(" ").append(node.max).append("\n");
        }
        System.out.print(sb);
    }

    static Node get(int left, int right, int idx, int start, int end) {
        if (right < start || end < left) {
            return null;
        }
        if (start <= left && right <= end) {
            return tree[idx];
        }
        int mid = (left + right) / 2;
        Node l = get(left, mid, idx * 2, start, end);
        Node r = get(mid + 1, right, idx * 2 + 1, start, end);
        if (l == null) {
            return r;
        }
        if (r == null) {
            return l;
        }
        return new Node(Math.min(l.min, r.min), Math.max(l.max, r.max));
    }

    static Node init(int left, int right, int idx) {
        if (left == right) {
            tree[idx] = new Node(arr[left], arr[left]);
            return tree[idx];
        }
        int mid = (left + right) / 2;
        Node l = init(left, mid, idx * 2);
        Node r = init(mid + 1, right, idx * 2 + 1);
        tree[idx] = new Node(Math.min(l.min, r.min), Math.max(l.max, r.max));
        return tree[idx];
    }

    static class Node {
        int min, max;

        public Node(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }
}
