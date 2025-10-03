import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        Map<Integer, Boolean> map = new HashMap<>();
        PriorityQueue<Max> maxQueue = new PriorityQueue<>();
        PriorityQueue<Min> minQueue = new PriorityQueue<>();
        
        for (String operation : operations) {
            String[] info = operation.split(" ");
            if (info[0].equals("I")) {
                int num = Integer.parseInt(info[1]);
                maxQueue.add(new Max(num));
                minQueue.add(new Min(num));
                map.put(num, true);
            }
            if (info[0].equals("D") && info[1].equals("1")) {
                while (!maxQueue.isEmpty()) {
                    int num = maxQueue.poll().number;
                    if (!map.get(num)) {
                        continue;
                    }
                    map.put(num, false);
                    break;
                }
            }
            if (info[0].equals("D") && info[1].equals("-1")) {
                while (!minQueue.isEmpty()) {
                    int num = minQueue.poll().number;
                    if (!map.get(num)) {
                        continue;
                    }
                    map.put(num, false);
                    break;
                }
            }
        }
        
        int maxNum = 0, minNum = 0;
        while (!maxQueue.isEmpty()) {
            int num = maxQueue.poll().number;
            if (map.get(num)) {
                maxNum = num;
                break;
            }
        }
        while (!minQueue.isEmpty()) {
            int num = minQueue.poll().number;
            if (map.get(num)) {
                minNum = num;
                break;
            }
        }
        return new int[]{maxNum, minNum};
    }
    
    class Max implements Comparable<Max> {
        int number;
        
        Max(int number) {
            this.number = number;
        }
        
        @Override
        public int compareTo(Max m) {
            return m.number - this.number;
        }
    }
    
    class Min implements Comparable<Min> {
        int number;
        
        Min(int number) {
            this.number = number;
        }
        
        @Override
        public int compareTo(Min m) {
            return this.number - m.number;
        }
    }
}