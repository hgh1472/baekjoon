import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, Set<String>> reportMap = new HashMap<>();
        for (String id : id_list) {
            reportMap.put(id, new HashSet<>());
        }
        
        Map<String, Integer> counts = new HashMap<>();
        for (String r : report) {
            String[] info = r.split(" ");
            Set<String> reportList = reportMap.get(info[0]);
            if (reportList.contains(info[1])) {
                continue;
            }
            reportList.add(info[1]);
            counts.put(info[1], counts.getOrDefault(info[1], 0) + 1);
        }
        
        int[] answer = new int[id_list.length];
        for (int i = 0; i < id_list.length; i++) {
            int count = 0;
            Set<String> targets = reportMap.get(id_list[i]);
            for (String target : targets) {
                if (counts.get(target) >= k) {
                    count++;
                }
            }
            answer[i] = count;
        }
        return answer;
    }
}