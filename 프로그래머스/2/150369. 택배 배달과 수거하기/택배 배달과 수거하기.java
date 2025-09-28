import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        PriorityQueue<Node> dq = new PriorityQueue<>();
        for (int i = 0; i < deliveries.length; i++) {
            if (deliveries[i] == 0) {
                continue;
            }
            dq.add(new Node(i+1, deliveries[i]));
        }
        for (int i = 0; i < pickups.length; i++) {
            if (pickups[i] == 0) {
                continue;
            }
            pq.add(new Node(i+1, pickups[i]));
        }
        
        long answer = 0;
        while (!dq.isEmpty() || !pq.isEmpty()) {
            int max = 0;
            int pickup = cap;
            int delivery = cap;
            while (delivery != 0 && !dq.isEmpty()) {
                Node peek = dq.peek();
                max = Math.max(max, peek.number);
                if (peek.count > delivery) {
                    peek.count -= delivery;
                    delivery = 0;
                }
                else {
                    delivery -= peek.count;
                    dq.poll();
                }
            }
            
            while (pickup != 0 && !pq.isEmpty()) {
                Node peek = pq.peek();
                max = Math.max(max, peek.number);
                if (peek.count > pickup) {
                    peek.count -= pickup;
                    pickup = 0;
                }
                else {
                    pickup -= peek.count;
                    pq.poll();
                }
            }
            
            answer += max * 2;
        }
        return answer;
    }
    
    class Node implements Comparable<Node> {
        int number;
        int count;
        
        Node(int number, int count) {
            this.number = number;
            this.count = count;
        }
        
        @Override
        public int compareTo(Node n) {
            return n.number - this.number;
        }
    }
}