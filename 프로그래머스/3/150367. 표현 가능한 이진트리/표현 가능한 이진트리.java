import java.util.*;

class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        int idx = 0;
        for (long number : numbers) {
            String binary = calculateBinary(number);
            int lastDepth = 0;
            while (!(Math.pow(2, lastDepth) <= binary.length() && binary.length() < Math.pow(2, lastDepth + 1)))
                lastDepth++;
            boolean result = dfs(binary, 0, binary.length() - 1, 0, lastDepth, false);
            if (result)
                answer[idx++] = 1;
            else
                answer[idx++] = 0;
        }
        
        return answer;
    }
    
    public boolean dfs(String binary, int left, int right, int depth, int lastDepth, boolean isZero) {
        if (depth > lastDepth)
            return true;
        int mid = (left + right) / 2;
        if (binary.charAt(mid) == '1' && isZero) {
            return false;
        }
        boolean next = binary.charAt(mid) == '0';
        if (!dfs(binary, left, mid - 1, depth + 1, lastDepth, next))
            return false;
        return dfs(binary, mid + 1, right, depth + 1, lastDepth, next);
    }
    
    public String calculateBinary(long number) {
        StringBuilder sb = new StringBuilder();
        while (number != 0) {
            sb.append(number % 2);
            number /= 2;
        }
        fillDummy(sb);
        return sb.reverse().toString();
    }
    
    public void fillDummy(StringBuilder sb) {
        int length = sb.length();
        int count = 0;
        while (!(Math.pow(2, count) <= length && length < Math.pow(2, count + 1))) {
            count += 1;
        }
        for (int i = length; i < Math.pow(2, count + 1) - 1; i++) {
            sb.append("0");
        }
    }
}