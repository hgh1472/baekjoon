import java.util.*;

class Solution {
    static int maxUserCount = 0;
    static int maxProfit = 0;
    static int[] emoticonInfo;
    static int[][] userInfo;
    public int[] solution(int[][] users, int[] emoticons) {
        emoticonInfo = emoticons;
        userInfo = users;
        makeDiscountCombination(new Stack<>());
        int[] answer = new int[2];
        answer[0] = maxUserCount;
        answer[1] = maxProfit;
        return answer;
    }
    
    public void makeDiscountCombination(Stack<Integer> discounts) {
        if (discounts.size() == emoticonInfo.length) {
            calculate(discounts);
            return;
        }
        for (int i = 10; i <= 40; i += 10) {
            discounts.push(i);
            makeDiscountCombination(discounts);
            discounts.pop();
        }
    }
    
    public void calculate(Stack<Integer> discounts) {
        int userCount = 0;
        int profit = 0;
        for (int i = 0; i < userInfo.length; i++) {
            int price = 0;
            int buyCondition = userInfo[i][0];
            int plusCondition = userInfo[i][1];
            for (int j = 0; j < emoticonInfo.length; j++) {
                if (discounts.get(j) < buyCondition) continue;
                price += emoticonInfo[j] * (100 - discounts.get(j)) / 100;
            }
            if (price >= plusCondition) {
                userCount += 1;
            }
            else {
                profit += price;
            }
        }
        if (maxUserCount < userCount) {
            maxUserCount = userCount;
            maxProfit = profit;
        }
        else if (maxUserCount == userCount && maxProfit < profit) {
            maxProfit = profit;
        }
    }
}