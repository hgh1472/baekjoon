# 가장 긴 증가하는 수열
import sys

input = sys.stdin.readline

n = int(input())


def solve(n, x, y):
    if n == 1:
        print(x, y)
        return

    solve(n - 1, x, 6 - x - y)
    print(x, y)
    solve(n-1,6 - x - y, y)


dp = [0] * (n + 1)
dp[1] = 1
for i in range(2, n + 1):
    dp[i] = 2*dp[i - 1] + 1
print(dp[n])
solve(n, 1, 3)
