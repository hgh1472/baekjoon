import sys
input = sys.stdin.readline

n = int(input())

numbers = []
for _ in range(n):
    numbers.append(int(input()))

orders = [1] * n
for i in range(1, n):
    for j in range(i):
        if numbers[i] > numbers[j]:
            orders[i] = max(orders[i], orders[j] + 1)

print(n - max(orders))
