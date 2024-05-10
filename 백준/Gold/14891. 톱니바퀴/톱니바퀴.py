import sys
input = sys.stdin.readline


def rotate(pos, clockwise, dir):
    if pos > 3 or pos < 0:
        return
    if dir == 'right' and pos + 1 <= 3 and gear[pos][right] != gear[pos+1][left]:
        rotate(pos+1, clockwise * -1, dir)
    if dir == 'left' and pos - 1 >= 0 and gear[pos][left] != gear[pos-1][right]:
        rotate(pos-1, clockwise * -1, dir)
    if dir == 'start':
        if pos + 1 <= 3 and gear[pos][right] != gear[pos+1][left]:
            rotate(pos+1, clockwise * -1, 'right')
        if pos - 1 >= 0 and gear[pos][left] != gear[pos-1][right]:
            rotate(pos-1, clockwise * -1, 'left')
    if clockwise == 1:
        gear[pos] = [gear[pos][7], gear[pos][0], gear[pos][1], gear[pos][2], gear[pos][3], gear[pos][4], gear[pos][5], gear[pos][6]]
    else:
        gear[pos] = [gear[pos][1], gear[pos][2], gear[pos][3], gear[pos][4], gear[pos][5], gear[pos][6], gear[pos][7], gear[pos][0]]


left, right = 6, 2
gear = []
gear.append(list(input().rstrip()))
gear.append(list(input().rstrip()))
gear.append(list(input().rstrip()))
gear.append(list(input().rstrip()))
k = int(input())
for i in range(k):
    target, direction = map(int, input().split())
    rotate(target - 1, direction, 'start')
score = 0
for i in range(4):
    if gear[i][0] == '1':
        score += 2 ** i
print(score)
