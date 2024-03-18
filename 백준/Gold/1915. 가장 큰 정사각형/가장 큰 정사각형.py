import sys

input = sys.stdin.readline

n, m = map(int, input().split())
info = []
for i in range(n):
    info.append(list(map(int, input().rstrip())))


def sol():
    max_size = 0
    for i in range(n):
        for j in range(m):
            if 0 <= i-1 and 0 <= j-1 and info[i][j] != 0:
                info[i][j] += min(info[i-1][j], info[i][j-1], info[i-1][j-1])
            max_size = max(max_size, info[i][j])
    area = max_size * max_size

    return area


print(sol())

