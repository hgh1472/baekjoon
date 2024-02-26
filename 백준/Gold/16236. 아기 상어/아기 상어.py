import sys
from collections import deque
import heapq

read = sys.stdin.readline
dx = [-1, 0, 0, 1]
dy = [0, -1, 1, 0]

n = int(read())
info = []

x, y = 0, 0

for i in range(n):
    info.append(list(map(int, read().split())))
    for j in range(n):
        if info[i][j] == 9:
            # 상어의 위치 저장
            x, y = i, j
            info[i][j] = 0


def bfs(x, y):
    size = 2
    eat = 0
    eatable = []
    # -1은 방문하지 않음을 나타낸다.
    visited = [[-1] * n for _ in range(n)]
    q = deque()

    # 가장 마지막에 먹은 물고기의 위치
    eat_x, eat_y = x, y
    temp_q = deque()
    q.append([x, y])
    # 시작위치는 0초
    visited[x][y] = 0
    while q:
        a, b = q.popleft()
        for i in range(4):
            nx = a + dx[i]
            ny = b + dy[i]
            if 0 <= nx < n and 0 <= ny < n and info[nx][ny] <= 6 and visited[nx][ny] == -1:
                if 0 < info[nx][ny] < size: # 먹을 수 있을 때
                    eatable.append([nx, ny])
                    visited[nx][ny] = visited[a][b] + 1
                elif info[nx][ny] == 0 or info[nx][ny] == size: # 지나갈 수 있을 때
                    temp_q.append([nx, ny])
                    visited[nx][ny] = visited[a][b] + 1

        # 시간이 1씩 증가할 때 마다 체크
        if len(q) == 0:
            # 먹을 수 있는 물고기가 존재한다면,
            if len(eatable) > 0:
                # 우선순위에 맞춰 정렬한다.
                eatable.sort()
                eat_x, eat_y = eatable[0]
                info[eat_x][eat_y] = 0
                eat += 1
                if eat >= size:
                    size += 1
                    eat = 0
                q = deque()
                q.append([eat_x, eat_y])
                temp_q = deque()
                # 시간 정보 초기화
                for i in range(n):
                    for j in range(n):
                        if i == eat_x and j == eat_y:
                            continue
                        visited[i][j] = -1
                # 먹은 위치까지 소모한 시간을 반영해준다.
                eatable = []
            else:
                q = temp_q
                temp_q = deque()
    if eat_x == x and eat_y == y:
        print(0)
    else:
        print(visited[eat_x][eat_y])


bfs(x, y)
