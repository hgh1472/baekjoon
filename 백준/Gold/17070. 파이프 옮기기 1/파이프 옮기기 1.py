import sys

input = sys.stdin.readline

dx = [0, 1, 1]
dy = [2, 2, 1]

n = int(input())
house = []
for i in range(n):
    house.append(list(map(int, input().split())))

dp_h = [[0] * n for _ in range(n)]
dp_v = [[0] * n for _ in range(n)]
dp_d = [[0] * n for _ in range(n)]
for i in range(1, n):
    if house[0][i] == 1:
        break
    dp_h[0][i] = 1

for i in range(1, n):
    for j in range(1, n):
        if 0 <= i-1 < n and 0 <= j-1 < n:
            if house[i-1][j-1] == 0 and house[i-1][j] == 0 and house[i][j-1] == 0 and house[i][j] == 0:
                dp_d[i][j] += dp_h[i-1][j-1] + dp_v[i-1][j-1] + dp_d[i-1][j-1]

        # 가로
        if 0 <= j-1 < n:
            if house[i][j] == 0 and house[i][j-1] == 0:
                dp_h[i][j] += dp_h[i][j-1] + dp_d[i][j-1]

        # 세로
        if 0 <= i-1 < n:
            if house[i][j] == 0 and house[i-1][j] == 0:
                dp_v[i][j] += dp_v[i-1][j] + dp_d[i-1][j]

print(dp_h[-1][-1] + dp_v[-1][-1] + dp_d[-1][-1])
# print(dp_h[-1][-1])
# print(dp_v[-1][-1])
# print(dp_d[-1][-1])
