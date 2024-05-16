import sys
input = sys.stdin.readline

a = input().rstrip()
b = input().rstrip()
len_a = len(a)
len_b = len(b)

dp = [0] * (len_a + 1)
tmp = [0] * (len_a + 1)
longest = 0

for i in range(len_b):
    tmp = dp.copy()
    for j in range(len_a):
        if b[i] == a[j]:
            dp[j + 1] = tmp[j] + 1
        else:
            dp[j + 1] = 0
    longest = max(longest, max(dp))

print(longest)
