import java.util.*;

class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int[][] board;
    int length;
    boolean[][] horizon;
    boolean[][] vertical;
    public int solution(int[][] board) {
        this.board = board;
        length = board.length;
        horizon = new boolean[length][length];
        vertical = new boolean[length][length];
        PriorityQueue<Robot> q = new PriorityQueue<>();
        q.add(new Robot(new Point(0, 0), new Point(0, 1), true, 0));
        while (!q.isEmpty()) {
            Robot r = q.poll();
            if ((r.p1.x == length-1 && r.p1.y == length-1) || (r.p2.x == length-1 && r.p2.y == length-1)) {
                return r.count;
            }
            
            // 평행 이동 확인
            for (int i = 0; i < 4; i++) {
                int nx1 = r.p1.x + dx[i];
                int ny1 = r.p1.y + dy[i];
                int nx2 = r.p2.x + dx[i];
                int ny2 = r.p2.y + dy[i];
                
                if (isMovable(nx1, ny1) && isMovable(nx2, ny2)) {
                    if (r.isHorizon) {
                        if (horizon[nx1][ny1] && horizon[nx2][ny2]) {
                            continue;
                        }
                        horizon[nx1][ny1] = true;
                        horizon[nx2][ny2] = true;
                    }
                    else {
                        if (vertical[nx1][ny1] && vertical[nx2][ny2]) {
                            continue;
                        }
                        vertical[nx1][ny1] = true;
                        vertical[nx2][ny2] = true;
                    }
                    q.add(new Robot(new Point(nx1, ny1), new Point(nx2, ny2), r.isHorizon, r.count + 1));
                }
            }
            
            // 회전 => 4가지
            if (r.isHorizon) {
                // 아래로의 회전
                if (isMovable(r.p1.x+1, r.p1.y) && isMovable(r.p2.x+1, r.p2.y)) {
                    if (!vertical[r.p1.x][r.p1.y] || !vertical[r.p1.x+1][r.p1.y]) {
                        q.add(new Robot(new Point(r.p1.x, r.p1.y), new Point(r.p1.x+1, r.p1.y), false, r.count+1));
                        vertical[r.p1.x][r.p1.y] = true;
                        vertical[r.p1.x+1][r.p1.y] = true;
                    }
                    if (!vertical[r.p2.x][r.p2.y] || !vertical[r.p2.x+1][r.p2.y]) {
                        q.add(new Robot(new Point(r.p2.x, r.p2.y), new Point(r.p2.x+1, r.p2.y), false, r.count+1));
                        vertical[r.p2.x][r.p2.y] = true;
                        vertical[r.p2.x+1][r.p2.y] = true;
                    }
                }
                // 위로 회전
                if (isMovable(r.p1.x-1, r.p1.y) && isMovable(r.p2.x-1, r.p2.y)) {
                    if (!vertical[r.p1.x][r.p1.y] || !vertical[r.p1.x-1][r.p1.y]) {
                        q.add(new Robot(new Point(r.p1.x, r.p1.y), new Point(r.p1.x-1, r.p1.y), false, r.count+1));
                        vertical[r.p1.x][r.p1.y] = true;
                        vertical[r.p1.x-1][r.p1.y] = true;
                    }
                    if (!vertical[r.p2.x][r.p2.y] || !vertical[r.p2.x-1][r.p2.y]) {
                        q.add(new Robot(new Point(r.p2.x, r.p2.y), new Point(r.p2.x-1, r.p2.y), false, r.count+1));
                        vertical[r.p2.x][r.p2.y] = true;
                        vertical[r.p2.x-1][r.p2.y] = true;
                    }
                }
            }
            else {
                // 오른쪽 회전
                if (isMovable(r.p1.x, r.p1.y+1) && isMovable(r.p2.x, r.p2.y+1)) {
                    if (!horizon[r.p1.x][r.p1.y] || !horizon[r.p1.x][r.p1.y+1]) {
                        q.add(new Robot(new Point(r.p1.x, r.p1.y), new Point(r.p1.x, r.p1.y+1), true, r.count+1));
                        horizon[r.p1.x][r.p1.y] = true;
                        horizon[r.p1.x][r.p1.y+1] = true;
                    }
                    if (!horizon[r.p2.x][r.p2.y] || !horizon[r.p2.x][r.p2.y+1]) {
                        q.add(new Robot(new Point(r.p2.x, r.p2.y), new Point(r.p2.x, r.p2.y+1), true, r.count+1));
                        horizon[r.p2.x][r.p2.y] = true;
                        horizon[r.p2.x][r.p2.y+1] = true;
                    }
                }
                // 왼쪽 회전
                if (isMovable(r.p1.x, r.p1.y-1) && isMovable(r.p2.x, r.p2.y-1)) {
                    if (!horizon[r.p1.x][r.p1.y] || !horizon[r.p1.x][r.p1.y-1]) {
                        q.add(new Robot(new Point(r.p1.x, r.p1.y), new Point(r.p1.x, r.p1.y-1), true, r.count+1));
                        horizon[r.p1.x][r.p1.y] = true;
                        horizon[r.p1.x][r.p1.y-1] = true;
                    }
                    if (!horizon[r.p2.x][r.p2.y] || !horizon[r.p2.x][r.p2.y-1]) {
                        q.add(new Robot(new Point(r.p2.x, r.p2.y), new Point(r.p2.x, r.p2.y-1), true, r.count+1));
                        horizon[r.p2.x][r.p2.y] = true;
                        horizon[r.p2.x][r.p2.y-1] = true;
                    }
                }
            }
        }
        return -1;
    }
    
    boolean isMovable(int x, int y) {
        if (x < 0 || length <= x || y < 0 || length <= y) {
            return false;
        }
        if (board[x][y] == 1) {
            return false;
        }
        return true;
    }
    
    class Point {
        int x;
        int y;
        
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    class Robot implements Comparable<Robot> {
        Point p1;
        Point p2;
        boolean isHorizon;
        int count;
        
        Robot(Point p1, Point p2, boolean isHorizon, int count) {
            this.p1 = p1;
            this.p2 = p2;
            this.isHorizon = isHorizon;
            this.count = count;
        }
        
        @Override
        public int compareTo(Robot o) {
            return this.count - o.count;
        }
    }
}