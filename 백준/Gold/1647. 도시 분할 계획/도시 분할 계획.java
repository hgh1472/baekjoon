import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int[] nation;
    static int[] rank;
    static int nationCount;
    static int minCost = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        nationCount = n;
        nation = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            nation[i] = i;
            rank[i] = 0;
        }
        HashMap<Integer, List<Edge>> edgeInfo = new HashMap<>();
        for (int i = 0; i < m; i++) {
            String[] split = br.readLine().split(" ");
            int a = Integer.parseInt(split[0]);
            int b = Integer.parseInt(split[1]);
            int c = Integer.parseInt(split[2]);
            if (!edgeInfo.containsKey(c))
                edgeInfo.put(c, new ArrayList<>());
            edgeInfo.get(c).add(new Edge(a, b));
        }

        calculateEdges(edgeInfo);
        System.out.println(minCost);
    }

    private static void calculateEdges(HashMap<Integer, List<Edge>> edgeInfo) {
        List<Integer> keySet = new ArrayList<>(edgeInfo.keySet());
        keySet.sort(Comparator.naturalOrder());
        for (Integer key : keySet) {
            List<Edge> edges = edgeInfo.get(key);
            for (Edge edge : edges) {
                if (nationCount == 2)
                    return;
                if (union(edge.firstNation, edge.secondNation))
                    minCost += key;
            }
        }
    }

    static int find(int x) {
        if (nation[x] != x) {
            nation[x] = find(nation[x]);
        }
        return nation[x];
    }

    static boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY)
            return false;
        nationCount--;
        if (rank[rootX] > rank[rootY]) {
            nation[rootY] = rootX;
        } else {
            nation[rootX] = rootY;
            if (rank[rootX] == rank[rootY])
                rank[rootY]++;
        }
        return true;
    }

    static class Edge {
        int firstNation;
        int secondNation;

        public Edge(int firstNation, int secondNation) {
            this.firstNation = firstNation;
            this.secondNation = secondNation;
        }
    }
}