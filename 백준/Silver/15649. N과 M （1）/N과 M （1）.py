import sys
from collections import deque

read = sys.stdin.readline
def backtracking(nums, count):
    if count == m:
        print(' '.join(map(str, nums)))
        return
    for i in range(1, n+1):
        if i in nums:
            continue
        nums.append(i)
        backtracking(nums, count+1)
        nums.pop()


n, m = map(int, read().split())
nums = []
for i in range(1, n+1):
    nums.append(i)
    backtracking(nums, 1)
    nums.pop()
