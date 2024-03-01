import sys
input = sys.stdin.readline

s = list(input().rstrip())
t = list(input().rstrip())

while t:
    if s == t:
        break
    if t[-1] == 'A':
        t.pop()
    elif t[-1] == 'B':
        t.pop()
        t.reverse()
if s == t:
    print("1")
else:
    print("0")
