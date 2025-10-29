import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    static int n, m;
    static char[][] map;
    static int[][] count;
    static int[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        map = new char[n][m];
        count = new int[n][m];
        visited = new int[n][m];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        int number = 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == '0' && visited[i][j] == 0) {
                    bfs(i, j, number);
                    number++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == '0') {
                    sb.append("0");
                }
                else {
                    int result = 0;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (nx < 0 || n <= nx || ny < 0 || m <= ny) {
                            continue;
                        }
                        if (set.contains(visited[nx][ny])) {
                            continue;
                        }
                        result += count[nx][ny];
                        set.add(visited[nx][ny]);
                    }
                    sb.append((result+1) % 10);
                    set.clear();
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void bfs(int x, int y, int number) {
        Deque<Node> q = new ArrayDeque<>();
        q.add(new Node(x, y));
        List<Node> visit = new ArrayList<>();
        while (!q.isEmpty()) {
            Node poll = q.poll();
            if (visited[poll.x][poll.y] != 0) {
                continue;
            }
            visit.add(poll);
            visited[poll.x][poll.y] = number;
            for (int i = 0; i < 4; i++) {
                int nx = poll.x + dx[i];
                int ny = poll.y + dy[i];
                if (nx < 0 || n <= nx || ny < 0 || m <= ny) {
                    continue;
                }
                if (map[nx][ny] == '1') {
                    continue;
                }
                if (visited[nx][ny] != 0) {
                    continue;
                }
                q.add(new Node(nx, ny));
            }
        }

        int result = visit.size();
        for (Node node : visit) {
            count[node.x][node.y] = result;
        }
    }

    static class Node {
        int x,y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
