import java.util.*;

class Solution {
    Map<String, Integer> dict;
    public int[] solution(String msg) {
        dict = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            dict.put(String.valueOf((char)('A' + i)), i+1);
        }
        
        int count = 27;
        
        List<Integer> answer = new ArrayList<>();
        int index = 0;
        while (index < msg.length()) {
            int start = index;
            while (index < msg.length() && dict.containsKey(msg.substring(start, index+1))) {
                index++;
            }
            answer.add(dict.get(msg.substring(start, index)));
            if (index < msg.length()) {
                dict.put(msg.substring(start, index+1), count);                
            }
            count++;
        }
        int[] arr = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            arr[i] = answer.get(i);
        }
        return arr;
    }
}