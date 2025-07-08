import java.util.*;

class Solution {
    char[] operators = {'+', '-', '*'};
    long result = 0;
    public long solution(String expression) {
        
//         List<Long> numbers = new ArrayList<>();
//         List<Character> operators = new ArrayList<>();

//         int idx = 0;
//         long num = 0;
//         while (idx < expression.length()) {
//             if ('0' <= expression.charAt(idx) && expression.charAt(idx) <= '9') {
//                 num *= 10;
//                 num += expression.charAt(idx) - '0';
//             }
//             else {
//                 numbers.add(num);
//                 num = 0;
//                 operators.add(expression.charAt(idx));
//             }
//             idx++;
//         }
//         numbers.add(num);
//         Expression exp = new Expression(numbers, operators);
//         exp.calculate('*');
//         exp.calculate('-');
//         return exp.numbers;
        
        dfs(new char[3], expression, new boolean[3], 0);
        
        return result;
    }
    
    void dfs(char[] ops, String expression, boolean[] visited, int count) {
        if (count == 3) {
            List<Long> numbers = new ArrayList<>();
            List<Character> operators = new ArrayList<>();
            
            int idx = 0;
            long num = 0;
            while (idx < expression.length()) {
                if ('0' <= expression.charAt(idx) && expression.charAt(idx) <= '9') {
                    num *= 10;
                    num += expression.charAt(idx) - '0';
                }
                else {
                    numbers.add(num);
                    num = 0;
                    operators.add(expression.charAt(idx));
                }
                idx++;
            }
            numbers.add(num);
            Expression exp = new Expression(numbers, operators);
            for (int i = 0; i < 3; i++) {
                exp.calculate(ops[i]);
            }
            result = Math.max(result, Math.abs(exp.numbers.get(0)));
            return;
        }
        for (int i = 0; i < 3; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            ops[count] = operators[i];
            dfs(ops, expression, visited, count+1);
            visited[i] = false;
        }
    }
    
    class Expression {
        List<Long> numbers;
        List<Character> operators;
        
        Expression(List<Long> numbers, List<Character> operators) {
            this.numbers = numbers;
            this.operators = operators;
        }
        
        void calculate(char op) {
            List<Long> nextNums = new ArrayList<>();
            nextNums.add(numbers.get(0));
            List<Character> nextOps = new ArrayList<>();
            
            
            int numIdx = 0;
            for (int i = 0; i < operators.size(); i++) {
                if (operators.get(i) == op) {
                    long num1 = nextNums.get(nextNums.size()-1);
                    long num2 = numbers.get(i+1);
                    long result;
                    if (op == '+') {
                        result = num1+num2;
                    }
                    else if (op == '-') {
                        result = num1 - num2;
                    }
                    else {
                        result = num1 * num2;
                    }
                    nextNums.set(nextNums.size()-1, result);
                }
                else {
                    nextNums.add(numbers.get(i+1));
                    nextOps.add(operators.get(i));
                }
            }
            this.numbers = nextNums;
            this.operators = nextOps;
        }
    }
}