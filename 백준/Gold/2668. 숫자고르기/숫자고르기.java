import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Main {
    static int[] next;
    static boolean[] visited;
    static List<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        next = new int[n+1];
        visited = new boolean[n+1];
        for (int i = 1; i <= n; i++) {
            next[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                Deque<Integer> q = new ArrayDeque<>();
                dfs(i, new int[n+1], q);
                while (!q.isEmpty()) {
                    visited[q.poll()] = true;
                }
            }
        }

        answer.sort(Integer::compareTo);

        StringBuffer sb = new StringBuffer();
        sb.append(answer.size()).append("\n");
        for (int i = 0; i < answer.size(); i++) {
            sb.append(answer.get(i)).append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(int n, int[] counts, Deque<Integer> localVisited) {
        localVisited.add(n);
        if (counts[n] == 2) {
            return;
        }
        if (counts[n] == 1) {
            answer.add(n);
        }
        counts[n]++;
        if (visited[next[n]]) {
            return;
        }
        dfs(next[n], counts, localVisited);
    }

}
