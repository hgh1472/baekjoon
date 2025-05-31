import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m;
    static List<Star> stars = new ArrayList<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        n = input[0];
        m = input[1];
        int l = input[2];
        int k = input[3];

        for (int i = 0; i < k; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            stars.add(new Star(input[0], input[1]));
        }

        int answer = k;
        for (Star star1 : stars) {
            for (Star star2 : stars) {
                int nx = star1.x;
                int ny = star2.y;

                answer = Math.min(answer, k - count(nx, ny, l));
            }
        }

        System.out.println(answer);

    }

    static int count(int x, int y, int l) {
        int count = 0;
        for (Star star : stars) {
            if (star.x < x || x + l < star.x || star.y < y || y + l < star.y) {
                continue;
            }
            count++;
        }
        return count;
    }

    static class Star {
        int x, y;

        public Star(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
