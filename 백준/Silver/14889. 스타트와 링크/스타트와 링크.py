import sys
from itertools import combinations

read = sys.stdin.readline

n = int(read())
players = []
for i in range(n):
    players.append(i)

s = []
diff = 40000
for i in range(n):
    s.append(list(map(int, read().split())))

length = len(list(combinations(players, n // 2))) // 2
for com in combinations(players, n // 2):
    length -= 1
    if length < 0:
        break
    a = 0
    b = 0
    for pair in combinations(com, 2):
        a += s[pair[0]][pair[1]]
        a += s[pair[1]][pair[0]]
    temp = []
    for i in range(n):
        if i in com:
            continue
        temp.append(i)
    for pair in combinations(temp, 2):
        b += s[pair[0]][pair[1]]
        b += s[pair[1]][pair[0]]
    if abs(a - b) < diff:
        diff = abs(a - b)

print(diff)