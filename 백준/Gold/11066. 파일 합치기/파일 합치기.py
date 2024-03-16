import sys


input = sys.stdin.readline

t = int(input())
for _ in range(t):
    k = int(input())
    files = list(map(int, input().split()))
    files_sum = [files[0]]
    for i in range(1, k):
        files_sum.append(files_sum[i-1] + files[i])


    dp = [[0] * k for _ in range(k)]
    for dif in range(1, k):
        for start in range(k):
            if start + dif == k:
                break
            dp[start][start+dif] = sys.maxsize
            for t in range(start, start+dif):
                if start != 0:
                    temp = files_sum[start + dif] - files_sum[start - 1]
                else:
                    temp = files_sum[start+dif]
                dp[start][start+dif] = min(dp[start][start+dif],
                                           dp[start][t]+dp[t+1][start+dif] + temp)
    print(dp[0][k-1])

