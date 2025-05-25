import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        
        Map<String, Boolean> visited = new HashMap<>();
        for (String word : words) {
            visited.put(word, false);
        }
        
        q.add(new Node(begin, 0));
        
        while (!q.isEmpty()) {
            Node node = q.poll();
            for (String word : words) {
                if (isDifferentOne(node.str, word) && !visited.get(word)) {
                    if (word.equals(target)) {
                        return node.count + 1;
                    }
                    q.add(new Node(word, node.count+1));
                    visited.put(word, true);
                }
            }
        }
        
        return 0;
    }
    
    boolean isDifferentOne(String a, String b) {
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == b.charAt(i)) {
                count++;
            }
        }
        if (count == a.length() - 1) {
                return true;
        }
        return false;
    }
    
    class Node implements Comparable<Node> {
        String str;
        int count;
        
        Node(String str, int count) {
            this.str = str;
            this.count = count;
        }
        
        @Override
        public int compareTo(Node n) {
            return this.count - n.count;
        }
    }
}