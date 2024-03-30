import sys
from collections import deque
input = sys.stdin.readline


def sol():
    n = int(input())
    nodes = list(map(int, input().split()))
    delete = int(input())
    if nodes[delete] == -1:
        print(0)
        return
    leaf = [True] * n
    leaf[delete] = False
    count = 0

    delete_count = 0
    for i in range(n):
        if nodes[i] == nodes[delete]:
            delete_count += 1
        if nodes[i] != -1:
            leaf[nodes[i]] = False

    for i in range(n):
        if not leaf[i]:
            continue
        root = nodes[i]
        while root != -1:
            if root == delete:
                break
            root = nodes[root]
        if root == -1:
            count += 1

    if delete_count == 1:
        count += 1

    print(count)

sol()
