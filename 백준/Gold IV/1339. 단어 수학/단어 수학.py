import sys

read = sys.stdin.readline

n = int(read())
info = {10 ** 0: [], 10 ** 1: [], 10 ** 2: [], 10 ** 3: [], 10 ** 4: [],
        10 ** 5: [], 10 ** 6: [], 10 ** 7: []}

for i in range(n):
    alphas = read().rstrip()
    for j in range(len(alphas)-1, -1, -1):
        info[10 ** (len(alphas) - j - 1)].append(alphas[j])

dic = {}
for i in range(26):
    dic[chr(ord('A') + i)] = 0
num = [0] * 26
for i in range(len(info)):
    for alpha in info[10 ** i]:
        dic[alpha] += 10 ** i

weight = 9

sorted_dic = sorted(dic.items(), key = lambda x:x[1], reverse=True)
for alpha in sorted_dic:
    if alpha[1] != 0:
        num[ord(alpha[0]) - ord('A')] = weight
        weight -= 1

result = 0
for i in range(len(info)):
    for alpha in info[10 ** i]:
        result += num[ord(alpha) - ord('A')] * 10 ** i

print(result)
