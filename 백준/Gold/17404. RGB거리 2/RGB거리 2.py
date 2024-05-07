import sys
input = sys.stdin.readline

n = int(input())
r = [0] * (n+1)
g = [0] * (n+1)
b = [0] * (n+1)
red = [[0, 0, 0] for _ in range(n+1)]
green = [[0, 0, 0] for _ in range(n+1)]
blue = [[0, 0, 0] for _ in range(n+1)]
for i in range(1, n+1):
    r[i], g[i], b[i] = map(int, input().split())
    if i == 1:
        red[i][0] = r[i]
        red[i][1] = 1000000
        red[i][2] = 1000000
        green[i][0] = 1000000
        green[i][1] = g[i]
        green[i][2] = 1000000
        blue[i][0] = 1000000
        blue[i][1] = 1000000
        blue[i][2] = b[i]
        continue
    red[i][0] = r[i] + min(red[i-1][1], red[i-1][2])
    red[i][1] = g[i] + min(red[i-1][0], red[i-1][2])
    red[i][2] = b[i] + min(red[i-1][0], red[i-1][1])
    green[i][0] = r[i] + min(green[i-1][1], green[i-1][2])
    green[i][1] = g[i] + min(green[i-1][0], green[i-1][2])
    green[i][2] = b[i] + min(green[i-1][0], green[i-1][1])
    blue[i][0] = r[i] + min(blue[i-1][1], blue[i-1][2])
    blue[i][1] = g[i] + min(blue[i-1][0], blue[i-1][2])
    blue[i][2] = b[i] + min(blue[i-1][0], blue[i-1][1])
print(min(red[n][1], red[n][2], green[n][0], green[n][2], blue[n][0], blue[n][1]))
