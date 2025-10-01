import java.util.*;

class Solution {
    public int[][] solution(int[][] rc, String[] operations) {
        Deque<Integer> left = new ArrayDeque<>();
        for (int i = 0; i < rc.length; i++) {
            left.addLast(rc[i][0]);
        }
        Deque<Integer> right = new ArrayDeque<>();
        for (int i = 0; i < rc.length; i++) {
            right.addLast(rc[i][rc[i].length-1]);
        }
        Deque<Integer>[] inners = new ArrayDeque[rc.length];
        for (int i = 0; i < rc.length; i++) {
            inners[i] = new ArrayDeque<>();
            for (int j = 1; j < rc[i].length-1; j++) {
                inners[i].addLast(rc[i][j]);
            }
        }
        
        int start = 0;
        int end = rc.length-1;
        for (String operation : operations) {
            if (operation.equals("ShiftRow")) {
                left.addFirst(left.removeLast());
                right.addFirst(right.removeLast());
                start -= 1;
                if (start < 0) {
                    start = rc.length - 1;
                }
                end -= 1;
                if (end < 0) {
                    end = rc.length - 1;
                }
            }
            else {
                int num = left.removeFirst();
                
                inners[start].addFirst(num);
                num = inners[start].removeLast();
                
                right.addFirst(num);
                num = right.removeLast();
                
                inners[end].addLast(num);
                num = inners[end].removeFirst();
                
                left.addLast(num);
            }
        }
        
        int[][] answer = new int[rc.length][rc[0].length];
        for (int i = 0; i < answer.length; i++) {
            answer[i][0] = left.removeFirst();
            for (int j = 1; j < answer[i].length - 1; j++) {
                answer[i][j] = inners[(start + i) % rc.length].removeFirst();
            }
            answer[i][answer[i].length-1] = right.removeFirst();
        }
        return answer;
    }
}