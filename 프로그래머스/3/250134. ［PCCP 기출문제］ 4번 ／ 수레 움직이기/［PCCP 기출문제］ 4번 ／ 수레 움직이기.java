import java.util.*;

class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int m, n;
    
    public int solution(int[][] maze) {
        this.m = maze.length;
        this.n = maze[0].length;
        
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
        
        int result1 = goFirst(maze, red, blue, 3, 4);
        int result2 = goFirst(maze, blue, red, 4, 3);
        
        if (result1 == Integer.MAX_VALUE && result2 == Integer.MAX_VALUE) {
            return 0;
        }
        if (result1 == Integer.MAX_VALUE) {
            return result2;
        }
        if (result2 == Integer.MAX_VALUE) {
            return result1;
        }
        return Math.min(result1, result2);
    }
    
    
    public int goFirst(int[][] maze, Node first, Node second, int firstDst, int secondDst) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(first);
        
        int result = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (maze[cur.x][cur.y] == firstDst) {
                int next = goSecond(maze, cur, second, secondDst);
                result = Math.min(result, Math.max(cur.nodes.size(), next));
            }
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (isOut(nx, ny)) {
                    continue;
                }
                if (cur.nodes.size() == 0 && nx == second.x && ny == second.y) {
                    continue;
                }
                if (maze[nx][ny] == 5 || cur.isVisited(nx, ny)) {
                    continue;
                }
                
                Node next = cur.next(nx, ny);
                q.add(next);
            }
        }
        
        return result;
    }

    
    public int goSecond(int[][] maze, Node first, Node second, int dst) {
        Map<Integer, Node> history = new HashMap<>();
        for (int i = 0; i < first.nodes.size(); i++) {
            history.put(i, first.nodes.get(i));
        }
        history.put(first.nodes.size(), first);
        
        boolean[][] visited = new boolean[m][n];
        Queue<Node> q = new ArrayDeque<>();
        q.add(second);
        visited[second.x][second.y] = true;
        
        int result = Integer.MAX_VALUE;
        
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (maze[cur.x][cur.y] == dst) {
                return cur.nodes.size();
            }
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (isOut(nx, ny)) {
                    continue;
                }
                if (visited[nx][ny] || maze[nx][ny] == 5) {
                    continue;
                }
                
                // 두번째가 길을 막음
                if (history.containsKey(cur.nodes.size()+2)) {
                    Node node = history.get(cur.nodes.size()+2);
                    if (nx == node.x && ny == node.y) {
                        continue;
                    }
                }
                
                // 먼저 움직인 수레가 길을 막고 있음
                if (history.containsKey(cur.nodes.size()+1)) {
                    Node node = history.get(cur.nodes.size()+1);
                    if (nx == node.x && ny == node.y) {
                        continue;
                    }
                }
                else {
                    if (nx == first.x && ny == first.y) {
                        continue;
                    }
                }
                
                Node next = cur.next(nx, ny);
                visited[nx][ny] = true;
                q.add(next);
            }
        }
        
        return Integer.MAX_VALUE;
    }
    
    public boolean isOut(int x, int y) {
        if (x < 0 || m <= x || y < 0 || n <= y) {
            return true;
        }
        return false;
    }
    
    
    class Node {
        int x, y;
        List<Node> nodes = new ArrayList<>();
        
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public boolean isVisited(int x, int y) {
            for (Node n : nodes) {
                if (n.x == x && n.y == y) {
                    return true;
                }
            }
            return false;
        }
        
        public Node next(int x, int y) {
            Node node = new Node(x, y);
            for (Node n : this.nodes) {
                node.nodes.add(n);
            }
            node.nodes.add(this);
            return node;
        }
    }
}