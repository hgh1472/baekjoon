import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        StringBuilder sb = new StringBuilder();
        Stack<String> operands = new Stack<>();
        Queue<String> alphabet = new ArrayDeque<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (isOperand(c)) {
                if (c == '(') {
                    operands.push(String.valueOf(c));
                }
                else if (c == ')') {
                    while (!alphabet.isEmpty()) {
                        sb.append(alphabet.poll());
                    }
                    while (!operands.isEmpty()) {
                        String pop = operands.pop();
                        if (pop.equals("(")) {
                            break;
                        }
                        sb.append(pop);
                    }
                } else {
                    if (!operands.isEmpty()) {
                        if (getPriority(operands.peek().charAt(0)) >= getPriority(c)) {
                            while (!alphabet.isEmpty()) {
                                sb.append(alphabet.poll());
                            }
                            while (!operands.isEmpty()) {
                                String pop = operands.pop();
                                if (pop.equals("(")) {
                                    operands.push(pop);
                                    break;
                                }
                                if (getPriority(pop.charAt(0)) < getPriority(c)) {
                                    operands.push(pop);
                                    break;
                                }
                                sb.append(pop);
                            }
                        }
                    }
                    operands.push(String.valueOf(c));
                }
            }
            else {
                alphabet.add(String.valueOf(c));
            }
        }
        while (!alphabet.isEmpty()) {
            sb.append(alphabet.poll());
        }
        while (!operands.isEmpty()) {
            sb.append(operands.pop());
        }
        System.out.println(sb);
    }

    public static boolean isOperand(char c) {
        if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')')
            return true;
        return false;
    }

    public static int getPriority(char c) {
        if (c == '*' || c =='/')
            return 2;
        else
            return 1;
    }
}
