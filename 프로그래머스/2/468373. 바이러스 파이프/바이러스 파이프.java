import java.util.*;

class Solution {
    Node[] nodes;
    int infection, n;
    int answer;
    public int solution(int n, int infection, int[][] edges, int k) {
        this.nodes = new Node[n+1];
        this.infection = infection;
        this.n = n;
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(i);
        }
        
        for (int[] e : edges) {
            int n1 = e[0];
            int n2 = e[1];
            nodes[n1].edges.add(new Edge(n2, e[2]));
            nodes[n2].edges.add(new Edge(n1, e[2]));
        }
        
        answer = 0;
        build(new int[k], 0, k);
        
        return answer;
    }
    
    public void build(int[] arr, int count, int k) {
        if (count == k) {
            int result = calculate(arr);
            answer = Math.max(answer, result);
            return;
        }
        for (int i = 1; i <= 3; i++) {
            arr[count] = i;
            build(arr, count + 1, k);
        }
    }
    
    public int calculate(int[] arr) {
        boolean[] infected = new boolean[n + 1];
        infected[infection] = true;
        for (int i = 0; i < arr.length; i++) {
            Queue<Node> q = new ArrayDeque<>();
            for (int j = 1; j <= n; j++) {
                if (infected[j]) {
                    q.add(nodes[j]);
                }
            }
            boolean[] visited = new boolean[n+1];
            while (!q.isEmpty()) {
                Node poll = q.poll();
                if (visited[poll.number]) {
                    continue;
                }
                visited[poll.number] = true;
                infected[poll.number] = true;
                for (Edge e : poll.edges) {
                    if (e.type != arr[i])  {
                        continue;
                    }
                    q.add(nodes[e.next]);
                }
            }
        }
        
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (infected[i]) {
                answer++;
            }
        }
        return answer;
    }
    
    class Node {
        int number;
        List<Edge> edges = new ArrayList<>();
        
        Node(int number) {
            this.number = number;
        }
    }
    
    class Edge {
        int next;
        int type;
        
        Edge(int next, int type) {
            this.next = next;
            this.type = type;
        }
    }
}