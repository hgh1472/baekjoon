import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < friends.length; i++) {
            map.put(friends[i], i);
        }
        
        int[][] info = new int[friends.length][friends.length];
        
        int[] numbers = new int[friends.length];
        
        for (String gift : gifts) {
            String[] split = gift.split(" ");
            int num1 = map.get(split[0]);
            int num2 = map.get(split[1]);
            info[num1][num2]++;
            numbers[num1]++;
            numbers[num2]--;
        }
        
        int[] result = new int[friends.length];
        
        for (int i = 0; i < info.length; i++) {
            for (int j = i; j < info.length; j++) {
                if (i == j) {
                    continue;
                }
                if (info[i][j] > info[j][i]) {
                    result[i]++;
                }
                else if (info[i][j] < info[j][i]) {
                    result[j]++;
                }
                else {
                    if (numbers[i] > numbers[j]) {
                        result[i]++;
                    }
                    else if (numbers[i] < numbers[j]) {
                        result[j]++;
                    }
                }
            }
        }
        
        int answer = 0;
        for (int i = 0; i < result.length; i++) {
            answer = Math.max(answer, result[i]);
        }
        return answer;
    }
}