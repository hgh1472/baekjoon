import sys
input = sys.stdin.readline

n = int(input())
income = [0] * (n + 1)
schedule = []
for i in range(n):
    day, pay = map(int, input().split())
    income[i] = max(income[i-1], income[i])
    if i + day <= n:
        income[i + day] = max(income[i] + pay, income[i + day])

print(max(income[n-1], income[n]))
