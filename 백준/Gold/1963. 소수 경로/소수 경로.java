import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.*;

public class Main {
    static boolean[] visited = new boolean[10000];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 2; i < 10000; i++) {
            int idx = 2;
            while (i * idx < 10000) {
                visited[i * idx] = true;
                idx++;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 1000; i < 10000; i++) {
            if (!visited[i]) {
                list.add(i);
            }
        }

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Map<Integer, Integer> visited = new HashMap<>();
            PriorityQueue<Node> q = new PriorityQueue<>();
            q.add(new Node(input[0], 0));
            boolean find = false;
            int answer = 0;
            while (!q.isEmpty()) {
                Node poll = q.poll();
                if (visited.containsKey(poll.key) && visited.get(poll.key) <= poll.count) {
                    continue;
                }
                if (poll.key == input[1]) {
                    find = true;
                    answer = poll.count;
                    break;
                }
                visited.put(poll.key, poll.count);
                for (Integer integer : list) {
                    if (isDifferentOnlyOne(poll.key, integer)) {
                        q.add(new Node(integer, poll.count + 1));
                    }
                }
            }
            if (find) {
                System.out.println(answer);
            }
            else {
                System.out.println("impossible");
            }
        }
    }

    static boolean isDifferentOnlyOne(int a, int b) {
        String str1 = String.valueOf(a);
        String str2 = String.valueOf(b);
        int diff = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                diff++;
            }
        }
        return diff == 1;
    }

    static class Node implements Comparable<Node> {
        int key;
        int count;

        public Node(int key, int count) {
            this.key = key;
            this.count = count;
        }

        @Override
        public int compareTo(Node o) {
            return this.count - o.count;
        }
    }
}
