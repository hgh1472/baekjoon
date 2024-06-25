import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<String> input = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            input.add(br.readLine());
        }
        System.out.println(solution(n, input));
    }

    static int solution(int n, List<String> words) {
        int groupCount = 0;
        for (String word : words) {
            groupCount++;
            boolean[] isChecked = new boolean[26];
            int i = 0;
            while (i < word.length()) {
                char tmp = word.charAt(i);
                int pos = (int)(tmp - 'a');
                if (isChecked[pos]) {
                    groupCount--;
                    break;
                }
                isChecked[pos] = true;
                while (i < word.length() && word.charAt(i) == tmp)
                    i++;
            }
        }
        return groupCount;
    }
}