import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] info;
    static int[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        info = new int[n][m];
        for (int i = 0; i < n; i++) {
            s = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                info[i][j] = Integer.parseInt(s[j]);
            }
        }

        int count = 0;
        visited = new int[n][m];
        while (hasCheese()) {
            visited = new int[n][m];
            count++;
        }
        System.out.println(count);
    }

    static boolean hasCheese() {
        Queue<Node> q = new ArrayDeque<>();
        Queue<Node> meltCheese = new ArrayDeque<>();
        q.add(new Node(0,0));
        visited[0][0] += 1;
        while (!q.isEmpty()) {
            Node poll = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = poll.x + dx[i];
                int ny = poll.y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                // 외부 공기
                if (info[nx][ny] == 0) {
                    if (visited[nx][ny] == 0) {
                        q.add(new Node(nx, ny));
                        visited[nx][ny] +=1;
                    }
                }
                // 외부에 맞닿은 치즈
                if (info[nx][ny] == 1) {
                    visited[nx][ny] += 1;
                    if (visited[nx][ny] == 2) {
                        meltCheese.add(new Node(nx, ny));
                    }
                }
            }
        }
        boolean isClear = meltCheese.isEmpty();
        while (!meltCheese.isEmpty()) {
            Node poll = meltCheese.poll();
            info[poll.x][poll.y] = 0;
        }
        return !isClear;
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
