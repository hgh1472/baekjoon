class Solution {
    private static final int[] dr = {1, 0, 0, -1};  // d, l, r, u
    private static final int[] dc = {0, -1, 1, 0};
    private static final String[] dir = {"d", "l", "r", "u"};

    String answer = "impossible";
    int N, M, K;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        N = n;
        M = m;
        K = k;

        int minDist = Math.abs(r - x) + Math.abs(c - y);

        // 1. 도달 불가능한 경우 가지치기
        if (minDist > k || (k - minDist) % 2 != 0) {
            return "impossible";
        }

        // 2. DFS 탐색 시작
        dfs(x, y, r, c, 0, "");

        return answer;
    }

    private void dfs(int row, int col, int r, int c, int moveCnt, String path) {
        // 3. 정답을 찾으면 더 이상 탐색할 필요 없음 (가지치기)
        if (!answer.equals("impossible")) return;

        // 4. 정확히 k번 이동 후 목적지 도착 시 정답 갱신
        if (moveCnt == K) {
            if (row == r && col == c) {
                answer = path;
            }
            return;
        }

        // 5. 현재 이동 횟수 + 남은 최소 거리 > k면 탐색 중단 (가지치기)
        int remainingMoves = K - moveCnt;
        int minDistToEnd = Math.abs(r - row) + Math.abs(c - col);
        if (remainingMoves < minDistToEnd) return;

        // 6. d → l → r → u 순서로 탐색 (사전순)
        for (int i = 0; i < 4; i++) {
            int nr = row + dr[i];
            int nc = col + dc[i];

            // 7. 격자 범위를 벗어나지 않는 경우에만 탐색
            if (nr >= 1 && nr <= N && nc >= 1 && nc <= M) {
                dfs(nr, nc, r, c, moveCnt + 1, path + dir[i]);
            }
        }
    }
}
