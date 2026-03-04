import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, answer = -1;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        int half = n / 2;
        String numberStr = String.valueOf(half);
        for (int i = 1; i <= numberStr.length(); i++) {
            getNumber(i, 0, 0, new boolean[10]);
            if (answer != -1) {
                int left = Math.max(answer, n - answer);
                System.out.println(left + " + " + (n - left));
                return;
            }
        }
        System.out.println(-1);
    }

    static void getNumber(int length, int nowLength, int number, boolean[] visited) {
        if (length == nowLength) {
            compare(number);
            return;
        }
        for (int i = 0; i < 10; i++) {
            if (visited[i]) {
                continue;
            }
            if (nowLength == 0 && i == 0) {
                continue;
            }
            int next = number * 10;
            next += i;
            visited[i] = true;
            getNumber(length, nowLength + 1, next, visited);
            visited[i] = false;
        }
    }

    static void compare(int number) {
        int org = number;
        if (n <= number) {
            return;
        }
        int other = n - number;
        boolean[] visited = new boolean[10];
        while (number != 0) {
            if (visited[number % 10]) {
                return;
            }
            visited[number % 10] = true;
            number /= 10;
        }
        while (other != 0) {
            if (visited[other % 10]) {
                return;
            }
            visited[other % 10] = true;
            other /= 10;
        }
        answer = org;
    }
}
