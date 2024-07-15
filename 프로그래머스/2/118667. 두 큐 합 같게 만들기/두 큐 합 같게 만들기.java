import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        long firstSum = 0;
        long secondSum = 0;
        Queue<Integer> firstQueue = new LinkedList<>();
        Queue<Integer> secondQueue = new LinkedList<>();
        for (int i = 0; i < queue1.length; i++) {
            firstQueue.add(queue1[i]);
            firstSum += queue1[i];
        }
        for (int i = 0; i < queue2.length; i++) {
            secondQueue.add(queue2[i]);
            secondSum += queue2[i];
        }
        if (firstSum == secondSum)
            return 0;
        if ((firstSum + secondSum) % 2 == 1)
            return -1;
        int count = 0;
        while (count <= 4 * (queue1.length + queue2.length)) {
            int num;
            if (firstSum > secondSum) {
                num = firstQueue.poll();
                firstSum -= num;
                secondQueue.add(num);
                secondSum += num;
                count += 1;
            }
            else if (firstSum < secondSum) {
                num = secondQueue.poll();
                secondSum -= num;
                firstQueue.add(num);
                firstSum += num;
                count += 1;
            }
            else {
                return count;
            }
        }
        return -1;
    }
}