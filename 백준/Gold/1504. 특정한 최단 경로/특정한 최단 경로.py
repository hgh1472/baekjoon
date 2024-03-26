import sys
from collections import deque
import heapq
input = sys.stdin.readline


def dijkstra(start):
    visited = [sys.maxsize] * (N+1)
    visited[start] = 0
    q = []
    heapq.heappush(q, [0, start])
    while q:
        dist, node = heapq.heappop(q)
        if dist > visited[node]:
            continue

        for dst, dis in graph[node]:
            cost = visited[node] + dis
            if visited[dst] > cost:
                visited[dst] = cost
                heapq.heappush(q, [visited[dst], dst])
    return visited

N, E = map(int, input().split())
graph = [[] for _ in range(N+1)]
for i in range(E):
    a, b, c = map(int, input().split())
    graph[a].append([b, c])
    graph[b].append([a, c])

v1, v2 = map(int, input().split())

distance_1 = dijkstra(1)
distance_v1 = dijkstra(v1)
distance_v2 = dijkstra(v2)

distance = 0
distance += min(distance_1[v1] + distance_v2[N], distance_1[v2] + distance_v1[N])
distance += distance_v1[v2]
if distance >= sys.maxsize:
    print(-1)
else:
    print(distance)
