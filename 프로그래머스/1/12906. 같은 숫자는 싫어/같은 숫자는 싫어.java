import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        Stack<Integer> stack = new Stack();
        for (int i : arr) {
            if (stack.isEmpty()) {
                stack.add(i);
            }
            else {
                if (stack.peek() != i)
                    stack.add(i);
            }
        }
        int[] ans = new int[stack.size()];
        int idx =stack.size()-1;
        while (!stack.isEmpty()) {
            ans[idx--] = stack.pop();
        }

        return ans;
    }
}