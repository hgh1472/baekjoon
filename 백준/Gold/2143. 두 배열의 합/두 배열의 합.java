import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long t = Integer.parseInt(br.readLine());

        int n = Integer.parseInt(br.readLine());
        long[] a = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i];
            a[i] = sum;
        }

        int m = Integer.parseInt(br.readLine());
        long[] b = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        sum = 0;
        for (int i = 0; i < m; i++) {
            sum += b[i];
            b[i] = sum;
        }
        Map<Long, Integer> sumCountA = new HashMap<>();
        for (long i : a) {
            sumCountA.put(i, sumCountA.getOrDefault(i, 0) + 1);
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                sumCountA.put(a[j] - a[i], sumCountA.getOrDefault(a[j] - a[i], 0) + 1);
            }
        }

        Map<Long, Integer> sumCountB = new HashMap<>();
        for (long i : b) {
            sumCountB.put(i, sumCountB.getOrDefault(i, 0) + 1);
        }
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                sumCountB.put(b[j] - b[i], sumCountB.getOrDefault(b[j] - b[i], 0) + 1);
            }
        }

        long answer = 0;
        for (Long sumA : sumCountA.keySet()) {
            if (sumCountB.containsKey(t - sumA)) {
                answer += (long) sumCountA.get(sumA) * sumCountB.get(t - sumA);
            }
        }

        System.out.println(answer);

    }
}
