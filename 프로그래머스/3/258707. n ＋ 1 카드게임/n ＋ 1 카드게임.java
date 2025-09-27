class Solution {
    public int solution(int coin, int[] cards) {
        boolean[] have = new boolean[cards.length+1];
        boolean[] draw = new boolean[cards.length+1];
        int n = cards.length;
        for (int i = 0; i < n / 3; i++) {
            have[cards[i]] = true;
        }
        
        int round;
        for (round = 0; round < n / 3; round++) {
            draw[cards[n/3 + 2*round]] = true;
            draw[cards[n/3 + 2*round + 1]] = true;
            boolean next = false;
            
            for (int i = 1; i <= n; i++) {
                if (have[i] && have[n+1-i]) {
                    have[i] = false;
                    have[n+1-i] = false;
                    next = true;
                    break;
                }
            }
            if (next) continue;
            
            if (coin == 0) {
                break;
            }
            for (int i = 1; i <= n; i++) {
                if (have[i] && draw[n+1-i]) {
                    have[i] = false;
                    draw[n+1-i] = false;
                    next = true;
                    coin--;
                    break;
                }
            }
            if (next) continue;
            
            if (coin < 2) {
                break;
            }
            for (int i = 1; i <= n; i++) {
                if (draw[i] && draw[n+1-i]) {
                    draw[i] = false;
                    draw[n+1-i] = false;
                    next = true;
                    coin -= 2;
                    break;
                }
            }
            if (!next) {
                break;
            }
        }
        
        return round + 1;
    }
}