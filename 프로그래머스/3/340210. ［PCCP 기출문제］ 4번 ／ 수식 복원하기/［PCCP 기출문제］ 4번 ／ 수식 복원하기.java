import java.util.*;

class Solution {
    public String[] solution(String[] expressions) {
        boolean[] isPossible = new boolean[10];
        
        // 진법 후보 계산 max보다 큰 진법만 가능
        int max = 1;
        int count = 0;
        char[] operators = new char[expressions.length];
        for (int i = 0; i < expressions.length; i++) {
            String expression = expressions[i];
            if (expression.contains("+")) {
                operators[i] = '+';
            }
            else {
                operators[i] = '-';
            }
            if (expression.contains("X")) {
                count++;
            }
            String nums = expression.replaceAll("[^0-9]", "");
            for (int j = 0; j < nums.length(); j++) {
                max = Math.max(max, nums.charAt(j) - '0');
            }
            
        }
        
        for (int i = max+1; i < 10; i++) {
            isPossible[i] = true;
        }
        
        // 계산 가능한 진법 확인
        for (int i = 0; i < expressions.length; i++) {
            String e = expressions[i];
            if (e.contains("X")) {
                continue;
            }
    
            String[] nums = getNumberStrings(e);
            
            for (int j = max+1; j < 10; j++) {
                int a = convertToDecimal(nums[0], j);
                int b = convertToDecimal(nums[1], j);
                int c = convertToDecimal(nums[2], j);
                if (c != calculate(a, b, operators[i])) {
                    isPossible[j] = false;
                }
            }
        }
        
        String[] answer = new String[count];
        int idx = 0;
        for (int j = 0; j < expressions.length; j++) {
            String e = expressions[j];
            if (e.charAt(e.length()-1) != 'X') {
                continue;
            }
            String[] op = getNumberStrings(e);
            boolean isCalculatable = true;
            String result = "";
            for (int i = max+1; i < 10; i++) {
                if (!isPossible[i]) {
                    continue;
                }
                int a = convertToDecimal(op[0], i);
                int b = convertToDecimal(op[1], i);
                int c = calculate(a, b, operators[j]);
                String tmp = Integer.toString(c, i);
                if (result.isEmpty()) {
                    result = tmp;
                }
                if (!result.equals(tmp)) {
                    result = "?";
                    break;
                }
            }
            answer[idx++] = e.substring(0, e.length()-1).concat(result);
        }
        return answer;
    }
    
    public String[] getNumberStrings(String str) {
        String[] strs = str.replaceAll("[^0-9]", " ").split(" ");
        String[] result;
        if (str.charAt(str.length()-1) == 'X') {
            result = new String[2];
        }
        else {
            result = new String[3];
        }
        int idx = 0;
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].isEmpty()) {
                continue;
            }
            result[idx++] = strs[i];
        }
        return result;
    }
    
    public int calculate(int a, int b, char op) {
        if (op == '+') {
            return a + b;
        }
        else {
            return a - b;
        }
    }
    
    public boolean isNumber(char c) {
        return '0' <= c && c <= '9';
    }
    
    public int convertToDecimal(String num, int n) {
        int result = 0;
        for (int i = 0; i < num.length(); i++) {
            result *= n;
            result += (num.charAt(i) - '0');
        }
        return result;
    }
}