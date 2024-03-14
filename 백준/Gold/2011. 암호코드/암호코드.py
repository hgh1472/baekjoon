import sys
import heapq

sys.setrecursionlimit(10 ** 6)

input = sys.stdin.readline

secret = list(input().rstrip())
dp = [0] * len(secret)
flag = 0
if secret[0] == '0':
    dp[0] = 0
    flag = 1
else:
    dp[0] = 1
for i in range(1, len(secret)):
    if i == 1:
        if 0 < (int(secret[i-1]) * 10 + int(secret[i])) <= 26:
            dp[i] += 1
        if secret[i] != '0':
            dp[i] += 1
        continue
    if secret[i] == '0':
        if int(secret[i-1]) >= 3 or int(secret[i-1]) == 0:
            flag = 1
        else:
            dp[i] = dp[i-2]
    else:
        if 0 < (int(secret[i-1]) * 10 + int(secret[i])) <= 26 and secret[i-1] != '0':
            dp[i] = dp[i-1] + dp[i-2]
        else:
            dp[i] = dp[i-1]

if flag != 1:
    print(dp[-1] % 1000000)
else:
    print(0)
