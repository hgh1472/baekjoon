import sys
from collections import deque
sys.setrecursionlimit(10 ** 6)
dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

input = sys.stdin.readline

n = int(input())
forest = []
for i in range(n):
    forest.append(list(map(int, input().split())))
visited = [[0] * n for _ in range(n)]


def dfs(x, y):
    count = 1
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        if 0 <= nx < n and 0 <= ny < n:
            if forest[nx][ny] > forest[x][y]:
                if visited[nx][ny] == 0:
                    count = max(count, dfs(nx, ny) + 1)
                else:
                    count = max(count, visited[nx][ny] + 1)
    visited[x][y] = count
    return count


count = 0
for i in range(n):
    for j in range(n):
        if visited[i][j] == 0:
            count = max(count, dfs(i, j))
        else:
            count = max(count, visited[i][j])

print(count)
