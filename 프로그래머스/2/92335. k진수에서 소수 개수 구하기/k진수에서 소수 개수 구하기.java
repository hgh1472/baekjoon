class Solution {
    public int solution(int n, int k) {
        String string = Integer.toString(n, k);
        int start = -1;
        int end = 0;
        
        int answer = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '0') {
                if (start == -1) {
                    continue;
                }
                long number = Long.parseLong(string.substring(start, i));
                if (isPrime(number)) {
                    answer++;
                }
                start = -1;
            }
            else {
                if (start == -1) {
                    start = i;
                }
            }
        }
        if (start != -1) {
            long number = Long.parseLong(string.substring(start, string.length()));
            if (isPrime(number)) {
                answer++;
            }
        }
        
        return answer;
    }
    
    boolean isPrime(long number) {
        if (number == 1) {
            return false;
        }
        for (long i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}