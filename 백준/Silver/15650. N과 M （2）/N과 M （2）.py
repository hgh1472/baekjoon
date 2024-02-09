import sys

read = sys.stdin.readline

n, m = map(int, read().split())

nums = []
visited = [0] * (n + 1)


def backtracking(num):
    if len(nums) == m:
        print(" ".join(map(str, nums)))
        return
    for i in range(1, n+1):
        if i <= num or visited[i] == 1:
            continue
        nums.append(i)
        visited[i] = 1
        backtracking(i)
        nums.pop()
        visited[i] = 0


for i in range(1, n+1):
    nums.append(i)
    visited[i] = 1
    backtracking(i)
    nums.pop()
    visited[i] = 0