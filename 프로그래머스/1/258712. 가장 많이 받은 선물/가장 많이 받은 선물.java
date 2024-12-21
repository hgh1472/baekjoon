import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        Map<String, Integer> friendNumbers = new HashMap<>();
        int[][] giftInfo = new int[friends.length][friends.length];
        int[] giftOrder = new int[friends.length];
        int[] result = new int[friends.length];
        for (int i = 0; i < friends.length; i++) {
            friendNumbers.put(friends[i], i);
        }
        for (String gift : gifts) {
            String[] info = gift.split(" ");
            int a = friendNumbers.get(info[0]);
            int b = friendNumbers.get(info[1]);
            giftInfo[a][b] += 1;
            giftOrder[a] += 1;
            giftOrder[b] -= 1;
        }
        for (int i = 0; i < friends.length; i++) {
            for (int j = i + 1; j < friends.length; j++) {
                int a = friendNumbers.get(friends[i]);
                int b = friendNumbers.get(friends[j]);
                if (giftInfo[a][b] > giftInfo[b][a]) {
                    result[a] += 1;
                }
                else if (giftInfo[a][b] < giftInfo[b][a]) {
                    result[b] += 1;
                }
                else {
                    if (giftOrder[a] > giftOrder[b]) {
                        result[a] += 1;
                    }
                    else if (giftOrder[a] < giftOrder[b]) {
                        result[b] += 1;
                    }
                }
            }
        }
        int answer = 0;
        for (int num : result) {
            answer = Math.max(answer, num);
        }
        return answer;
    }
}