import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class Main {
    static List<Integer> order;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);

        ArrayList<Integer>[] problems = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            problems[i] = new ArrayList<>();
        }

        int[] count = new int[n+1];
        for (int i = 0; i < m; i++) {
            s = br.readLine().split(" ");
            int first = Integer.parseInt(s[0]);
            int second = Integer.parseInt(s[1]);
            problems[first].add(second);
            count[second]++;
        }

        Queue<Integer> q = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            if (count[i] == 0)
                q.add(i);
        }
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            Integer poll = q.poll();
            sb.append(poll + " ");
            for (int i = 0; i < problems[poll].size(); i++) {
                Integer p = problems[poll].get(i);
                count[p]--;
                if (count[p] == 0)
                    q.add(p);
            }
        }
        System.out.println(sb.toString());
    }
}