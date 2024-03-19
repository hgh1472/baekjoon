import sys
from collections import deque
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
input = sys.stdin.readline

n, m = map(int, input().split())

info = []
visited = [[[0, 0] for _ in range(m)] for _ in range(n)]
for i in range(n):
    info.append(list(input().rstrip()))
visited[0][0][0] = visited[0][0][1] = 1


def bfs():
    q = deque()
    q.append([0, 0, 1, 0])
    while q:
        x, y, distance, broke = q.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0 <= nx < n and 0 <= ny < m:
                if broke == 1 and info[nx][ny] == '0':
                    if visited[nx][ny][broke] > distance + 1 or visited[nx][ny][broke] == 0:
                        visited[nx][ny][broke] = distance + 1
                        q.append([nx, ny, distance + 1, broke])
                elif broke == 0:
                    if info[nx][ny] == '0':
                        if visited[nx][ny][broke] > distance + 1 or visited[nx][ny][broke] == 0:
                            visited[nx][ny][broke] = distance + 1
                            q.append([nx, ny, distance + 1, broke])
                    else:
                        if visited[nx][ny][1] > distance + 1 or visited[nx][ny][1] == 0:
                            visited[nx][ny][1] = distance + 1
                            q.append([nx, ny, distance + 1, 1])


bfs()

if visited[-1][-1][0] == 0 and visited[-1][-1][1] == 0:
    print(-1)
elif visited[-1][-1][0] == 0:
    print(visited[-1][-1][1])
elif visited[-1][-1][1] == 0:
    print(visited[-1][-1][0])
else:
    print(min(visited[-1][-1][0], visited[-1][-1][1]))
