import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Main {
    static int[] dice = new int[6];
    static Map<Integer, List<Integer>> dimensions = new HashMap<>();
    static int[] points = new int[8];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < 6; i++) {
            dice[i] = inputs[i];
        }
        if (n == 1) {
            Arrays.sort(dice);
            System.out.println(dice[0] + dice[1] + dice[2] + dice[3] + dice[4]);
            return;
        }
        init();
        int minIdx = getMinIdx();
        long dimension2 = getMinDimension2(minIdx);
        long dimension3 = getMinDimension3();
//        System.out.println("dimension3 = " + dimension3);
//        System.out.println("dimension2 = " + dimension2);
//        System.out.println("minIdx = " + minIdx);
        long answer = 4 * dimension3;
        answer += dimension2 * ((n - 2) * 4L + (n - 1) * 4L);
        answer += dice[minIdx] * ((long) (n - 2) * (n - 2) + (long) (n - 2) * (n - 1) * 4);
        System.out.println(answer);
    }

    private static void init() {
        for (int i = 0; i < 6; i++) {
            if (i == 0 || i == 5) {
                List<Integer> dis = new ArrayList<>();
                dis.add(1);
                dis.add(2);
                dis.add(3);
                dis.add(4);
                dimensions.put(i, dis);
            } else if (i == 1 || i == 4) {
                List<Integer> dis = new ArrayList<>();
                dis.add(0);
                dis.add(2);
                dis.add(3);
                dis.add(5);
                dimensions.put(i, dis);
            } else {
                List<Integer> dis = new ArrayList<>();
                dis.add(0);
                dis.add(1);
                dis.add(4);
                dis.add(5);
                dimensions.put(i, dis);
            }
        }
        points[0] = dice[0] + dice[1] + dice[2];
        points[1] = dice[0] + dice[2] + dice[4];
        points[2] = dice[0] + dice[3] + dice[4];
        points[3] = dice[0] + dice[1] + dice[3];

        points[4] = dice[5] + dice[2] + dice[1];
        points[5] = dice[5] + dice[2]  +dice[4];
        points[6] = dice[5] + dice[3] + dice[4];
        points[7] = dice[5] + dice[1] + dice[3];
    }

    static int getMinIdx() {
        int min = 51;
        int idx = 0;
        for (int i = 0; i < 6; i++) {
            if (min > dice[i]) {
                min = dice[i];
                idx = i;
            }
        }
        return idx;
    }

    static int getMinDimension2(int minIdx) {
        List<Integer> dis = dimensions.get(minIdx);
        int min = 50;
        for (Integer di : dis) {
            min = Math.min(min, dice[di]);
        }
        return dice[minIdx] + min;
    }

    static int getMinDimension3() {
        return Arrays.stream(points).min().getAsInt();
    }
}
