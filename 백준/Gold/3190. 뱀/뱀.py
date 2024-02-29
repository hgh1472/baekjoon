import sys
from collections import deque
input = sys.stdin.readline
apple = 2
d = ['r', 'd', 'l', 'u']


def di(dr, info):
    if info == 'D':
        return (dr + 1) % 4
    else:
        return (dr + 3) % 4


n = int(input())
board = [[0] * n for _ in range(n)]
k = int(input())
for i in range(k):
    a, b = map(int, input().split())
    board[a - 1][b - 1] = apple
l = int(input())
directions = []
for i in range(l):
    directions.append(list(input().split()))
    directions[i][0] = int(directions[i][0])
change = 0
direction = 0
sec = 0
snake = deque()
snake.append([0, 0])
while True:
    x, y = snake.pop()
    snake.append([x, y])
    if change < l and directions[change][0] == sec:
        direction = di(direction, directions[change][1])
        change += 1
    if direction == 0:
        y += 1
    elif direction == 1:
        x += 1
    elif direction == 2:
        y -= 1
    elif direction == 3:
        x -= 1
    sec += 1
    if not (0 <= x < n and 0 <= y < n) or [x, y] in snake:
        print(sec)
        break
    snake.append([x, y])
    if board[x][y] != apple:
        snake.popleft()
    board[x][y] = 0
