import heapq
import sys

sys.setrecursionlimit(100000)

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]
read = sys.stdin.readline

R, C = map(int, read().split())

board = []
for i in range(R):
    board.append(list(read().rstrip()))

# alphas = [board[0][0]]
alphas = [False] * 26
alphas[ord(board[0][0]) - ord('A')] = True
max_count = 1


def dfs(a, b, count):
    global max_count
    if max_count < count:
        max_count = count
    for i in range(4):
        nx = a + dx[i]
        ny = b + dy[i]
        # if 0 <= nx < R and 0 <= ny < C and board[nx][ny] not in alphas:
        if 0 <= nx < R and 0 <= ny < C and not alphas[ord(board[nx][ny]) - ord('A')]:
            # alphas.append(board[nx][ny])
            alphas[ord(board[nx][ny]) - ord('A')] = True
            dfs(nx, ny, count + 1)
            # alphas.pop()
            alphas[ord(board[nx][ny]) - ord('A')] = False


dfs(0, 0, 1)
print(max_count)
