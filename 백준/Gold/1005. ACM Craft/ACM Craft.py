import sys
import heapq

sys.setrecursionlimit(10 ** 6)

def sol(num):
    visited[num] = 1
    if len(cond_in[num]) == 0 or dp[num] != d[num]:
        return dp[num]
    time = 0
    for org in cond_in[num]:
        if visited[org] == 1:
            time = max(time, dp[org] + d[num])
        else:
            time = max(time, sol(org) + d[num])
    dp[num] = time
    return dp[num]


input = sys.stdin.readline

t = int(input())
for i in range(t):
    n, k = map(int, input().split())
    visited = [0] * n
    d = list(map(int, input().split()))
    dp = []
    for j in range(n):
        dp.append(d[j])
    cond_in = [[] for _ in range(n)]
    for j in range(k):
        x, y = map(int, input().split())
        x -= 1
        y -= 1
        cond_in[y].append(x)
    w = int(input())
    print(sol(w-1))
