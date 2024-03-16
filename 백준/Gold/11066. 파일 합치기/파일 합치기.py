import sys


input = sys.stdin.readline

t = int(input())
for _ in range(t):
    k = int(input())
    files = list(map(int, input().split()))
    files_sum = [[0] * k for _ in range(k)]
    for i in range(k):
        for j in range(k):
            files_sum[i][j] = sum(files[i:j+1])

    dp = [[0] * k for _ in range(k)]
    for dif in range(1, k):
        for start in range(k):
            if start + dif == k:
                break
            dp[start][start+dif] = sys.maxsize
            for t in range(start, start+dif):
                dp[start][start+dif] = min(dp[start][start+dif],
                                           dp[start][t]+dp[t+1][start+dif] + files_sum[start][t] + files_sum[t+1][start+dif])
    print(dp[0][k-1])

