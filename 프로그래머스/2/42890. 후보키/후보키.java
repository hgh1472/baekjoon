import java.util.*;

class Solution {
    Set<String> answer = new HashSet<>();
    public int solution(String[][] relation) {
        for (int i = 1; i <= relation[0].length; i++) {
            dfs(i, 0, 0, new int[i], relation);
        }
        return answer.size();
    }
    
    public void dfs(int depth, int now, int index, int[] keys, String[][] relation) {
        if (depth == now) {
            calculate(relation, keys);
            return;
        }
        for (int i = index; i < relation[0].length; i++) {
            keys[now] = i;
            dfs(depth, now+1, i+1, keys, relation);
        }
    }
    
    public void calculate(String[][] relation, int[] keys) {
        // 튜플 별 키 조합
        Set<String> tmp = new HashSet<>();
        for (int i = 0; i < relation.length; i++) {
            String str = new String();
            for (int key : keys) {
                str = str.concat(relation[i][key]);
            }
            tmp.add(str);
        }
        
        // 유일성 X
        if (tmp.size() != relation.length) {
            return;
        }
        
        // 최소성 X
        String key = new String();
        for (int k : keys) {
            key = key.concat(String.valueOf(k));
        }
        for (String ans : answer) {
            int duplicateCount = 0;
            for (int i = 0; i < ans.length(); i++) {
                for (int j = 0; j < key.length(); j++) {
                    if (ans.charAt(i) == key.charAt(j)) {
                        duplicateCount++;
                        break;
                    }
                }
            }
            if (duplicateCount == ans.length()) {
                return;
            }
        }
        
        answer.add(key);
    }
    
}