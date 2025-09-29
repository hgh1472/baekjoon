import java.util.*;

class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            String binary = convert(numbers[i]);
            answer[i] = check(binary, 0, binary.length() - 1, true);
        }
        return answer;
    }
    
    int check(String binary, int left, int right, boolean possible) {
        if (left > right) {
            return 1;
        }
        int mid = (left + right) / 2;
        boolean isOne = binary.charAt(mid) == '1';
        if (!possible && isOne) {
            return 0;
        }
        if (check(binary, left, mid - 1, isOne) == 0) {
            return 0;
        }
        return check(binary, mid + 1, right, isOne);
    }
    
    String convert(long number) {
        StringBuilder sb = new StringBuilder();
        while (number >= 2) {
            sb.append(number % 2);
            number /= 2;
        }
        sb.append(number);
        String add = add(sb.length());
        return sb.append(add).reverse().toString();
    }
    
    String add(int length) {
        if (length == 1) {
            return "";
        }
        int len = 0;
        while ((int) Math.pow(2, len) <= length) {
            len++;
        }
        return "0".repeat(((int) Math.pow(2, len)) - 1 - length);
    }
}