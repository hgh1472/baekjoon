import sys
input = sys.stdin.readline

n = int(input())
k = int(input())

sensor = list(map(int, input().split()))
sensor.sort()
if sensor[0] < 0:
    low = sensor[0]
    for i in range(n):
        sensor[i] += (-low)

dist = []
for i in range(1, n):
    dist.append(sensor[i] - sensor[i-1])
dist.sort()
ans = sum(dist[0:n-k])
print(ans)
