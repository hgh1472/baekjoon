import sys
from collections import deque
input = sys.stdin.readline


def d(number):
    return number * 2 % 10000


def s(number):
    number = number - 1
    if number == -1:
        return 9999
    return number


def l(number):
    d1 = number // 1000
    d2 = number % 1000 // 100
    d3 = number % 100 // 10
    d4 = number % 10
    return d2 * 1000 + d3 * 100 + d4 * 10 + d1


def r(number):
    d1 = number // 1000
    d2 = number % 1000 // 100
    d3 = number % 100 // 10
    d4 = number % 10
    return d4 * 1000 + d1 * 100 + d2 * 10 + d3


def calculate(number, op):
    if op == 1:
        return d(number)
    if op == 2:
        return s(number)
    if op == 3:
        return l(number)
    if op == 4:
        return r(number)


def change(number, coms):
    for c in coms:
        number = calculate(number, int(c))
    return number


def bfs(a, b):
    cmds = deque()
    duplicated = [False] * 10000
    for i in range(1, 5):
        tmp = calculate(a, i)
        if tmp == b:
            return str(i)
        if not duplicated[tmp]:
            duplicated[tmp] = True
            cmds.append(str(i))
    while True:
        cmd = cmds.popleft()
        num = change(a, cmd)
        for i in range(1, 5):
            tmp = calculate(num, i)
            if tmp != b:
                if not duplicated[tmp]:
                    duplicated[tmp] = True
                    cmds.append(cmd + str(i))
            else:
                return cmd + str(i)


def convert(op):
    if op == '1':
        print('D', end='')
    elif op == '2':
        print('S', end='')
    elif op == '3':
        print('L', end='')
    elif op == '4':
        print('R', end='')


t = int(input())
for _ in range(t):
    a, b = map(int, input().split())
    commands = bfs(a, b)
    for com in commands:
        convert(com)
    print()
