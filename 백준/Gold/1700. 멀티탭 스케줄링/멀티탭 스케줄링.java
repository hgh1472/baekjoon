import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int k;
    static boolean[] isConnected;
    static Map<Integer, PriorityQueue<Integer>> info = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream((br.readLine().split(" "))).mapToInt(Integer::parseInt).toArray();
        n = input[0];
        k = input[1];

        List<Integer> plugs = new ArrayList<>();
        isConnected = new boolean[k + 1];
        for (int i = 1; i <= k; i++) {
            info.put(i, new PriorityQueue<>());
        }

        input = Arrays.stream((br.readLine().split(" "))).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < k; i++) {
            info.get(input[i]).add(i);
        }

        int answer = 0;
        for (int e : input) {
            if (isConnected[e]) {
                info.get(e).poll();
                continue;
            }
            if (plugs.size() == n && !isConnected[e]) {
                int lastUse = 0;
                int target = 0;
                for (int plug : plugs) {
                    PriorityQueue<Integer> plugInfo = info.get(plug);
                    if (plugInfo.isEmpty()) {
                        target = plug;
                        break;
                    }
                    if (lastUse < plugInfo.peek()) {
                        lastUse = plugInfo.peek();
                        target = plug;
                    }
                }
                plugs.remove(Integer.valueOf(target));
                isConnected[target] = false;
                answer++;
            }
            plugs.add(e);
            info.get(e).poll();
            isConnected[e] = true;
        }
        System.out.println(answer);
    }
}
