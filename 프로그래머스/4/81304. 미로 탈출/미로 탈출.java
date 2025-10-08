import java.util.*;

class Solution {
    static class Edge {
        int to, cost;
        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static class Node implements Comparable<Node> {
        int idx, cost, state; // 현재 노드, 비용, 트랩상태
        Node(int idx, int cost, int state) {
            this.idx = idx;
            this.cost = cost;
            this.state = state;
        }
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        // 트랩 인덱스 매핑
        Map<Integer, Integer> trapIndex = new HashMap<>();
        for (int i = 0; i < traps.length; i++) {
            trapIndex.put(traps[i], i);
        }

        // 정방향 / 역방향 그래프 모두 저장
        List<Edge>[] graph = new ArrayList[n + 1];
        List<Edge>[] reverse = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            reverse[i] = new ArrayList<>();
        }

        for (int[] road : roads) {
            int a = road[0], b = road[1], cost = road[2];
            graph[a].add(new Edge(b, cost));
            reverse[b].add(new Edge(a, cost));
        }

        // 다익스트라
        int MAX_STATE = 1 << traps.length;
        int[][] dist = new int[n + 1][MAX_STATE];
        for (int i = 1; i <= n; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0, 0));
        dist[start][0] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int node = cur.idx;
            int cost = cur.cost;
            int state = cur.state;

            if (node == end) return cost;
            if (dist[node][state] < cost) continue; // 더 빠르게 방문한 케이스가 있음

            // 현재 노드가 트랩이면 상태 토글
            if (trapIndex.containsKey(node)) {
                int bit = 1 << trapIndex.get(node); // 해당 트랩의 비트 00100
                state ^= bit; // on/off 반전: xor 연산 -> 01100 XOR 00100 = 01000
            }

            // 정방향 간선 탐색
            for (Edge e : graph[node]) {
                int next = e.to;
                int nextCost = cost + e.cost;

                // 방향이 반전되어 있으면 이동 불가
                if (isReversed(node, next, state, trapIndex)) continue;

                if (dist[next][state] > nextCost) {
                    dist[next][state] = nextCost;
                    pq.offer(new Node(next, nextCost, state));
                }
            }

            // 역방향 간선 탐색
            for (Edge e : reverse[node]) {
                int next = e.to;
                int nextCost = cost + e.cost;

                // 이번엔 반전되어 있을 때만 이동 가능
                if (!isReversed(node, next, state, trapIndex)) continue;

                if (dist[next][state] > nextCost) {
                    dist[next][state] = nextCost;
                    pq.offer(new Node(next, nextCost, state));
                }
            }
        }

        return -1; // 도달 불가
    }

    // 간선이 반전되어 있는지 판단
    private boolean isReversed(int u, int v, int state, Map<Integer, Integer> trapIndex) {
        boolean uActive = trapIndex.containsKey(u) && (state & (1 << trapIndex.get(u))) != 0;
        boolean vActive = trapIndex.containsKey(v) && (state & (1 << trapIndex.get(v))) != 0;
        return uActive ^ vActive; // XOR: 둘 중 하나만 켜져 있으면 반전
    }
}
