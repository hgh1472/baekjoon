import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    static Node[] nodes;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        nodes = new Node[n+1];

        int[][] map = new int[n+1][n+1];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(map[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < k; i++) {
            input = br.readLine().split(" ");
            int first = Integer.parseInt(input[0]);
            int second = Integer.parseInt(input[1]);
            map[first][second] = 1;
        }

        floyd(map);

        int s = Integer.parseInt(br.readLine());
        for (int i = 0; i < s; i++) {
            input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);

            if (map[a][b] != Integer.MAX_VALUE) {
                System.out.println(-1);
                continue;
            }
            if (map[b][a] != Integer.MAX_VALUE) {
                System.out.println(1);
                continue;
            }
            System.out.println(0);
        }
    }

    static void floyd(int[][] map) {
        for (int i = 1; i < map.length; i++) {
            for (int j = 1; j < map.length; j++) {
                for (int k = 1; k < map.length; k++) {
                    if (map[j][i] == Integer.MAX_VALUE || map[i][k] == Integer.MAX_VALUE) {
                        continue;
                    }
                    map[j][k] = Math.min(map[j][k], map[j][i] + map[i][k]);
                }
            }
        }
    }

    static class Node {
        int number;
        List<Node> next = new ArrayList<>();

        Node(int number) {
            this.number = number;
        }
    }
}
