import sys

input = sys.stdin.readline
dx = [-1, 1, 0, 0]
dy = [0, 0, 1, -1]

n, m = map(int, input().split())
paper = []
visited = [[False] * m for _ in range(n)]
max_val = 0
for i in range(n):
    paper.append(list(map(int, input().split())))
    max_val = max(max(paper[i]), max_val)

max_sum = 0


def dfs(x, y, step, value):
    if step == 4:
        global max_sum
        max_sum = max(max_sum, value)
        return
    if (4 - step) * max_val + value <= max_sum:
        return

    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        if 0 <= nx < n and 0 <= ny < m and visited[nx][ny] is False:
            visited[nx][ny] = True
            dfs(nx, ny, step + 1, value + paper[nx][ny])
            if step == 2:
                dfs(x, y, step + 1, value + paper[nx][ny])
            visited[nx][ny] = False


for i in range(n):
    for j in range(m):
        visited[i][j] = True
        dfs(i, j, 1, paper[i][j])
        visited[i][j] = False
print(max_sum)