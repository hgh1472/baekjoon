import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int cursor = 0;
    static char[] stack = new char[100];
    static int top = -1;
    static int stackA = 0;
    static int stackB = 0;
    static String input;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
        List<Integer> answer = new ArrayList<>();
        for (cursor = 0; cursor < input.length(); cursor++) {
            int temp;
            if (input.charAt(cursor) == '(') {
                temp = a(0, stackA, 1);
                if (temp == 0) {
                    System.out.println(0);
                    return;
                }
                answer.add(temp);
            } else if (input.charAt(cursor) == '[') {
                temp = a(0, stackB, 2);
                if (temp == 0) {
                    System.out.println(0);
                    return;
                }
                answer.add(temp);
            } else {
                System.out.println(0);
                return;
            }
        }
        if (stackA != 0 || stackB != 0) {
            System.out.println(0);
            return;
        }
        int ans = 0;
        for (Integer i : answer) {
            ans += i;
        }
        System.out.println(ans);
    }

    /**
     * 0 0 2
     *
     */
    static int a(int value, int before, int type) {
        int initValue = value;
        int local;
        if (type == 1) {
            stackA++;
            stack[++top] = '(';
            local = stackA;
        } else {
            stackB++;
            stack[++top] = '[';
            local = stackB;
        }
        while (local != before && cursor + 1 < input.length()) {
            cursor++;
            if (input.charAt(cursor) == '(') {
                int temp = a(initValue, stackA, 1);
                if (temp == 0) {
                    return 0;
                }
                value += temp;
            }
            else if (input.charAt(cursor) == '[') {
                int temp = a(initValue, stackB, 2);
                if (temp == 0) {
                    return 0;
                }
                value += temp;
            }
            else if (input.charAt(cursor) == ')') {
                stackA--;
                if (stackA < 0 || stack[top] != '(') {
                    return 0;
                }
                top--;
                if (value == 0) {
                    value++;
                }
                return 2 * value;
            } else if (input.charAt(cursor) == ']') {
                stackB--;
                if (stackB < 0 || stack[top] != '[') {
                    return 0;
                }
                top--;
                if (value == 0) {
                    value++;
                }
                return 3 * value;
            }
        }
        return value;
    }
}