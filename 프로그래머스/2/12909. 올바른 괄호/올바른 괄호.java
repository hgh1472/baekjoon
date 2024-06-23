class Solution {
    boolean solution(String s) {        
        char[] temp = s.toCharArray();
        int stack = 0;
        for (char c : temp) {
            if (c == '(') {
                stack++;
            }
            if (c == ')') {
                stack--;
            }
            if (stack < 0) {
                return false;
            }
        }
        if (stack != 0)
            return false;
        return true;
    }
}