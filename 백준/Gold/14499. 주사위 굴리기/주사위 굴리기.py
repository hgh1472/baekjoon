import sys
input = sys.stdin.readline


def east_rotate():
    global y
    if y + 1 >= m:
        return
    dice[up], dice[down], dice[left], dice[right] = dice[left], dice[right], dice[down], dice[up]
    if info[x][y+1] == 0:
        info[x][y+1] = dice[down]
    else:
        dice[down] = info[x][y+1]
        info[x][y+1] = 0
    print(dice[up])
    y += 1
    return


def west_rotate():
    global y
    if y - 1 < 0:
        return
    dice[up], dice[down], dice[left], dice[right] = dice[right], dice[left], dice[up], dice[down]
    if info[x][y - 1] == 0:
        info[x][y - 1] = dice[down]
    else:
        dice[down] = info[x][y - 1]
        info[x][y - 1] = 0
    print(dice[up])
    y -= 1
    return


def north_rotate():
    global x
    if x - 1 < 0:
        return
    dice[up], dice[down], dice[front], dice[rear] = dice[front], dice[rear], dice[down], dice[up]
    if info[x - 1][y] == 0:
        info[x - 1][y] = dice[down]
    else:
        dice[down] = info[x - 1][y]
        info[x - 1][y] = 0
    print(dice[up])
    x -= 1
    return


def south_rotate():
    global x
    if x + 1 >= n:
        return
    dice[up], dice[down], dice[front], dice[rear] = dice[rear], dice[front], dice[up], dice[down]
    if info[x + 1][y] == 0:
        info[x + 1][y] = dice[down]
    else:
        dice[down] = info[x + 1][y]
        info[x + 1][y] = 0
    print(dice[up])
    x += 1
    return


n, m, x, y, k = map(int, input().split())
dice = [0, 0, 0, 0, 0, 0, 0]
up = 1
down = 6
front = 5
rear = 2
left = 4
right = 3
info = []
for _ in range(n):
    info.append(list(map(int, input().split())))

command = list(map(int, input().split()))

for c in command:
    if c == 1:
        east_rotate()
    if c == 2:
        west_rotate()
    if c == 3:
        north_rotate()
    if c == 4:
        south_rotate()
