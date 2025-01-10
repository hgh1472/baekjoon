import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Flower> flowers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int startDate = 100 * input[0] + input[1];
            int endDate = 100 * input[2] + input[3];
            flowers.add(new Flower(startDate, endDate));
        }

        Collections.sort(flowers);

        int count = 0;
        int latestDate = 301;
        int idx = 0;
        while (idx < n) {
            if (latestDate < flowers.get(idx).startDate || latestDate > 1130) {
                break;
            }
            if (flowers.get(idx).startDate <= latestDate && latestDate < flowers.get(idx).endDate) {
                int maxEndDate = flowers.get(idx).endDate;
                while (idx + 1 < n && latestDate >= flowers.get(idx + 1).startDate) {
                    idx++;
                    maxEndDate = Math.max(maxEndDate, flowers.get(idx).endDate);
                }
                count++;
                latestDate = maxEndDate;
            }
            idx++;
        }

        if (latestDate <= 1130) {
            System.out.println(0);
            return;
        }
        System.out.println(count);
    }

    static class Flower implements Comparable<Flower> {
        int startDate;
        int endDate;

        public Flower(int startDate, int endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
        }

        @Override
        public int compareTo(Flower o) {
            if (this.startDate == o.startDate) {
                return this.endDate - o.endDate;
            }
            return this.startDate - o.startDate;
        }
    }
}
