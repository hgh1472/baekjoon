import sys
from collections import deque

read = sys.stdin.readline


def commands(command, nums):
    reverse = False
    for com in command:
        if com == 'D':
            if len(nums) == 0:
                print("error")
                return
            else:
                if not reverse:
                    nums.popleft()
                if reverse:
                    nums.pop()
        if com == 'R':
            if reverse:
                reverse = False
            elif not reverse:
                reverse = True
    if not reverse:
        print("[", end='')
        for i in range(len(nums)):
            print(nums[i], end='')
            if i != len(nums) - 1:
                print(",", end='')
        print("]")
    if reverse:
        print("[", end='')
        for i in range(len(nums) - 1, -1, -1):
            print(nums[i], end='')
            if i != 0:
                print(",", end='')
        print("]")


T = int(read())
for i in range(T):
    command = list(read().rstrip())
    n = int(read())
    string = read().rstrip()
    nums = deque()
    num = 0
    if n != 0:
        for char in string:
            if '0' <= char <= '9':
                num *= 10
                num += ord(char) - ord('0')
            elif char == '[':
                continue
            else:
                nums.append(num)
                num = 0
    commands(command, nums)
