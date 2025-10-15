import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class Main {
    static int maxRow = 3;
    static int maxCol = 3;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int r = Integer.parseInt(input[0]) - 1;
        int c = Integer.parseInt(input[1]) - 1;
        int k = Integer.parseInt(input[2]);
        int[][] arr = new int[100][100];
        for (int i = 0; i < 3; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(input[j]);
            }
        }

        int count = 0;
        if (arr[r][c] == k) {
            System.out.println(count);
            return;
        }
        while (count < 100) {
            if (maxRow >= maxCol) {
                row(arr);
            }
            else {
                col(arr);
            }
            count++;

            if (arr[r][c] == k) {
                System.out.println(count);
                return;
            }
        }
        System.out.println(-1);
    }

    static void row(int[][] arr) {
        int tmpCol = 0;
        for (int i = 0; i < maxRow; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            int j = 0;
            while (j < maxCol) {
                if (arr[i][j] == 0) {
                    j++;
                    continue;
                }
                map.put(arr[i][j], map.getOrDefault(arr[i][j], 0) + 1);
                j++;
            }

            PriorityQueue<Node> q = new PriorityQueue<>();
            for (Entry<Integer, Integer> entry : map.entrySet()) {
                q.add(new Node(entry.getKey(), entry.getValue()));
            }

            int idx = 0;
            while (idx < 100 && !q.isEmpty()) {
                Node poll = q.poll();
                arr[i][idx] = poll.number;
                arr[i][idx+1] = poll.count;
                idx += 2;
            }
            tmpCol = Math.max(tmpCol, idx);

            while (idx < 100) {
                arr[i][idx++] = 0;
            }
        }
        maxCol = tmpCol;
    }

    static void col(int[][] arr) {
        int tmpRow = 0;
        for (int i = 0; i < maxCol; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            int j = 0;
            while (j < maxRow) {
                if (arr[j][i] == 0) {
                    j++;
                    continue;
                }
                map.put(arr[j][i], map.getOrDefault(arr[j][i], 0) + 1);
                j++;
            }

            PriorityQueue<Node> q = new PriorityQueue<>();
            for (Entry<Integer, Integer> entry : map.entrySet()) {
                q.add(new Node(entry.getKey(), entry.getValue()));
            }

            int idx = 0;
            while (idx < 100 && !q.isEmpty()) {
                Node poll = q.poll();
                arr[idx][i] = poll.number;
                arr[idx+1][i] = poll.count;
                idx += 2;
            }
            tmpRow = Math.max(tmpRow, idx);

            while (idx < 100) {
                arr[idx++][i] = 0;
            }
        }
        maxRow = tmpRow;
    }

    static class Node implements Comparable<Node> {
        int number;
        int count;

        Node(int number, int count) {
            this.number = number;
            this.count = count;
        }

        @Override
        public int compareTo(Node node) {
            if (this.count == node.count) {
                return this.number - node.number;
            }
            return this.count - node.count;
        }
    }
}
