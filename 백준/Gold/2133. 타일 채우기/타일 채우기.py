import sys

input = sys.stdin.readline

n = int(input())

dp = [0] * (n + 1)
if n >= 2:
    dp[2] = 3
for i in range(4, n + 1, 2):
    local_sum = 0
    for j in range(2, i - 4 + 1, 2):
        local_sum += dp[j]
    dp[i] = dp[i - 2] * dp[2] + local_sum * 2 + 2

print(dp[n])
