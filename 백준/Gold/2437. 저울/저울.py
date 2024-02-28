import sys
import bisect
import heapq
input = sys.stdin.readline


n = int(input())
weights = list(map(int, input().split()))
weights.sort()
sum = 0
if weights[0] == 1:
    for weight in weights:
        if sum + 1 >= weight:
            sum += weight
        else:
            break
    print(sum + 1)
else:
    print("1")

