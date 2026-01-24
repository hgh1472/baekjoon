import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int maxA, maxB, maxC;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        maxA = input[0];
        maxB = input[1];
        maxC = input[2];
        boolean[][][] dp = new boolean[input[0] + 1][input[1]+1][input[2]+1];
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 0, maxC));

        boolean[] answer = new boolean[201];
        while (!q.isEmpty()) {
            Node poll = q.poll();
            if (dp[poll.a][poll.b][poll.c]) {
                continue;
            }
            dp[poll.a][poll.b][poll.c] = true;
            if (poll.a == 0) {
                answer[poll.c] = true;
            }
            if (poll.a != 0) {
                if (poll.b != maxB) {
                    q.add(poll.pourAtoB());
                }
                if (poll.c != maxC) {
                    q.add(poll.pourAtoC());
                }
            }

            if (poll.b != 0) {
                if (poll.a != maxA) {
                    q.add(poll.pourBtoA());
                }
                if (poll.c != maxC) {
                    q.add(poll.pourBtoC());
                }
            }

            if (poll.c != 0) {
                if (poll.a != maxA) {
                    q.add(poll.pourCtoA());
                }
                if (poll.b != maxB) {
                    q.add(poll.pourCtoB());
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 201; i++) {
            if (answer[i]) {
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb);
    }

    static class Node {
        int a, b, c;

        public Node(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public Node pourAtoB() {
            int needB = maxB - b;
            int pour = Math.min(a, needB);
            return new Node(a - pour, b + pour, c);
        }

        public Node pourAtoC() {
            int needC = maxC - c;
            int pour = Math.min(a, needC);
            return new Node(a - pour, b, c + pour);
        }

        public Node pourBtoA() {
            int needA = maxA - a;
            int pour = Math.min(b, needA);
            return new Node(a + pour, b - pour, c);
        }

        public Node pourBtoC() {
            int needC = maxC - c;
            int pour = Math.min(b, needC);
            return new Node(a, b - pour, c + pour);
        }

        public Node pourCtoA() {
            int needA = maxA - a;
            int pour = Math.min(c, needA);
            return new Node(a + pour, b, c - pour);
        }

        public Node pourCtoB() {
            int needB = maxB - b;
            int pour = Math.min(c, needB);
            return new Node(a, b + pour, c - pour);
        }
    }
}
