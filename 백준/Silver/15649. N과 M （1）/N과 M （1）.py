import sys
from collections import deque

read = sys.stdin.readline
def backtracking(nums, count):
    if count == m:
        for i in range(m):
            if i != m-1:
                print(nums[i], end=' ')
            else:
                print(nums[i])
        return
    for i in range(1, n+1):
        if nums.count(i) != 0:
            continue
        nums.append(i)
        backtracking(nums, count+1)
        nums.pop()



n, m = map(int, read().split())

nums = deque()
for i in range(1, n+1):
    nums.append(i)
    backtracking(nums, 1)
    nums.pop()