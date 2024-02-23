import sys

sys.setrecursionlimit(1000000)

read = sys.stdin.readline
tetris=[[(0,0),(0,1),(0,2),(0,3)],\
        [(0,0),(1,0),(2,0),(3,0)],\
        [(0,0),(1,0),(0,1),(1,1)],\
        [(0,0),(1,0),(2,0),(2,1)],\
        [(0,1),(1,1),(2,1),(2,0)],\
        [(0,0),(0,1),(1,1),(2,1)],\
        [(0,0),(0,1),(1,0),(2,0)],\
        [(0,0),(1,0),(1,1),(1,2)],\
        [(0,2),(1,1),(1,2),(1,0)],\
        [(0,0),(0,1),(0,2),(1,2)],\
        [(0,0),(1,0),(0,1),(0,2)],\
        [(0,0),(1,0),(1,1),(2,1)],\
        [(0,1),(1,1),(1,0),(2,0)],\
        [(1,0),(1,1),(0,1),(0,2)],\
        [(0,0),(0,1),(1,1),(1,2)],\
        [(0,1),(1,0),(1,1),(1,2)],\
        [(0,0),(0,1),(0,2),(1,1)],\
        [(0,0),(1,0),(1,1),(2,0)],\
        [(0,1),(1,1),(1,0),(2,1)]]

n, m = map(int, read().split())
paper = []


for i in range(n):
    paper.append(list(map(int, read().split())))

max_count = 0


def brute(x, y):
    if x >= n:
        return
    if y >= m:
        brute(x + 1, 0)
        return
    global max_count
    for block in tetris:
        local = 0
        for dx, dy in block:
                nx = x + dx
                ny = y + dy
                if 0 <= nx < n and 0 <= ny < m:
                    local += paper[nx][ny]
        if max_count < local:
            max_count = local
    brute(x, y + 1)

brute(0, 0)
print(max_count)