import java.util.*;
class Solution {
    public int solution(String[] friends, String[] gifts) {
        HashMap<String, Integer> friendKey = new HashMap<>();
        for (int i = 0; i < friends.length; i++) {
            friendKey.put(friends[i], i);
        }
        int[][] giftGraph = new int[friends.length][friends.length];
        int[] giftIndex = new int[friends.length];
        for (String gift : gifts) {
            String[] person = gift.split(" ");
            int give = friendKey.get(person[0]);
            int given = friendKey.get(person[1]);
            giftGraph[give][given] += 1;
            giftIndex[give] += 1;
            giftIndex[given] -= 1;
        }
        int[] giftCount = new int[friends.length];
        for (int i = 0; i < giftGraph.length; i++) {
            for (int j = i + 1; j < giftGraph.length; j++) {
                if (giftGraph[i][j] > giftGraph[j][i]) {
                    giftCount[i]++;
                }
                else if (giftGraph[i][j] < giftGraph[j][i]) {
                    giftCount[j]++;
                }
                else {
                    if (giftIndex[i] > giftIndex[j])
                        giftCount[i]++;
                    else if (giftIndex[i] < giftIndex[j])
                        giftCount[j]++;
                }
            }
        }
        int maxGiftCount = 0;
        for (int i = 0; i < friends.length; i++) {
            if (maxGiftCount < giftCount[i])
                maxGiftCount = giftCount[i];
        }
        return maxGiftCount;
    }
}