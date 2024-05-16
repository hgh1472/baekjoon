import sys
input = sys.stdin.readline

a = input().rstrip()
b = input().rstrip()
len_a = len(a)
len_b = len(b)

longest = 0
start, end = 0, 1
while end <= len_a:
    if a[start:end] in b:
        end += 1
    else:
        start += 1
    longest = max(longest, end - start)

print(longest - 1)

