import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, String> parents = new HashMap<>();
        Map<String, Integer> profits = new HashMap<>();
        for (int i = 0; i < enroll.length; i++) {
            parents.put(enroll[i], referral[i]);
            profits.put(enroll[i], 0);
        }
        
        for (int i = 0; i < seller.length; i++) {
            int income = amount[i] * 100;
            int out = income * 10 / 100;
            int profit = income - out;
            profits.put(seller[i], profits.get(seller[i]) + profit);
            String parent = parents.get(seller[i]);
            while (out != 0 && !parent.equals("-")) {
                income = out;
                out = income * 10 / 100;
                profit = income - out;
                profits.put(parent, profits.get(parent) + profit);
                parent = parents.get(parent);
            }
        }
        
        int[] answer = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = profits.get(enroll[i]);
        }
        return answer;
    }
}