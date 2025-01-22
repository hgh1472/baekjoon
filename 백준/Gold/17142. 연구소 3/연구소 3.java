import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static int[][] info;
    static List<Node> virus = new ArrayList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int answer = 100000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = input[0];
        m = input[1];
        info = new int[n][n];
        for (int i = 0; i < n; i++) {
            info[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < n; j++) {
                if (info[i][j] == 2) {
                    virus.add(new Node(i, j, 0));
                }
            }
        }

        combination(0, m, new boolean[virus.size()], 0);
        if (answer == 100000) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    static void combination(int d, int f, boolean[] visited, int last) {
        if (d == f) {
            int result = getMinTime(visited);
            if (result != -1) {
                answer = Math.min(answer, result);
            }
            return;
        }
        for (int i = last; i < virus.size(); i++) {
            visited[i] = true;
            combination(d + 1, f, visited, i + 1);
            visited[i] = false;
        }
    }

    static int getMinTime(boolean[] visited) {
        int[][] temp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (info[i][j] == 1) {
                    // 벽 = -2
                    temp[i][j] = -2;
                } else {
                    // 방문안한 곳 = -1
                    temp[i][j] = -1;
                }
            }
        }

        PriorityQueue<Node> q = new PriorityQueue<>();
        for (int i = 0; i < virus.size(); i++) {
            if (visited[i]) {
                q.add(new Node(virus.get(i).x, virus.get(i).y, 0));
            }
        }

        int maxTime = 0;
        while (!q.isEmpty()) {
            Node node = q.poll();
            // 이미 방문했는데, 더 짧은 시간이 아니라면 제외
            if (temp[node.x][node.y] > -1 && temp[node.x][node.y] <= node.time) {
                continue;
            }
            temp[node.x][node.y] = node.time;
            /**
             * 비활성 바이러스를 활성 바이러스로 변화하는데 시간은 반영되지 않는다.
             * 이때 비활성 바이러스를 활성 바이러스로 바꾸는데 시간은 소요되지만, 비활성 바이러스가 활성 바이러스가 되지 않았다고 해서 틀린 상황은 아님
             * 빈칸은 다 퍼트렸는데 비활성 바이러스가 존재하더라도 해당 상황은 바이러스가 모두 존재하는 것임 
             */
            if (maxTime < node.time && info[node.x][node.y] == 0) {
                maxTime = node.time;
            }
            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                // 범위 밖
                if (nx < 0 || n <= nx || ny < 0 || n <= ny) {
                    continue;
                }
                // 벽
                if (temp[nx][ny] == -2) {
                    continue;
                }
                q.add(new Node(nx, ny, node.time + 1));
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 방문 못했는데, 바이러스도 아닌 상황 => 실패
                if (temp[i][j] == -1 && info[i][j] == 0) {
                    return -1;
                }
            }
        }
        return maxTime;
    }

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int time;

        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }
}