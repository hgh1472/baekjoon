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
    front = number % 1000
    back = number // 1000
    return front * 10 + back


def r(number):
    front = number % 10
    back = number // 10
    return front * 1000 + back


def calculate(number, op):
    if op == 1:
        return d(number)
    if op == 2:
        return s(number)
    if op == 3:
        return l(number)
    if op == 4:
        return r(number)


def bfs(a, b):
    cmds = deque()
    duplicated = [False] * 10000
    cmds.append([a,[]])
    while True:
        num, cmd = cmds.popleft()
        for i in range(1, 5):
            tmp = 0
            if i == 1:
                tmp = num * 2 % 10000
            if i == 2:
                tmp = num - 1
                if tmp == -1:
                    tmp = 9999
            if i == 3:
                tmp = (num % 1000) * 10 + num // 1000
            if i == 4:
                tmp = (num % 10) * 1000 + num // 10
            if tmp != b:
                if not duplicated[tmp]:
                    duplicated[tmp] = True
                    cmds.append([tmp, cmd + [i]])
            else:
                return cmd + [i]


def convert(op):
    if op == 1:
        print('D', end='')
    elif op == 2:
        print('S', end='')
    elif op == 3:
        print('L', end='')
    elif op == 4:
        print('R', end='')


t = int(input())
for _ in range(t):
    a, b = map(int, input().split())
    commands = bfs(a, b)
    for com in commands:
        convert(com)
    print()
