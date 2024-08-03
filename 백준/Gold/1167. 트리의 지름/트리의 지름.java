import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    // MST를 만들고 가장 긴 Edge?
    static int v;
    static Map<Integer, Map<Integer, Integer>> graph;
    static int maxDiameter = 0;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        v = Integer.parseInt(br.readLine());
        graph = new HashMap<>();
        visited = new boolean[v+1];
        for (int i = 0; i < v; i++) {
            String[] s = br.readLine().split(" ");
            int n1 = Integer.parseInt(s[0]);
            if (!graph.containsKey(n1)) {
                graph.put(n1, new HashMap<>());
            }
            Map<Integer, Integer> n1Edges = graph.get(n1);
            for (int j = 1; j < s.length; j += 2) {
                if (Integer.parseInt(s[j]) == -1) {
                    break;
                }
                int n2 = Integer.parseInt(s[j]);
                int d = Integer.parseInt(s[j + 1]);
                if (!graph.containsKey(n2)) {
                    graph.put(n2, new HashMap<>());
                }
                if (!n1Edges.containsKey(n2)) {
                    n1Edges.put(n2, d);
                    graph.get(n2).put(n1, d);
                }
            }
        }
        dfs(1);
        System.out.println(maxDiameter);
    }

    public static int dfs(int n1) {
        visited[n1] = true;
        Map<Integer, Integer> edges = graph.get(n1);
        int maxDistance = 0;
        List<Integer> distances = new ArrayList<>();
        for (Integer n2 : edges.keySet()) {
            if (visited[n2])
                continue;
            int d = edges.get(n2) + dfs(n2);
            maxDistance = Math.max(maxDistance, d);
            distances.add(d);
        }
        // 연결된 노드가 2개 이상 => 나를 거치고 연결된 거리가 더 길다
        if (distances.size() >= 2) {
            Collections.sort(distances);
            int d1 = distances.get(distances.size() - 1);
            int d2 = distances.get(distances.size() - 2);
            maxDiameter = Math.max(maxDiameter, d1 + d2);
        }
        // n1과 연결된 노드가 1개일 경우, 가장 멀리있는 노드를 고려
        else if (distances.isEmpty()) {
            return 0;
        } else {
            maxDiameter = Math.max(maxDiameter, distances.get(0));
        }
        return maxDistance;
    }
}