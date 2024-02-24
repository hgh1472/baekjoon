import sys

read = sys.stdin.readline

for i in range(3):
    n = int(read())
    sum = 0
    for j in range(n):
        sum += int(read())
    if sum > 0:
        print("+")
    elif sum < 0:
        print("-")
    else:
        print("0")
