import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        Map<Integer, Node> map = new HashMap<>();
        
        for (int[] edge : edges) {
            if (!map.containsKey(edge[0])) {
                map.put(edge[0], new Node(edge[0]));
            }
            if (!map.containsKey(edge[1])) {
                map.put(edge[1], new Node(edge[1]));
            }
            map.get(edge[0]).outs.add(map.get(edge[1]));
            map.get(edge[1]).ins.add(map.get(edge[0]));
        }
        
        int start = 0;
        int graphCount = 0;
        for (Integer key : map.keySet()) {
            Node node = map.get(key);
            if (node.ins.size() == 0 && node.outs.size() >= 2) {
                start = key;
                for (Node out : node.outs) {
                    out.ins.remove(node);
                    graphCount++;
                }
                start = key;
                break;
            }
        }
        
        int barCount = 0;
        int eightCount = 0;
        for (Integer key : map.keySet()) {
            Node node = map.get(key);
            if (node.outs.size() == 2 && node.ins.size() == 2) {
                eightCount++;
            }
            if (node.outs.size() == 1 && node.ins.size() == 0) {
                barCount++;
            }
            if (node.outs.size() == 0 && node.ins.size() == 0) {
                barCount++;
            }
        }
        
        int[] answer = {start, graphCount - barCount - eightCount, barCount, eightCount};
        return answer;
    }
    
    class Node {
        int number;
        List<Node> outs = new ArrayList<>();
        List<Node> ins = new ArrayList<>();
        
        Node(int number) {
            this.number = number;
        }
    }
}