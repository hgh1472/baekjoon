import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, k, count;
    static int[][] info;
    static boolean[][] visited;
    static int[] islandNumber;
    static List<Island> islands = new ArrayList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static PriorityQueue<Bridge> q = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = input[0];
        m = input[1];
        info = new int[n][m];
        visited = new boolean[n][m];
        count = 0;
        for (int i = 0; i < n; i++) {
            info[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (info[i][j] == 1 && !visited[i][j]) {
                    Island island = new Island();
                    count++;
                    dfs(i, j, island);
                    islands.add(island);
                }
            }
        }
        islandNumber = new int[count + 1];
        for (int i = 0; i < islandNumber.length; i++) {
            islandNumber[i] = i;
        }
        initBridge();
        int answer = kruskal();
        System.out.println(answer);
    }

    static void initBridge() {
        for (Island island : islands) {
            for (Point p : island.points) {
                for (int i = 0; i < 4; i++) {
                    int nx = p.x + dx[i];
                    int ny = p.y + dy[i];
                    if (!isOutRange(nx, ny) && !isSea(nx, ny)) {
                        continue;
                    }
                    while (!isOutRange(nx, ny) && isSea(nx, ny)) {
                        nx = nx + dx[i];
                        ny = ny + dy[i];
                    }
                    if (isOutRange(nx, ny)) {
                        continue;
                    }
                    int length = Math.abs(nx - p.x) + Math.abs(ny - p.y) - 1;
                    if (length <= 1) {
                        continue;
                    }
                    q.add(new Bridge(info[p.x][p.y], info[nx][ny], length));
                }
            }
        }
    }

    static int kruskal() {
        int bridgeCount = 0;
        int answer = 0;
        while (!q.isEmpty() && bridgeCount < islandNumber.length - 1) {
            Bridge b = q.poll();
            if (find(b.start) == find(b.end)) {
                continue;
            }
            union(b.start, b.end);
            answer += b.cost;
            bridgeCount++;
        }
        if (bridgeCount == count - 1) {
            return answer;
        }
        return -1;
    }

    static int find(int x) {
        if (islandNumber[x] != x) {
            islandNumber[x] = find(islandNumber[x]);
        }
        return islandNumber[x];
    }

    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        islandNumber[rootX] = rootY;
    }

    static boolean isSea(int x, int y) {
        return info[x][y] == 0;
    }

    static void dfs(int x, int y, Island island) {
        visited[x][y] = true;
        island.points.add(new Point(x, y));
        info[x][y] = count;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isOutRange(nx, ny) || isSea(nx, ny) || visited[nx][ny]) {
                continue;
            }
            dfs(nx, ny, island);
        }
    }

    static boolean isOutRange(int x, int y) {
        return x < 0 || n <= x || y < 0 || m <= y;
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Island {
        List<Point> points = new ArrayList<>();

        public Island() {
        }
    }

    static class Bridge implements Comparable<Bridge> {
        int start;
        int end;
        int cost;

        public Bridge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Bridge o) {
            return this.cost - o.cost;
        }
    }
}
