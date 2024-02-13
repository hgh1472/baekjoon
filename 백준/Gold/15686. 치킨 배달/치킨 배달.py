import sys
from itertools import combinations

read = sys.stdin.readline


def distance(x, y, chickens):
    shortest = 10000
    for a, b in chickens:
        dis = abs(a - x) + abs(b - y)
        if shortest > dis:
            shortest = dis
    return shortest


n, m = map(int, read().split())
info = []
chicks = []
house = []
shortestChicken = 50 * 50 * 50
for i in range(n):
    info.append(list(map(int, read().split())))
    for j in range(n):
        if info[i][j] == 2:
            chicks.append([i, j])
        if info[i][j] == 1:
            house.append([i, j])

for case in combinations(chicks, m):
    dist = 0
    for x, y in house:
        dist += distance(x, y, case)
    if shortestChicken > dist:
        shortestChicken = dist

print(shortestChicken)

