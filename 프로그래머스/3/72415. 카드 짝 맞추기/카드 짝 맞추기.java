import java.util.*;
class Solution {
    Map<Integer, List<Point>> map = new HashMap<>();
    boolean[] visited;
    boolean[] contains = new boolean[7];
    int n = 0;
    int startX, startY;
    int dist = Integer.MAX_VALUE;
    int[][] info;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    
    public int solution(int[][] board, int r, int c) {
        info = board;
        int answer = 0;
        startX = r;
        startY = c;
        for (int i = 1; i <= 6; i++) {
            map.put(i, new ArrayList<>());
        }
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != 0) {
                    map.get(board[i][j]).add(new Point(i, j));
                    contains[board[i][j]] = true;
                    n++;
                }
            }
        }
        n /= 2;
        
        visited = new boolean[7];
        getOrder(0, 0, new Point(startX, startY));
        return dist;
    }
    
    
    void getOrder(int d, int value, Point cur) {
        if (d == n) {
            dist = Math.min(value, dist);
            return;
        }
        
        for (int i = 1; i <= 6; i++) {
            if (visited[i] || !contains[i]) {
                continue;
            }
            visited[i] = true;
            List<Point> points = map.get(i);
            
            Point p1 = points.get(0);
            Point p2 = points.get(1);
            
            int dist1 = getDistance(cur.x, p1.x, cur.y, p1.y) + getDistance(p1.x, p2.x, p1.y, p2.y) + 2;
            int dist2 = getDistance(cur.x, p2.x, cur.y, p2.y) + getDistance(p2.x, p1.x, p2.y, p1.y) + 2;
            
            info[p1.x][p1.y] = 0;
            info[p2.x][p2.y] = 0;
            
            getOrder(d + 1, value + dist1, p2);
            getOrder(d + 1, value + dist2, p1);
            
            info[p1.x][p1.y] = i;
            info[p2.x][p2.y] = i;
            visited[i] = false;
        }
    }
    
    
    int getDistance(int x1, int x2, int y1, int y2) {
        Queue<Point> q = new ArrayDeque();
        int[][] count = new int[4][4];
        
        q.add(new Point(x1, y1));
        count[x1][y1] = 1;
        
        while (!q.isEmpty()) {
            Point p = q.poll();
            if (p.x == x2 && p.y == y2) {
                return count[p.x][p.y] - 1;
            }
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if (nx < 0 || 4 <= nx || ny < 0 || 4 <= ny) {
                    continue;
                }
                if (count[nx][ny] != 0) {
                    continue;
                }
                count[nx][ny] = count[p.x][p.y] + 1;
                q.add(new Point(nx, ny));
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                while (0 <= nx && nx < 4 && 0 <= ny && ny < 4) {
                    if (info[nx][ny] != 0) {
                        break;
                    }
                    nx += dx[i];
                    ny += dy[i];
                }
                if (!(0 <= nx && nx < 4 && 0 <= ny && ny < 4)) {
                    nx -= dx[i];
                    ny -= dy[i];
                }
                if (count[nx][ny] != 0) {
                    continue;
                }
                q.add(new Point(nx, ny));
                count[nx][ny] = count[p.x][p.y] + 1;
            }
        }
        return count[x2][y2] - 1;
    }
    
    class Point {
        int x;
        int y;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}