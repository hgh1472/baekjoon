import sys
from collections import deque
input = sys.stdin.readline
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]


def bfs():
    water_q = deque()
    pos_q = deque()
    for x, y in water:
        water_q.append((x, y, 0))
    pos_q.append((pos[0], pos[1], 0))
    info[pos[0]][pos[1]] = 0
    day = 0
    while len(pos_q) != 0:
        day += 1
        while water_q and water_q[0][2] == day - 1:
            x, y, z = water_q.popleft()
            for k in range(4):
                nx = x + dx[k]
                ny = y + dy[k]
                if 0 <= nx < r and 0 <= ny < c:
                    if info[nx][ny] == '.':
                        info[nx][ny] = '*'
                        water_q.append((nx, ny, day))
        while pos_q and pos_q[0][2] == day - 1:
            x, y, z = pos_q.popleft()
            for k in range(4):
                nx = x + dx[k]
                ny = y + dy[k]
                if 0 <= nx < r and 0 <= ny < c:
                    if info[nx][ny] == '.':
                        info[nx][ny] = day
                        pos_q.append((nx, ny, day))
                    elif info[nx][ny] == 'D':
                        print(day)
                        return

    print("KAKTUS")


r, c = map(int, input().split())

info = []
water = []
pos = ()

for i in range(r):
    info.append(list(input().rstrip()))
    for j in range(c):
        if info[i][j] == '*':
            water.append((i, j))
        if info[i][j] == 'S':
            pos = (i, j)


bfs()

