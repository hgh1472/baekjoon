import sys
import heapq
from collections import deque
input = sys.stdin.readline
sys.setrecursionlimit(10 ** 6)


n, m = map(int, input().split())
in_edges = [0] * (n+1)
out_edges = [[] for _ in range(n+1)]
visited = [False] * (n + 1)
for i in range(m):
    a, b = map(int, input().split())
    out_edges[a].append(b)
    in_edges[b] += 1

q = []
for i in range(1, n+1):
    heapq.heappush(q, (in_edges[i], i))

while q:
    count, num = heapq.heappop(q)
    if visited[num]:
        continue
    print(num, end=' ')
    visited[num] = True
    for i in out_edges[num]:
        in_edges[i] -= 1
        heapq.heappush(q, (in_edges[i], i))
