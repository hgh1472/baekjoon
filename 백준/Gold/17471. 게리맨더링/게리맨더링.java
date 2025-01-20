import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static Map<Integer, List<Integer>> info = new HashMap<>();
    static int[] p;
    static int answer = 1000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        p = new int[n + 1];
        int[] input = Arrays.stream((br.readLine().split(" "))).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < n; i++) {
            p[i + 1] = input[i];
        }

        for (int i = 1; i <= n; i++) {
            input = Arrays.stream((br.readLine().split(" "))).mapToInt(Integer::parseInt).toArray();
            List<Integer> list = new ArrayList<>();
            for (int j = 1; j <= input[0]; j++) {
                list.add(input[j]);
            }
            info.put(i, list);
        }

        for (int i = 1; i <= n - 1; i++) {
            boolean[] visited = new boolean[n + 1];
            dfs(0, i, visited, 0);
        }
        if (answer == 1000) {
            answer = -1;
        }
        System.out.println(answer);
    }

    public static void dfs(int depth, int finalDepth, boolean[] visited, int last) {
        if (depth == finalDepth) {
            calculate(visited);
            return;
        }
        for (int i = last + 1; i <= n; i++) {
            if (visited[i])
                continue;
            visited[i] = true;
            dfs(depth + 1, finalDepth, visited, i);
            visited[i] = false;
        }
    }

    public static void calculate(boolean[] visited) {
        int a = 0;
        int b = 0;
        for (int i = 1; i <= n; i++) {
            if (visited[i]) {
                a = i;
                break;
            }
        }

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                b = i;
                break;
            }
        }
        int result1 = bfs(a, visited);
        int result2 = bfs(b, visited);
        if (result1 == -1 || result2 == -1) {
            return;
        }
        answer = Math.min(answer, Math.abs(result1));
    }

    public static int bfs(int s, boolean[] visited) {
        boolean[] local = new boolean[n + 1];
        Queue<Integer> q = new ArrayDeque<>();
        q.add(s);
        while (!q.isEmpty()) {
            int i = q.poll();
            if (local[i]) {
                continue;
            }
            local[i] = true;
            for (int e : info.get(i)) {
                if (visited[s] != visited[e])
                    continue;
                q.add(e);
            }
        }
        int a = 0;
        int b = 0;
        for (int i = 1; i <= n; i++) {
            if (visited[i] == visited[s]) {
                if (!local[i]) {
                    return -1;
                }
                a += p[i];
            }
            else {
                b += p[i];
            }
        }
        return Math.min(answer, Math.abs(a - b));
    }
}
