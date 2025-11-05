import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] sort = new int[n];
        Map<Integer, List<Integer>> before = new HashMap<>();
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            if (!before.containsKey(arr[i])) {
                before.put(arr[i], new ArrayList<>());
            }
            before.get(arr[i]).add(i);
            sort[i] = arr[i];
        }

        Arrays.sort(sort);
        Map<Integer, List<Integer>> after = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (!after.containsKey(sort[i])) {
                after.put(sort[i], new ArrayList<>());
            }
            after.get(sort[i]).add(i);
        }

        int move = 0;
        for (int value : after.keySet()) {
            List<Integer> afterIndexes = after.get(value);
            List<Integer> beforeIndexes = before.get(value);

            for (int i = 0; i < afterIndexes.size(); i++) {
                int afterIdx = afterIndexes.get(i);
                int beforeIdx = beforeIndexes.get(i);

                move = Math.max(beforeIdx - afterIdx, move);
            }
        }

        System.out.println(move+1);
    }
}
