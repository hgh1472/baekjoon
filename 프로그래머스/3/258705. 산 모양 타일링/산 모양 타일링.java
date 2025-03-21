import java.util.*;
class Solution {
    public int solution(int n, int[] tops) {
        int[] connectedDp = new int[n];
        int[] separateDp = new int[n];
        connectedDp[0] = 1;
        separateDp[0] = 2 + tops[0];
        for (int i = 1; i < n; i++) {
            connectedDp[i] = (connectedDp[i-1] + separateDp[i-1]) % 10007;
            separateDp[i] = ((1 + tops[i]) * connectedDp[i-1] + (2  +tops[i]) * separateDp[i-1]) % 10007;
        }
        return (connectedDp[n-1] + separateDp[n-1]) % 10007;
    }
}