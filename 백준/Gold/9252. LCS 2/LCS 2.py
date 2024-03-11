import sys

input = sys.stdin.readline

first = input().strip()
second = input().strip()
length_first = len(first)
length_second = len(second)
dp = [[0] * (length_second + 1) for _ in range(length_first + 1)]

for i in range(length_first):
    for j in range(length_second):
        if first[i] == second[j]:
            dp[i + 1][j + 1] = dp[i][j] + 1
            # dp[i+1][j+1] = max(dp[i][j+1], dp[i+1][j]) + 1을 하게 되면 중복되는 경우 발생
            # 비교되는 문자의 앞글자까지와 비교해야 한다.
        else:
            dp[i + 1][j + 1] = max(dp[i][j + 1], dp[i + 1][j])

x = length_first
y = length_second
string = []
while dp[x][y] != 0:
    if x-1 > 0 and dp[x-1][y] == dp[x][y]:
        x -= 1
    elif y-1 > 0 and dp[x][y-1] == dp[x][y]:
        y -= 1
    else:
        y -= 1
        string.append(second[y])
string.reverse()
print(dp[-1][-1])
print(''.join(string))
