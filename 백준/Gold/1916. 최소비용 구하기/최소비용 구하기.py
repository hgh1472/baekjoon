import heapq
import sys

read = sys.stdin.readline
inf = sys.maxsize

n = int(read())
cost = [[inf] * (n+1) for _ in range(n+1)]
for i in range(1, n+1):
    cost[i][i] = 0
m = int(read())
for i in range(m):
    a, b, c = map(int, read().split())
    if cost[a][b] > c:
        cost[a][b] = c

start, des = map(int, read().split())

distance = [inf] * (n+1)
distance[start] = 0


def dijkstra():
    q = []
    visited = [False] * (n+1)
    heapq.heappush(q,[0, start])
    while q:
        dist, node = heapq.heappop(q)
        if visited[node]:
            continue
        for i in range(1, n+1):
            if cost[node][i] == sys.maxsize:
                continue
            if distance[i] > distance[node] + cost[node][i]:
                distance[i] = distance[node] + cost[node][i]
                visited[node] = False
                heapq.heappush(q, [distance[i], i])

dijkstra()
print(distance[des])
