import java.util.*;

class Solution {
    int answer = 0;
    List<BanList> banLists = new ArrayList<>();
    public int solution(String[] user_id, String[] banned_id) {
        
        dfs(user_id, banned_id, 0, new boolean[user_id.length]);
        
        return answer;
    }
    
    public void dfs(String[] user_id, String[] banned_id, int count, boolean[] visited) {
        if (count == banned_id.length) {
            Set<String> users = new HashSet<>();
            for (int i = 0; i < user_id.length; i++) {
                if (visited[i]) {
                    users.add(user_id[i]);
                }
            }
            
            for (BanList b : banLists) {
                int dupCount = 0;
                for (String user : users) {
                    for (String u : b.users) {
                        if (user.equals(u)) {
                            dupCount++;
                            break;
                        }
                    }
                }
                if (dupCount == banned_id.length) {
                    return;
                }
            }
            answer++;
            BanList list = new BanList(users);
            banLists.add(list);
            return;
        }
        for (int i = 0; i < user_id.length; i++) {
            if (visited[i]) {
                continue;
            }
            if (isPossible(banned_id[count], user_id[i])) {
                visited[i] = true;
                dfs(user_id, banned_id, count+1, visited);
                visited[i] = false;
            }
        }
    }
    
    public boolean isPossible(String ban, String user) {
        if (ban.length() != user.length()) {
            return false;
        }
        for (int i = 0; i < ban.length(); i++) {
            if (ban.charAt(i) == '*') {
                continue;
            }
            if (ban.charAt(i) != user.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    
    class BanList {
        Set<String> users;
        
        BanList(Set<String> users) {
            this.users = users;
        }
        
    }
}