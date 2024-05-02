import sys
input = sys.stdin.readline

n = int(input())
schedule = []
for i in range(n):
    schedule.append(list(map(int, input().split())))

income = [0] * (n + 1)
max_income = 0
for i in range(n):
    income[i] = max(income[i-1], income[i])
    if i + schedule[i][0] <= n:
        income[i + schedule[i][0]] = max(income[i] + schedule[i][1], income[i + schedule[i][0]])

print(max(income[n-1], income[n]))
