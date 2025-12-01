import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = Arrays.stream(br.readLine().split(" "))
                          .mapToInt(Integer::parseInt)
                          .toArray();

        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            map.put(i, 0);
        }

        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            int tmp = 2;
            int target = num * tmp;
            while (target <= 1000000) {
                if (map.containsKey(target)) {
                    map.put(num, map.get(num) + 1);
                    map.put(target, map.get(target) - 1);
                }
                tmp++;
                target = num * tmp;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i : arr) {
            sb.append(map.get(i)).append(" ");
        }
        System.out.println(sb);
    }
}
