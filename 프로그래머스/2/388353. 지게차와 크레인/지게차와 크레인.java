import java.util.*;

class Solution {
    char[][] map;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    
    int removedCount = 0;
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        
        map = new char[storage.length+2][storage[0].length()+2];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = '.';
            }
        }
        
        for (int i = 0; i < storage.length; i++) {
            for (int j = 0; j < storage[0].length(); j++) {
                map[1+i][j+1] = storage[i].charAt(j);
            }
        }
        
        for (String req : requests) {
            if (req.length() == 2) {
                crain(req.charAt(0));
            }
            if (req.length() == 1) {
                car(req.charAt(0));
            }
        }
        
        
        return storage.length * storage[0].length() - removedCount;
    }
    
    void crain(char c) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == c) {
                    map[i][j] = '.';
                    removedCount++;
                }
            }
        }
    }
    
    void car(char c) {
        Queue<Node> q = new ArrayDeque<>();
        List<Node> removed = new ArrayList<>();
        boolean[][] visited = new boolean[map.length][map[0].length];
        
        q.add(new Node(0, 0));
        visited[0][0] = true;
        while (!q.isEmpty()) {
            Node poll = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = poll.x + dx[i];
                int ny = poll.y + dy[i];
                if (nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                if (map[nx][ny] == c) {
                    removed.add(new Node(nx, ny));
                    visited[nx][ny] = true;
                }
                if (map[nx][ny] == '.') {
                    q.add(new Node(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }
        
        for (Node node : removed) {
            map[node.x][node.y] = '.';
            removedCount++;
        }
    }
    
    class Node {
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}