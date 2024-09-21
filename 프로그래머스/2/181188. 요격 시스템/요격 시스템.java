import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        PriorityQueue<Sector> q = new PriorityQueue<>();
        
        for (int[] target : targets) {
            q.add(new Sector(target[0], target[1]));
        }
        
        int lastIdx = -1;
        while (!q.isEmpty()) {
            Sector sector = q.poll();
            if (!sector.isInRange(lastIdx)) {
                lastIdx = sector.end - 1;
                answer++;
            }
        }
        return answer;
    }
    
    class Sector implements Comparable<Sector> {
        int start;
        int end;
        
        Sector(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        public boolean isInRange(int idx) {
            if (this.start <= idx && idx < this.end)
                return true;
            return false;
        }
        
        @Override
        public int compareTo(Sector o) {
            return this.end - o.end;
        }
    }
}