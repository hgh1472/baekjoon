import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        long x1 = Long.parseLong(input[0]);
        long y1 = Long.parseLong(input[1]);
        long x2 = Long.parseLong(input[2]);
        long y2 = Long.parseLong(input[3]);

        input = br.readLine().split(" ");
        long x3 = Long.parseLong(input[0]);
        long y3 = Long.parseLong(input[1]);
        long x4 = Long.parseLong(input[2]);
        long y4 = Long.parseLong(input[3]);

        Point a = new Point(x1, y1);
        Point b = new Point(x2, y2);
        Point c = new Point(x3, y3);
        Point d = new Point(x4, y4);

        int ab_cd = ccw(a, b, c) * ccw(a, b, d);
        int cd_ab = ccw(c, d, a) * ccw(c, d, b);
        if (ab_cd == 0 && cd_ab == 0) {
            if (a.compareTo(b) > 0) {
                Point tmp = a;
                a = b;
                b = tmp;
            }
            if (c.compareTo(d) > 0) {
                Point tmp = c;
                c = d;
                d = tmp;
            }

            if (a.compareTo(d) <= 0 && c.compareTo(b) <= 0) {
                System.out.println(1);
            }
            else {
                System.out.println(0);
            }
        } else if (ab_cd <= 0 && cd_ab <= 0) {
            System.out.println(1);
        }
        else {
            System.out.println(0);
        }
    }

    public static int ccw(Point a, Point b, Point c) {
        long result = 0;
        result += a.x * b.y + b.x * c.y + c.x * a.y;
        result -= (b.x*a.y +c.x*b.y + a.x * c.y);
        if (result > 0) {
            return 1;
        } else if (result == 0) {
            return 0;
        }
        return -1;
    }

    static class Point implements Comparable<Point> {
        long x, y;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point point) {
            if (this.x == point.x) {
                return (int) (this.y - point.y);
            }
            return (int) (this.x - point.x);
        }
    }
}
