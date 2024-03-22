import sys
input = sys.stdin.readline
sys.setrecursionlimit(10 ** 6)


n, m = map(int, input().split())
in_edges = [[] for _ in range(n+1)]
visited = [False] * (n + 1)
for i in range(m):
    a, b = map(int, input().split())
    in_edges[b].append(a)

lines = []


def dfs(num):
    if visited[num]:
        return
    visited[num] = True
    for i in in_edges[num]:
        dfs(i)
    lines.append(str(num))


for i in range(1, n+1):
    dfs(i)
print(" ".join(lines))
