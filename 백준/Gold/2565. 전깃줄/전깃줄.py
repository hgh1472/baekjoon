# 가장 긴 증가하는 수열
import sys

input = sys.stdin.readline


n = int(input())
lines = []
for i in range(n):
    a, b = map(int, input().split())
    lines.append([a, b])

lines.sort()
sorted_lines = []
for i in range(n):
    sorted_lines.append(lines[i][1])
# dp는 i번째까지 중에서 value[i]보다 작은 것 중 dp[i]값이 가장 큰 것을 선택한다.
#dp[i]는 dp[i]까지 가능한 최대 길이의 증가하는 수열의 길이이다.
inc_dp = [1] * n

# 가장 긴 증가하는 수열 구하기
for i in range(n):
    local = 0
    for j in range(i):
        if sorted_lines[j] < sorted_lines[i]:
            local = max(local, inc_dp[j])
        inc_dp[i] = local + 1
print(n - max(inc_dp))
