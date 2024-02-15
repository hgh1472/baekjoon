import sys

read = sys.stdin.readline

n = int(read())
nums = []
plus = 0
zero = 0
minus = 0
for i in range(n):
    nums.append(int(read()))
    if nums[i] > 0:
        plus += 1
    if nums[i] == 0:
        zero += 1
    if nums[i] < 0:
        minus += 1

nums.sort(reverse=True)
result = 0
local = 0

for i in range(plus):
    if local == 0 and i == plus - 1:
        result += nums[i]
        break
    if local == 0:
        local = nums[i]
    else:
        if local == 1 or nums[i] == 1:
            result += local + nums[i]
            local = 0
            continue
        local *= nums[i]
        result += local
        local = 0

if minus != 0:
    if minus % 2 == 0:
        for i in range(len(nums) - 1, plus+zero - 2, -1):
            if local == 0:
                local = nums[i]
            else:
                local *= nums[i]
                result += local
                local = 0
    else:
        for i in range(len(nums) - 1, plus + zero - 1, -1):
            if local == 0:
                local = nums[i]
            else:
                local *= nums[i]
                result += local
                local = 0
        if zero == 0:
            result += nums[plus+zero]

print(result)