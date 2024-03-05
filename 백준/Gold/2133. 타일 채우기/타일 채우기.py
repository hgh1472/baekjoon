import sys

input = sys.stdin.readline

n = int(input())

dp = [0] * 31
dp[2] = 3
for i in range(4, n + 1, 2):
    local_sum = 0
    for j in range(2, i - 4 + 1, 2):
        local_sum += dp[j]
    dp[i] = dp[i - 2] * dp[2] + local_sum * 2 + 2

print(dp[n])
# 4
# 2 2
# dp[2 * i] = dp[2 * (i-1)] * dp[2] + 2 * sum(dp[i - j]) + 2
#
#
# dp[2]
# 2 -> 3
#
# dp[4]
# 2 2 -> 3 * 3
# 4 -> 2
#
# dp[6] = dp[4] * dp[2] + 2 * dp[2] + 2
# 2 2 2 -> 27
# 4 2 -> 2 * 3
# 2 4 -> 2 * 3
# 6 -> 2
#
# dp[8] = dp[6] * dp[2] + dp[4] * 2 + dp[2] * 2 + 2
# 2 2 2 2 -> dp[2] * dp[2] * dp[2] * dp[2] = 81 # dp[6]
# 4 2 2 -> 2 * 3 * 3 = 18 # dp[6]
# 2 4 2 -> 2 * 3 * 3 = 18 # dp[6]
# 6 2 -> 2 * 3 #dp[6]
# 2 2 4 -> 2 * 3 * 3 = 18 # #dp[4]
# 4 4 -> 4 #dp[4]
# 2 6 -> 2 * 3 #dp[2]
# + 2

# 화면 세종관 202