import sys

input = sys.stdin.readline

n = int(input())
max_nums = [0] * 3
min_nums = [0] * 3
for _ in range(n):
    a, b, c = map(int, input().split())
    new_1 = a + max(max_nums[0], max_nums[1])
    new_2 = b + max(max_nums[0], max_nums[1], max_nums[2])
    new_3 = c + max(max_nums[1], max_nums[2])
    max_nums = [new_1, new_2, new_3]
    new_4 = a + min(min_nums[0], min_nums[1])
    new_5 = b + min(min_nums[0], min_nums[1], min_nums[2])
    new_6 = c + min(min_nums[1], min_nums[2])
    min_nums = [new_4, new_5, new_6]

print(max(max_nums))
print(min(min_nums))
