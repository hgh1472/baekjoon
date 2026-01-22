import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        int s = Integer.parseInt(input);
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(1, 0, 0));
        boolean[][] visited = new boolean[2000][2000];

        while (!q.isEmpty()) {
            Node poll = q.poll();
            if (visited[poll.now][poll.clipboard]) {
                continue;
            }
            visited[poll.now][poll.clipboard] = true;

            if (poll.now < s) {
                Node copy = poll.copyAndPaste();
                if (copy.now == s) {
                    System.out.println(copy.time);
                    return;
                }
                q.add(copy);
            }

            if (poll.clipboard > 0 && poll.now < s) {
                Node paste = poll.paste();
                if (paste.now == s) {
                    System.out.println(paste.time);
                    return;
                }
                q.add(paste);
            }

            if (poll.now <= 2) {
                continue;
            }
            Node delete = poll.delete();
            if (delete.now == s) {
                System.out.println(delete.time);
                return;
            }
            q.add(delete);
        }
        System.out.println(-1);
    }

    static class Node implements Comparable<Node> {
        int now;
        int clipboard;
        int time;

        public Node(int now, int clipboard, int time) {
            this.now = now;
            this.clipboard = clipboard;
            this.time = time;
        }

        public Node copyAndPaste() {
            return new Node(this.now * 2, this.now, this.time + 2);
        }

        public Node paste() {
            return new Node(this.now + this.clipboard, this.clipboard, time + 1);
        }

        public Node delete() {
            return new Node(this.now - 1, this.clipboard, time + 1);
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }
}
