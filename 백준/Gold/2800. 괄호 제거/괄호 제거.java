import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Node> nodes;
    static Set<String> results;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        nodes = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                Node node = new Node(i);
                int stack = 1;
                for (int j = i + 1; j < str.length(); j++) {
                    if (str.charAt(j) == '(') {
                        stack++;
                    }
                    if (str.charAt(j) == ')') {
                        stack--;
                    }
                    if (stack == 0) {
                        node.e = j;
                        break;
                    }
                }
                nodes.add(node);
            }
        }

        results = new HashSet<>();
        simulate(0, nodes.size(), 0, str);
        List<String> lists = new ArrayList<>(results);
        Collections.sort(lists);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < results.size(); i++) {
            sb.append(lists.get(i)).append("\n");
        }
        System.out.print(sb);
    }

    static void simulate(int idx, int n, int cur, String str) {
        if (idx == n) {
            build(cur, str, n);
            return;
        }
        simulate(idx + 1, n, cur | (1 << idx), str);
        simulate(idx + 1, n, cur, str);
    }

    static void build(int cur, String str, int n) {
        boolean[] isPass = new boolean[str.length()];
        for (int i = 0; i < n; i++) {
            if ((cur & (1 << i)) == (1 << i)) {
                Node node = nodes.get(i);
                isPass[node.s] = true;
                isPass[node.e] = true;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (isPass[i]) {
                continue;
            }
            sb.append(str.charAt(i));
        }

        results.add(sb.toString());
    }

    static class Node {
        int s, e;

        public Node(int s) {
            this.s = s;
        }
    }
}
