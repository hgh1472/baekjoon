import java.util.*;

class Solution {
    static final int INF = 1_000_000_000;
    
    int h, n, m, k;
    String[] grid;
    Panel[] panels;
    int evX, evY;
    
    int[] preMask;
    
    int[][] panelToPanel;
    int[] panelToEv;
    int[] evToPanel;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    public int solution(int h, String[] grid, int[][] panels, int[][] seqs) {
        this.h = h;
        this.grid = grid;
        this.n = grid.length;
        this.m = grid[0].length();
        this.k = panels.length;
        
        this.panels = new Panel[k];
        for (int i = 0; i < k; i++) {
            int[] p = panels[i];
            this.panels[i] = new Panel(i, p[0], p[1] - 1, p[2] - 1);
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i].charAt(j) == '@') { // 엘레베이터 발견
                    evX = i;
                    evY = j;
                }
            }
        }
        
        preMask = new int[k];
        for (int[] seq : seqs) {
            int a = seq[0] - 1;
            int b = seq[1] - 1;
            preMask[b] |= (1 << a);
        }
        
        processDistances();
        
        int START = k;
        int FULL = (1 << k) - 1;
        
        int[][] dp = new int[1 << k][k + 1];
        for (int i = 0; i < (1 << k); i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][START] = 0;
        
        for (int mask = 0; mask <= FULL; mask++) {
            for (int cur = 0; cur <= k; cur++) {
                if (dp[mask][cur] == INF) {
                    continue;
                }
                for (int next = 0; next < k; next++) {
                    if ((mask & (1 << next)) != 0) {
                        continue;
                    }
                    if ((mask & preMask[next]) != preMask[next]) {
                        continue;
                    }
                    int cost = moveCost(cur, next);
                    if (cost >= INF) {
                        continue;
                    }
                    
                    int nextMask = mask | (1 << next);
                    dp[nextMask][next] = Math.min(dp[nextMask][next], dp[mask][cur] + cost);
                }
            }
        }
        int answer = INF;
        for (int cur = 0; cur < k; cur++) {
            answer = Math.min(answer, dp[FULL][cur]);
        }
        
        return answer;
    }
    
    void processDistances() {
        panelToPanel = new int[k][k];
        panelToEv = new int[k];
        
        int[][] distFromEv = bfs(evX, evY);
        
        for (int i = 0; i < k; i++) {
            panelToEv[i] = distFromEv[panels[i].x][panels[i].y];
        }
        
        for (int i = 0; i < k; i++) {
            int[][] dist = bfs(panels[i].x, panels[i].y);
            for (int j = 0; j < k; j++) {
                panelToPanel[i][j] = dist[panels[j].x][panels[j].y];
            }
        }
    }
    
    int moveCost(int cur, int next) {
        if (cur == k) {
            return moveBetweenPanels(0, next);
        }
        return moveBetweenPanels(cur, next);
    }
    
    int moveBetweenPanels(int a, int b) {
        Panel pa = panels[a];
        Panel pb = panels[b];
        
        if (pa.floor == pb.floor) {
            return panelToPanel[a][b];
        }
        if (panelToEv[a] < 0 || panelToEv[b] < 0) return INF;
        
        return panelToEv[a] + Math.abs(pa.floor - pb.floor) + panelToEv[b];
    }
    
    int[][] bfs(int sx, int sy) {
        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], -1);
        }
        
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sx, sy});
        dist[sx][sy] = 0;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                if (grid[nx].charAt(ny) == '#') {
                    continue;
                }
                if (dist[nx][ny] != -1) {
                    continue;
                }
                dist[nx][ny] = dist[x][y] + 1;
                q.add(new int[]{nx, ny});
            }
        }
        return dist;
    }
    
    class Panel {
        int num;
        int floor;
        int x, y;
        
        Panel(int num, int floor, int x, int y) {
            this.num = num;
            this.floor = floor;
            this.x = x;
            this.y = y;
        }
    }
}