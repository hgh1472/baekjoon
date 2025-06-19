import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        boolean[] visited = new boolean[B.length];
        PriorityQueue<Number> q = new PriorityQueue<>();
        for (int i = 0; i < A.length; i++) {
            q.add(new Number(A[i]));
        }
        
        int answer = 0;
        int left = 0;
        int right = B.length-1;
        Arrays.sort(B);
        while (!q.isEmpty()) {
            int number = q.poll().number;
            int max = B[right];
            if (max <= number) {
                visited[left] = true;
                left++;
            }
            else {
                visited[right] = true;
                right--;
                answer++;
            }
        }
        return answer;
    }
    
    class Number implements Comparable<Number> {
        int number;
        
        Number(int n) {
            this.number = n;
        }
        
        @Override
        public int compareTo(Number n) {
            return n.number - this.number;
        }
    }
}