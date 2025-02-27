import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        Map<Integer, List<Node>> edges = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            edges.put(i, new ArrayList<>());
        }
        for (int[] fare : fares) {
            edges.get(fare[0]).add(new Node(fare[1], fare[2]));
            edges.get(fare[1]).add(new Node(fare[0], fare[2]));
        }
        int[][] distance = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }
        
        for (int i = 1; i <= n; i++) {
            PriorityQueue<Node> q = new PriorityQueue<>();
            distance[i][i] = 0;
            q.add(new Node(i, 0));
            while (!q.isEmpty()) {
                Node node = q.poll();
                if (distance[i][node.num] < node.distance) {
                    continue;
                }
                // distance[i][node.num] = node.distance;
                for (Node e : edges.get(node.num)) {
                    if (distance[i][e.num] <= node.distance + e.distance) {
                        continue;
                    }
                    distance[i][e.num] = node.distance + e.distance;
                    q.add(new Node(e.num, node.distance + e.distance));
                }
            }
        }
        
        int answer = Integer.MAX_VALUE;
        for (int i = 1; i<= n; i++) {
            int d = distance[s][i] + distance[i][a] + distance[i][b];
            answer = Math.min(answer, d);
        }
        return answer;
    }
    
    class Node implements Comparable<Node> {
        int num;
        int distance;
        
        Node(int num, int distance) {
            this.num = num;
            this.distance = distance;
        }
        
        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }
    }
}