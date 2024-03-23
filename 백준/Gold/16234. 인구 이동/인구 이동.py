import sys
from collections import deque
input = sys.stdin.readline
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]


def bfs(tx, ty):
    visited[tx][ty] = day
    stack = [[tx, ty]]
    populations = nations[tx][ty]
    for a, b in stack:
        for i in range(4):
            nx = a + dx[i]
            ny = b + dy[i]
            if 0 <= nx < n and 0 <= ny < n and visited[nx][ny] != day:
                if L <= abs(nations[a][b] - nations[nx][ny]) <= R:
                    visited[nx][ny] = day
                    populations += nations[nx][ny]
                    stack.append([nx, ny])
    if len(stack) > 1:
        avg = populations // len(stack)
        for a, b in stack:
            nations[a][b] = avg
            pos.append([a, b])


n, L, R = map(int, input().split())
nations = []
for i in range(n):
    nations.append(list(map(int, input().split())))

pos = deque([x, y] for y in range(n) for x in range(y % 2, n, 2))
day = 0
visited = [[-1] * n for _ in range(n)]
while pos:
    for _ in range(len(pos)):
        x, y = pos.popleft()
        if visited[x][y] < day:
            bfs(x, y)
    day += 1


print(day-1)
