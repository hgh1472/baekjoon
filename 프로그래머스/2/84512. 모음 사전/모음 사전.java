import java.util.*;

class Solution {
    public int solution(String word) {
        int[] add = {781, 156, 31, 6, 1};
        Map<Character, Integer> order = new HashMap<>();
        order.put('A', 1);
        order.put('E', 2);
        order.put('I', 3);
        order.put('O', 4);
        order.put('U', 5);
        int answer = word.length();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            answer += add[i] * (order.get(c) - 1);
        }
        
        return answer;
    }
}