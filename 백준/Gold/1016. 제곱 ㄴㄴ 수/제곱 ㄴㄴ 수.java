import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long[] input = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        System.out.println(solve(input[0], input[1]));
    }

    static long solve(long min, long max) {
        Set<Long> numbers = new HashSet<>();
        long sqrt = (long) Math.sqrt(max);

        for (long i = 2; i <= sqrt; i++) {
            long pow = (long) Math.pow(i, 2);
            long count = Math.max(1, min / pow);
            while (pow * count <= max) {
                numbers.add(pow * count);
                count++;
            }
        }

        long answer = 0;
        for (long i = min; i <= max; i++) {
            if (numbers.contains(i)) {
                continue;
            }
            answer++;
        }

        return answer;
    }
}
