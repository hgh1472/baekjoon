import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static final int INF = Integer.MAX_VALUE;
    static int[][] edges;
    static long[] distances;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = input[0];
        int m = input[1];
        edges = new int[m][3];

        for (int i = 0; i < m; i++) {
           input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
           edges[i][0] = input[0];
           edges[i][1] = input[1];
           edges[i][2] = input[2];
        }

        boolean isMinusCycle = bellmanFord(n);
        if (isMinusCycle) {
            System.out.println(-1);
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= n; i++) {
            if (distances[i] == INF) {
                sb.append("-1\n");
            }
            else {
                sb.append(distances[i]).append("\n");
            }
        }
        System.out.println(sb);
    }

    static boolean bellmanFord(int n) {
        distances = new long[n+1];
        Arrays.fill(distances, INF);
        distances[1] = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int[] edge : edges) {
                int start = edge[0];
                int end = edge[1];
                int cost = edge[2];

                if (distances[start] == INF) {
                    continue;
                }

                distances[end] = Math.min(distances[end], distances[start] + cost);
            }
        }

        for (int[] edge : edges) {
            int start = edge[0];
            int end = edge[1];
            int cost = edge[2];
            if (distances[start] == INF) {
                continue;
            }

            if (distances[end] > distances[start] + cost) {
                return true;
            }
        }
        return false;
    }
}
