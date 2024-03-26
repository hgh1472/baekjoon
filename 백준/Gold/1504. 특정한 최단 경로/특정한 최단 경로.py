import sys
from collections import deque
input = sys.stdin.readline


def dijkstra(start):
    visited[start][start] = 0
    q = deque()
    q.append([0, start])
    while q:
        dist, node = q.popleft()
        if dist > visited[start][node]:
            continue
        for dst, dis in graph[node]:
            if visited[start][dst] > visited[start][node] + dis:
                visited[start][dst] = visited[start][node] + dis
                q.append([visited[start][dst], dst])


N, E = map(int, input().split())
graph = [[] for _ in range(N+1)]
visited = [[sys.maxsize] * (N+1) for _ in range(N+1)]
for i in range(E):
    a, b, c = map(int, input().split())
    graph[a].append([b, c])
    graph[b].append([a, c])

v1, v2 = map(int, input().split())

dijkstra(1)
dijkstra(v1)
dijkstra(v2)

distance = 0
distance += min(visited[1][v1] + visited[v2][N], visited[1][v2] + visited[v1][N])
distance += visited[v1][v2]
if distance >= sys.maxsize:
    print(-1)
else:
    print(distance)
