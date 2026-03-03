import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] root;
    static int n, m;
    static Edge start;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = input[0];
        root = new int[n+1];
        m = input[1];
        List<Edge> up = new ArrayList<>();
        List<Edge> down = new ArrayList<>();


        for (int i = 0; i < m+1; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Edge edge = new Edge(input[0], input[1], input[2] == 0);
            if (input[2] == 0) {
                up.add(edge);
            }
            else {
                down.add(edge);
            }
            if ((input[0] == 0 && input[1] == 1) || (input[0] == 1 && input[1] == 0)) {
                start = edge;
            }
        }

        System.out.println(getMax(up, down) - getMin(up, down));
    }

    static int getMax(List<Edge> up, List<Edge> down) {
        for (int i = 0; i < n + 1; i++) {
            root[i] = i;
        }
        root[1] = 0;
        int edgeCount = 1;
        int stack = 0;
        if (start.isUp) {
            stack++;
        }
        for (Edge edge : up) {
            int left = getRoot(edge.left);
            int right = getRoot(edge.right);
            if (left == right) {
                continue;
            }
            root[left] = right;
            edgeCount++;
            stack++;
            if (edgeCount == n) {
                break;

            }
        }
        return stack * stack;
    }

    static int getMin(List<Edge> up, List<Edge> down) {
        for (int i = 0; i < n + 1; i++) {
            root[i] = i;
        }
        root[1] = 0;
        int edgeCount = 1;
        int stack = 0;
        if (start.isUp) {
            stack++;
        }
        for (Edge edge : down) {
            int left = getRoot(edge.left);
            int right = getRoot(edge.right);
            if (left == right) {
                continue;
            }
            root[left] = right;
            edgeCount++;
            if (edgeCount == n) {
                break;
            }
        }
        if (edgeCount == n) {
            return stack * stack;
        }

        for (Edge edge : up) {
            int left = getRoot(edge.left);
            int right = getRoot(edge.right);
            if (left == right) {
                continue;
            }
            root[left] = right;
            edgeCount++;
            stack++;
            if (edgeCount == n) {
                break;

            }
        }
        return stack * stack;
    }

    static int getRoot(int x) {
        if (root[x] != x) {
            root[x] = getRoot(root[x]);
        }
        return root[x];
    }

    static class Edge {
        int left, right;
        boolean isUp;

        public Edge(int left, int right, boolean isUp) {
            this.left = left;
            this.right = right;
            this.isUp = isUp;
        }
    }
}
