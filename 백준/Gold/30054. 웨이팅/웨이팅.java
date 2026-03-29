import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int answer = Integer.MIN_VALUE;

        PriorityQueue<Node> waitings = new PriorityQueue<>();
        Map<Integer, Node> notEntered = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            int t1 = Integer.parseInt(input[0]);
            int t2 = Integer.parseInt(input[1]);
            Node e = new Node(t1, t2);
            waitings.add(e);
            notEntered.put(t1, e);
        }

        int time = 0;
        while (true) {
            // 모두 입장
            if (notEntered.isEmpty()) {
                break;
            }
            time++;
            // 해당 시간에 예약한 사람이 아직 입장하지 않음
            if (notEntered.containsKey(time)) {
                Node node = notEntered.get(time);
                // 기다리고 있는 중
                if (node.t2 <= time) {
                    answer = Math.max(answer, time - node.t2);
                    notEntered.remove(time);
                    continue;
                }
            }

            // 웨이팅 리스트에서 입장
            while (!waitings.isEmpty() && waitings.peek().t2 <= time) {
                Node poll = waitings.poll();
                // 웨이팅에 있는 사람이 이미 들어감
                if (!notEntered.containsKey(poll.t1)) {
                    continue;
                }
                answer = Math.max(answer, time - poll.t2);
                notEntered.remove(poll.t1);
                break;
            }
        }

        System.out.println(answer);
    }

    static class Node implements Comparable<Node> {
        int t1, t2;

        public Node(int t1, int t2) {
            this.t1 = t1;
            this.t2 = t2;
        }

        // 도착순
        @Override
        public int compareTo(Node o) {
            if (this.t2 == o.t2) {
                return this.t1 - o.t1;
            }
            return this.t2 - o.t2;
        }
    }

    static class Wait implements Comparable<Wait> {
        int t1, t2;

        public Wait(int t1, int t2) {
            this.t1 = t1;
            this.t2 = t2;
        }

        @Override
        public int compareTo(Wait o) {
            return this.t1 - o.t1;
        }
    }
}
