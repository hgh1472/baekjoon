import heapq
import sys

read = sys.stdin.readline

lecture = []
n = int(read())
for i in range(n):
    lecture.append(list(map(int, read().split())))

lecture.sort(key=lambda x: [x[0], x[1]])
time = []
count = 0
for start, end in lecture:
    if len(time) == 0:
        count += 1
        heapq.heappush(time, end)
        continue

    fastest = heapq.heappop(time)
    if fastest > start:
        count += 1
        heapq.heappush(time, fastest)
        heapq.heappush(time, end)
    else:
        heapq.heappush(time, end)

print(count)

