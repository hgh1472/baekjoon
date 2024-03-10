import sys

input = sys.stdin.readline

n = int(input())
nums = list(map(int, input().split()))
dp = [1] * n
idx = [-1] * n
for i in range(n):
    idx[i] = i
    for j in range(i):
        if nums[i] > nums[j]:
            dp[i] = max(dp[i], dp[j] + 1)
            if dp[i] == dp[j] + 1:
                idx[i] = j
longest_idx = dp.index(max(dp))
numbers = []
while True:
    numbers.append(nums[longest_idx])
    if longest_idx == idx[longest_idx]:
        break
    longest_idx = idx[longest_idx]

print(len(numbers))
print(' '.join(map(str, numbers[::-1])))
