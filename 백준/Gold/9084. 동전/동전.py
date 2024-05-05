import sys
input = sys.stdin.readline

t = int(input())
for _ in range(t):
    n = int(input())
    coins = list(map(int, input().split()))
    money = int(input())
    dp = [0] * (money + 1)
    for i in range(n):
        for j in range(1, money + 1):
            if j >= coins[i]:
                if j % coins[i] == 0 and j // coins[i] == 1:
                    dp[j] = dp[j] + 1
                else:
                    dp[j] = dp[j] + dp[j - coins[i]]
            else:
                dp[j] = dp[j]
    print(dp[money])
