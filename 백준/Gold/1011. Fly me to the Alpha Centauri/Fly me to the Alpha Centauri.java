import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            String[] s = br.readLine().split(" ");
            int d = Integer.parseInt(s[1]) - Integer.parseInt(s[0]);
            System.out.println(getCount(d));
        }
    }

    public static int getCount(double num) {
        double ceiling = 1;
        for (int i = 1; i <= num; i++) {
            if (Math.pow(i, 2) >= num) {
                ceiling = i;
                break;
            }
        }
        if (num - Math.pow(ceiling - 1, 2) < Math.pow(ceiling, 2) - num) {
            return (int) (ceiling - 1) * 2;
        } else {
            return (int) (ceiling + ceiling - 1);
        }
    }
}