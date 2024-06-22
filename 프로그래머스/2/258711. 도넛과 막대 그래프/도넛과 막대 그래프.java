import java.util.*;

class Solution {
    int[] answer = new int[4];
    HashMap<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
    int[] out = new int[1000001];
    public int[] solution(int[][] edges) {
        for (int[] edge : edges) {
            if (!graph.containsKey(edge[0])) {
                graph.put(edge[0], new ArrayList<>());
            }
            if (!graph.containsKey(edge[1])) {
                graph.put(edge[1], new ArrayList<>());
            }
            
            graph.get(edge[0]).add(edge[1]);
            out[edge[1]] += 1;
        }
        
        // 시작점 찾기 => 들어오는 간선 X, 나가는 간선 2개 이상
        for (Integer i : graph.keySet()) {
            if (out[i] == 0 && graph.get(i).size() >= 2) {
                answer[0] = i;
                break;
            }
        }
        
        // 막대 그래프 찾기 => 나가는 간선이 0인 정점 개수 => out 체크
        // 나가는 간선은 그래프에 저장 X
        for (Integer i : graph.keySet()) {
            if (graph.get(i).size() == 0) {
                answer[2]++;
            }
        }
        
        // 8자 그래프 찾기 => 들어오는 간선이 존재하고, 나가는 간선이 2개인 정점 개수
        for (Integer i : graph.keySet()) {
            if (i == answer[0])
                continue;
            if (graph.get(i).size() == 2)
                answer[3]++;
        }
                
        answer[1] = graph.get(answer[0]).size() - answer[2] - answer[3];
        return answer;
    }
}