# 가장 긴 증가하는 수열
import sys

input = sys.stdin.readline

n = int(input())

dp = [[] for _ in range(n+1)]
dp[1] = [[1, 3]]
for i in range(2, n + 1):
    for a, b in dp[i-1]:
        if a == 2:
            a = 3
        elif a == 3:
            a = 2
        if b == 2:
            b = 3
        elif b == 3:
            b = 2
        dp[i].append([a, b])
    dp[i].append([1, 3])
    for a, b in dp[i- 1]:
        if a == 2:
            a = 1
        elif a == 1:
            a = 2
        if b == 2:
            b = 1
        elif b == 1:
            b = 2
        dp[i].append([a, b])

print(len(dp[n]))
for a, b in dp[n]:
    print(a, b)
