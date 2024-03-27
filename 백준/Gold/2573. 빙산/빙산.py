import sys
from collections import deque
input = sys.stdin.readline
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]


def bfs(a, b):
    bfs_q = deque()
    bfs_q.append((a, b))
    visited[a][b] = True
    while bfs_q:
        x, y = bfs_q.popleft()
        melt_count = 0
        for k in range(4):
            nx = x + dx[k]
            ny = y + dy[k]
            if 0 <= nx < n and 0 <= ny < m:
                if info[nx][ny] != 0 and not visited[nx][ny]:
                    bfs_q.append((nx, ny))
                    visited[nx][ny] = True
                if info[nx][ny] == 0:
                    melt_count += 1
        if melt_count >= 1:
            melt_stack.append((x, y, melt_count))


n, m = map(int, input().split())
info = []
for i in range(n):
    info.append(list(map(int, input().split())))

year = 0
while True:
    count = 0
    visited = [[False] * m for _ in range(n)]
    melt_stack = []
    for i in range(n):
        for j in range(m):
            if info[i][j] != 0 and not visited[i][j]:
                bfs(i, j)
                count += 1
    while melt_stack:
        mx, my, melted = melt_stack.pop()
        info[mx][my] = max(0, info[mx][my] - melted)

    if count >= 2:
        break
    elif count == 0:
        year = 0
        break
    year += 1

print(year)
