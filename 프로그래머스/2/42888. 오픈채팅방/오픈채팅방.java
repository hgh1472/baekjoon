import java.util.*;

class Solution {
    
    Map<String, String> users = new HashMap<>();
    String enter = "%s님이 들어왔습니다.";
    String leave = "%s님이 나갔습니다.";
    
    public String[] solution(String[] record) {
        int count = 0;
        
        for (String r : record) {
            String[] info = r.split(" ");
            if (info[0].equals("Enter")) {
                users.put(info[1], info[2]);
                count++;
            }
            if (info[0].equals("Leave")) {
                count++;
            }
            if (info[0].equals("Change")) {
                users.put(info[1], info[2]);
            }
        }
        
        String[] answer = new String[count];
        
        int idx = 0;
        for (String r : record) {
            String[] info = r.split(" ");
            if (info[0].equals("Enter")) {
                answer[idx++] = String.format(enter, users.get(info[1]));
            }
            if (info[0].equals("Leave")) {
                answer[idx++] = String.format(leave, users.get(info[1]));
            }
        }
        
        return answer;
    }
}