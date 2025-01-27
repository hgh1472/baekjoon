import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int k;
    static int answer = 0;
    static String[] words;
    static Map<String, Boolean> visited = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = input[0];
        k = input[1];

        words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
        }

        for (int i = 0; i < 26; i++) {
            visited.put(String.valueOf((char) ('a' + i)), false);
        }

        if (k < 5) {
            System.out.println(0);
            return;
        }
        visited.put("a", true);
        visited.put("n", true);
        visited.put("t", true);
        visited.put("i", true);
        visited.put("c", true);

        combination(0, k - 5, 0);
        System.out.println(answer);
    }

    static void combination(int d, int f, int last) {
        if (d == f) {
            int result = countPossible();
            answer = Math.max(answer, result);
        }

        for (int i = last; i < 26; i++) {
            String target = String.valueOf((char) ('a' + i));
            if (visited.get(target)) {
                continue;
            }
            visited.put(target, true);
            combination(d + 1, f, i + 1);
            visited.put(target, false);
        }
    }

    static int countPossible() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            boolean isPossible = true;
            for (int j = 0; j < words[i].length(); j++) {
                if (!visited.get(String.valueOf(words[i].charAt(j)))) {
                    isPossible = false;
                    break;
                }
            }
            if (isPossible) {
                count++;
            }
        }
        return count;
    }
}
