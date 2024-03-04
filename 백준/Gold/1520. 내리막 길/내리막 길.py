import sys
sys.setrecursionlimit(10 ** 6)
dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

input = sys.stdin.readline

m, n = map(int, input().split())
info = []

for i in range(m):
    info.append(list(map(int, input().split())))

dp = [[-1] * n for _ in range(m)]
dp[-1][-1] = 1

def dfs(x, y):
    if dp[x][y] != -1:
        return dp[x][y]
    cnt = 0
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        if 0 <= nx < m and 0 <= ny < n:
            if info[nx][ny] < info[x][y]:
                cnt += dfs(nx, ny)

    dp[x][y] = cnt
    return dp[x][y]


dfs(0, 0)
print(dp[0][0])
