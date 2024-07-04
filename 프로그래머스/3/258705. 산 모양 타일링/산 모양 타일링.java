import java.util.*;
class Solution {
    public int solution(int n, int[] tops) {
        int[] connectDp = new int[n];
        int[] separateDp = new int[n];
        connectDp[0] = 1;
        separateDp[0] = 2 + tops[0];
        for (int i = 1; i < n; i++) {
            connectDp[i] = (connectDp[i-1] + separateDp[i-1]) % 10007;
            separateDp[i] = (connectDp[i-1] * (1+tops[i]) + separateDp[i-1] * (2+tops[i])) % 10007;
        }
        return (connectDp[n-1] + separateDp[n-1]) % 10007;
    }
}