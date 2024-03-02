import sys
input = sys.stdin.readline

n, k = map(int, input().split())
coins = []
values = [sys.maxsize] * (k + 1)
for i in range(n):
    coin = int(input())
    if coin > k:
        continue
    values[coin] = 1

for i in range(k+1):
    if values[i] == 1:
        continue
    for j in range(i // 2 + 1):
        values[i] = min(values[i], values[j] + values[i - j])
if values[k] == sys.maxsize:
    print(-1)
else:
    print(values[k])
