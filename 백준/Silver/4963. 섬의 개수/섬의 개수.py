import sys
from collections import deque

read = sys.stdin.readline

dx = [1, -1, 0, 0, 1, 1, -1, -1]
dy = [0, 0, 1, -1, 1, -1, -1, 1]

def bfs(a, b):
    q = deque()
    q.append([a, b])
    visited[a][b] = 0
    while q:
        x, y = q.popleft()
        for i in range(8):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0 <= nx < h and 0 <= ny < w:
                if visited[nx][ny] == 0 and info[nx][ny] == 1:
                    q.append([nx, ny])
                    visited[nx][ny] = 1


while True:
    w, h = map(int, read().split())
    if w == 0 and h == 0:
        break

    count = 0
    info = []
    visited = [[0] * w for _ in range(h)]
    for i in range(h):
        info.append(list(map(int, read().split())))

    for i in range(h):
        for j in range(w):
            if info[i][j] == 1 and visited[i][j] == 0:
                bfs(i, j)
                count += 1

    print(count)
