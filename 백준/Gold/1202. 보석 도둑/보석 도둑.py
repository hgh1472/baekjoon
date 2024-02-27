import sys
import heapq
read = sys.stdin.readline

n, k = map(int, read().split())
jewel = []
bags = []
tmp = []

for i in range(n):
    heapq.heappush(jewel, list(map(int, read().split())))
for i in range(k):
    bags.append(int(read()))

bags.sort()
result = 0
while bags:
    bag = heapq.heappop(bags)
    while jewel and jewel[0][0] <= bag:
        heapq.heappush(tmp, -jewel[0][1])
        heapq.heappop(jewel)
    if tmp:
        result -= heapq.heappop(tmp)

print(result)
