import sys
import heapq
input = sys.stdin.readline

N, M, X = map(int, input().split())
bridge = [[] for _ in range(N+1)]
for i in range(M):
    s, e, t = map(int, input().split())
    bridge[s].append([e, t])

result = [0] * (N+1)
for i in range(1, N+1):
    distance = [sys.maxsize] * (N+1)
    distance[i] = 0
    q = []
    heapq.heappush(q, (0, i))
    while q:
        d, a = heapq.heappop(q)
        for end, time in bridge[a]:
            if distance[a] + time < distance[end]:
                distance[end] = distance[a] + time
                heapq.heappush(q, (distance[end], end))
    if i == X:
        for j in range(1, N+1):
            result[j] += distance[j]
    else:
        result[i] += distance[X]
print(max(result))
