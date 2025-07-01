import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int[] wrong = new int[diffs.length];
        
        long start = times[0];
        int maxDiff = 1;
        long minus = 0;
        PriorityQueue<Node> q = new PriorityQueue<>();
        for (int i = 1; i < diffs.length; i++) {
            if (diffs[i] != 1) {
                q.add(new Node(diffs[i], times[i], times[i-1]));
                minus += times[i] + times[i-1];
            }
            start += calculate(times[i], times[i-1], diffs[i], 1);
            maxDiff = Math.max(maxDiff, diffs[i]);
        }
        
        if (start <= limit) {
            return 1;
        }
        
        for (int level = 2; level <= maxDiff; level++) {
            start -= minus;
            if (start <= limit) {
                return level;
            }
            if (level >= q.peek().diff) {
                while (!q.isEmpty() && level >= q.peek().diff) {
                    Node n = q.poll();
                    minus -= (n.now+n.prev);
                }
            }
        }
        return maxDiff;
    }
    
    
    public long calculate(int now, int prev, int diff, int level) {
        return (long) (now + (now + prev) * (diff - Math.min(diff, level)));
    }
    
    class Node implements Comparable<Node> {
        int diff;
        int now;
        int prev;
        
        Node(int diff, int now, int prev) {
            this.diff = diff;
            this.now = now;
            this.prev = prev;
        }
        
        @Override
        public int compareTo(Node n) {
            return this.diff - n.diff;
        }
    }
}