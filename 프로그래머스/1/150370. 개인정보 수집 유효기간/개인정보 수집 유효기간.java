import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < terms.length; i++) {
            String[] info = terms[i].split(" ");
            map.put(info[0], Integer.parseInt(info[1]));
        }
        
        int todayNum = calculate(today);
        
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < privacies.length; i++) {
            String[] info = privacies[i].split(" ");
            int num = calculate(info[0]);
            int add = 28 * map.get(info[1]);
            if (todayNum >= num + add) {
                result.add(i);
            }
        }
        
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i) + 1;
        }
        return answer;
    }
    
    int calculate(String day) {
        String[] info = day.split("\\.");
        return 28 * 12 * Integer.parseInt(info[0]) + 28 * Integer.parseInt(info[1]) + Integer.parseInt(info[2]);
    }
}