import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        long sum1 = 0;
        long sum2 = 0;
        
        Deque<Integer> q1 = new ArrayDeque<>();
        Deque<Integer> q2 = new ArrayDeque<>();
        for (int i = 0; i < queue1.length; i++) {
            sum1 += (long) queue1[i];
            q1.addLast(queue1[i]);
        }
        for (int i = 0; i < queue2.length; i++) {
            sum2 += (long) queue2[i];
            q2.addLast(queue2[i]);
        }
        
        int count = 0;
        while (sum1 != sum2 && count <= queue1.length * 3) {
            if (sum1 == sum2) {
                return count;
            }
            else if (sum1 > sum2) {
                int num = q1.removeFirst();
                q2.addLast(num);
                sum1 -= num;
                sum2 += num;
            }
            else if (sum1 < sum2) {
                int num = q2.removeFirst();
                q1.addLast(num);
                sum2 -= num;
                sum1 += num;
            }
            count++;
        }
        
        if (sum1 == sum2) {
            return count;
        }
        return -1;
    }
}