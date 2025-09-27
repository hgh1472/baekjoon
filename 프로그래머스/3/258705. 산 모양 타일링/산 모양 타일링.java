class Solution {
    public int solution(int n, int[] tops) {
        int answer = 0;
        int a, b;
        if (tops[0] == 1) {
            a = 3;
            b = 1;
        }
        else {
            a = 2;
            b = 1;
        }
        
        for (int i = 1; i < n; i++) {
            int newA = a;
            int newB = b;
            if (tops[i] == 1) {
                newA = a * 3 + b * 2;
            }
            else {
                newA = a * 2 + b;
            }
            newB = a + b;
            a = newA % 10007;
            b = newB % 10007;
        }
        
        return (a + b) % 10007;
    }
}