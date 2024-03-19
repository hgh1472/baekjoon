import sys
from collections import deque
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
input = sys.stdin.readline

def bfs():
    n, m = map(int, input().split())

    info = []
    for i in range(n):
        info.append(list(input().rstrip()))
    visited = [[[False, False] for _ in range(m)] for _ in range(n)]
    visited[0][0][0] = visited[0][0][1] = True
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
                if info[nx][ny] == '1':
                    if broke == 1 or visited[nx][ny][1]:
                        continue
                    visited[nx][ny][1] = True
                    q.append([nx, ny, distance + 1, 1])
                else:
                    if visited[nx][ny][broke]:
                        continue
                    visited[nx][ny][broke] = True
                    q.append([nx, ny, distance + 1, broke])
    print(-1)


bfs()
