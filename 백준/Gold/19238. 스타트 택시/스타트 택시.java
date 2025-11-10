import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, fuel;
    static int[][] map;
    static int[][] info;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        fuel = Integer.parseInt(input[2]);
        map = new int[n][n];
        info = new int[n][n];
        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        input = br.readLine().split(" ");
        Node car = new Node(Integer.parseInt(input[0]) - 1, Integer.parseInt(input[1]) - 1, fuel);
        int[] distance = new int[m+1];
        Map<Integer, Node> dst = new HashMap<>();
        boolean impossible = false;
        for (int i = 1; i <= m; i++) {
            input = br.readLine().split(" ");
            int x1 = Integer.parseInt(input[0]) - 1;
            int y1 = Integer.parseInt(input[1]) - 1;
            int x2 = Integer.parseInt(input[2]) - 1;
            int y2 = Integer.parseInt(input[3]) - 1;
            info[x1][y1] = i;
            distance[i] = getDistance(x1, y1, x2, y2);
            if (distance[i] == -1) {
                impossible = true;
            }
            dst.put(i, new Node(x2, y2, 0));
        }
        if (impossible) {
            System.out.println(-1);
            return;
        }

        for (int i = 0; i < m; i++) {
            Node person = findPerson(car);
            if (person == null) {
                System.out.println(-1);
                return;
            }
            car.x = person.x;
            car.y = person.y;
            car.fuel -= person.fuel;
            int number = info[person.x][person.y];
            info[person.x][person.y] = 0;
            if (car.fuel < 0) {
                System.out.println(-1);
                return;
            }
            int dist = distance[number];
            Node node = dst.get(number);
            car.x = node.x;
            car.y = node.y;
            car.fuel -= dist;
            if (car.fuel < 0) {
                System.out.println(-1);
                return;
            }
            car.fuel += 2 * dist;
        }
        System.out.println(car.fuel);
    }

    static int getDistance(int x1, int y1, int x2, int y2) {
        Queue<Node> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];

        q.add(new Node(x1, y1, 0));
        while (!q.isEmpty()) {
            Node poll = q.poll();
            if (visited[poll.x][poll.y]) {
                continue;
            }
            if (poll.x == x2 && poll.y == y2) {
                return poll.fuel;
            }
            visited[poll.x][poll.y] = true;
            for (int i = 0; i < 4; i++) {
                int nx = poll.x + dx[i];
                int ny = poll.y + dy[i];
                if (isOut(nx, ny) || isWall(nx, ny)) {
                    continue;
                }
                q.add(new Node(nx, ny, poll.fuel + 1));
            }
        }
        return -1;
    }

    static Node findPerson(Node car) {
        List<Node> list = new ArrayList<>();
        Queue<Node> q = new ArrayDeque<>();

        boolean find = false;
        q.add(new Node(car.x, car.y, 0));
        int level = 0;
        boolean[][] visited = new boolean[n][n];
        while (!q.isEmpty() && !find) {
            while (!q.isEmpty() && q.peek().fuel == level) {
                Node poll = q.poll();
                if (visited[poll.x][poll.y]) {
                    continue;
                }
                if (info[poll.x][poll.y] > 0) {
                    list.add(poll);
                    find = true;
                    continue;
                }
                visited[poll.x][poll.y] = true;
                for (int i = 0; i < 4; i++) {
                    int nx = poll.x + dx[i];
                    int ny = poll.y + dy[i];
                    if (isOut(nx, ny) || isWall(nx, ny)) {
                        continue;
                    }
                    if (poll.fuel + 1 > car.fuel) {
                        continue;
                    }
                    Node node = new Node(nx, ny, poll.fuel + 1);
                    q.add(node);
                }
            }
            level++;
        }

        if (list.isEmpty()) {
            return null;
        }
        Collections.sort(list);
        return list.get(0);
    }

    static boolean isOut(int x, int y) {
        return x < 0 || n <= x || y < 0 || n <= y;
    }

    static boolean isWall(int x, int y) {
        return map[x][y] == 1;
    }

    static class Node implements Comparable<Node> {
        int x, y;
        int fuel;

        public Node(int x, int y, int fuel) {
            this.x = x;
            this.y = y;
            this.fuel = fuel;
        }

        @Override
        public int compareTo(Node o) {
            if (this.x != o.x) {
                return this.x - o.x;
            }
            return this.y - o.y;
        }
    }
}
