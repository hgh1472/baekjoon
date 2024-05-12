import sys
from collections import deque
input = sys.stdin.readline
dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]


def diffusion():
    temp = [[0] * c for _ in range(r)]
    while dust:
        x, y = dust.pop()
        cnt = 0
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if nx < 0 or nx >= r or ny < 0 or ny >= c or room[nx][ny] == -1:
                continue
            temp[nx][ny] += room[x][y] // 5
            cnt += room[x][y] // 5
        temp[x][y] -= cnt
    for i in range(r):
        for j in range(c):
            room[i][j] += temp[i][j]


def clean():
    up_x, up_y = cleaner[0][0] - 1, cleaner[0][1]
    room[up_x][up_y] = 0
    while up_x - 1 >= 0:
        up_x -= 1
        room[up_x + 1][up_y] = room[up_x][up_y]
    while up_y + 1 < c:
        up_y += 1
        room[up_x][up_y - 1] = room[up_x][up_y]
    while up_x + 1 <= cleaner[0][0]:
        up_x += 1
        room[up_x - 1][up_y] = room[up_x][up_y]
    room[up_x][up_y] = 0
    while up_y - 1 > 0:
        up_y -= 1
        room[up_x][up_y + 1] = room[up_x][up_y]
    room[up_x][up_y] = 0
    down_x, down_y = cleaner[1][0] + 1, cleaner[1][1]
    room[down_x][down_y] = 0
    while down_x + 1 < r:
        down_x += 1
        room[down_x - 1][down_y] = room[down_x][down_y]
    while down_y + 1 < c:
        down_y += 1
        room[down_x][down_y - 1] = room[down_x][down_y]
    while down_x - 1 >= cleaner[1][0]:
        down_x -= 1
        room[down_x + 1][down_y] = room[down_x][down_y]
    while down_y - 1 > 0:
        down_y -= 1
        room[down_x][down_y + 1] = room[down_x][down_y]
    room[down_x][down_y] = 0


r, c, t = map(int, input().split())
room = []
cleaner = []
dust = deque()
for i in range(r):
    room.append(list(map(int, input().split())))
    for j in range(c):
        if room[i][j] == -1:
            cleaner.append([i, j])
        if room[i][j] >= 5:
            dust.append([i, j])

for i in range(t):
    diffusion()
    clean()
    for j in range(r):
        for k in range(c):
            if room[j][k] >= 1:
                dust.append([j, k])

dust_cnt = 0
for i in range(r):
    for j in range(c):
        if room[i][j] >= 1:
            dust_cnt += room[i][j]

print(dust_cnt)
