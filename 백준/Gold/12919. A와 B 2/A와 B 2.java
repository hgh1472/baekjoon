import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int countA;
    static int countB;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String org = br.readLine();
        String dst = br.readLine();

        countA = 0;
        countB = 0;
        for (int i = 0; i < dst.length(); i++) {
            if (dst.charAt(i) == 'A') {
                countA++;
            }
            else if (dst.charAt(i) == 'B') {
                countB++;
            }
            else {
                System.out.println(0);
                return;
            }
        }

        int a = 0, b = 0;
        for (int i = 0; i < org.length(); i++) {
            if (org.charAt(i) == 'A') {
                a++;
            }
            else if (org.charAt(i) == 'B') {
                b++;
            }
            else {
                System.out.println(0);
                return;
            }
        }

        boolean result = calculate(org, dst, a, b);
        if (result) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }

    }

    static boolean calculate(String s, String dst, int ca, int cb) {
        if (s.length() == dst.length()) {
            return s.equals(dst);
        }
        boolean possible = false;
        if (ca < countA) {
            StringBuffer sb = new StringBuffer(s).append("A");
            String tmp1 = sb.toString();
            String tmp2 = sb.reverse().toString();
            if (dst.contains(tmp1) || dst.contains(tmp2)) {
                possible = calculate(tmp1, dst, ca + 1, cb);
            }
        }
        if (possible) {
            return true;
        }
        if (cb < countB) {
            StringBuffer sb = new StringBuffer(s).append("B");
            String tmp1 = sb.toString();
            String tmp2 = sb.reverse().toString();
            if (dst.contains(tmp1) || dst.contains(tmp2)) {
                possible = calculate(tmp2, dst, ca, cb + 1);
            }
        }
        return possible;
    }

}
