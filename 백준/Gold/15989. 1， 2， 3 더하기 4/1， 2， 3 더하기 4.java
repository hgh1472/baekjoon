import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(solution(n));
        }
    }

    static int solution(int n) {
        int answer = 1;
        answer += n / 2 + n / 3;
        for (int i = 1; i <= n / 3; i++) {
            answer += (n - 3 * i) / 2;
        }
        return answer;
    }
}
