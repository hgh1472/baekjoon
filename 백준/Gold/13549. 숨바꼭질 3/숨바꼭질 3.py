import sys
import heapq

read = sys.stdin.readline

n, k = map(int, read().split())


def sol():
    if n >= k:
        print(n - k)
        return
    visited = [sys.maxsize] * 100001
    l = []
    heapq.heappush(l, (0, n))
    while l:
        cost, pos = heapq.heappop(l)
        visited[pos] = cost
        if pos == k:
            print(cost)
            return
        if pos + 1 <= k and visited[pos + 1] > cost + 1:
            heapq.heappush(l, (cost + 1, pos+1))
        if 0 <= pos - 1 <= k + 1 and visited[pos - 1] > cost + 1:
            heapq.heappush(l, (cost + 1, pos-1))
        if 0 < pos * 2 <= k + 1 and visited[pos * 2] > cost:
            heapq.heappush(l, (cost, 2 * pos))

sol()
