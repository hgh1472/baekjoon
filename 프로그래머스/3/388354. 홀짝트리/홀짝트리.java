import java.util.*;

class Solution {
    Map<Integer, Boolean> visited;
    Map<Integer, Node> nodeMap;
    int[] answer;
    public int[] solution(int[] nodes, int[][] edges) {
        nodeMap = new HashMap<>();
        visited = new HashMap<>();
        answer = new int[2];
        for (int i = 0; i < nodes.length; i++) {
            nodeMap.put(nodes[i], new Node(nodes[i]));
            visited.put(nodes[i], false);
        }
        
        for (int[] edge : edges) {
            nodeMap.get(edge[0]).nodes.add(edge[1]);
            nodeMap.get(edge[1]).nodes.add(edge[0]);
        }
        
        for (int node : nodes) {
            if (visited.get(node)) {
                continue;
            }
            visited.put(node, true);
            bfs(node);
        }
        return answer;
        
    }
    
    void bfs(int node) {
        Queue<Integer> q = new ArrayDeque<>();
        
        boolean isHol = true;
        boolean isReverse = true;
        
        boolean canHolRoot = true;
        boolean canReverseRoot = true;
        q.add(node);
        while (!q.isEmpty()) {
            int n = q.poll();
            int countIfRoot = nodeMap.get(n).nodes.size();
            if ((nodeMap.get(n).number + countIfRoot-1) % 2 == 0) { // 루트가 아닐때 홀짝 노드
                // 역홀짝 트리를 고려할 때, 이 노드는 루트가 되어야 함
                if (canReverseRoot) {
                    canReverseRoot = false;
                }
                else {
                    isReverse = false;
                }
            }
            else { // 루트가 아닐 때 역홀짝 노드
                // 홀짝 트리를 고려할 때, 이 노드는 루트가 되어야 함
                if (canHolRoot) {
                    canHolRoot = false;
                }
                else {
                    isHol = false;
                }
            }
            visited.put(n, true);
            for (int child : nodeMap.get(n).nodes) {
                if (visited.get(child)) {
                    continue;
                }
                q.add(child);
            }
        }
        if (isHol && !canHolRoot) {
            answer[0]++;
        }
        if (isReverse && !canReverseRoot) {
            answer[1]++;
        }
    }
    
    class Node {
        int number;
        List<Integer> nodes = new ArrayList<>();
        
        Node(int number) {
            this.number = number;
        }
    }
}