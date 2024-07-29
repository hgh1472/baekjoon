import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] info;
    static int n;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        info = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                info[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        List<Continent> continents = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (info[i][j] == 1) {
                    checkContinent(continents, i, j);
                }
            }
        }

        min = 10001;

        for (int i = 0; i < continents.size(); i++) {
            Continent first = continents.get(i);
            for (int j = i+1; j < continents.size(); j++) {
                Continent second = continents.get(j);
                calculateDistance(first, second);
            }
        }
        System.out.println(min);
    }

    static void calculateDistance(Continent first, Continent second) {
        List<Node> firstList = first.list;
        List<Node> secondList = second.list;

        for (Node n1 : firstList) {
            for (Node n2 : secondList) {
                min = Math.min(min, Math.abs(n1.x - n2.x) + Math.abs(n1.y - n2.y) - 1);
            }
        }
    }

    static void checkContinent(List<Continent> continents, int x, int y) {
        Continent continent = new Continent(new ArrayList<>());

        Queue<Node> q = new ArrayDeque<>();
        info[x][y] = -1; // -1 => 방문한 곳
        q.add(new Node(x, y));

        while (!q.isEmpty()) {
            Node poll = q.poll();
            boolean isEdge = false;
            for (int i = 0; i < 4; i++) {
                int nx = poll.x + dx[i];
                int ny = poll.y + dy[i];

                // 범위 밖이고 방문한 곳이면 통과
                if (nx < 0 || n <= nx || ny < 0 || n <= ny || info[nx][ny] == -1)
                    continue;

                // 대륙에 속하지 않는 땅이라면 통과
                if (info[nx][ny] == 0) {
                    isEdge = true;
                    continue;
                }
                info[nx][ny] = -1;
                q.add(new Node(nx, ny));
            }
            if (isEdge)
                continent.list.add(poll);
        }
        continents.add(continent);
    }

    static class Continent {
        List<Node> list;

        public Continent(List<Node> list) {
            this.list = list;
        }
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
