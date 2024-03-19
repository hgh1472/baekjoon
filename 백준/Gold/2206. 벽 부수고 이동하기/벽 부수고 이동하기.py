import sys
from collections import deque
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
input = sys.stdin.readline

n, m = map(int, input().split())

info = []
visited = [[[False, False] for _ in range(m)] for _ in range(n)]
for i in range(n):
    info.append(list(input().rstrip()))
visited[0][0][0] = visited[0][0][1] = True


def bfs():
    q = deque()
    q.append([0, 0, 1, 0])
    while q:
        x, y, distance, broke = q.popleft()
        if x == n-1 and y == m-1:
            print(distance)
            return
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0 <= nx < n and 0 <= ny < m:
                if broke == 1 and info[nx][ny] == '0':
                    if not visited[nx][ny][broke]:
                        visited[nx][ny][broke] = True
                        q.append([nx, ny, distance + 1, broke])
                elif broke == 0:
                    if info[nx][ny] == '0':
                        if not visited[nx][ny][broke]:
                            visited[nx][ny][broke] = True
                            q.append([nx, ny, distance + 1, broke])
                    else:
                        if not visited[nx][ny][1]:
                            visited[nx][ny][1] = True
                            q.append([nx, ny, distance + 1, 1])
    print(-1)


bfs()
