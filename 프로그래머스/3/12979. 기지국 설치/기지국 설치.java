import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int idx = 1;
        int answer = 0;
        
        for (int i = 0; i < stations.length; i++) {
            int s = stations[i] - w;
            int length = s-idx; // 현재 위치에서 전파가 닿는 곳까지의 거리
            if (length <= 0) {
                idx = s + 2*w +1;
            }
            else {
                int add = length / (2*w+1); // 전파가 닿기까지 새로 세워야하는 기지국 수
                if (length % (2*w+1) > 0) {
                    add++;
                }
                answer += add;
            }
            idx = s + 2*w+1;
        }
        
        if (idx <= n) {
            int length = n+1 - idx;
            int add = (length / (2*w+1));
            if (length % (2*w+1) > 0) {
                add++;
            }
            answer += add;
            idx += (2*w + 1) * add + 1;
        }
        
        return answer;

    }
}
/**
13 [3, 7, 11] 1 4
5 [3] 2 0
6 [3] 2 1
16 [1, 16] 2 2
6 [4] 2 1
11 [1, 4] 1 2
11 [1, 5] 1 3
5 [1, 2, 3, 4, 5] 1 0
200000000 [100000000] 5 18181818
*/
