import sys
import heapq
input = sys.stdin.readline
sys.setrecursionlimit(10 ** 6)


def find(n):
    if sack[n] != n:
        sack[n] = find(sack[n])
        return sack[n]
    return n


def union(a, b):
    x = find(a)
    y = find(b)
    sack[y] = x


v, e = map(int, input().split())
q = []
for i in range(e):
    a, b, w = map(int, input().split())
    heapq.heappush(q, (w, a, b))
sack = [0]
weights = 0
for i in range(1, v + 1):
    sack.append(i)
count = 0
while count != v-1:
    w, a, b = heapq.heappop(q)
    if find(a) != find(b):
        weights += w
        union(a, b)
        count += 1
print(weights)
