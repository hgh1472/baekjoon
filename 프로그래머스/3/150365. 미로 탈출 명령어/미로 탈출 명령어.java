class Solution {
    static StringBuilder sb;
    static int down = 0, left = 1, right = 2, up = 3;
    static int[] command;
    static int limit, a, b, dr, dc;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        if (Math.abs(x - r) + Math.abs(y - c) > k || (k - (Math.abs(x - r) + Math.abs(y - c))) % 2 == 1)
            return "impossible";
        sb = new StringBuilder();
        limit = k;
        a = n;
        b = m;
        dr = r;
        dc = c;
        command = new int[4];
        if (r - x < 0) // 도착점이 더 위에 있음
            command[up] += -(r-x);
        else
            command[down] += r-x;
        if (c - y < 0) // 도착점이 더 왼쪽에 있음
            command[left] += -(c-y);
        else
            command[right] += c-y;
        
        dfs(x, y, Math.abs(r-x) + Math.abs(c-y), 0);
        return sb.toString();
    }
    
    // 플러스하는 것은 무조건 원점으로 돌아와야 한다.
    public void dfs(int nx, int ny, int count, int k) {
        int distance = Math.abs(dr - nx) + Math.abs(dc-ny);
        // down
        if (nx < a) {
            if (command[down] > 0) {
                command[down] -= 1;
                sb.append("d");
                dfs(nx+1, ny, count - 1,k + 1);
                return;
            }
            if (count + k + 2 <= limit) {
                command[down]+=1;
                command[up]+=1;
                dfs(nx, ny, count + 2,k);
                return;
            }
        }
        
        // left
        if (ny > 1) {
            if (command[left] > 0) {
                command[left]-=1;
                sb.append("l");
                dfs(nx, ny-1, count-1, k+1);
                return;
            }
            if (count + k + 2 <= limit) {
                command[left]+=1;
                command[right]+=1;
                dfs(nx,ny, count + 2, k);
                return;
            }
        }
        
        // right
        if (ny < b) {
            if (command[right] > 0) {
                command[right]-=1;
                sb.append("r");
                dfs(nx, ny+1, count-1, k+1);
                return;
            }
            if (count + k + 2 <= limit) {
                command[right]+=1;
                command[left]+=1;
                dfs(nx, ny, count + 2, k);
                return;
            }
        }
        
        // up
        if (nx > 1) {
            if (command[up] > 0) {
                command[up]-=1;
                sb.append("u");
                dfs(nx - 1, ny, count-1, k+1);
                return;
            }
            if (count + k + 2 <= limit) {
                command[up]+=1;
                command[down]+=1;
                dfs(nx, ny, count + 2, k);
                return;
            }
        }
    }
}
// 현재 위치에서 확인
//  밑으로 내려갈 수 있나? => 남은 횟수와 현재 위치가 범위내에 있는지
//      올라가는 경우까지 카운트한다.
//  