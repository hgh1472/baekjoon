import java.util.*;

class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int m, n;
    boolean[][][] visited;
    boolean isRedEnd = false;
    boolean isBlueEnd = false;
    int answer = Integer.MAX_VALUE;
    
    public int solution(int[][] maze) {
        this.m = maze.length;
        this.n = maze[0].length;
        this.visited = new boolean[maze.length][maze[0].length][2];
        
        Node red = new Node(0, 0);
        Node blue = new Node(0, 0);
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 1) {
                    red.x = i;
                    red.y = j;
                }
                if (maze[i][j] == 2) {
                    blue.x = i;
                    blue.y = j;
                }
            }
        }
        
        visited[red.x][red.y][0] = true;
        visited[blue.x][blue.y][1] = true;
        dfs(maze, red, blue, 0);
        
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
    
    public void dfs(int[][] maze, Node red, Node blue, int count) {
        if (isRedEnd && isBlueEnd) {
            answer = Math.min(answer, count);
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Node nRed = (isRedEnd) ? red : next(maze, red, i);
                Node nBlue = (isBlueEnd) ? blue : next(maze, blue, j);
                
                // 범위 밖
                if (isOut(nRed.x, nRed.y) || isOut(nBlue.x, nBlue.y)) {
                    continue;
                }
                // 같은 칸
                if (nRed.x == nBlue.x && nRed.y == nBlue.y) {
                    continue;
                }
                // 벽
                if (maze[nRed.x][nRed.y] == 5 || maze[nBlue.x][nBlue.y] == 5) {
                    continue;
                }
                // 스왑
                if (red.x == nBlue.x && red.y == nBlue.y && nRed.x == blue.x && nRed.y == blue.y) {
                    continue;
                }
                if (!isRedEnd && visited[nRed.x][nRed.y][0]) {
                    continue;
                }
                if (!isBlueEnd && visited[nBlue.x][nBlue.y][1]) {
                    continue;
                }
                
                if (maze[nRed.x][nRed.y] == 3) {
                    isRedEnd = true;
                }
                if (maze[nBlue.x][nBlue.y] == 4) {
                    isBlueEnd = true;
                }
                
                visited[nRed.x][nRed.y][0] = true;
                visited[nBlue.x][nBlue.y][1] = true;
                
                dfs(maze, nRed, nBlue, count+1);
                
                visited[nRed.x][nRed.y][0] = false;
                visited[nBlue.x][nBlue.y][1] = false;
                // isRedEnd = false;
                // isBlueEnd = false;
                if (maze[nRed.x][nRed.y] == 3) {
                    isRedEnd = false;
                }
                if (maze[nBlue.x][nBlue.y] == 4) {
                    isBlueEnd = false;
                }
            }
        }
    }
    
    public Node next(int[][] maze, Node node, int i) {
        int nx = node.x + dx[i];
        int ny = node.y + dy[i];
        return new Node(nx, ny);
    }
    
    public boolean isOut(int x, int y) {
        if (x < 0 || m <= x || y < 0 || n <= y) {
            return true;
        }
        return false;
    }
    
    
    class Node {
        int x, y;
        
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}