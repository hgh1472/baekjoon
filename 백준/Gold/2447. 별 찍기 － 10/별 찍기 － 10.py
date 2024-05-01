import sys
input = sys.stdin.readline

# 크기 9의 패턴은 공백으로 채워진 가운데의 3*3 정사각형을 크기 3의 패턴으로 둘러싼 형태

def check(number):
    count = 0
    while number > 0:
        if number % 3 == 0:
            number /= 3
            count += 1
            if number == 1:
                break
        else:
            return 0
    return count


n = int(input())
c = check(n)
numbers = []
for i in range(1, c+1):
    numbers.append(3 ** i)

for i in range(0, n):
    for j in range(0, n):
        flag = 1
        for k in numbers:
            if k/3 <= i % k < 2*k/3 and k/3 <= j % k < 2*k/3: #크기 n의 공백
                print(' ', end='')
                flag = 0
                break
        if flag == 1:
            print('*', end='')
    print()
