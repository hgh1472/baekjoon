import sys
input = sys.stdin.readline
sys.setrecursionlimit(10 ** 6)

n = int(input())
lines = [[] for _ in range(n+1)]
leaf = [True] * (n+1)
for _ in range(n-1):
    p, c, w = map(int, input().split())
    lines[p].append([c, w])
    lines[c].append([p, w])
    leaf[p] = False


def bfs(start, wei):
    visited[start] = True
    for node, weight in lines[start]:
        if not visited[node]:
            weights[node] = weight + wei
            bfs(node, weight + wei)


diameter = 0
weights = [0] * (n+1)
visited = [False] * (n+1)
bfs(1, 0)

max_distance = 0
idx = 0
for i in range(1, n+1):
    if not leaf[i]:
        continue
    if max_distance < weights[i]:
        max_distance = weights[i]
        idx = i

visited = [False] * (n+1)
bfs(idx, 0)
print(max(weights))
