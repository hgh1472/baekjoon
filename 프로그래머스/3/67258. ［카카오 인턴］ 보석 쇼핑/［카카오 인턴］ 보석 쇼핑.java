import java.util.*;

class Solution {
    Map<String, Integer> gemCounts = new HashMap<>();
    public int[] solution(String[] gems) {
        Set<String> keyset = new HashSet<>();
        for (String gem : gems) {
            keyset.add(gem);
        }
        
        int gemSize = keyset.size();
        int nowCount = 0;
        
        int left = 0;
        int right = 1;
        gemCounts.put(gems[left], 1);
        nowCount++;
        int[] answer = new int[2];
        int minimumLength = 100001;
        while (right <= gems.length && left < right) {
            if (isFull(nowCount, gemSize)) {
                if (minimumLength > right - left) {
                    minimumLength = Math.min(minimumLength, right - left);
                    answer[0] = left + 1;
                    answer[1] = right;
                }
                int count = gemCounts.get(gems[left]);
                if (count == 1) {
                    nowCount--;
                }
                gemCounts.put(gems[left], gemCounts.get(gems[left]) - 1);
                left++;
                continue;
            }
            if (right == gems.length) {
                break;
            }
            int count = gemCounts.getOrDefault(gems[right], 0);
            if (count == 0) {
                nowCount++;
            }
            gemCounts.put(gems[right], count + 1);
            right++;
        }
        if (isFull(nowCount, gemSize)) {
            if (minimumLength > right - left) {
                answer[0] = left + 1;
                answer[1] = right;
            }
        }
        return answer;
    }
    
    boolean isFull(int nowCount, int keySize) {
        if (nowCount == keySize) {
            return true;
        }
        return false;
    }
}