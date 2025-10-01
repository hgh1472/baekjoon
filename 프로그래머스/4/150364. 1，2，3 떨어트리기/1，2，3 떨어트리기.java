import java.util.*;

class Solution {
    public static final int LESS = 0;
    public static final int POSSIBLE = 1;
    public static final int MORE = 2;
    public int[] solution(int[][] edges, int[] target) {
        Node[] nodes = new Node[target.length + 1];
        for (int i = 1; i < nodes.length; i++) {
            nodes[i] = new Node(i);
        }
        for (int[] edge : edges) {
            nodes[edge[0]].add(nodes[edge[1]]);
        }
        for (int i = 1; i < nodes.length; i++) {
            Collections.sort(nodes[i].edges);
        }
        int[] counts = new int[target.length+1];
        int lessCount = target.length, possibleCount = 0, moreCount = 0;
        int[] min = new int[target.length+1];
        int[] max = new int[target.length+1];
        for (int i = 0; i < target.length; i++) {
            if (target[i] == 0) {
                possibleCount++;
                lessCount--;
            }
            min[i+1] = target[i] / 3;
            if (target[i] % 3 != 0) {
                min[i+1]++;
            }
            max[i+1] = target[i];
        }
        
        List<Integer> order = new ArrayList<>();
        while (possibleCount != target.length && moreCount == 0) {
            Node cur = nodes[1];
            while (!cur.isEnd()) {
                cur = cur.getNext();
            }
            order.add(cur.number);
            counts[cur.number]++;
            if (min[cur.number] == counts[cur.number]) {
                possibleCount++;
                lessCount--;
            }
            if (max[cur.number] < counts[cur.number]) {
                moreCount++;
                possibleCount--;
            }
        }
        if (moreCount != 0) {
            return new int[]{-1};
        }
        
        int[] answer = new int[order.size()];
        for (int i = 0; i < order.size(); i++) {
            int t = target[order.get(i)-1];
            if ((counts[order.get(i)] - 1) * 3 >= t - 1) {
                answer[i] = 1;
                target[order.get(i)-1] -= 1;
            }
            else if ((counts[order.get(i)] - 1) * 3 >= t - 2) {
                answer[i] = 2;
                target[order.get(i)-1] -= 2;
            }
            else {
                answer[i] = 3;
                target[order.get(i)-1] -= 3;
            }
            counts[order.get(i)]--;
        }
        return answer;
    }
    
    class Node implements Comparable<Node> {
        int number;
        List<Node> edges = new ArrayList<>();
        int index = 0;
        
        Node(int number) {
            this.number = number;
        }
        
        void add(Node node) {
            this.edges.add(node);
        }
        
        boolean isEnd() {
            return this.edges.size() == 0;
        }
        
        Node getNext() {
            Node next = edges.get(index);
            index = (index + 1) % edges.size();
            return next;
        }
        
        @Override
        public int compareTo(Node n) {
            return this.number - n.number;
        }
    }
}