import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 최대로 덮을 수 있는 트램펄린의 여러 케이스 중에서 왼쪽 꼭짓점(x,y)가 최대로 될 수 있는 하나의 케이스만 생각
 * => 왼쪽면, 위쪽면은 각각 최소 1개의 별이 존재 (같을 수도 있음)
 * 2개의 별, 별1, 별2를 뽑음. 그 다음 별1이 왼쪽면, 별2가 위쪽면에 접해있다고 가정하고 계산. 이 과정에서 최대로 덮을 수 있는 케이스가 나온다.
 */
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
