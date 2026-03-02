import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int d = input[0];
            int n = input[1];
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int sum = 0;
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, 1);
            int answer = 0;
            for (int i = 0; i < n; i++) {
                sum += input[i];
                sum %= d;
                answer += map.getOrDefault(sum, 0);
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }
}
