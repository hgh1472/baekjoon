import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static String input;
    static int answer = -2147483647;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        input = br.readLine();

        for (int i = 0; i <= (n / 2 + 1) / 2; i++) {
            combination(0, i, new boolean[n], 0);
        }
        System.out.println(answer);
    }

    static void combination(int d, int f, boolean[] visited, int last) {
        if (d == f) {
            int result = calculateAll(visited);
            answer = Math.max(answer, result);
            return;
        }
        for (int i = last; i < n - 2; i += 2) {
            visited[i] = true;
            combination(d + 1, f, visited, i + 4);
            visited[i] = false;
        }
    }

    static int calculateAll(boolean[] visited) {
        List<Integer> nums = new ArrayList<>();
        List<String> operators = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (isNumber(input.charAt(i))) {
                if (visited[i]) {
                    int num = calculate(input.charAt(i), input.charAt(i + 1), input.charAt(i + 2));
                    nums.add(num);
                    i += 2;
                }
                else {
                    nums.add((int) (input.charAt(i) - '0'));
                }
            }
            else {
                operators.add(String.valueOf(input.charAt(i)));
            }
        }

        int result = nums.get(0);
        for (int i = 1; i < nums.size(); i++) {
            if (operators.get(i - 1).equals("+")) {
                result += nums.get(i);
            } else if (operators.get(i - 1).equals("-")) {
                result -= nums.get(i);
            }
            else {
                result *= nums.get(i);
            }
        }
        return result;
    }

    static boolean isNumber(char i) {
        return '0' <= i && i <= '9';
    }

    static int calculate(char n1, char o, char n2) {
        int a = (int) (n1 - '0');
        int b = (int) (n2 - '0');
        if (o == '+') {
            return a + b;
        }
        else if (o == '-') {
            return a - b;
        }
        else {
            return a * b;
        }
    }
}