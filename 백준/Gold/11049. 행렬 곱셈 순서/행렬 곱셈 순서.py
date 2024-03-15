import sys


input = sys.stdin.readline

n = int(input())
size = []
for i in range(n):
    size.append(list(map(int, input().split())))
dp = [[0] * n for _ in range(n)]

for dif in range(1, n):
    for start in range(n):
        if start + dif == n:
            break
        dp[start][start+dif] = sys.maxsize
        for t in range(start, start+dif):
            dp[start][start + dif] = min(dp[start][start+dif], dp[start][t] + dp[t+1][start+dif] + size[start][0] * size[t][1] * size[start+dif][1])
print(dp[0][-1])
