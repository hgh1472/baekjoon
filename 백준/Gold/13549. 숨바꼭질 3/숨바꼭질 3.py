import sys

read = sys.stdin.readline

n, k = map(int, read().split())


def solution(n, k):
	if n >= k:
		return n - k
	elif k == 1:
		return 1
	elif k % 2:
		return 1 + min(solution(n, k - 1), solution(n, k + 1))
	else:
		return min(solution(n, k // 2), k - n)


result = solution(n, k)
print(result)

