import java.util.*;

class Solution {
    int[][] board;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    boolean[] visited;
    public int solution(int[][] board, int r, int c) {
        this.visited = new boolean[7];
        this.board = board;
        int count = 0;
        Arrays.fill(visited, true);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != 0) {
                    count++;
                    visited[board[i][j]] = false;
                }
            }
        }
        count /= 2;
        
        return dfs(r, c, count);
    }
    
    int dfs(int x, int y, int count) {
        if (count == 0) {
            return 0;
        }
        int result = Integer.MAX_VALUE;
        for (int i = 1; i <= 6; i++) {
            if (visited[i]) {
                continue;
            }
            Node start = findCard(x, y, i);
            board[start.x][start.y] = 0;
            Node end = findCard(start.x, start.y, i);
            board[end.x][end.y] = 0;
            visited[i] = true;
            result = Math.min(result, dfs(end.x, end.y, count - 1) + start.cost + end.cost + 2);
            visited[i] = false;
            board[start.x][start.y] = i;
            board[end.x][end.y] = i;
        }
        return result;
    }
    
    Node findCard(int x, int y, int card) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        if (board[x][y] == card) {
            return new Node(x, y, 0);
        }
        q.add(new Node(x, y, 0));
        while (!q.isEmpty()) {
            Node poll = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = poll.x + dx[i];
                int ny = poll.y + dy[i];
                if (isOutOfRange(nx, ny)) {
                    continue;
                }
                if (board[nx][ny] == card) {
                    return new Node(nx, ny, poll.cost + 1);
                }
                q.add(new Node(nx, ny, poll.cost + 1));
                
                // ctrl move
                nx = poll.x;
                ny = poll.y;
                while (!isOutOfRange(nx + dx[i], ny + dy[i])) {
                    nx += dx[i];
                    ny += dy[i];
                    if (board[nx][ny] != 0) {
                        break;
                    }
                }
                if (poll.x + dx[i] != nx || poll.y + dy[i] != ny) {
                    if (board[nx][ny] == card) {
                        return new Node(nx, ny, poll.cost + 1);
                    }
                    q.add(new Node(nx, ny, poll.cost + 1));
                }
            }
        }
        
        return null;
    }
    
    boolean isOutOfRange(int x, int y) {
        return x < 0 || 4 <= x || y < 0 || 4 <= y;
    }
    
    class Node implements Comparable<Node> {
        int x, y;
        int cost;
        
        Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node n) {
            return this.cost - n.cost;
        }
    }
}