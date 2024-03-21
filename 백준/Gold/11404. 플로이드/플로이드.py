import sys
import heapq
input = sys.stdin.readline
sys.setrecursionlimit(10 ** 6)

n = int(input())
m = int(input())
lines = [[sys.maxsize] * (n+1) for _ in range(n+1)]

for i in range(1, n+1):
    lines[i][i] = 0

for i in range(m):
    a, b, c = map(int, input().split())
    lines[a][b] = min(lines[a][b], c)

for middle in range(1, n+1):
    for start in range(1, n+1):
        for end in range(1, n+1):
            lines[start][end] = min(lines[start][end], lines[start][middle] + lines[middle][end])

for i in range(1, n+1):
    for j in range(1, n+1):
        if j != n:
            if lines[i][j] != sys.maxsize:
                print(lines[i][j], end=' ')
            else:
                print(0, end=' ')
        else:
            if lines[i][j] != sys.maxsize:
                print(lines[i][j])
            else:
                print(0)

