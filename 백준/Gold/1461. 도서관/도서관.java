import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Main {
    static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = input[0];
        m = input[1];
        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<Integer> minus = new ArrayList<>();
        List<Integer> plus = new ArrayList<>();
        int maxMinus = 0;
        int maxPlus = 0;
        for (int i : input) {
            if (i < 0) {
                minus.add(-i);
                maxMinus = Math.max(-i, maxMinus);
            }
            else {
                plus.add(i);
                maxPlus = Math.max(i, maxPlus);
            }
        }

        Collections.sort(minus);
        Collections.sort(plus);

        int distance = 0;
        if (maxMinus > maxPlus) {
            distance += getDistance(plus, false);
            distance += getDistance(minus, true);
        }
        else {
            distance += getDistance(plus, true);
            distance += getDistance(minus, false);
        }
        System.out.println(distance);
    }

    static int getDistance(List<Integer> list, boolean isSecond) {
        int distance = 0;
        int start = 0;
        int r = list.size() % m;
        if (r != 0) {
            distance += list.get(r - 1) * 2;
            start += r;
        }
        for (int i = start + m - 1; i < list.size(); i += m) {
            distance += list.get(i) * 2;
        }
        if (isSecond) {
            distance -= list.get(list.size() - 1);
        }
        return distance;
    }
}
