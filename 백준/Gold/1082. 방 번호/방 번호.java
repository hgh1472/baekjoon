import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        m = Integer.parseInt(br.readLine());
        Number[] numbers = new Number[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = new Number(i, input[i]);
        }
        Number[] sorted = new Number[n];
        for (int i = 0; i < n; i++) {
            sorted[i] = numbers[i];
        }
        Arrays.sort(sorted);

        if (n == 1) {
            System.out.println(0);
            return;
        }
        int cost = 0;
        List<Number> purchase = new ArrayList<>();
        if (sorted[0].number == 0) {
            if (cost + sorted[1].cost <= m) {
                purchase.add(sorted[1]);
                cost += sorted[1].cost;
            }
            else {
                System.out.println(0);
                return;
            }
        }
        while (cost + sorted[0].cost <= m) {
            purchase.add(sorted[0]);
            cost += sorted[0].cost;
        }

        for (int idx = 0; idx < purchase.size(); idx++) {
            Number target = purchase.get(idx);
            for (int i = n - 1; i > target.number; i--) {
                int costDiff = numbers[i].cost - target.cost;
                if (cost + costDiff <= m) {
                    purchase.set(idx, numbers[i]);
                    cost += costDiff;
                    break;
                }
            }
        }

        purchase.sort((n1, n2) -> n2.number - n1.number);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < purchase.size(); i++) {
            sb.append(purchase.get(i).number);
        }
        System.out.println(sb);
    }

    static class Number implements Comparable<Number> {
        int number;
        int cost;

        public Number(int number, int cost) {
            this.number = number;
            this.cost = cost;
        }

        @Override
        public int compareTo(Number o) {
            if (this.cost == o.cost) {
                return o.number - this.number;
            }
            return this.cost - o.cost;
        }
    }
}
