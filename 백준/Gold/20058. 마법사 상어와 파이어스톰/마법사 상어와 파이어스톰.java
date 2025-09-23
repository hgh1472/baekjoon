import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static int[][] ices;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    static int total = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        int pow = (int) Math.pow(2, n);
        ices = new int[pow][pow];

        for (int i = 0; i < pow; i++) {
            ices[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[] fireStorms = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int fireStorm : fireStorms) {
            int length = (int) Math.pow(2, fireStorm);
            for (int i = 0; i < ices.length; i += length) {
                for (int j = 0; j < ices.length; j+= length) {
                    rotate(i, j, i+length-1, j+length-1);
                }
            }

            check();
        }

        boolean[][] visited = new boolean[pow][pow];

        int count = 0;
        for (int i = 0; i < ices.length; i++) {
            for (int j = 0; j < ices.length; j++) {
                if (ices[i][j] != 0) {
                    count = Math.max(count, dfs(i, j, visited));
                }
            }
        }
        System.out.println(total);
        System.out.println(count);
    }

    static int dfs(int x, int y, boolean[][] visited) {
        if (visited[x][y]) {
            return 0;
        }
        visited[x][y] = true;

        total += ices[x][y];
        int count = 1;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ices.length <= nx || ny < 0 || ices.length <= ny) {
                continue;
            }
            if (ices[nx][ny] != 0) {
                count += dfs(nx, ny, visited);
            }
        }

        return count;
    }

    static void rotate(int x, int y, int nx, int ny) {
        int length = nx - x + 1;
        int[][] temp = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                temp[j][length-i-1] = ices[x+i][y+j];
            }
        }

        for (int i = x; i <= nx; i++) {
            for (int j = y; j <= ny; j++) {
                ices[i][j] = temp[i-x][j-y];
            }
        }
    }

    static void check() {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < ices.length; i++) {
            for (int j = 0; j < ices.length; j++) {
                if (ices[i][j] == 0) {
                    continue;
                }
                int count = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (nx < 0 || ices.length <= nx || ny < 0 || ices.length <= ny) {
                        continue;
                    }
                    if (ices[nx][ny] > 0) {
                        count++;
                    }
                }
                if (count <= 2) {
                    nodes.add(new Node(i, j));
                }
            }
        }

        for (Node node : nodes) {
            ices[node.x][node.y]--;
        }
    }

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
