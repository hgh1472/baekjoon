import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

class Main {
    static int[] root;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        root = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            root[i] = i;
        }

        PriorityQueue<Problem> q = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            q.add(new Problem(input[0], input[1]));
        }

        int answer = 0;
        while (!q.isEmpty()) {
            Problem p = q.poll();
            int pos = find(p.deadline);
            if (pos == 0) {
                continue;
            }
            answer += p.reward;
            root[pos] = root[pos - 1];
        }
        System.out.println(answer);
    }

    static int find(int x) {
        if (root[x] != x) {
            root[x] = find(root[x]);
        }
        return root[x];
    }

    static class Problem implements Comparable<Problem> {
        int deadline;
        int reward;

        public Problem(int deadline, int reward) {
            this.deadline = deadline;
            this.reward = reward;
        }

        @Override
        public int compareTo(Problem problem) {
            return problem.reward - this.reward;
        }
    }
}
