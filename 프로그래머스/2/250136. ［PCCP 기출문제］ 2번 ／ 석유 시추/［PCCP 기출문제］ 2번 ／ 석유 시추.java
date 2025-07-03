import java.util.*;

class Solution {
    int[] dx = {-1, 1, 0 ,0};
    int[] dy = {0, 0, -1, 1};
    Map<Integer, Integer> oils = new HashMap<>();
    public int solution(int[][] land) {
        int number = 2;
        boolean[][] visited = new boolean[land.length][land[0].length];
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[i].length; j++) {
                if (visited[i][j]) {
                    continue;
                }
                if (land[i][j] == 1) {
                    int total = bfs(land, i, j, number, visited);
                    oils.put(number, total);
                    number++;
                }
            }
        }
        
        int answer = 0;
        for (int j = 0; j < land[0].length; j++) {
            boolean[] isGet = new boolean[number];
            int get = 0;
            for (int i = 0; i < land.length; i++) {
                if (land[i][j] > 1 && !isGet[land[i][j]]) {
                    get += oils.get(land[i][j]);
                    isGet[land[i][j]] = true;
                }
            }
            answer = Math.max(answer, get);
        }
        
        return answer;
    }
    
    int bfs(int[][] land, int x, int y, int number, boolean[][] visited) {
        Queue<Node> q = new ArrayDeque<>();
        int count = 0;
        
        q.add(new Node(x, y));
        land[x][y] = number;
        visited[x][y] = true;
        
        while (!q.isEmpty()) {
            Node cur = q.poll();
            count++;
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || land.length <= nx || ny < 0 || land[0].length <= ny) {
                    continue;
                }
                if (land[nx][ny] != 1) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                visited[nx][ny] = true;
                land[nx][ny] = number;
                q.add(new Node(nx, ny));
            }
        }
        return count;
    }
    
    class Node {
        int x, y;
        
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}