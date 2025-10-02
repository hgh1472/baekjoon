import java.util.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        Vertex[] vertices = new Vertex[n+1];
        for (int i = 1; i <= n; i++) {
            vertices[i] = new Vertex(i);
        }
        
        for (int[] path : paths) {
            vertices[path[0]].add(new Edge(path[1], path[2]));
            vertices[path[1]].add(new Edge(path[0], path[2]));
        }
        
        for (int gate : gates) {
            vertices[gate].isGate = true;
        }
        
        for (int summit : summits) {
            vertices[summit].isSummit = true;
        }

        int minIntensity = Integer.MAX_VALUE;
        int result = 0;
        Arrays.sort(summits);
        for (int summit : summits) {
            PriorityQueue<Node> q = new PriorityQueue<>();
            q.add(new Node(summit, 0));
            while (!q.isEmpty()) {
                Node poll = q.poll();
                if (vertices[poll.number].intensity <= poll.intensity) {
                    continue;
                }
                if (vertices[poll.number].isGate) {
                    if (minIntensity > poll.intensity) {
                        minIntensity = poll.intensity;
                        result = summit;
                    }
                    break;
                }
                vertices[poll.number].intensity = poll.intensity;
                for (Edge edge : vertices[poll.number].edges) {
                    if (vertices[edge.dst].isSummit) {
                        continue;
                    }
                    int next = Math.max(poll.intensity, edge.intensity);
                    if (vertices[edge.dst].intensity > next) {
                        q.add(new Node(edge.dst, next));
                    }
                }
            }
        }
        
        return new int[]{result, minIntensity};
    }
    
    class Vertex {
        int number;
        List<Edge> edges = new ArrayList<>();
        int intensity;
        boolean isGate;
        boolean isSummit;
        
        Vertex(int number) {
            this.number = number;
            this.intensity = Integer.MAX_VALUE;
            this.isGate = false;
            this.isSummit = false;
        }
        
        void add(Edge e) {
            this.edges.add(e);
        }
    }
    
    class Edge {
        int dst;
        int intensity;
        
        Edge(int dst, int intensity) {
            this.dst = dst;
            this.intensity = intensity;
        }
    }
    
    class Node implements Comparable<Node> {
        int number;
        int intensity;
        
        Node(int number, int intensity) {
            this.number = number;
            this.intensity = intensity;
        }
        
        @Override
        public int compareTo(Node n) {
            if (this.intensity != n.intensity) {
                return this.intensity - n.intensity;
            }
            return this.number - n.number;
        }
    }
}