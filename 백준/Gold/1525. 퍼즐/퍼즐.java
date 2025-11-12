import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dx = {-1, 1, -3, 3};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] numbers = new int[3][3];
        for (int i = 0; i < 3; i++) {
            numbers[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        String start = "";
        int zeroIdx = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                start = start.concat(String.valueOf(numbers[i][j]));
                if (numbers[i][j] == 0) {
                    zeroIdx = i * 3 + j;
                }
            }
        }
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(start, zeroIdx, 0));

        Set<String> visited = new HashSet<>();
        while (!q.isEmpty()) {
            Node poll = q.poll();
            if (visited.contains(poll.str)) {
                continue;
            }
            if (poll.str.equals("123456780")) {
                System.out.println(poll.count);
                return;
            }
            visited.add(poll.str);
            for (int i = 0; i < 4; i++) {
                int idx = poll.zeroIdx + dx[i];
                if (idx < 0 || 9 <= idx) {
                    continue;
                }
                if (Math.abs(idx - poll.zeroIdx) == 1 && idx / 3 != poll.zeroIdx / 3) {
                    continue;
                }
                char c = poll.str.charAt(idx);
                StringBuilder sb = new StringBuilder(poll.str);
                sb.replace(idx, idx + 1, "0");
                sb.replace(poll.zeroIdx, poll.zeroIdx+1, String.valueOf(c));
                String next = sb.toString();
                if (visited.contains(next)) {
                    continue;
                }
                q.add(new Node(next, idx, poll.count + 1));
            }
        }
        System.out.println(-1);
    }

    static class Node {
        String str;
        int zeroIdx;
        int count;

        public Node(String str, int zeroIdx, int count) {
            this.str = str;
            this.zeroIdx = zeroIdx;
            this.count = count;
        }
    }
}
