import sys
input = sys.stdin.readline
sys.setrecursionlimit(10 ** 6)
east, west, south, north = 1,2,3,4


def change(x, y, direction, c):
    count = 0
    if direction == west:
        while 0 <= y - 1 < m and office[x][y - 1] <= 5:
            y -= 1
            if c == 7:
                info[x][y] += 1
                if info[x][y] == 1 and office[x][y] == 0:
                    count += 1
            if c == 0:
                info[x][y] -= 1
                if info[x][y] == 0 and office[x][y] == 0:
                    count -= 1
    if direction == east:
        while 0 <= y + 1 < m and office[x][y + 1] <= 5:
            y += 1
            if c == 7:
                info[x][y] += 1
                if info[x][y] == 1 and office[x][y] == 0:
                    count += 1
            if c == 0:
                info[x][y] -= 1
                if info[x][y] == 0 and office[x][y] == 0:
                    count -= 1
    if direction == south:
        while 0 <= x + 1 < n and office[x + 1][y] <= 5:
            x += 1
            if c == 7:
                info[x][y] += 1
                if info[x][y] == 1 and office[x][y] == 0:
                    count += 1
            if c == 0:
                info[x][y] -= 1
                if info[x][y] == 0 and office[x][y] == 0:
                    count -= 1
    if direction == north:
        while 0 <= x - 1 < n and office[x - 1][y] <= 5:
            x -= 1
            if c == 7:
                info[x][y] += 1
                if info[x][y] == 1 and office[x][y] == 0:
                    count += 1
            if c == 0:
                info[x][y] -= 1
                if info[x][y] == 0 and office[x][y] == 0:
                    count -= 1
    return count


def dfs(count, area):
    if count == cctv_count:
        global min_area
        min_area = min(min_area, n * m - cctv_count - wall - area)
        # print("area = ", area)
        # for i in info:
        #     print(i)
        # print("min_area = ", min_area)
        return
    cnt = 0
    x, y, num = cctv[count]
    if num == 5:
        cnt += change(x, y, east, 7)
        cnt += change(x, y, west, 7)
        cnt += change(x, y, south, 7)
        cnt += change(x, y, north, 7)
        dfs(count + 1, area + cnt)
        cnt += change(x, y, east, 0)
        cnt += change(x, y, west, 0)
        cnt += change(x, y, south, 0)
        cnt += change(x, y, north, 0)
    if num == 4:
        cnt += change(x, y, west, 7)
        cnt += change(x, y, north, 7)
        cnt += change(x, y, east, 7)
        dfs(count + 1, area + cnt)
        cnt += change(x, y, west, 0)
        cnt += change(x, y, south, 7)
        dfs(count + 1, area + cnt)
        cnt += change(x, y, north, 0)
        cnt += change(x, y, west, 7)
        dfs(count + 1, area + cnt)
        cnt += change(x, y, east, 0)
        cnt += change(x, y, north, 7)
        dfs(count + 1, area + cnt)
        cnt += change(x, y, south, 0)
        cnt += change(x, y, west, 0)
        cnt += change(x, y, north, 0)
    if num == 3:
        cnt += change(x, y, north, 7)
        cnt += change(x, y, east, 7)
        dfs(count + 1, area + cnt)
        cnt += change(x, y, north, 0)
        cnt += change(x, y, south, 7)
        dfs(count + 1, area + cnt)
        cnt += change(x, y, east, 0)
        cnt += change(x, y, west, 7)
        dfs(count + 1, area + cnt)
        cnt += change(x, y, south, 0)
        cnt += change(x, y, north, 7)
        dfs(count + 1, area + cnt)
        cnt += change(x, y, north, 0)
        cnt += change(x, y, west, 0)
    if num == 2:
        cnt += change(x, y, east, 7)
        cnt += change(x, y, west, 7)
        dfs(count + 1, area + cnt)
        cnt += change(x, y, north, 7)
        cnt += change(x, y, south, 7)
        cnt += change(x, y, west, 0)
        cnt += change(x, y, east, 0)
        dfs(count + 1, area + cnt)
        cnt += change(x, y, north, 0)
        cnt += change(x, y, south, 0)
    if num == 1:
        cnt += change(x, y, east, 7)
        dfs(count + 1, area + cnt)
        cnt += change(x, y, east, 0)
        cnt += change(x, y, south, 7)
        dfs(count + 1, area + cnt)
        cnt += change(x, y, south, 0)
        cnt += change(x, y, west, 7)
        dfs(count + 1, area + cnt)
        cnt += change(x, y, west, 0)
        cnt += change(x, y, north, 7)
        dfs(count + 1, area + cnt)
        cnt += change(x, y, north, 0)


n, m = map(int, input().split())
office = []
cctv = []
info = [[0] * m for _ in range(n)]
cctv_count = 0
wall = 0
for i in range(n):
    office.append(list(map(int, input().split())))
    for j in range(m):
        if 1 <= office[i][j] <= 5:
            cctv.append([i, j, office[i][j]])
            cctv_count += 1
        if office[i][j] == 6:
            wall += 1
min_area = n * m - cctv_count - wall

dfs(0, 0)
print(min_area)
