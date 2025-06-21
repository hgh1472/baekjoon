class Solution {
    public int solution(int sticker[]) {
        
        int a = 0, b = 0, c = 0, d = sticker[0];
        
        for (int i = 1; i < sticker.length-1; i++) {
            int tmpA = Math.max(a, b);
            int tmpB = a + sticker[i];
            
            int tmpC = Math.max(c, d);
            int tmpD = c + sticker[i];
            a = tmpA;
            b = tmpB;
            c = tmpC;
            d = tmpD;
            if (i == 1) {
                d = 0;
            }
        }
        
        int tmpA = Math.max(a, b);
        int tmpB = a + sticker[sticker.length-1];
        
        return Math.max(Math.max(tmpA, tmpB), Math.max(c, d));
    }
}