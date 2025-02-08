import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            String input = br.readLine();
            int result = find(input, 0, input.length() - 1, false);
            sb.append(result).append("\n");
        }
        System.out.print(sb);
    }

    static int find(String s, int left, int right, boolean pseudo) {
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
                continue;
            }
            if (pseudo) {
                return 2;
            }
            if (find(s, left + 1, right, true) == 0) {
                return 1;
            }
            if (find(s, left, right - 1, true) == 0) {
                return 1;
            }
            return 2;
        }
        return 0;
    }
}
