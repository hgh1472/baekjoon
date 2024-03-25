import sys
from collections import deque
input = sys.stdin.readline


def bfs(start, g):
    visited[start] = g
    q = deque()
    q.append(start)
    while q:
        node = q.popleft()
        for c in edge[node]:
            if visited[c] == -1:
                visited[c] = (visited[node] + 1) % 2
                q.append(c)
            else:
                if visited[c] == visited[node]:
                    return False
    return True


k = int(input())
for _ in range(k):
    v, e = map(int, input().split())
    edge = [[] for _ in range(v+1)]
    for i in range(e):
        a, b = map(int, input().split())
        edge[a].append(b)
        edge[b].append(a)
    visited = [-1] * (v+1)
    divisible = True
    for i in range(1, v+1):
        if visited[i] == -1 and len(edge[i]) != 0:
            divisible = bfs(i, visited[i])
        if not divisible:
           break
    if divisible:
        print("YES")
    else:
        print("NO")
