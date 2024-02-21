import sys

read = sys.stdin.readline

T = int(read())
for i in range(T):
    # RR은 삭제, R을 기준으로 명령을 나눈다, D가 몇번나왔는지 길이로 저장
    commands  = [*map(len, input().rstrip().replace("RR", "").split("R"))]
    n = int(read())
    numbers = read().strip('[]\n').split(",")
    reverse = (len(commands) + 1) % 2

    # command의 짝수 인덱스는 리버스되지 않은 상태에서의 D
    front = sum(commands[0::2])
    # command의 홀수 인덱스는 리버스된 상태에서의 D
    back = sum(commands[1::2])
    if front + back > n:
        print("error")
        continue
    else:
        numbers = numbers[front:n-back]
    if reverse:
        numbers.reverse()
    print("[", ",".join(numbers), "]", sep="")
