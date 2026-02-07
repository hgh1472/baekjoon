import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int MOD = 1000000000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] num = new int[n];
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(num);

        int[] diff = new int[n-1];
        for (int i = 0; i < n-1; i++) {
            diff[i] = num[i+1] - num[i];
        }

        int gcd = diff[0];
        for (int i = 1; i < n - 1; i++) {
            gcd = gcd(Math.max(gcd, diff[i]), Math.min(gcd, diff[i]));
        }

        StringBuilder sb = new StringBuilder();
        List<Integer> answers = new ArrayList<>();
        int max = (int) Math.sqrt(gcd);
        for (int i = 2; i <= max; i++) {
            if (gcd % i == 0) {
                answers.add(i);
                if (gcd / i != i) {
                    answers.add(gcd / i);
                }
            }
        }
        answers.add(gcd);
        Collections.sort(answers);
        for (int i = 0; i < answers.size(); i++) {
            sb.append(answers.get(i)).append(" ");
        }
        System.out.println(sb);
    }

    static int gcd(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
