import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.*;

public class Main {
    static int d, t;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int x = input[0];
        int y = input[1];
        d = input[2];
        t = input[3];

        double answer = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        double dist = answer;
        if (dist < d) {
            answer = Math.min(answer, t + d- dist);
            answer = Math.min(answer, 2 * t);
        }
        else {
            double jumpCount = Math.floor(dist / d);
            answer = Math.min(answer, jumpCount * t + (dist - jumpCount * d));
            answer = Math.min(answer, (jumpCount + 1) * t);
        }
        System.out.printf("%.10f\n", answer);
    }
}
