import java.util.*;

class Solution {
    public int solution(int n, int k) {
        StringBuilder sb = new StringBuilder();
        
        while (n / k != 0) {
            sb.append(n % k);
            n /= k;
        }
        sb.append(n);
        
        String number = sb.reverse().toString();
        
        String[] candidates = number.split("0");
        
        int answer = 0;
        for (String s : candidates) {
            if (s.isEmpty()) {
                continue;
            }
            long cand = Long.parseLong(s);
            if (isPrime(cand)) {
                answer++;
            }
        }
        
        return answer;
    }
    
    boolean isPrime(long n) {
        if (n <= 1) {
            return false;
        }
        long sqrt = (long) Math.sqrt(n);
        for (long i = 2; i <= sqrt; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}