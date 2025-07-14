import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        Integer number = 0;
        while (sb.length() <= t * m) {
            sb.append(number.toString(number, n));
            number++;
        }
        
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < t; i++) {
            char add = sb.toString().charAt((m*i)+(p-1));
            if ('a' <= add && add <= 'z') {
                add -= 'a';
                add += 'A';
            }
            answer.append(add);
        }
        return answer.toString();
    }
}