import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Map<Integer, Integer> ladder = new HashMap<>();
    static Map<Integer, Integer> snake = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = input[0];
        int m = input[1];
        for (int i = 0; i < n; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            ladder.put(input[0], input[1]);
        }

        for (int i = 0; i < m; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            snake.put(input[0], input[1]);
        }

        System.out.println(roll());
    }

    private static int roll() {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(1, 0));
        while (!q.isEmpty()) {
            Node node = q.poll();
            int rollDice = 0;
            for (int i = 1; i <= 6; i++) {
                int pos = node.position + i;
                if (pos == 100) {
                    return node.count + 1;
                }
                if (ladder.containsKey(pos)) {
                    q.add(new Node(ladder.get(pos), node.count + 1));
                }
                else if (snake.containsKey(pos)) {
                    q.add(new Node(snake.get(pos), node.count + 1));
                }
                else {
                    rollDice = i;
                }
            }
            if (rollDice != 0) {
                q.add(new Node(node.position + rollDice, node.count + 1));
            }
        }
        return -1;
    }

    static class Node implements Comparable<Node> {
        int position;
        int count;

        public Node(int position, int count) {
            this.position = position;
            this.count = count;
        }

        @Override
        public int compareTo(Node o) {
            return this.count - o.count;
        }
    }
}
