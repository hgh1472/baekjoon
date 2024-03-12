import sys

input = sys.stdin.readline

# 팰린드롬은 앞뒤가 대칭인 것을 말한다.
n = int(input())
numbers = list(map(int, input().split()))
m = int(input())

same_range = []
start, end = 0, 0
for i in range(1, n):
    if numbers[start] == numbers[i]:
        end = i
    else:
        if end != start:
            same_range.append([start, end])
        start = i
if end != start:
    same_range.append([start, end])
# dp의 첫번째 인덱스는 numbers에서 기준이되는 숫자의 인덱스
# dp의 두번째 인덱스는 기준 인덱스에서 멀어지는 범위를 말한다. 즉, dp[3][2]는 2 1 3 1 2이다.
# numbers의 인덱스 중앙값을 기준으로 최대 + n // 2, - n // 2까지의 인덱스를 가질 수 있다.
max_dis = n // 2
dp = [[0] * (max_dis + 1) for _ in range(n)]
for i in range(n):
    # 자기 자신만 가지는 수열은 팰린드롬이다.
    dp[i][0] = 1
    for j in range(max_dis + 1):
        if i + j >= n or i - j < 0:
            break

        if numbers[i + j] == numbers[i - j]:
            # s, e 범위가 짝수일 때 모든 숫자가 같다면 팰린드롬이다. 따라서 수열이 모두 같은숫자라면 2로 저장한다.
            if numbers[i + j] == numbers[i]:
                dp[i][j] = 2
            else:
                dp[i][j] = 1
        else:  # 범위를 넓혀갈 때 팰린드롬이 아닌 수가 나오면 그 수열을 포함한 수열은 팰린드롬이 아니다.
            break

for i in range(m):
    s, e = map(int, input().split())
    s -= 1
    e -= 1
    if (e - s) % 2 == 1:
        e -= 1
        std = (s + e) // 2
        dis = e - std
        flag = dp[std][dis]
        if flag != 2:
            print(0)
        else:
            if numbers[std] == numbers[e + 1]:
                print(1)
            else:
                print(0)
    else:
        std = (s + e) // 2
        dis = e - std
        if dp[std][dis] != 0:
            print(1)
        else:
            print(0)
