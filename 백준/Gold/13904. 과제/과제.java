import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

class Main {
    static PriorityQueue<Homework> homeworks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        homeworks = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            homeworks.add(new Homework(input[0], input[1]));
        }

        int[] answer = new int[1001];
        while (!homeworks.isEmpty()) {
            Homework homework = homeworks.poll();
            for (int i = homework.day; i > 0; i--) {
                if (answer[i] < homework.reward) {
                    answer[i] = homework.reward;
                    break;
                }
            }
        }
        System.out.println(Arrays.stream(answer).sum());
    }

    static class Homework implements Comparable<Homework> {
        int day;
        int reward;

        public Homework(int day, int reword) {
            this.day = day;
            this.reward = reword;
        }

        @Override
        public int compareTo(Homework o) {
            return o.reward - this.reward;
        }
    }
}
