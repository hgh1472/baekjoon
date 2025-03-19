import java.util.*;

class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1 ,1};
    public int solution(int[][] board) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        
        q.add(new Node(0, 0, 1, 0));
        q.add(new Node(0, 0, 3, 0));
        
        int[][][] costs = new int[4][board.length][board.length];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < board.length; j++) {
                for (int k = 0; k < board.length; k++) {
                    costs[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        
        while (!q.isEmpty()) {
            Node n = q.poll();
            if (costs[n.direction][n.x][n.y] <= n.cost) {
                continue;
            }
            costs[n.direction][n.x][n.y] = n.cost;
            for (int i = 0; i < 4; i++) {
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];
                if (nx < 0 || board.length <= nx || ny < 0 || board.length <= ny) {
                    continue;
                }
                if (board[nx][ny] == 1) {
                    continue;
                }
                int cost = (n.direction == i) ? n.cost + 100 : n.cost + 600;
                if (costs[i][nx][ny] > cost) {
                    q.add(new Node(nx, ny, i, cost));
                }
            }
        }
        
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            answer = Math.min(answer, costs[i][board.length - 1][board.length - 1]);
        }
        return answer;
    }
    
    class Node implements Comparable<Node> {
        int x, y;
        int direction;
        int cost;
        
        Node(int x, int y, int direction, int cost) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}