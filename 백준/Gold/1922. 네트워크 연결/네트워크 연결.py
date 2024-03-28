import sys
input = sys.stdin.readline


def find(num):
    if sack[num] != num:
        sack[num] = find(sack[num])
    return sack[num]


def union(num1, num2):
    a = find(num1)
    b = find(num2)

    sack[b] = a


n = int(input())
m = int(input())

lines = []

for _ in range(m):
    a, b, c = map(int, input().split())
    lines.append((c, a, b))

sack = [i for i in range(n+1)]

lines.sort()

total = 0
count = 0
for weight, a, b in lines:
    if find(a) != find(b):
        union(a, b)
        total += weight
        count += 1
    if count == n-1:
        break

print(total)
