import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        while (t != 0) {
            String[] input = br.readLine().split(" ");
            int h = Integer.parseInt(input[0]);
            int w = Integer.parseInt(input[1]);
            char[][] map = new char[h][w];

            for (int i = 0; i < h; i++) {
                map[i] = br.readLine().toCharArray();
            }
            Set<Character> keys = new HashSet<>();
            String s = br.readLine();
            for (int i = 0; i < s.length(); i++) {
                keys.add(s.charAt(i));
            }

            while (true) {
                Set<Character> find = findKeys(map, keys);
                if (find.isEmpty()) {
                    break;
                }
                keys.addAll(find);
            }
            int count = findDocument(map, keys);
            System.out.println(count);
            t--;
        }
    }

    static int findDocument(char[][] map, Set<Character> keys) {
        boolean[][] visited = new boolean[map.length][map[0].length];
        Queue<Node> q = new ArrayDeque<>();

        for (int i = 0; i < map.length; i++) {
            if (canStart(map[i][0], keys)) {
                q.add(new Node(i, 0));
            }
            if (canStart(map[i][map[0].length - 1], keys)) {
                q.add(new Node(i, map[0].length - 1));
            }
        }
        for (int i = 0; i < map[0].length; i++) {
            if (canStart(map[0][i], keys)) {
                q.add(new Node(0, i));
            }
            if (canStart(map[map.length - 1][i], keys)) {
                q.add(new Node(map.length-1, i));
            }
        }

        int count = 0;
        while (!q.isEmpty()) {
            Node poll = q.poll();
            if (visited[poll.x][poll.y]) {
                continue;
            }
            visited[poll.x][poll.y] = true;
            if (map[poll.x][poll.y] == '$') {
                count++;
            }
            for (int i = 0; i < 4; i++) {
                int nx = poll.x + dx[i];
                int ny = poll.y + dy[i];
                if (nx < 0 || ny < 0 || map.length <= nx || map[0].length <= ny) {
                    continue;
                }
                if (map[nx][ny] == '*') {
                    continue;
                }
                if ('A' <= map[nx][ny] && map[nx][ny] <= 'Z') {
                    if (!keys.contains(Character.toLowerCase(map[nx][ny]))) {
                        continue;
                    }
                }
                q.add(new Node(nx, ny));
            }
        }
        return count;
    }

    static Set<Character> findKeys(char[][] map, Set<Character> keys) {
        Set<Character> find = new HashSet<>();
        boolean[][] visited = new boolean[map.length][map[0].length];
        Queue<Node> q = new ArrayDeque<>();

        for (int i = 0; i < map.length; i++) {
            if (canStart(map[i][0], keys)) {
                q.add(new Node(i, 0));
            }
            if (canStart(map[i][map[0].length - 1], keys)) {
                q.add(new Node(i, map[0].length - 1));
            }
        }
        for (int i = 0; i < map[0].length; i++) {
            if (canStart(map[0][i], keys)) {
                q.add(new Node(0, i));
            }
            if (canStart(map[map.length - 1][i], keys)) {
                q.add(new Node(map.length-1, i));
            }
        }

        while (!q.isEmpty()) {
            Node poll = q.poll();
            if (visited[poll.x][poll.y]) {
                continue;
            }
            visited[poll.x][poll.y] = true;
            if ('a' <= map[poll.x][poll.y] && map[poll.x][poll.y] <= 'z') {
                if (!keys.contains(map[poll.x][poll.y])) {
                    find.add(map[poll.x][poll.y]);
                }
            }
            for (int i = 0; i < 4; i++) {
                int nx = poll.x + dx[i];
                int ny = poll.y + dy[i];
                if (nx < 0 || ny < 0 || map.length <= nx || map[0].length <= ny) {
                    continue;
                }
                if (map[nx][ny] == '*') {
                    continue;
                }
                if ('A' <= map[nx][ny] && map[nx][ny] <= 'Z') {
                    if (!keys.contains(Character.toLowerCase(map[nx][ny]))) {
                        continue;
                    }
                }
                q.add(new Node(nx, ny));
            }
        }
        return find;
    }

    static boolean canStart(char c, Set<Character> keys) {
        if (c == '.') {
            return true;
        }
        if (c == '$') {
            return true;
        }
        if ('A' <= c && c <= 'Z') {
            if (keys.contains(Character.toLowerCase(c))) {
                return true;
            }
        }
        if ('a' <= c && c <= 'z') {
            keys.add(Character.toUpperCase(c));
            return true;
        }
        return false;
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
