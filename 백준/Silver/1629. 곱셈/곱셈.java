import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        long a = Long.parseLong(input[0]);
        long b = Long.parseLong(input[1]);
        long c = Long.parseLong(input[2]);

		System.out.println(divide(a, b, c));
    }

	public static long divide(long a, long b, long c) {
		if (b == 1) {
			return a % c;
		}
		long result = divide(a, b / 2, c);
		long left = result;
		long right = result;
		if (b % 2 != 0) {
			left = (left * (a % c)) % c;
		}
		return (left * right) % c;
	}
}
