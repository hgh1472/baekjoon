import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Lecture> q = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            q.add(new Lecture(input[0], input[1]));
        }
        int[] result = new int[10001];
        while (!q.isEmpty()) {
            Lecture poll = q.poll();
            for (int i = poll.due; i > 0; i--) {
                if (result[i] < poll.pay) {
                    result[i] = poll.pay;
                    break;
                }
            }
        }
        System.out.println(Arrays.stream(result).sum());
    }

    static class Lecture implements Comparable<Lecture> {
        @Override
        public int compareTo(Lecture o) {
            return o.pay - this.pay;
        }

        int pay;
        int due;

        public Lecture(int pay, int due) {
            this.pay = pay;
            this.due = due;
        }
    }
}
