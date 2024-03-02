import sys
input = sys.stdin.readline

n, k = map(int, input().split())
coins = []
values = [sys.maxsize] * (k + 1)
for i in range(n):
    coin = int(input())
    if coin > k:
        continue
    coins.append(coin)
values[0] = 0
for coin in coins:
    for i in range(coin, k+1):
        values[i] = min(values[i - coin] + 1, values[i])

if values[k] == sys.maxsize:
    print(-1)
else:
    print(values[k])
