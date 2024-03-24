import sys
import heapq
input = sys.stdin.readline


def dijkstra(start, bridges, distance):
    q = []
    heapq.heappush(q, (0, start))
    distance[start] = 0
    while q:
        dis, node = heapq.heappop(q)
        if distance[node] < dis:
            continue
        for end, time in bridges[node]:
            if distance[end] > dis + time:
                distance[end] = dis + time
                heapq.heappush(q, (distance[end], end))


N, M, X = map(int, input().split())
bridge_to_x = [[] for _ in range(N+1)]
bridge_from_x = [[] for _ in range(N+1)]
distance_to_x = [sys.maxsize] * (N+1)
distance_from_x = [sys.maxsize] * (N+1)

for i in range(M):
    s, e, t = map(int, input().split())
    bridge_to_x[s].append([e, t])
    bridge_from_x[e].append([s, t])

dijkstra(X, bridge_to_x, distance_to_x)
dijkstra(X, bridge_from_x, distance_from_x)

result = [distance_to_x[i] + distance_from_x[i] for i in range(1, N+1)]
print(max(result))
