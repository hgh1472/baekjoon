import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.*;

public class Main {
    static int m, n, k;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = input[0];
        m = input[1];
        k = input[2];

        List<FireBall> fireBalls = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            fireBalls.add(new FireBall(input[0] - 1, input[1] - 1, input[2], input[3], input[4]));
        }

        Map<Point, List<FireBall>> map = new HashMap<>();
        for (int i = 0; i < k; i++) {
            for (FireBall fireBall : fireBalls) {
                fireBall.move();
                map.putIfAbsent(fireBall.point, new ArrayList<>());
                List<FireBall> list = map.get(fireBall.point);
                list.add(fireBall);
            }

            List<FireBall> next = new ArrayList<>();
            for (Point point : map.keySet()) {
                List<FireBall> fireBallList = map.get(point);
                if (fireBallList.size() > 1) {
                    int totalMass = 0;
                    int totalSpeed = 0;
                    int oddCount = 0;
                    int evenCount = 0;
                    for (FireBall fireBall : fireBallList) {
                        totalMass += fireBall.m;
                        totalSpeed += fireBall.s;
                        if (fireBall.d % 2 == 0) {
                            evenCount++;
                        }
                        else {
                            oddCount++;
                        }
                    }
                    if (totalMass < 5) {
                        continue;
                    }
                    if (oddCount == 0 || evenCount == 0) {
                        next.add(new FireBall(point.r, point.c, totalMass / 5, totalSpeed / fireBallList.size(), 0));
                        next.add(new FireBall(point.r, point.c, totalMass / 5, totalSpeed / fireBallList.size(), 2));
                        next.add(new FireBall(point.r, point.c, totalMass / 5, totalSpeed / fireBallList.size(), 4));
                        next.add(new FireBall(point.r, point.c, totalMass / 5, totalSpeed / fireBallList.size(), 6));
                    } else {
                        next.add(new FireBall(point.r, point.c, totalMass / 5, totalSpeed / fireBallList.size(), 1));
                        next.add(new FireBall(point.r, point.c, totalMass / 5, totalSpeed / fireBallList.size(), 3));
                        next.add(new FireBall(point.r, point.c, totalMass / 5, totalSpeed / fireBallList.size(), 5));
                        next.add(new FireBall(point.r, point.c, totalMass / 5, totalSpeed / fireBallList.size(), 7));
                    }
                }
                else {
                    next.add(fireBallList.get(0));
                }
            }
            fireBalls = next;
            map.clear();
        }

        int totalMass = 0;
        for (FireBall fireBall : fireBalls) {
            totalMass += fireBall.m;
        }
        System.out.println(totalMass);
    }

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object object) {
            if (object == null || getClass() != object.getClass()) return false;
            Point point = (Point) object;
            return r == point.r && c == point.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }

    static class FireBall {
        Point point;
        int m, s, d;

        public FireBall(int r, int c, int m, int s, int d) {
            this.point = new Point(r, c);
            this.m = m;
            this.s = s;
            this.d = d;
        }

        public void move() {
            int nextR = point.r;
            int nextC = point.c;
            nextR += dx[d] * s;
            nextC += dy[d] * s;
            if (nextR < 0) {
                int a = -nextR / n;
                if (-nextR % n != 0) {
                    a++;
                }
                nextR = nextR + n * a;
            }
            if (nextR >= n) {
                nextR = nextR % n;
            }

            if (nextC < 0) {
                int a = -nextC / n;
                if (-nextC % n != 0) {
                    a++;
                }
                nextC = nextC + n * a;
            }
            if (nextC >= n) {
                nextC = nextC % n;
            }
            this.point = new Point(nextR, nextC);
        }
    }
}
