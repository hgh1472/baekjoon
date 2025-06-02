import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {
        List<Magic> magics = new ArrayList<>();
        for (String ban : bans) {
            magics.add(new Magic(ban));
        }
        Collections.sort(magics);
        for (Magic ban : magics) {
            long order = calculateOrder(ban.str);
            if (order <= n) {
                n++;
            }
        }
        String answer = convertString(n);
        return answer;
    }
    
    String convertString(long n) {
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            int flag = 0;
            if (n%26 == 0) {
                sb.append("z");
                flag = 1;
            }
            else {
                char add = 'a';
                add += ((n%26)-1);
                sb.append(add);
            }
            n /=26;
            n -= flag;
        }
        sb.reverse();
        return sb.toString();
    }
    
    long calculateOrder(String str) {
        long order = 0;
        for (int i = 0; i < str.length(); i++) {
            order *= 26;
            order += str.charAt(i) - 'a' + 1;
        }
        return order;
    }
    
    class Magic implements Comparable<Magic> {
        String str;
        
        Magic(String str) {
            this.str = str;
        }
        
        @Override
        public int compareTo(Magic m) {
            if (this.str.length() < m.str.length()) {
                return -1;
            }
            if (this.str.length() == m.str.length()) {
                for (int i = 0; i < this.str.length(); i++) {
                    if (this.str.charAt(i) < m.str.charAt(i)) {
                        return -1;
                    }
                    if (this.str.charAt(i) > m.str.charAt(i)) {
                        return 1;
                    }
                }
                return 0;
            }
            return 1;
        }
    }
}