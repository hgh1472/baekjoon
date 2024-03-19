import sys
from collections import deque

def bfs():
    n, m = map(int, sys.stdin.readline().split())
    direction = [[1,0],[-1,0],[0,-1],[0,1]]
    info = []
    for i in range(n):
        info.append(list(sys.stdin.readline().rstrip()))

    visited = [[[False, False] for _ in range(m)] for _ in range(n)]
    visited[0][0][0] = visited[0][0][1] = True

    q = deque()
    q.append([0, 0, 1, 0])
    while q:
        x, y, distance, broke = q.popleft()
        if x == n-1 and y == m-1:
            print(distance)
            return
        for dx, dy in direction:
            nx = x + dx
            ny = y + dy
            if nx < 0 or nx == n or ny < 0 or ny == m:
                continue
            if visited[nx][ny][broke]:
                continue
            if info[nx][ny] == '1':
                if broke == 1:
                    continue
                q.append([nx, ny, distance + 1, broke + 1])
                visited[nx][ny][1] = True
            else:
                q.append([nx, ny, distance + 1, broke])
                visited[nx][ny][broke] = True
    print(-1)


bfs()
