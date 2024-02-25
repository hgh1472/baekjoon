import sys

read = sys.stdin.readline
dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

n, m = map(int, read().split())
room = []
r, c, d = map(int, read().split())

for i in range(n):
    room.append(list(map(int, read().split())))
count = 0


def clean(x, y, p):
    if room[x][y] == 0:
        room[x][y] = 2
        global count
        count += 1
    for i in range(1, 5):
        k = p - i
        if k < 0:
            k += 4
        nx = x + dx[k]
        ny = y + dy[k]
        if 0 <= nx < n and 0 <= ny < m and room[nx][ny] == 0:
            return clean(nx, ny, k)
    nx = x - dx[p]
    ny = y - dy[p]
    if 0 <= nx < n and 0 <= ny < m and room[nx][ny] != 1:
        return clean(nx, ny, p)
    else:
        return


clean(r, c, d)
print(count)
