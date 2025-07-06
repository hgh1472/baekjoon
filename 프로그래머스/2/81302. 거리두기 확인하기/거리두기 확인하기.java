import java.util.*;

class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    
    
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        for (int i = 0; i < places.length; i++) {
            int result = 1;
            for (int j = 0; j < places[0].length; j++) {
                for (int k = 0; k < 5; k++) {
                    if (places[i][j].charAt(k) == 'P') {
                        result = Math.min(result, bfs(places[i], j, k));
                    }
                }
            }
            answer[i] = result;
        }
        return answer;
    }
    
    int bfs(String[] places, int x, int y) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(x, y, 0));
        
        boolean[][] visited = new boolean[places.length][places[0].length()];
        visited[x][y] = true;
        
        while (!q.isEmpty()) {
            Node poll = q.poll();
            if (poll.distance >= 2) {
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int nx = poll.x + dx[i];
                int ny = poll.y + dy[i];
                if (nx < 0 || ny < 0 || visited.length <= nx || visited[0].length <= ny) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                if (places[nx].charAt(ny) == 'X') {
                    continue;
                }
                if (places[nx].charAt(ny) == 'O') {
                    q.add(new Node(nx, ny, poll.distance+1));
                    visited[nx][ny] = true;
                }
                if (places[nx].charAt(ny) == 'P') {
                    return 0;
                }
            }
        }
        return 1;
    }
    
    class Node {
        int x, y;
        int distance;
        
        Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}