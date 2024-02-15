import sys

read = sys.stdin.readline

n = int(read())
info = [0] * 26
for i in range(n):
    alphas = read().rstrip()
    for j in range(len(alphas)):
        info[ord(alphas[len(alphas) - 1 - j]) - ord('A')] += 10 ** j

info.sort(reverse=True)
result = 0
num = 9
for i in info:
    if i == 0:
        break
    result += num * i
    num -= 1

print(result)
