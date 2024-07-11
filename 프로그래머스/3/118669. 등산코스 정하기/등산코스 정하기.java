import java.util.*;
class Solution {
    static int[] maxIntensity;
    static int number;
    static HashSet<Integer> gateSet = new HashSet<>();
    static HashSet<Integer> summitSet = new HashSet<>();
    static List<List<Edge>> graph = new ArrayList<>();
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        maxIntensity = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Edge>());
        }
        for (int gate : gates)
            gateSet.add(gate);
        
        for (int summit : summits)
            summitSet.add(summit);
        for (int[] path : paths) {
            graph.get(path[0]).add(new Edge(path[1], path[2]));
            graph.get(path[1]).add(new Edge(path[0], path[2]));
        }
        number = n;
        // info에 정보 저장
        Arrays.sort(summits);
        int finalIntensity = 10000001;
        for (int i = 1; i <= n; i++){
            if (gateSet.contains(i))
                maxIntensity[i] = 0;
            else
                maxIntensity[i] = 10000001;
        }
        int finalSummit = 0;
        dijkstra(paths);
        for (int summit : summits) {
            if (maxIntensity[summit] < finalIntensity) {
                finalIntensity = maxIntensity[summit];
                finalSummit = summit;
            }
        }
        return new int[]{finalSummit, finalIntensity};
    }
    
    public void dijkstra(int[][] paths) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        for (Integer i : gateSet)
            q.add(new Node(i, 0));
        while (!q.isEmpty()) {
            Node node = q.poll();
            if (summitSet.contains(node.num) || maxIntensity[node.num] < node.intensity)
                continue;
            List<Edge> edges = graph.get(node.num);
            for (Edge edge : edges) {
                if (gateSet.contains(edge.dst))
                    continue;
                int intensity = Math.max(node.intensity, edge.weight);
                if (maxIntensity[edge.dst] <= intensity)
                    continue;
                maxIntensity[edge.dst] = intensity;
                q.add(new Node(edge.dst, intensity));
            }
        }
    }
    
    class Node implements Comparable<Node> {
        int num;
        int intensity;
        
        public Node(int num, int intensity) {
            this.num = num;
            this.intensity = intensity;
        }
        
        public int compareTo(Node o) {
            int n = this.intensity - o.intensity;
            if (n == 0)
                return Integer.compare(this.num, o.intensity);
            return Integer.compare(this.intensity, o.intensity);
        }
    }
    class Edge {
        int dst;
        int weight;
        
        public Edge(int dst, int weight) {
            this.dst = dst;
            this.weight = weight;
        }
    }
}