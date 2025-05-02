import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            dfs(new char[n-1], n-1, 0);
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(char[] operators, int n, int size) {
        if (size == n) {
            calculate(operators, size);
            return;
        }
        operators[size] = ' ';
        dfs(operators, n, size + 1);
        operators[size] = '+';
        dfs(operators, n, size + 1);
        operators[size] = '-';
        dfs(operators, n, size + 1);
    }

    private static void calculate(char[] operators, int size) {
        int result = 0;
        int local = 1;

        char op = '+';
        for (int i = 0; i < size; i++) {
            if (operators[i] == ' ') {
                local = local * 10 + i+2;
                continue;
            }
            else {
                if (op == '+') {
                    result += local;
                }
                if (op == '-') {
                    result -= local;
                }
                op = operators[i];
                local = i+2;
            }
        }

        if (op == '+') {
            result += local;
        }
        if (op == '-') {
            result -= local;
        }
        if (result == 0) {
            for (int i = 0; i < size; i++) {
                sb.append(i+1).append(operators[i]);
            }
            sb.append(size+1).append("\n");
        }
    }

}
