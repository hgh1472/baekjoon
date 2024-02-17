import sys

read = sys.stdin.readline

a = ['a', 'e', 'i', 'o', 'u']


def brute(count, i):
    if count == L:
        check = 0
        for char in a:
            if char in password:
                check += 1
        if check >= 1 and len(password) - check >= 2:
            print("".join(password))
    else:
        for j in range(i, C):
            password.append(alphas[j])
            brute(count + 1, j + 1)
            password.pop()


L, C = map(int, read().split())

alphas = list(read().split())
alphas.sort()

password = []

brute(0, 0)
