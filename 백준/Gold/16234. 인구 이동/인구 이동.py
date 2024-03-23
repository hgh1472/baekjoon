import sys
from collections import deque
input = sys.stdin.readline
sys.setrecursionlimit(10 ** 6)
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]


def bfs(x, y, l, r):
    q= deque()
    q.append([x, y])
    visited[x][y] = 1
    pos = [[x, y]]
    populations = nations[x][y]
    while q:
        a, b = q.popleft()
        for i in range(4):
            nx = a + dx[i]
            ny = b + dy[i]
            if 0 <= nx < n and 0 <= ny < n and visited[nx][ny] == 0:
                if l <= abs(nations[a][b] - nations[nx][ny]) <= r:
                    pos.append([nx, ny])
                    visited[nx][ny] = 1
                    populations += nations[nx][ny]
                    q.append([nx, ny])
    count = len(pos)
    for a, b in pos:
        nations[a][b] = populations // count
    return count

n, l, r = map(int, input().split())
nations = []
for i in range(n):
    nations.append(list(map(int, input().split())))

day = -1
flag = 1
while flag == 1:
    day += 1
    flag = 0
    visited = [[0] * n for _ in range(n)]
    for i in range(n):
        for j in range(n):
            if visited[i][j] == 0:
                change = bfs(i, j, l, r)
                if change >= 2:
                    flag = 1

print(day)
