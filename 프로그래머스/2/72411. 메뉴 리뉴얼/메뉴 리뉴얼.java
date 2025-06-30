import java.util.*;

class Solution {
    boolean[] contains = new boolean[26];
    List<String> result = new ArrayList<>();
    PriorityQueue<Node>[] q = new PriorityQueue[11];
    public String[] solution(String[] orders, int[] course) {
        for (int i = 0; i < 11; i++) {
        q[i] = new PriorityQueue<>();
        }
        for (int i = 0; i < orders.length; i++) {
            for (int j = 0; j < orders[i].length(); j++) {
                contains[orders[i].charAt(j) - 'A'] = true;
            }
        }
        
        for (int i = 0; i < course.length; i++) {
            combination(orders, course[i], 0, 0, new ArrayList<>());
        }
        int total = 0;
        for (int i = 0; i < 11; i++) {
            total += q[i].size();
        }
        String[] answer = new String[total];
        int idx = 0;
        for (int i = 0; i < 11; i++) {
            while (!q[i].isEmpty()) {
                answer[idx++] = q[i].poll().s;
            }
        }
        Arrays.sort(answer);
        return answer;
    }
    
    public void combination(String[] orders, int depth, int now, int idx, List<String> set) {
        if (now == depth) {
            int count = 0;
            Collections.sort(set);
            for (int i = 0; i < orders.length; i++) {
                count++;
                for (int j = 0; j < set.size(); j++) {
                    String s = set.get(j);
                    if (!orders[i].contains(s)) {
                        count--;
                        break;
                    }
                }
            }
            if (count < 2) {
                return;
            }
            if (!q[depth].isEmpty()) {
                if (q[depth].peek().count > count) {
                    return;
                }
                if (q[depth].peek().count < count) {
                    while (!q[depth].isEmpty()) {
                        q[depth].poll();
                    }
                }
            }
            
            String add = "";
            for (int i = 0; i < set.size(); i++) {
                String s = set.get(i);
                add = add.concat(s);
            }
            q[depth].add(new Node(add, count));
            return;
        }
        for (int i = idx; i < 26; i++) {
            if (!contains[i]) {
                continue;
            }
            String s = String.valueOf((char)('A'+i));
            set.add(s);
            combination(orders, depth, now+1, i+1, set);
            set.remove(s);
        }
    }
    
    class Node implements Comparable<Node> {
        String s;
        int count;
        
        Node(String s, int count) {
            this.s = s;
            this.count = count;
        }
        
        @Override
        public int compareTo(Node n) {
            return n.count - this.count;
        }
        
    }
}