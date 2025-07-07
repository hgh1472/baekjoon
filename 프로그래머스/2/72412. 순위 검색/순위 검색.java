import java.util.*;

class Solution {
    Map<String, List<Integer>> cond;
    public int[] solution(String[] info, String[] query) {
        cond = new HashMap<>();
        for (int i = 0; i < info.length; i++) {
            String[] s = info[i].split(" ");
            for (int j = 0; j < 2; j++) {
                String k1 = (j % 2 == 0) ? s[0] : "-";
                for (int k = 0; k < 2; k++) {
                    String k2 = (k % 2 == 0) ? s[1] : "-";
                    for (int t = 0; t < 2; t++) {
                        String k3 = (t % 2 == 0) ? s[2] : "-";
                        for (int x = 0; x < 2; x++) {
                            String k4 = (x % 2 == 0) ? s[3] : "-";
                            String key = k1+k2+k3+k4;
                            if (!cond.containsKey(key)) {
                                cond.put(key, new ArrayList<>());
                            }
                            cond.get(key).add(Integer.parseInt(s[4]));
                        }
                    }
                }
            }
        }
        
        for (String key : cond.keySet()) {
            Collections.sort(cond.get(key));
        }
        
        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            query[i] = query[i].replaceAll(" and ", "");
            String[] q = query[i].split(" ");
            answer[i] = cond.containsKey(q[0]) ? binarySearch(q[0], Integer.parseInt(q[1])) : 0;
        }
        return answer;
    }
    
    void dfs(String[] s, int count, String key) {
        if (count == 4) {
            if (!cond.containsKey(key)) {
                cond.put(key, new ArrayList<>());
            }
            cond.get(key).add(Integer.parseInt(s[4]));
            return;
        }
        dfs(s, count+1, key + s[count]);
        dfs(s, count+1, key + "-");
    }
    
    	
    // 이분 탐색
    private int binarySearch(String key, int score) {
        List<Integer> list = cond.get(key);
        int start = 0, end = list.size() - 1;
 
        while (start <= end) {
            int mid = (start + end) / 2;
            if (list.get(mid) < score)
                start = mid + 1;
            else
                end = mid - 1;
        }
 
        return list.size() - start;
    }
    
    int search(List<Integer> targets, int score) {
        int left = 0;
        int right = targets.size()-1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (targets.get(mid) == score) {
                return mid;
            }
            else if (targets.get(mid) < score) {
                left = mid+1;
            }
            else {
                right = mid-1;
            }
        }
        return left;
    }
}