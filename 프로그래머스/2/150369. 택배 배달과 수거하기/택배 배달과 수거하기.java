import java.util.*;
class Solution {
    public  long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int last = n-1;
        while (true) {
            int delivery = 0;
            int take = 0;
            while (last >= 0 && deliveries[last] == 0 && pickups[last] == 0)
                last--;
            if (last == -1)
                break;
            for (int i = last; i >= 0; i --) {
                delivery += deliveries[i];
                if (delivery > cap) {
                    deliveries[i] = delivery - cap;
                    break;
                }
                deliveries[i] = 0;
            }
            for (int i = last; i >= 0; i--) {
                take += pickups[i];
                if (take > cap) {
                    pickups[i] = take - cap;
                    break;
                }
                pickups[i] = 0;
            }
            answer += (last + 1) * 2;
        }
        return answer;
    }
}