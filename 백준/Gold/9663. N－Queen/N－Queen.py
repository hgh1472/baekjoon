import sys

read = sys.stdin.readline
sys.setrecursionlimit(100000)
def check(r):
    for i in range(r):
        if row[i] == row[r] or abs(row[r] - row[i]) == r - i:
            return False
    return True



def backtracking(r, queen):
    global count
    if queen == n:
        count += 1
    else:
        for i in range(n):
            row[r] = i
            if check(r):
                backtracking(r+1, queen + 1)




n = int(read())

row = [0] * n
count = 0
backtracking(0, 0)
print(count)

